package tree;

public class NAnd<T> extends Node<T> {

    public NAnd(Node<T> left, Node<T> right) {
        super((T) "and");
        add(left);
        add(right);
    }

    public void visit() {
        System.out.println("Begin And:");
        children.get(0).visit();
        System.out.println("and");
        children.get(1).visit();
        System.out.println("End And.");
    }
}
