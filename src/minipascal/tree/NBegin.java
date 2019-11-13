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
        children.get(0).compile();
    }

    public String rebuild() {
        // no es necesario el rebuild
        return "NBegin";
    }
}
