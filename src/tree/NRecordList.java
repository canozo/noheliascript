package tree;

public class NRecordList<T> extends Node<T> {

    public NRecordList(Node<T> decl) {
        super((T) "record_list");
        add(decl);
    }

    public void visit() {
        System.out.println("Begin record list:");
        for (Node<T> child : children) {
            child.visit();
        }
        System.out.println("End record list.");
    }
}
