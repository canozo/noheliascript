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

    public String rebuild() {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (Node<T> child : children) {
            if (first) {
                builder.append(child.rebuild());
                first = false;
            } else {
                builder.append(String.format(", %s", child.rebuild()));
            }
        }
        return builder.toString();
    }
}
