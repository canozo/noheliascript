package tree;

public class NFor<T> extends Node<T> {

    public NFor(Node<T> asign, Node<T> toFactor, Node<T> doStmnt) {
        super((T) "for");
        add(asign);
        add(doStmnt);
        add(toFactor);
    }

    public void visit() {
        System.out.println("Begin for:");
        children.get(0).visit();
        children.get(1).visit();
        System.out.println("To:");
        children.get(2).visit();
        System.out.println("End for.");
    }
}
