package minipascal.tree;

public class NIDList<T> extends Node<T> {

    public NIDList(Node<T> id) {
        super((T) "id_list");
        add(id);
    }

    public void visit() {
        System.out.println("Begin id list:");
        for (Node<T> child : children) {
            child.visit();
        }
        System.out.println("End id list.");
    }
}
