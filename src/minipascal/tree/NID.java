package minipascal.tree;

import minipascal.util.Globals;

public class NID<T> extends NodeType<T> {

    public NID(T data) {
        super(data, null);
    }

    public void visit() {
        // identificador, ver si ha sido definido
        if (!Globals.simbolos.contains(data, Globals.ambito) && !Globals.simbolos.contains(data, 0)) {
            System.err.println("ERROR EN: " + data);
            System.err.println("El identificador no hace referencia a un nombre valido.");
            System.err.println();
            Globals.error = true;
            return;
        }
        // si es una variable
        type = Globals.simbolos.get(data, Globals.ambito);
    }

    public String rebuild() {
        return (String) data;
    }

    public void compile() {
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", data.toString());
    }
}
