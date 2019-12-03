package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Cuadruplo;
import minipascal.util.cuadruplo.Marcador;
import minipascal.util.types.Type;

public class NBool<T> extends NodeType<T> {

    public NBool(T data) {
        super(data, Type.BOOLEAN);
    }

    public void visit() {
        // true o false
    }

    public void compile() {
        Boolean boolData = (Boolean) data;

        if (boolData) {
            listaV = Globals.crearLista(new Marcador(false));
            place = "1";
        } else {
            listaF = Globals.crearLista(new Marcador(false));
            place = "0";
        }

        if (parent instanceof NAnd || parent instanceof NOr) {
            Globals.cuadruplos.add(new Cuadruplo("goto", "null"));
        } else {
            // TODO puede ser NOpRel =, o el padre puede ser una asignacion en algun punto
        }
    }

    public String rebuild() {
        return data.toString();
    }
}
