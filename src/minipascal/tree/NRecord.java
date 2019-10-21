package minipascal.tree;

public class NRecord<T> extends Node<T> {

    public NRecord(Node<T> id, Node<T> fields) {
        super((T) "record");
        add(id);
        add(fields);
    }

    public void visit() {
        System.out.println("Begin Record: Name:");
        children.get(0).visit();
        System.out.println("Record fields:");
        children.get(1).visit();
        System.out.println("End.");
    }
}
