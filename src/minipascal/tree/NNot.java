package minipascal.tree;

public class NNot<T> extends Node<T> {

    public NNot(Node<T> some) {
        super((T) "not");
        add(some);
    }

    public void visit() {
        // operador not
        children.get(0).visit();
    }
}
