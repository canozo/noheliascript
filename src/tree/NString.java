package tree;

public class NString<T> extends Node<T> {

    public NString(T data) {
        super(data);
    }

    public void visit() {
        System.out.println("String: " + data);
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", data.toString());
    }
}
