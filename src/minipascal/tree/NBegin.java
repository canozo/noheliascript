package minipascal.tree;

public class NBegin<T> extends Node<T> {

    public NBegin(Node<T> stmntList) {
        super((T) "begin");
        add(stmntList);
    }

    public void visit() {
        children.get(0).visit();
    }
}
