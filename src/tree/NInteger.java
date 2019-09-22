package tree;

public class NInteger<T> extends Node<T> {

    public NInteger(T data) {
        super(data);
    }

    public void visit() {
        System.out.println("Integer: " + data.toString());
    }
}