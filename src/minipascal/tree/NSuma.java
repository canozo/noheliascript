package minipascal.tree;

public class NSuma<T> extends Node<T> {

    public NSuma(Node<T> left, T data, Node<T> right) {
        super(data);
        add(left);
        add(right);
    }

    public NSuma(T data, Node<T> term) {
        super(data);
        add(term);
    }

    public void visit() {
        // operador suma
        if (children.size() == 1) {
            // suma unaria (-5)
            children.get(0).visit();
        } else if (children.size() == 2) {
            // suma binaria (5-3)
            children.get(0).visit();
            children.get(1).visit();
        }
    }
}
