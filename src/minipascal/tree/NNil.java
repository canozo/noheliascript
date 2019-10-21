package minipascal.tree;

public class NNil<T> extends Node<T> {

    public NNil() {
        super(null);
    }

    public void visit() {
        System.out.println("Null.");
    }

    @Override
    public String toString() {
        return "null";
    }
}
