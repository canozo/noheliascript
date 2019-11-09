package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NRepeat<T> extends Node<T> {

    public NRepeat(Node<T> repeatStmnt, Node<T> exprUntil) {
        super((T) "repeat");
        add(repeatStmnt);
        add(exprUntil);
    }

    public void visit() {
        // repeat
        children.get(0).visit();
        // until
        children.get(1).visit();

        NodeType bo = (NodeType) children.get(1);
        if (!Type.BOOLEAN.equals(bo.type)) {
            System.err.println("ERROR: La variable <" + bo + "> no es de tipo boolean. (repeat)");
            Globals.error = true;
        }
    }
}
