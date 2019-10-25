package minipascal.tree;

public class NMult<T> extends Node<T> {

    public NMult(Node<T> left, T data, Node<T> right) {
        super(data);
        add(left);
        add(right);
    }

    public void visit() {
        // operador multiplicacion (data)
        for (Node<T> child : children) {
            child.visit();
        }
    }
}
