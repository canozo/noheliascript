package minipascal.util;

import minipascal.util.cuadruplo.ArrayVar;
import minipascal.util.cuadruplo.Cuadruplo;
import minipascal.util.types.TRecord;
import minipascal.util.types.Type;

import java.util.ArrayList;
import java.util.List;

public class CodigoFinal {

    private ArrayList<String> varGlobales;
    private ArrayList<String> strGlobales;
    private ArrayList<String> lineas;
    private int numLinea;
    private int ambito;
    private boolean[] disponibles;
    private boolean error;

    public CodigoFinal() {
        varGlobales = new ArrayList<>();
        strGlobales = new ArrayList<>();
        lineas = new ArrayList<>();
        numLinea = 1;
        ambito = 0;

        error = false;
        disponibles = new boolean[10];
        for (int i = 0; i < 10; i += 1) {
            disponibles[i] = true;
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
                varGlobales.add(String.format("%s: .space %d\n", var, tRecord.getCompleteSize()));
            }
        }
    }

    public void compilar(List<Cuadruplo> cuadruplos) {
        for (Cuadruplo c : cuadruplos) {
            if (c.arg1.startsWith("$t")) {
                kill(c.arg1);
            }

            if (c.arg2.startsWith("$t")) {
                kill(c.arg2);
            }

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

        if (res.contains("[")) {
            ArrayVar var = new ArrayVar(c.res);
            res = getTemp();
            addLine(String.format("la %s, %s", res, var.var));
            res = String.format("-%d(%s)", var.offset, res);
        } else if (arg.contains("[")) {
            ArrayVar var = new ArrayVar(arg);
            arg = getTemp();
            addLine(String.format("la %s, %s", arg, var.var));
            arg = String.format("-%d(%s)", var.offset, arg);
        }

        if (arg.startsWith("$t")) {
            // arg puede ser registro
            addLine(String.format("sw %s, %s", arg, res));
        } else if (Globals.simbolos.contains(arg, ambito)) {
            // dependeria del tipo de la variable
            Type type = Globals.simbolos.get(arg, ambito);
            if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                // si es integer o boolean
                String temp = getTemp();
                addLine(String.format("lw %s, %s", temp, arg));
                addLine(String.format("sw %s, %s", temp, res));
                kill(temp);
            } else if (type.equals(Type.CHAR)) {
                // si es char
                String temp = getTemp();
                addLine(String.format("lb %s, %s", temp, arg));
                addLine(String.format("sb %s, %s", temp, res));
                kill(temp);
            } else {
                // TODO es un record
            }
        } else if (arg.equals("$ret")) {
            // arg puede ser un valor de retorno
            addLine(String.format("sw $v0, %s", res));
        } else {
            // arg puede ser const
            String t = getTemp();
            addLine(String.format("li %s, %s", t, arg));
            addLine(String.format("sw %s, %s", t, res));
            kill(t);
        }

        if (!res.equals(c.res)) {
            killDir(res);
        } else if (!arg.equals(c.arg1)) {
            killDir(arg);
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

    private void opInitFunc(Cuadruplo c) {
        // aumentar el ambito en 1
        ambito += 1;
        addTag(c.res);
    }

    private void opEndFunc(Cuadruplo c) {
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

        if (res.contains("[")) {
            ArrayVar var = new ArrayVar(res);
            res = getTemp();
            addLine(String.format("la %s, %s", res, var.var));
            res = String.format("-%d(%s)", var.offset, res);
        }

        Type type = Globals.simbolos.get(c.res, ambito);
        if (type == null) {
          // TODO puede ser un record
        } else if (type.equals(Type.INTEGER)) {
            addLine("li $v0, 5");
            addLine("syscall");
            addLine("sw $v0, " + res);
        } else {
            addLine("li $v0, 8");
            addLine("la $a0, " + res);
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

        if (arg2.contains("[")) {
            ArrayVar var = new ArrayVar(arg2);
            arg2 = getTemp();
            addLine(String.format("la %s, %s", arg2, var.var));
            arg2 = String.format("-%d(%s)", var.offset, arg2);
        }

        Type type = Globals.simbolos.get(c.arg2, ambito);
        if (type == null) {
            // TODO puede ser un record
        } else if (type.equals(Type.INTEGER)) {
            addLine("li $v0, 1");
            addLine("lw $a0, " + arg2);
            addLine("syscall");
        } else {
            addLine("li $v0, 11");
            addLine("lb $a0, " + arg2);
            addLine("syscall");
        }

        if (!arg2.equals(c.arg2)) {
            killDir(arg2);
        }
    }

    private void killDir(String temp) {
        String t = temp.replaceAll("\\)", "").split("\\(")[1];
        int killMe = Integer.parseInt(t.substring(2));
        disponibles[killMe] = true;
    }

    private void kill(String temp) {
        int killMe = Integer.parseInt(temp.substring(2));
        disponibles[killMe] = true;
    }

    private void claim(String temp) {
        int pos = Integer.parseInt(temp.substring(2));
        disponibles[pos] = false;
    }

    private String getTemp() {
        for (int i = 0; i < 10; i += 1) {
            if (disponibles[i]) {
                disponibles[i] = false;
                return String.format("$t%d", i);
            }
        }
        // ya no hay temporales, posible error
        error = true;
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
