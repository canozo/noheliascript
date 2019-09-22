package tree;

public class NNot<T> extends Node<T> {

    public NNot(Node<T> some) {
        super((T) "not");
        add(some);
    }

    public void visit() {
        System.out.println("Not:");
        children.get(0).visit();
    }
}
