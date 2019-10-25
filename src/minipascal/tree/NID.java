package minipascal.tree;

public class NID<T> extends Node<T> {

    public NID(T data) {
        super(data);
    }

    public void visit() {
        // identificador
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", data.toString());
    }
}
