package tree;

public class NRepeat<T> extends Node<T> {

    public NRepeat(Node<T> repeatStmnt, Node<T> exprUntil) {
        super((T) "repeat");
        add(repeatStmnt);
        add(exprUntil);
    }

    public void visit() {
        System.out.println("Begin repeat:");
        children.get(0).visit();
        System.out.println("Until:");
        children.get(1).visit();
        System.out.println("End repeat.");
    }
}
