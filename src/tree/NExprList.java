package tree;

public class NExprList<T> extends Node<T> {

    public NExprList(Node<T> expr) {
        super((T) "expr_list");
        add(expr);
    }

    public void visit() {
        System.out.println("Begin expresion list:");
        for (Node<T> child : children) {
            child.visit();
        }
        System.out.println("End expresion list.");
    }
}
