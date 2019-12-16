package minipascal.util;

import minipascal.util.cuadruplo.ArrayVar;
import minipascal.util.cuadruplo.Cuadruplo;
import minipascal.util.types.TFunc;
import minipascal.util.types.TRecord;
import minipascal.util.types.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodigoFinal {

    private ArrayList<String> varGlobales;
    private ArrayList<String> strGlobales;
    private ArrayList<String> lineas;
    private int numLinea;
    private int ambito;
    private boolean[] tempDisponibles;
    private boolean[] argsDisponibles;
    private TFunc function;
    private int sp;
    private int usedS;
    private boolean error;

    public CodigoFinal() {
        varGlobales = new ArrayList<>();
        strGlobales = new ArrayList<>();
        lineas = new ArrayList<>();
        numLinea = 1;
        ambito = 0;
        function = null;
        sp = 0;
        usedS = 0;
        error = false;

        tempDisponibles = new boolean[10];
        for (int i = 0; i < 10; i += 1) {
            tempDisponibles[i] = true;
        }

        argsDisponibles = new boolean[4];
        for (int i = 0; i < 4; i += 1) {
            argsDisponibles[i] = true;
        }

        // agregar las variables globales (int, char, bool y records)
        for (String var : Globals.simbolos.column(0).keySet()) {
            Type type = Globals.simbolos.get(var, 0);

            if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                varGlobales.add(String.format("%s: .word %d\n", var, 0));
            } else if (type.equals(Type.CHAR)) {
                varGlobales.add(String.format("%s: .byte '%c'\n", var, '0'));
            } else {
                // es un record
                TRecord tRecord = (TRecord) type;
                // XXX what
                varGlobales.add(
                    String.format("%s: .space %d\n", var, tRecord.getCompleteSize())
                );
            }
        }
    }

    public void compilar(List<Cuadruplo> cuadruplos) {
        for (Cuadruplo cc : cuadruplos) {
            Cuadruplo c = cc.copy();
            if (c.res != null && c.res.startsWith("$t")) {
                claim(c.res);
            }

            addTag("_" + numLinea);
            switch (c.op) {
                case "read":
                    opRead(c);
                    break;
                case "write":
                    opWrite(c);
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "div":
                    opMath(c);
                    break;
                case ":=":
                    opAssign(c);
                    break;
                case "if= goto":
                case "if> goto":
                case "if< goto":
                case "if>= goto":
                case "if<= goto":
                case "if<> goto":
                    opIfGoto(c);
                    break;
                case "goto":
                    opGoto(c);
                    break;
                case "param":
                    opParam(c);
                    break;
                case "call":
                    opCall(c);
                    break;
                case "init_func":
                    opInitFunc(c);
                    break;
                case "end_func":
                    opEndFunc(c);
                    break;
                case "init_main":
                    opInitMain();
                    break;
                case "end_main":
                    opEndMain();
                    break;
                default:
                    break;
            }

            if (!c.arg1.equals(c.res) && c.arg1.startsWith("$t")) {
                kill(c.arg1);
            }

            if (!c.arg2.equals(c.res) && c.arg2.startsWith("$t")) {
                kill(c.arg2);
            }

            numLinea += 1;
        }
    }

    private String addString(String str) {
        String resVar = String.format("_str%d", strGlobales.size());
        strGlobales.add(String.format("%s: .asciiz %s\n", resVar, str));
        return resVar;
    }

    private void addLine(String line) {
        lineas.add(String.format("\t%s\n", line));
    }

    private void addTag(String tag) {
        lineas.add(String.format("%s:\n", tag));
    }

    private void opAssign(Cuadruplo c) {
        String res = c.res;
        String arg = c.arg1;

        // type de arg, si es direccion
        Type type = null;

        if (arg.equals("$ret")) {
            addLine(String.format("move %s, $v0", res));
            return;
        }

        if (arg.contains("[")) {
            ArrayVar var = new ArrayVar(arg, 0);
            type = var.type;
            arg = getTemp();
            addLine(String.format("la %s, %s", arg, var.var));
            arg = String.format("-%d(%s)", var.offset, arg);
        } else if (arg.startsWith("_")) {
            // res es variable
            if (Globals.simbolos.contains(arg, 0)) {
                type = Globals.simbolos.get(arg, 0);
            } else {
                TFunc.B place = function.getArgVar(arg);
                type = place.type;
                if (place.arg) {
                    arg = place.place;
                }
            }
        }

        if (res.contains("[")) {
            ArrayVar var = new ArrayVar(c.res, 0);
            type = var.type;
            res = getTemp();
            addLine(String.format("la %s, %s", res, var.var));
            res = String.format("-%d(%s)", var.offset, res);
        } else if (function != null && function.type.equals(res)) {
            // retorno de funcion
            res = "$v0";
            type = function.returnType;
        } else if (res.startsWith("_")) {
            // res es variable
            if (Globals.simbolos.contains(res, 0)) {
                type = Globals.simbolos.get(res, 0);
                res = getTemp();
            } else {
                TFunc.B place = function.getArgVar(res);
                type = place.type;
                if (place.arg) {
                    res = place.place;
                } else {
                    res = getTemp();
                    c.res = place.place;
                }
            }
        }

        if (type == null) {
            System.err.println("Error: type en assign es null.");
            error = true;
            return;
        }

        if (function != null && c.res.equals(function.type)) {
            // es un statement de retorno
            kill(res);
            res = "$v0";
        }

        if (res.startsWith("$")) {
            // _a := _b, move back (if its not record)
            // _a := _b[2], move back
            // $t := _b
            if (arg.startsWith("_") || arg.startsWith("-")) {
                if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                    addLine(String.format("lw %s, %s", res, arg));
                } else if (type.equals(Type.CHAR)) {
                    addLine(String.format("lb %s, %s", res, arg));
                } else {
                    // copiar de un record a otro
                    String from = getTemp();
                    String to = getTemp();
                    String t = getTemp();

                    addLine(String.format("la %s, %s", from, arg));
                    addLine(String.format("la %s, %s", to, res));

                    TRecord tRecord = (TRecord) type;
                    int offset = 0;
                    for (String key : tRecord.fields.keySet()) {
                        Type field = tRecord.getField(key);
                        if (field.equals(Type.INTEGER) || field.equals(Type.BOOLEAN)) {
                            addLine(String.format("lw %s, -%d(%s)", t, offset, from));
                            addLine(String.format("sw %s, -%d(%s)", t, offset, to));
                        } else if (field.equals(Type.CHAR)) {
                            addLine(String.format("lb %s, -%d(%s)", t, offset, from));
                            addLine(String.format("sb %s, -%d(%s)", t, offset, to));
                        }
                        offset += field.size;
                    }

                    kill(from);
                    kill(to);
                    kill(t);
                }
            }

            // _a := $t, move back
            // $ret = $t
            else if (arg.startsWith("$")) {
                addLine(String.format("move %s, %s", res, arg));
            }

            // _a := 1
            // $t := 1
            else {
                addLine(String.format("li %s, %s", res, arg));
            }
        }

        else if (res.startsWith("-")) {
            // _a[2] := _b, move back
            // _a[2] := _b[2], move back
            if (arg.startsWith("_") || arg.startsWith("-")) {
                String t = getTemp();
                if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                    addLine(String.format("lw %s, %s", t, arg));
                } else if (type.equals(Type.CHAR)) {
                    addLine(String.format("lb %s, %s", t, arg));
                }
                kill(t);
            }
        }

        if (!arg.equals(c.arg1)) {
            if (arg.startsWith("-")) {
                killDir(arg);
            }
        }

        if (!res.equals(c.res) && !res.equals("$v0")) {
            if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                addLine(String.format("sw %s, %s", res, c.res));
            } else if (type.equals(Type.CHAR)) {
                addLine(String.format("sb %s, %s", res, c.res));
            }

            if (res.startsWith("-")) {
                killDir(res);
            } else {
                kill(res);
            }
        }
    }

    private void opMath(Cuadruplo c) {
        // puede ser una suma unaria:
        if (c.arg2.equals("")) {
            if (c.op.equals("-")) {
                // resta unaria
                String res = c.res;
                String num = c.arg1;

                // hacer temporales para variables si se necesitan
                if (res.startsWith("_")) {
                    // res es variable
                    if (Globals.simbolos.contains(res, 0)) {
                        res = getTemp();
                    } else {
                        TFunc.B place = function.getArgVar(res);
                        if (place.arg) {
                            res = place.place;
                        } else {
                            res = getTemp();
                            c.res = place.place;
                        }
                    }
                }

                if (num.startsWith("_")) {
                    // num es variable
                    if (Globals.simbolos.contains(num, 0)) {
                        num = getTemp();
                        addLine(String.format("lw %s, %s", num, c.arg1));
                    } else {
                        TFunc.B place = function.getArgVar(num);
                        if (place.arg) {
                            num = place.place;
                        } else {
                            num = getTemp();
                            addLine(String.format("lw %s, %s", num, place.place));
                        }
                    }
                }

                // num no puede ser una constante
                if (!(num.contains("$"))) {
                    num = getTemp();
                }

                addLine(String.format("mul %s, %s, %s", res, num, "-1"));

                if (!res.equals(c.res) && res.startsWith("$t")) {
                    addLine(String.format("sw %s, %s", res, c.res));
                    kill(res);
                }

                if (!num.equals(c.arg1)) {
                    kill(num);
                }
            }
            return;
        }

        // operacion binaria
        String res = c.res;
        String left = c.arg1;
        String right = c.arg2;

        // hacer temporales para variables si se necesitan
        if (res.startsWith("_")) {
            // res es variable
            if (Globals.simbolos.contains(res, 0)) {
                res = getTemp();
            } else {
                TFunc.B place = function.getArgVar(res);
                if (place.arg) {
                    res = place.place;
                } else {
                    res = getTemp();
                    c.res = place.place;
                }
            }
        }

        if (left.startsWith("_")) {
            // arg1 es variable
            if (Globals.simbolos.contains(left, 0)) {
                left = getTemp();
                addLine(String.format("lw %s, %s", left, c.arg1));
            } else {
                TFunc.B place = function.getArgVar(left);
                if (place.arg) {
                    left = place.place;
                } else {
                    left = getTemp();
                    addLine(String.format("lw %s, %s", left, place.place));
                }
            }
        }

        if (right.startsWith("_")) {
            // arg2 es variable
            if (Globals.simbolos.contains(right, 0)) {
                right = getTemp();
                addLine(String.format("lw %s, %s", right, c.arg2));
            } else {
                TFunc.B place = function.getArgVar(right);
                if (place.arg) {
                    right = place.place;
                } else {
                    right = getTemp();
                    addLine(String.format("lw %s, %s", right, place.place));
                }
            }
        }

        // left no puede ser una constante
        if (!(left.contains("$"))) {
            String constante = left;
            left = getTemp();
            addLine(String.format("li %s, %s", left, constante));
        }

        switch (c.op) {
            case "+":
                addLine(String.format("add %s, %s, %s", res, left, right));
                break;
            case "-":
                addLine(String.format("sub %s, %s, %s", res, left, right));
                break;
            case "*":
                addLine(String.format("mul %s, %s, %s", res, left, right));
                break;
            case "/":
            case "div":
                addLine(String.format("div %s, %s, %s", res, left, right));
                break;
            case "mod":
                break;
            default:
                break;
        }

        if (!res.equals(c.res) && res.startsWith("$t")) {
            addLine(String.format("sw %s, %s", res, c.res));
            kill(res);
        }

        if (!left.equals(c.arg1)) {
            kill(left);
        }

        if (!right.equals(c.arg2)) {
            kill(right);
        }
    }

    private void opIfGoto(Cuadruplo c) {
        // goto condicional
        String left = c.arg1;
        String right = c.arg2;
        String etiq = c.resM.toString();

        // hacer temporales para variables si se necesitan
        if (left.startsWith("_")) {
            // arg1 es variable
            if (Globals.simbolos.contains(left, 0)) {
                left = getTemp();
                addLine(String.format("lw %s, %s", left, c.arg1));
            } else {
                TFunc.B place = function.getArgVar(left);
                if (place.arg) {
                    left = place.place;
                } else if (place.type.equals(Type.INTEGER) || place.type.equals(Type.BOOLEAN)) {
                    left = getTemp();
                    addLine(String.format("lw %s, %s", left, place.place));
                } else if (place.type.equals(Type.CHAR)) {
                    left = getTemp();
                    addLine(String.format("lb %s, %s", left, place.place));
                }
            }
        }

        if (right.startsWith("_")) {
            // arg2 es variable
            if (Globals.simbolos.contains(right, 0)) {
                right = getTemp();
                addLine(String.format("lw %s, %s", right, c.arg2));
            } else {
                TFunc.B place = function.getArgVar(left);
                if (place.arg) {
                    right = place.place;
                } else if (place.type.equals(Type.INTEGER) || place.type.equals(Type.BOOLEAN)) {
                    right = getTemp();
                    addLine(String.format("lw %s, %s", right, place.place));
                } else if (place.type.equals(Type.CHAR)) {
                    right = getTemp();
                    addLine(String.format("lb %s, %s", right, place.place));
                }
            }
        }

        // left no puede ser una constante
        if (!(left.contains("$"))) {
            String constante = left;
            left = getTemp();
            addLine(String.format("li %s, %s", left, constante));
        }

        switch (c.op) {
            case "if= goto":
                addLine(String.format("beq %s, %s, %s", left, right, etiq));
                break;
            case "if> goto":
                addLine(String.format("bgt %s, %s, %s", left, right, etiq));
                break;
            case "if< goto":
                addLine(String.format("blt %s, %s, %s", left, right, etiq));
                break;
            case "if>= goto":
                addLine(String.format("bge %s, %s, %s", left, right, etiq));
                break;
            case "if<= goto":
                addLine(String.format("ble %s, %s, %s", left, right, etiq));
                break;
            case "if<> goto":
                addLine(String.format("bne %s, %s, %s", left, right, etiq));
                break;
            default:
                break;
        }

        if (!left.equals(c.arg1)) {
            kill(left);
        }

        if (!right.equals(c.arg2)) {
            kill(right);
        }
    }

    private void opGoto(Cuadruplo c) {
        // goto obligatorio
        addLine(String.format("b %s", c.resM));
    }

    private void opParam(Cuadruplo c) {
        // puede ser:
        String arg = c.arg1;
        String param = getArg();

        // cargar en args si se necesita
        if (arg.startsWith("_")) {
            // arg es una variable
            if (Globals.simbolos.contains(arg, 0)) {
                Type type = Globals.simbolos.get(arg, ambito);
                if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                    addLine(String.format("lw %s, %s", param, arg));
                } else if (type.equals(Type.CHAR)) {
                    addLine(String.format("lb %s, %s", param, arg));
                }
            } else {
                TFunc.B place = function.getArgVar(arg);
                if (place.arg) {
                    addLine(String.format("move %s, %s", param, place.place));
                } else if (place.type.equals(Type.INTEGER) || place.type.equals(Type.BOOLEAN)) {
                    addLine(String.format("lw %s, %s", param, place.place));
                } else if (place.type.equals(Type.CHAR)) {
                    addLine(String.format("lb %s, %s", param, place.place));
                }
            }
        } else if (arg.startsWith("$t")) {
            // es un temporal
            addLine(String.format("move %s, %s", param, arg));
        } else {
            // const val
            addLine(String.format("li %s, %s", param, arg));
        }
    }

    private void opCall(Cuadruplo c) {
        if (Integer.parseInt(c.arg1) > 4) {
            System.err.println("Error: Se enviaron mas de 4 argumentos.");
        }

        // guardar temporales ocupados
        int offset = 0;
        for (int i = 0; i < 10; i += 1) {
            if (!tempDisponibles[i]) {
                offset += 4;
                addLine(String.format("sw $t%d, -%d($sp)", i, offset));
            }
        }
        if (offset != 0) {
            addLine(String.format("sub $sp, $sp, %d", offset));
        }
        addLine(String.format("jal %s", c.res));

        // restaurar temporales
        offset = 0;
        for (int i = 9; i >= 0; i -= 1) {
            if (!tempDisponibles[i]) {
                addLine(String.format("sw $t%d, %d($sp)", i, offset));
                offset += 4;
            }
        }
        if (offset != 0) {
            addLine(String.format("add $sp, $sp, %d", offset));
        }

        cleanArgs();
    }

    private void opInitFunc(Cuadruplo c) {
        function = Globals.funciones.get(c.res);
        function.type = "_" + function.type;

        // aumentar el ambito en 1
        ambito += 1;
        addTag(c.res);

        addLine("sw $fp, -4($sp)");
        addLine("sw $ra, -8($sp)");

        // guardar saved temps
        int i = 0;
        int s = 0;
        int stackSize = 12;
        for (Type type : function.args) {
            function.addName(function.argNames.get(i), type, true);
            if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                addLine(String.format("sw $s%d, -%d($sp)", s, stackSize));
            } else if (type.equals(Type.CHAR)) {
                addLine(String.format("sb $s%d, -%d($sp)", s, stackSize));
            }
            i += 1;
            s += 1;
            stackSize += 4;
        }

        // agregar variables locales en el stack (o en la pila si entran)
        for (String key : function.varsLocales.keySet()) {
            if (s < 8) {
                function.addName(key, function.varsLocales.get(key), true);
                Type type = function.varsLocales.get(key);
                if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                    addLine(String.format("sw $s%d, -%d($sp)", s, stackSize));
                } else if (type.equals(Type.CHAR)) {
                    addLine(String.format("sb $s%d, -%d($sp)", s, stackSize));
                }
            } else {
                function.addName(key, function.varsLocales.get(key), false);
            }
            s += 1;
            stackSize += 4;
        }

        usedS = s;
        sp = stackSize;

        addLine("move $fp, $sp");
        addLine(String.format("sub $sp, $sp, %d", stackSize));

        // mover args a saved temps
        for (i = 0; i < Math.min(4, function.args.size()); i += 1) {
            addLine(String.format("move $s%d, $a%d", i, i));
        }

        // setear place en la function para mayor facilidad
        int nsp = 0;
        for (i = 0; i < function.names.size(); i += 1) {
            TFunc.B b = function.names.get(i);
            if (i < 8) {
                b.place = "$s" + i;
            } else {
                // se guarda en el stack
                b.place = nsp + "($sp)";
                nsp += 4;
            }
        }
    }

    private void opEndFunc(Cuadruplo c) {
        addLine("move $sp, $fp");
        for (int i = Math.min(8, function.names.size()) - 1; i >= 0; i -= 1) {
            usedS -= 1;
            sp -= 4;
            TFunc.B name = function.names.get(i);

            if (name.type.equals(Type.INTEGER) || name.type.equals(Type.BOOLEAN)) {
                addLine(String.format("lw $s%d, -%d($sp)", usedS, sp));
            } else if (name.type.equals(Type.CHAR)) {
                addLine(String.format("lb $s%d, -%d($sp)", usedS, sp));
            }
        }

        addLine("lw $ra, -8($sp)");
        addLine("lw $fp, -4($sp)");
        addLine("jr $ra");

        sp = 0;
        function = null;
    }

    private void opInitMain() {
        // cambiar el ambito a 0
        ambito = 0;

        // agregar lineas necesarias para el main
        addTag("main");
        addLine("move $fp, $sp");
    }

    private void opEndMain() {
        addLine("li $v0, 10");
        addLine("syscall");
    }

    private void opRead(Cuadruplo c) {
        String res = c.res;

        Type type = Globals.simbolos.get(res, ambito);
        if (type == null) {
            type = Globals.simbolos.get(res, 0);
        }

        TFunc.B b = null;
        if (function != null) {
            b = function.getArgVar(res);
            res = b.place;
            type = b.type;
        }

        if (res.contains("[")) {
            ArrayVar var = new ArrayVar(res, 0);
            type = var.type;
            res = getTemp();
            addLine(String.format("la %s, %s", res, var.var));
            res = String.format("-%d(%s)", var.offset, res);
        }

        if (type == null) {
            System.err.println("Error: Type es null.");
            error = true;
        } else if (type.equals(Type.INTEGER)) {
            addLine("li $v0, 5");
            addLine("syscall");
            if (b != null && b.arg) {
                addLine(String.format("move %s, $v0", res));
            } else {
                addLine(String.format("sw $v0, %s", res));
            }
        } else if (type.equals(Type.CHAR)) {
            addLine("li $v0, 8");
            if (b != null && b.arg) {
                addLine(String.format("la $a0, (%s)", res));
            } else {
                addLine(String.format("la $a0, %s", res));
            }
            addLine("li $a1, 4");
            addLine("syscall");
        }

        if (!res.equals(c.res)) {
            killDir(res);
        }
    }

    private void opWrite(Cuadruplo c) {
        // agregar string a los globales
        String place = addString(c.arg1);

        // imprimir string
        addLine("li $v0, 4");
        addLine("la $a0, " + place);
        addLine("syscall");

        // imprimir variable (si tiene argumento, integer o char)
        if (c.arg2.equals("")) {
            return;
        }

        String arg2 = c.arg2;

        Type type = Globals.simbolos.get(c.arg2, ambito);
        if (type == null) {
            type = Globals.simbolos.get(c.arg2, 0);
        }

        TFunc.B b = null;
        if (function != null) {
            b = function.getArgVar(arg2);
            arg2 = b.place;
            type = b.type;
        }

        if (arg2.contains("[")) {
            ArrayVar var = new ArrayVar(arg2, 0);
            type = var.type;
            arg2 = getTemp();
            addLine(String.format("la %s, %s", arg2, var.var));
            arg2 = String.format("-%d(%s)", var.offset, arg2);
        }

        if (type == null) {
            System.err.println("Error: Type es null.");
            error = true;
        } else if (b != null && b.arg) {
            if (type.equals(Type.INTEGER)) {
                addLine("li $v0, 1");
            } else {
                addLine("li $v0, 11");
            }
            addLine(String.format("move $a0, %s", arg2));
            addLine("syscall");
        } else if (type.equals(Type.INTEGER)) {
            addLine("li $v0, 1");
            addLine(String.format("lw $a0, %s", arg2));
            addLine("syscall");
        } else if (type.equals(Type.CHAR)) {
            addLine("li $v0, 11");
            addLine(String.format("lb $a0, %s", arg2));
            addLine("syscall");
        }

        if (!arg2.equals(c.arg2)) {
            killDir(arg2);
        }
    }

    private void killDir(String temp) {
        if (temp.startsWith("-")) {
            String t = temp.replaceAll("\\)", "").split("\\(")[1];
            int killMe = Integer.parseInt(t.substring(2));
            tempDisponibles[killMe] = true;
        }
    }

    private void kill(String temp) {
        if (temp.startsWith("$t")) {
            int killMe = Integer.parseInt(temp.substring(2));
            tempDisponibles[killMe] = true;
        }
    }

    private void claim(String temp) {
        int pos = Integer.parseInt(temp.substring(2));
        tempDisponibles[pos] = false;
    }

    private void cleanArgs() {
        for (int i = 0; i < 4; i += 1) {
            argsDisponibles[i] = true;
        }
    }

    private String getArg() {
        for (int i = 0; i < 4; i += 1) {
            if (argsDisponibles[i]) {
                argsDisponibles[i] = false;
                return String.format("$a%d", i);
            }
        }
        // ya no hay args disponibles, error
        error = true;
        System.err.println("Error: Ya no hay args disponibles.");
        return String.format("$a%d", 420);
    }

    private String getTemp() {
        for (int i = 0; i < 10; i += 1) {
            if (tempDisponibles[i]) {
                tempDisponibles[i] = false;
                return String.format("$t%d", i);
            }
        }
        // ya no hay temporales, posible error
        error = true;
        System.err.println("Error: Ya no hay temporales disponibles.");
        return String.format("$t%d", 420);
    }

    public boolean error() {
        return error;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append(".data\n");

        // agregar var globales
        for (String var : varGlobales) {
            res.append(var);
        }

        for (String str: strGlobales) {
            res.append(str);
        }

        res.append(".text\n");
        res.append(".globl main\n");

        // resto del codigo de lineas (incluye funciones y demas)
        for (String linea : lineas) {
            res.append(linea);
        }

        return res.toString();
    }
}
