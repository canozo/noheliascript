package tree;

public class NArgs<T> extends Node<T> {

    public NArgs(Node<T> type, Node<T> ids) {
        super((T) "args");
        type.add(ids);
        add(type);
    }

    public void visit() {
        System.out.println("Begin arg list:");
        for (Node<T> child : children) {
            child.visit();
        }
        System.out.println("End arg list.");
    }
}
