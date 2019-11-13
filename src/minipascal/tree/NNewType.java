package minipascal.tree;

import minipascal.util.Globals;

public class NNewType<T> extends NodeType<T> {

    public NNewType(T data) {
        super(data, null);
    }

    public void visit() {
        // tipo creado por el usuario (record)
        if (!Globals.records.containsKey(data)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println("El identificador  \"" + data + "\" no hace referencia a un tipo.");
            System.err.println();
            Globals.error = true;
            return;
        }
        // el tipo si hace referencia a un record
        type = Globals.findType((String) data);
    }

    public void compile() {
        // no necesitamos hacer nada
    }

    public String rebuild() {
        return (String) data;
    }
}
