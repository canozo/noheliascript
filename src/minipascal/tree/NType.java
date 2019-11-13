package minipascal.tree;

public class NType<T> extends Node<T> {

    public NType(T data) {
        super(data);
    }

    public void visit() {
        // tipo simple (integer, real, boolean, char)
    }

    public void compile() {
        // no necesitamos hacer nada
    }

    public String rebuild() {
        return (String) data;
    }
}
