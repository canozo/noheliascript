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
    private String function;
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
        for (Cuadruplo c : cuadruplos) {
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
        boolean skip = false;

        String res = c.res;
        String arg = c.arg1;

        // type de arg, si es direccion
        Type fieldType = null;

        if (res.contains("[")) {
            ArrayVar var = new ArrayVar(c.res, ambito);
            res = getTemp();
            addLine(String.format("la %s, %s", res, var.var));
            res = String.format("-%d(%s)", var.offset, res);
        }

        if (arg.contains("[")) {
            ArrayVar var = new ArrayVar(arg, ambito);
            fieldType = var.type;
            arg = getTemp();
            addLine(String.format("la %s, %s", arg, var.var));
            arg = String.format("-%d(%s)", var.offset, arg);
        }

        if (res.equals(function)) {
            // es un statement de retorno
            res = "$v0";
            if (arg.startsWith("-")) {
                // arg es una direccion
                if (fieldType == null) {
                    System.err.println("Error: Type es null.");
                    error = true;
                } else if (fieldType.equals(Type.INTEGER) || fieldType.equals(Type.BOOLEAN)) {
                    addLine(String.format("lw %s, %s", res, arg));
                } else if (fieldType.equals(Type.CHAR)) {
                    addLine(String.format("lb %s, %s", res, arg));
                }
            } else if (arg.startsWith("$t")) {
                // arg puede ser registro
                addLine(String.format("move %s, %s", res, arg));
            } else {
                // arg trae const val
                addLine(String.format("li %s, %s", res, arg));
            }
            skip = true;
        }

        if (!skip && res.startsWith("$t")) {
            if (arg.startsWith("-")) {
                // arg es una direccion
                if (fieldType == null) {
                    System.err.println("Error: Type es null.");
                    error = true;
                } else if (fieldType.equals(Type.INTEGER) || fieldType.equals(Type.BOOLEAN)) {
                    addLine(String.format("lw %s, %s", res, arg));
                } else if (fieldType.equals(Type.CHAR)) {
                    addLine(String.format("lb %s, %s", res, arg));
                }
                killDir(arg);
            } else if (arg.equals("$ret")) {
                // arg es un valor de retorno
                addLine(String.format("move %s, $v0", res));
            } else {
                // arg trae integer o constchar
                addLine(String.format("li %s, %s", res, arg));
            }
        } else if (!skip) {
            // ver si arg es una variable primero
            // .startsWith("_")
            if (Globals.simbolos.contains(arg, ambito)) {
                // dependeria del tipo de la variable
                Type type = Globals.simbolos.get(arg, ambito);
                if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                    String temp = getTemp();
                    addLine(String.format("lw %s, %s", temp, arg));
                    addLine(String.format("sw %s, %s", temp, res));
                    kill(temp);
                } else if (type.equals(Type.CHAR)) {
                    String temp = getTemp();
                    addLine(String.format("lb %s, %s", temp, arg));
                    addLine(String.format("sb %s, %s", temp, res));
                    kill(temp);
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
                skip = true;
            }

            if (!skip) {
                if (arg.startsWith("-")) {
                    // arg es una direccion
                    if (fieldType == null) {
                        System.err.println("Error: Type es null.");
                        error = true;
                    } else if (fieldType.equals(Type.INTEGER) || fieldType.equals(Type.BOOLEAN)) {
                        String temp = getTemp();
                        addLine(String.format("lw %s, %s", temp, arg));
                        addLine(String.format("sw %s, %s", temp, res));
                        kill(temp);
                    } else if (fieldType.equals(Type.CHAR)) {
                        String temp = getTemp();
                        addLine(String.format("lb %s, %s", temp, arg));
                        addLine(String.format("sb %s, %s", temp, res));
                        kill(temp);
                    }
                } else if (arg.startsWith("$t")) {
                    // arg puede ser registro
                    addLine(String.format("sw %s, %s", arg, res));
                } else if (arg.startsWith("'")) {
                    // arg trae integer o constchar
                    String t = getTemp();
                    addLine(String.format("li %s, %s", t, arg));
                    addLine(String.format("sb %s, %s", t, res));
                    kill(t);
                } else {
                    // arg trae integer
                    String t = getTemp();
                    addLine(String.format("li %s, %s", t, arg));
                    addLine(String.format("sw %s, %s", t, res));
                    kill(t);
                }
            }
        }

        if (!arg.equals(c.arg1)) {
            killDir(arg);
        }

        if (!res.equals(c.res)) {
            killDir(res);
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
                    res = getTemp();
                }

                if (num.startsWith("_")) {
                    // num es variable
                    num = getTemp();
                    addLine(String.format("lw %s, %s", num, c.arg1));
                }

                // num no puede ser una constante
                if (!(num.contains("$"))) {
                    num = getTemp();
                }

                addLine(String.format("mul %s, %s, %s", res, num, "-1"));

                if (!res.equals(c.res)) {
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
            res = getTemp();
        }

        if (left.startsWith("_")) {
            // arg1 es variable
            if (ambito == 0) {
                left = getTemp();
                addLine(String.format("lw %s, %s", left, c.arg1));
            } else {
                TFunc tFunc = Globals.funciones.get(function);
                left = tFunc.getArgVar(left);
            }
        }

        if (right.startsWith("_")) {
            // arg2 es variable
            if (ambito == 0) {
                right = getTemp();
                addLine(String.format("lw %s, %s", right, c.arg2));
            } else {
                TFunc tFunc = Globals.funciones.get(function);
                right = tFunc.getArgVar(right);
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

        if (!res.equals(c.res)) {
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
            left = getTemp();
            addLine(String.format("lw %s, %s", left, c.arg1));
        }

        if (right.startsWith("_")) {
            // arg2 es variable
            right = getTemp();
            addLine(String.format("lw %s, %s", right, c.arg2));
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
            // .startsWith("_")
            Type type = Globals.simbolos.get(arg, ambito);
            if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                addLine(String.format("lw %s, %s", param, arg));
            } else if (type.equals(Type.CHAR)) {
                addLine(String.format("lb %s, %s", param, arg));
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

        // TODO guardar temporales vivos
        for (int i = 0; i < 10; i += 1) {
            if (!tempDisponibles[i]) {

            }
        }
        addLine(String.format("jal %s", c.res));
        // TODO restaurar temporales
        cleanArgs();
    }

    private void opInitFunc(Cuadruplo c) {
        TFunc tFunc = Globals.funciones.get(c.res);
        if (!tFunc.returnType.equals(Type.VOID)) {
            function = c.res;
        }

        // aumentar el ambito en 1
        ambito += 1;
        addTag(c.res);

        addLine("sw $fp, -4($sp)");
        addLine("sw $ra, -8($sp)");

        // guardar saved temps
        int s = 0;
        int stackSize = 12;
        for (Type type : tFunc.args) {
            if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                addLine(String.format("sw $s%d, -%d($sp)", s, stackSize));
            } else if (type.equals(Type.CHAR)) {
                addLine(String.format("sb $s%d, -%d($sp)", s, stackSize));
            }
            s += 1;
            stackSize += 4;
//            stackSize += type.size;
        }
        usedS = s;
        sp = stackSize;

        addLine("move $fp, $sp");
        addLine(String.format("sub $sp, $sp, %d", stackSize));

        // mover args a saved temps
        for (int i = 0; i < Math.min(4, tFunc.args.size()); i += 1) {
            addLine(String.format("move $s%d, $a%d", i, i));
        }
    }

    private void opEndFunc(Cuadruplo c) {
        addLine("move $sp, $fp");
        TFunc tFunc = Globals.funciones.get(c.res);
        Collections.reverse(tFunc.args);
        for (Type type : tFunc.args) {
            usedS -= 1;
            sp -= 4;
//            sp -= type.size;

            if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                addLine(String.format("lw $s%d, -%d($sp)", usedS, sp));
            } else if (type.equals(Type.CHAR)) {
                addLine(String.format("lb $s%d, -%d($sp)", usedS, sp));
            }
        }
        Collections.reverse(tFunc.args);

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

        // .startsWith("_")
        Type type = Globals.simbolos.get(c.res, ambito);
        if (res.contains("[")) {
            ArrayVar var = new ArrayVar(res, ambito);
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
            addLine(String.format("sw $v0, %s", res));
        } else if (type.equals(Type.CHAR)) {
            addLine("li $v0, 8");
            addLine(String.format("la $a0, %s", res));
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

        // .startsWith("_")
        Type type = Globals.simbolos.get(c.arg2, ambito);
        if (arg2.contains("[")) {
            ArrayVar var = new ArrayVar(arg2, ambito);
            type = var.type;
            arg2 = getTemp();
            addLine(String.format("la %s, %s", arg2, var.var));
            arg2 = String.format("-%d(%s)", var.offset, arg2);
        }

        if (type == null) {
            System.err.println("Error: Type es null.");
            error = true;
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
