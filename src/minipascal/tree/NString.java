package minipascal.tree;

public class NString<T> extends Node<T> {

    public NString(T data) {
        super(data);
    }

    public void visit() {
        // conststr
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", data.toString());
    }
}
