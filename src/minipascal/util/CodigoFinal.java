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

    public CodigoFinal() {
        varGlobales = new ArrayList<>();
        strGlobales = new ArrayList<>();
        lineas = new ArrayList<>();
        numLinea = 1;
        ambito = 0;

        // agregar las variables globales (int, char y bool)
        for (String var : Globals.simbolos.column(0).keySet()) {
            Type type = Globals.simbolos.get(var, 0);

            if (type.equals(Type.INTEGER) || type.equals(Type.BOOLEAN)) {
                addGlobalInt(var, 0);
            }

            if (type.equals(Type.CHAR)) {
                addGlobalChar(var, '0');
            }
        }
    }

    public void compilar(List<Cuadruplo> cuadruplos) {
        for (Cuadruplo c : cuadruplos) {
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

    private void addGlobalInt(String var, int inicial) {
        varGlobales.add(String.format("_%s: .word %d\n", var, inicial));
    }

    private void addGlobalChar(String var, char inicial) {
        varGlobales.add(String.format("_%s: .byte '%c'\n", var, inicial));
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
        // arg1 puede ser const
        if (c.arg1.startsWith("$t")) {
            addLine(String.format("sw %s, _%s", c.arg1, c.res));
        }

        // arg1 puede ser registro
        if (c.arg1.startsWith("$t")) {
            addLine(String.format("sw %s, _%s", c.arg1, c.res));
        }

        // arg1 puede ser variable
        if (c.arg1.startsWith("$t")) {
            addLine(String.format("sw %s, _%s", c.arg1, c.res));
        }
    }

    private void opSuma(Cuadruplo c) {
        // puede ser una suma unaria:
        if (c.arg2.equals("")) {
            // no afecta el resultado:
            // 1 = +1
            // -1 = -(+1)
            return;
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
        addTag("_" + c.res);
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
            addLine("sw $v0, _" + c.res);
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
            addLine("la $a0, _" + c.arg2);
            addLine("syscall");
        } else {
            // TODO char
        }
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
