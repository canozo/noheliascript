package minipascal.tree;

public class NRecordField<T> extends Node<T> {

    public NRecordField(Node<T> variable, Node<T> field) {
        super((T) ".");
        add(variable);
        add(field);
    }

    public void visit() {
        // acceso de field a record, ej: persona.nombre
        // TODO verificar que variable es un record, y tiene el tipo field
        children.get(0).visit();
        children.get(1).visit();
    }
}
