package minipascal.tree;

public class NBegin<T> extends Node<T> {

    public NBegin(Node<T> stmntList) {
        super((T) "begin");
        add(stmntList);
    }

    public void visit() {
        children.get(0).visit();
    }

    public void compile() {
        Node<T> stmntList = children.get(0);
        stmntList.compile();
        listaSig = stmntList.listaSig;
    }

    public String rebuild() {
        // no es necesario el rebuild
        return "NBegin";
    }
}
