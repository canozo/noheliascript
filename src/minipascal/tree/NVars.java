package minipascal.tree;

public class NVars<T> extends Node<T> {

    public NVars(Node<T> type, Node<T> ids) {
        super((T) "vars");
        type.add(ids);
        add(type);
    }

    public void visit() {
        // con cada llamado, hay un nuevo ambito
        System.out.println("Begin var list:");
        for (Node<T> child : children) {
            child.visit();
        }
        System.out.println("End var list.");
    }
}
