package minipascal.tree;

// No se necesita

@Deprecated
public class NNil<T> extends Node<T> {

    public NNil() {
        super(null);
    }

    public void visit() {
        System.out.println("Null.");
    }

    public String rebuild() {
        return "nil";
    }

    public void compile() {
    }

    @Override
    public String toString() {
        return "null";
    }
}
