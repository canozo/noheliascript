package tree;

public class NRecord<T> extends Node<T> {

    public NRecord(Node<T> id, Node<T> maybeVars) {
        super((T) "record");
        add(id);
        add(maybeVars);
    }

    public void visit() {
        System.out.println("Begin Record: Name:");
        children.get(0).visit();
        if (children.get(1) != null) {
            System.out.println("Record vars:");
            children.get(1).visit();
        }
        System.out.println("End.");
    }
}
