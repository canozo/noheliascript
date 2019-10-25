package minipascal.tree;

public class NRepeat<T> extends Node<T> {

    public NRepeat(Node<T> repeatStmnt, Node<T> exprUntil) {
        super((T) "repeat");
        add(repeatStmnt);
        add(exprUntil);
    }

    public void visit() {
        // repeat
        children.get(0).visit();
        // until
        children.get(1).visit();
    }
}
