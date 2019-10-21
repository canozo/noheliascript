package minipascal.tree;

public class NFields<T> extends Node<T> {

    public NFields(Node<T> type, Node<T> ids) {
        super((T) "fields");
        type.add(ids);
        add(type);
    }

    public void visit() {
        System.out.println("Begin field list:");
        for (Node<T> child : children) {
            child.visit();
        }
        System.out.println("End field list.");
    }
}
