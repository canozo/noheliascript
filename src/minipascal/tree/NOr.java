package minipascal.tree;

public class NOr<T> extends Node<T> {

    public NOr(Node<T> left, Node<T> right) {
        super((T) "or");
        add(left);
        add(right);
    }

    public void visit() {
        // operador or
        children.get(0).visit();
        children.get(1).visit();
    }
}
