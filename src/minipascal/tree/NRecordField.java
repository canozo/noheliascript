package minipascal.tree;

public class NRecordField<T> extends Node<T> {

    public NRecordField(Node<T> variable, Node<T> field) {
        super((T) ".");
        add(variable);
        add(field);
    }

    public void visit() {
        System.out.println("Begin record var field: ");
        children.get(0).visit();
        System.out.println(".");
        children.get(1).visit();
        System.out.println("End record var field: ");
    }
}
