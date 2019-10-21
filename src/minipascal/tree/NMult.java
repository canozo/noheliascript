package minipascal.tree;

public class NMult<T> extends Node<T> {

    public NMult(Node<T> left, T data, Node<T> right) {
        super(data);
        add(left);
        add(right);
    }

    public void visit() {
        System.out.println("Begin Mult, operador: " + data.toString());
        for (Node<T> child : children) {
            child.visit();
        }
        System.out.println("End Mult.");
    }
}
