package tree;

public class NType<T> extends Node<T> {

    public NType(T data) {
        super(data);
    }

    public void visit() {
        System.out.println("Type: " + data.toString());
        for (Node<T> child : children) {
            child.visit();
        }
    }
}
