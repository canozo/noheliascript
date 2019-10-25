package minipascal.tree;

public class NExprList<T> extends Node<T> {

    public NExprList(Node<T> expr) {
        super((T) "expr_list");
        add(expr);
    }

    public void visit() {
        // lista de expresiones
        for (Node<T> child : children) {
            child.visit();
        }
    }
}
