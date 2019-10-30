package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.TRecord;
import minipascal.util.types.Type;

public class NRecordField<T> extends NodeType<T> {

    public NRecordField(Node<T> variable, Node<T> field) {
        super((T) ".", null);
        add(variable);
        add(field);
    }

    public void visit() {
        // acceso de field a record, ej: persona.nombre
        String variable = (String) children.get(0).data;
        String field = (String) children.get(1).data;

        if (!Globals.simbolos.contains(variable, Globals.ambito)) {
            System.err.println("ERROR: El identificador \"" + variable + "\" no hace referencia a una variable.");
            Globals.error = true;
            return;
        }

        Type type = Globals.simbolos.get(variable, Globals.ambito);
        if (!(type instanceof TRecord)) {
            System.err.println("ERROR: El identificador \"" + variable + "\" no hace referencia a un record.");
            Globals.error = true;
            return;
        }

        TRecord typeRec = (TRecord) type;
        Type typeField = typeRec.getField(field);
        if (typeField == null) {
            String p = String.format("ERROR: El identificador \"%s\" no tiene el campo \"%s\".", variable, field);
            System.err.println(p);
            Globals.error = true;
            return;
        }

        // la variable es un record y si tiene el campo solicitado
        this.type = typeField;
    }
}
