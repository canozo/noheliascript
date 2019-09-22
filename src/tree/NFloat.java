package tree;

public class NFloat<T> extends Node<T> {

    public NFloat(T data) {
        super(data);
    }

    public void visit() {
        System.out.println("Float: " + data.toString());
    }
}
