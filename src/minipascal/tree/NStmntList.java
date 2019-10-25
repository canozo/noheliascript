package minipascal.tree;

public class NStmntList<T> extends Node<T> {

    public NStmntList(Node<T> expr) {
        super((T) "stmnt_list");
        add(expr);
    }

    public void visit() {
        // lista de statements
        for (Node<T> child : children) {
            child.visit();
        }
    }
}
