package minipascal.util.optimizacion;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Cuadruplo;

import java.util.List;

public class VariableRenamer {

    private List<Cuadruplo> cuadruplos;

    public VariableRenamer(List<Cuadruplo> cuadruplos) {
        this.cuadruplos = cuadruplos;
    }

    public void renameGlobals() {
        // cada variable (global) que se encuentre se renombra a guion bajo y el nombre original
        // ej: 'persona' => '_persona'
        for (String var : Globals.simbolos.column(0).keySet()) {
            replace(var, "_" + var);
        }
    }

    @SuppressWarnings("Duplicates")
    private void replace(String before, String after) {
        for (Cuadruplo c : cuadruplos) {
            if (c.arg1.equals(before)) {
                c.arg1 = after;
            }

            if (c.arg2.equals(before)) {
                c.arg2 = after;
            }

            if (c.res != null && c.res.equals(before)) {
                c.res = after;
            }
        }
    }
}
