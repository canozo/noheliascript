package tree;

public class NChar<T> extends Node<T> {

    public NChar(T data) {
        super(data);
    }

    public void visit() {
        System.out.println("Char: " + data.toString());
    }

    @Override
    public String toString() {
        return String.format("'%s'", data.toString());
    }
}
