package minipascal.tree;

public class NIDList<T> extends Node<T> {

    public NIDList(Node<T> id) {
        super((T) "id_list");
        add(id);
    }

    public void visit() {
        // lista de IDs
        for (Node<T> child : children) {
            child.visit();
        }
    }
}
