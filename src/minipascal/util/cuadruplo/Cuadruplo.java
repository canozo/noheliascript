package minipascal.util.cuadruplo;

import minipascal.util.Globals;

import java.util.ArrayList;

public class Cuadruplo {

    public String op;
    public String arg1;
    public String arg2;
    public String res;
    public Marcador resM;

    public Cuadruplo(String op, String arg1, String arg2, String res) {
        // si recibimos los cuatro argumentos, es posible que obtengamos mas de 3 direcciones
        // casos especiales:
        //      op |     arg1 |     arg2 |     res
        // ---------------------------------------
        //       + |     a[1] |        b |       c
        //       * |        b |     a[1] |       c
        //       / |        b |        c |    a[1]
        // en los primeros dos casos, simplemente guardamos el valor de a[1] en una temporal
        // en el tercer caso, tenemos que guardar el resultado de b y c en una temporal
        // y asignar la temporal a a[1]

        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.res = res;

        int saltosExtra = 0;
        ArrayList<Marcador> saltosAqui = new ArrayList<>();

        for (Marcador m : Marcador.marcadores) {
            if (m.sigCuad > Globals.cuadruplos.size()) {
                if (!m.res) {
                    saltosAqui.add(m);
                }
            }
        }

        if (!op.equals("write") && arg1.contains("[")) {
            // primer caso
            String temp = Globals.temporalNuevo();
            Globals.cuadruplos.add(new Cuadruplo(":=", arg1, temp));
            this.arg1 = arg1 = temp;
            saltosExtra += 1;
        }

        if (arg2.contains("[")) {
            // segundo caso
            String temp = Globals.temporalNuevo();
            Globals.cuadruplos.add(new Cuadruplo(":=", arg2, temp));
            this.arg2 = arg2 = temp;
            saltosExtra += 1;
        }

        if (res != null && res.contains("[")) {
            // tercer caso
            String temp = Globals.temporalNuevo();
            Globals.cuadruplos.add(new Cuadruplo(op, arg1, arg2, temp));
            this.op = ":=";
            this.arg1 = temp;
            this.arg2 = "";
            saltosExtra += 1;
        }

        for (Marcador m : saltosAqui) {
            m.sigCuad = m.sigCuad + saltosExtra;
        }
    }

    public Cuadruplo(String op, String arg1, String res) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = "";
        this.res = res;
    }

    public Cuadruplo(String op, String res) {
        this.op = op;
        this.arg1 = "";
        this.arg2 = "";
        this.res = res;
    }

    public Cuadruplo(String op, Marcador resM) {
        this.op = op;
        this.arg1 = "";
        this.arg2 = "";
        this.resM = resM;
    }

    public Cuadruplo() {
        this.op = "";
        this.arg1 = "";
        this.arg2 = "";
        this.res = "";
    }

    public void setRes(Marcador resM) {
        this.resM = resM;
    }

    @Override
    public String toString() {
        if (op.equals("write")) {
            int maxSize = Math.min(arg1.length(), 15);
            String shortStr = String.format("\"%s\"", arg1.substring(1, maxSize - 1));
            return String.format("%15s | %15s | %15s | %15s", op, shortStr, arg2, res);
        } else if (resM != null) {
            return String.format("%15s | %15s | %15s | %15s", op, arg1, arg2, resM);
        }
        return String.format("%15s | %15s | %15s | %15s", op, arg1, arg2, res);
    }
}
