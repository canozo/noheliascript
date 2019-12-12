package minipascal.tree;

public class NRecord<T> extends Node<T> {

    public NRecord(Node<T> id, Node<T> fields) {
        super((T) "record");
        add(id);
        add(fields);
    }

    public void visit() {
        // definicion del record
        // agregar info del record con su nombre y campos
        children.get(1).visit();
    }

    public void compile() {
        children.get(1).compile();
    }

    public String rebuild() {
        // no es necesario el rebuild
        return "NRecord";
    }
}
