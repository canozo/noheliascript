package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NWhile<T> extends Node<T> {

    public NWhile(Node<T> whileExpr, Node<T> doStmnt) {
        super((T) "while");
        add(whileExpr);
        add(doStmnt);
    }

    public void visit() {
        // while
        children.get(0).visit();
        // do
        children.get(1).visit();

        NodeType bo = (NodeType) children.get(0);
        if (!Type.BOOLEAN.equals(bo.type)) {
            System.err.println("ERROR: La variable <" + bo + "> no es de tipo boolean. (while)");
            Globals.error = true;
        }
    }
}
