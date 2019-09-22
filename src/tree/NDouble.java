package tree;

public class NDouble<T> extends Node<T> {

    public NDouble(T data) {
        super(data);
    }

    public void visit() {
        System.out.println("Double: " + data.toString());
    }
}
