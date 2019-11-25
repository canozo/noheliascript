package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Cuadruplo;
import minipascal.util.types.Type;

public class NBool<T> extends NodeType<T> {

    public NBool(T data) {
        super(data, Type.BOOLEAN);
    }

    public void visit() {
        // true o false
    }

    public void compile() {
        // TODO preguntar al ing
        Boolean boolData = (Boolean) data;
        place = boolData.toString();

        if (boolData) {
            listaV = Globals.crearLista(Globals.cuadruplos.size() + 1);
            Globals.cuadruplos.add(new Cuadruplo("goto", null));
        } else {
            listaF = Globals.crearLista(Globals.cuadruplos.size() + 1);
            Globals.cuadruplos.add(new Cuadruplo("goto", null));
        }
    }

    public String rebuild() {
        return data.toString();
    }
}
