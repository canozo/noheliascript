package minipascal.util;

import minipascal.util.cuadruplo.Cuadruplo;
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

        // agregar las variables globales (int, char y bool)
        for (String var : Globals.simbolos.column(0).keySet()) {
            Type type = Globals.simbolos.get(var, 0);

            if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                varGlobales.add(String.format("%s: .word %d\n", var, 0));
            }

            if (type.equals(Type.CHAR)) {
                varGlobales.add(String.format("%s: .byte '%c'\n", var, '0'));
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
                    opSuma(c);
                    break;
                case "-":
                    opResta(c);
                    break;
                case ":=":
                    opAsign(c);
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

    private void opAsign(Cuadruplo c) {
        if (c.res.contains("[")) {
            // TODO c.res puede tener un record field
            return;
        }

        if (c.arg1.startsWith("$t")) {
            // arg1 puede ser registro
            addLine(String.format("sw %s, %s", c.arg1, c.res));
        } else if (Globals.simbolos.contains(c.arg1, ambito)) {
            // arg1 puede ser variable
            System.out.println("opAsign Variable");
        } else if (c.arg1.equals("RES")) {
            // arg1 puede ser un valor de retorno
            addLine(String.format("sw $v0, %s", c.res));
        } else {
            // arg1 puede ser const
            String t = getTemp();
            addLine(String.format("li %s, %s", t, c.arg1));
            addLine(String.format("sw %s, %s", t, c.res));
            kill(t);
        }
    }

    private void opSuma(Cuadruplo c) {
        // puede ser una suma unaria:
        if (c.arg2.equals("")) {
            return;
        }

        // suma binaria
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
        if (!(left.startsWith("$"))) {
            String temp = left;
            left = right;
            right = temp;

            // chanchada:
            temp = c.arg1;
            c.arg1 = c.arg2;
            c.arg2 = temp;
        }

        addLine(String.format("add %s, %s, %s", res, left, right));

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

    private void opResta(Cuadruplo c) {
        // puede ser una suma unaria:
        if (c.arg2.equals("")) {
            // resta unaria
            return;
        }
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
        if (c.res.contains("[")) {
            // TODO es un record
            return;
        }

        Type type = Globals.simbolos.get(c.res, ambito);
        if (type.equals(Type.INTEGER)) {
            addLine("li $v0, 5");
            addLine("syscall");
            addLine("sw $v0, " + c.res);
        } else {
            // TODO char
        }
    }

    private void opWrite(Cuadruplo c) {
        // agregar string a los globales
        String place = addString(c.arg1);

        // imprimir string
        addLine("li $v0, 4");
        addLine("la $la0, " + place);
        addLine("syscall");

        // imprimir variable (si tiene argumento, integer o char)
        if (c.arg2.equals("")) {
            return;
        }

        if (c.arg2.contains("[")) {
            // TODO es un record
            return;
        }

        Type type = Globals.simbolos.get(c.arg2, ambito);
        if (type.equals(Type.INTEGER)) {
            addLine("li $v0, 1");
            addLine("la $a0, " + c.arg2);
            addLine("syscall");
        } else {
            addLine("li $v0, 11");
            addLine("lb $a0, " + c.arg2);
            addLine("syscall");
        }
    }

    private void kill(String temp) {
        int killMe = Integer.parseInt(temp.substring(2));
        disponibles[killMe] = true;
    }

    private void claim(String temp) {
        int pos = Integer.parseInt(temp.substring(2));
        disponibles[pos] = false;
    }

    @SuppressWarnings("Duplicates")
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
