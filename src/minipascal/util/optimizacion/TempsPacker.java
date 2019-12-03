package minipascal.util.optimizacion;

import minipascal.util.cuadruplo.Cuadruplo;

import java.util.List;

public class TempsPacker {

    private List<Cuadruplo> cuadruplos;
    private boolean[] disponibles;
    private boolean error;

    public TempsPacker(List<Cuadruplo> cuadruplos) {
        this.cuadruplos = cuadruplos;
        this.disponibles = new boolean[10];
        for (int i = 0; i < 10; i += 1) {
            disponibles[i] = true;
        }
        this.error = false;
    }

    public boolean error() {
        return error;
    }

    public void pack() {
        // si un temporal se declara (aparece en res), lo tomamos como vivo
        // si un temporal se utiliza (aparece en arg1 o arg2), lo tomamos como muerto
        for (Cuadruplo c : cuadruplos) {
            if (c.arg1.startsWith("$t")) {
                kill(c.arg1);
            }

            if (c.arg2.startsWith("$t")) {
                kill(c.arg2);
            }

            if (c.res != null && c.res.startsWith("$t")) {
                String temp = getTemp();
                int numViejo = Integer.parseInt(c.res.substring(2));
                if (numViejo > 9) {
                    replace(c.res, temp);
                }
            }
        }
    }

    private void kill(String temp) {
        int killMe = Integer.parseInt(temp.substring(2));
        disponibles[killMe] = true;
    }

    @SuppressWarnings("Duplicates")
    private void replace(String temp, String by) {
        for (Cuadruplo c : cuadruplos) {
            if (c.arg1.equals(temp)) {
                c.arg1 = by;
            }

            if (c.arg2.equals(temp)) {
                c.arg2 = by;
            }

            if (c.res != null && c.res.equals(temp)) {
                c.res = by;
            }
        }
    }

    private String getTemp() {
        for (int i = 0; i < 10; i += 1) {
            if (disponibles[i]) {
                disponibles[i] = false;
                return String.format("$t%d", i);
            }
        }
        // ya no hay temporales, posible error
        this.error = true;
        return String.format("$t%d", 420);
    }
}
