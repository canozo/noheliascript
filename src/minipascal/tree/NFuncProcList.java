package minipascal.tree;

public class NFuncProcList<T> extends Node<T> {

    public NFuncProcList(Node<T> decl) {
        super((T) "func_proc_list");
        add(decl);
    }

    public void visit() {
        for (Node<T> child : children) {
            child.visit();
        }
    }
}
