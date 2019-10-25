package minipascal.tree;

public class NWhile<T> extends Node<T> {

    public NWhile(Node<T> whileExpr, Node<T> doStmnt) {
        super((T) "while");
        add(whileExpr);
        add(doStmnt);
    }

    public void visit() {
        // while
        children.get(0).visit();
        // do
        children.get(1).visit();
    }
}
