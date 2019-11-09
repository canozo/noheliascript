package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NFor<T> extends Node<T> {

    public NFor(Node<T> asign, Node<T> toFactor, Node<T> doStmnt) {
        super((T) "for");
        add(asign);
        add(toFactor);
        add(doStmnt);
    }

    public void visit() {
        // for loop:
        // asign:
        children.get(0).visit();
        // to:
        children.get(1).visit();
        // statements:
        children.get(2).visit();

        NodeType to = (NodeType) children.get(1);
        if (!Type.INTEGER.equals(to.type)) {
            System.err.println("ERROR: La variable <" + to + "> no es de tipo integer. (For Loop)");
            Globals.error = true;
        }
    }
}
