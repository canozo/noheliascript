package tree;

public class NOr<T> extends Node<T> {

    public NOr(Node<T> left, Node<T> right) {
        super((T) "or");
        add(left);
        add(right);
    }

    public void visit() {
        System.out.println("Begin Or:");
        children.get(0).visit();
        System.out.println("or");
        children.get(1).visit();
        System.out.println("End Or.");
    }
}
