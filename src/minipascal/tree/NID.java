package minipascal.tree;

import minipascal.util.Globals;

public class NID<T> extends NodeType<T> {

    public NID(T data) {
        super(data, null);
    }

    public void visit() {
        // identificador, ver si ha sido definido
        if (!Globals.simbolos.contains(data, Globals.ambito) && !Globals.simbolos.contains(data, 0)) {
            System.err.println("ERROR: El identificador \"" + data + "\" no hace referencia a una variable.");
            return;
        }
        // si es una variable
        type = Globals.simbolos.get(data, Globals.ambito);
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", data.toString());
    }
}
