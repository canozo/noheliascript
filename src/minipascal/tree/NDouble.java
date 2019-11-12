package minipascal.tree;

// No se necesita

@Deprecated
public class NDouble<T> extends Node<T> {

    public NDouble(T data) {
        super(data);
    }

    public void visit() {
        // double
    }

    public String rebuild() {
        return data.toString();
    }
}
