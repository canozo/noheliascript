package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NRepeat<T> extends Node<T> {

    public NRepeat(Node<T> repeatStmnt, Node<T> exprUntil) {
        super((T) "repeat");
        add(repeatStmnt);
        add(exprUntil);
    }

    @SuppressWarnings("Duplicates")
    public void visit() {
        // repeat
        children.get(0).visit();
        // until
        children.get(1).visit();

        NodeType bo = (NodeType) children.get(1);
        if (!Type.BOOLEAN.equals(bo.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(bo.rebuild() + " no es de tipo boolean.");
            System.err.println("Tipo recibido: " + bo.type);
            System.err.println();
            Globals.error = true;
        }
    }

    public void compile() {
    }

    public String rebuild() {
        Node<T> until = children.get(1);
        return String.format("repeat ... until %s", until.rebuild());
    }
}
