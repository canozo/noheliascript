package minipascal.tree;

import minipascal.util.Globals;

public class NNewType<T> extends Node<T> {

    public NNewType(T data) {
        super(data);
    }

    public void visit() {
        // tipo creado por el usuario (record)
        if (!Globals.records.containsKey(data)) {
            System.err.println("ERROR: El identificador  \"" + data + "\" no hace referencia a un tipo.");
            Globals.error = true;
        }
        // el tipo si hace referencia a un record
    }
}
