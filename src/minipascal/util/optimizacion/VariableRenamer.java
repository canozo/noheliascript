package minipascal.util.optimizacion;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import minipascal.util.Globals;
import minipascal.util.cuadruplo.Cuadruplo;
import minipascal.util.types.TFunc;
import minipascal.util.types.TRecord;
import minipascal.util.types.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VariableRenamer {

    private List<Cuadruplo> cuadruplos;
    private Table<String, Integer, Type> newSimbolos;
    private Map<String, TFunc> newFunciones;
    private Map<String, TRecord> newRecords;

    public VariableRenamer(List<Cuadruplo> cuadruplos) {
        this.cuadruplos = cuadruplos;
        newSimbolos = HashBasedTable.create();
        newFunciones = new HashMap<>();
        newRecords = new HashMap<>();
    }

    public void renameGlobals() {
        // cada variable (global) que se encuentre se renombra a guion bajo y el nombre original
        for (String var : Globals.simbolos.column(0).keySet()) {
            replace(var, "_" + var);
            // cambiar de la tabla de simbolos
            Type type = Globals.simbolos.get(var, 0);
            newSimbolos.put("_" + var, 0, type);
        }

        // hacemos lo mismo para funciones
        for (String func : Globals.funciones.keySet()) {
            replace(func, "_" + func);
            // cambiar de la tabla de funciones
            TFunc type = Globals.funciones.get(func);
            newFunciones.put("_" + func, type);
        }

        // hacemos lo mismo para records
        for (String record : Globals.records.keySet()) {
            replace(record, "_" + record);
            // cambiar de la tabla de funciones
            TRecord type = Globals.records.get(record);
            newRecords.put("_" + record, type);
        }

        // reemplazar valores viejos por los nuevos
        Globals.simbolos.cellSet().clear();
        Globals.simbolos.putAll(newSimbolos);

        Globals.funciones.clear();
        Globals.funciones.putAll(newFunciones);

        Globals.records.clear();
        Globals.records.putAll(newRecords);
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
