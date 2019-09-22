package tree;

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
        System.out.println("Begin Suma:");
        if (children.size() == 1) {
            System.out.println("Operador suma: " + data.toString());
            children.get(0).visit();
        } else if (children.size() == 2) {
            children.get(0).visit();
            System.out.println("Operador suma: " + data.toString());
            children.get(1).visit();
        }
        System.out.println("End suma.");
    }
}
