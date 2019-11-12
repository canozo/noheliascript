package minipascal.tree;

// No se necesita

@Deprecated
public class NRecordList<T> extends Node<T> {

    public NRecordList(Node<T> decl) {
        super((T) "record_list");
        add(decl);
    }

    public void visit() {
        // lista de definiciones de records
        for (Node<T> child : children) {
            child.visit();
        }
    }

    public void compile() {
    }

    public String rebuild() {
        // no es necesario el rebuild
        return "NRecordList";
    }
}
