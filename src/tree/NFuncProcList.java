package tree;

public class NFuncProcList<T> extends Node<T> {

    public NFuncProcList(Node<T> id) {
        super((T) "func_proc_list");
        add(id);
    }

    public void visit() {
        System.out.println("Begin function/procedure list:");
        for (Node<T> child : children) {
            child.visit();
        }
        System.out.println("End function/procedure list.");
    }
}
