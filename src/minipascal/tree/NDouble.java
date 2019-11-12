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

    public void compile() {
    }

    public String rebuild() {
        return data.toString();
    }
}
