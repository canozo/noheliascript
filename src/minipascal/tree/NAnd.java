package minipascal.tree;

public class NAnd<T> extends Node<T> {

    public NAnd(Node<T> left, Node<T> right) {
        super((T) "and");
        add(left);
        add(right);
    }

    public void visit() {
    }
}
