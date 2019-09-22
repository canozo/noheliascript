package tree;

public class NStmntList<T> extends Node<T> {

    public NStmntList(Node<T> expr) {
        super((T) "stmnt_list");
        add(expr);
    }

    public void visit() {
        System.out.println("Begin statement list:");
        for (Node<T> child : children) {
            child.visit();
        }
        System.out.println("End statement list.");
    }
}
