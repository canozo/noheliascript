package tree;

public class NWhile<T> extends Node<T> {

    public NWhile(Node<T> whileExpr, Node<T> doStmnt) {
        super((T) "while");
        add(whileExpr);
        add(doStmnt);
    }

    public void visit() {
        System.out.println("Begin while mientras:");
        children.get(0).visit();
        children.get(1).visit();
        System.out.println("End while.");
    }
}
