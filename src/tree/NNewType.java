package tree;

public class NNewType<T> extends Node<T> {

    public NNewType(T data) {
        super(data);
    }

    public void visit() {
        System.out.println("New type: " + data.toString());
        for (Node<T> child : children) {
            child.visit();
        }
    }
}
