package minipascal.util.cuadruplo;

import minipascal.util.Globals;
import minipascal.util.types.TRecord;
import minipascal.util.types.Type;

public class ArrayVar {

    public String var;
    public int offset;
    public Type type;

    public ArrayVar(String direccion, int ambito) {
        // recibe una variable con direccionamiento, ej: persona[5]
        String temp = direccion.replaceAll("]", "");
        String[] splitted = temp.split("\\[");
        var = splitted[0];
        offset = Integer.parseInt(splitted[1]);

        // buscar el type en la tabla de simbolos
        TRecord type = (TRecord) Globals.simbolos.get(var, ambito);
        this.type = type.getFieldType(offset);
    }
}
