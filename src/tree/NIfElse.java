package tree;

public class NIfElse<T> extends Node<T> {

    public NIfElse(Node<T> ifExpr, Node<T> thenStmnt) {
        super((T) "if");
        add(ifExpr);
        add(thenStmnt);
    }

    public NIfElse(Node<T> ifExpr, Node<T> thenStmnt, Node<T> elseStmnt) {
        super((T) "if");
        add(ifExpr);
        add(thenStmnt);
        add(elseStmnt);
    }

    public void visit() {
        System.out.println("Begin if:");
        children.get(0).visit();
        System.out.println("then:");
        children.get(1).visit();
        if (children.size() > 2) {
            System.out.println("else:");
            children.get(2).visit();
        }
        System.out.println("End if.");
    }
}
