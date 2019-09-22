package tree;

public class NID<T> extends Node<T> {

    public NID(T data) {
        super(data);
    }

    public void visit() {
        System.out.println("Identificador: " + data.toString());
    }
}
