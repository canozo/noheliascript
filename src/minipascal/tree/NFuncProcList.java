package minipascal.tree;

// No se necesita

@Deprecated
public class NFuncProcList<T> extends Node<T> {

    public NFuncProcList(Node<T> decl) {
        super((T) "func_proc_list");
        add(decl);
    }

    public void visit() {
        for (Node<T> child : children) {
            child.visit();
        }
    }

    public void compile() {
    }

    public String rebuild() {
        // no es necesario el rebuild
        return "NFuncProcList";
    }
}
