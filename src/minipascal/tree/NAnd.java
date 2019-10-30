package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NAnd<T> extends NodeType<T> {

    public NAnd(Node<T> left, Node<T> right) {
        super((T) "and", Type.BOOLEAN);
        add(left);
        add(right);
    }

    public void visit() {
        // operador and
        children.get(0).visit();
        children.get(1).visit();

        NodeType left = (NodeType) children.get(0);
        NodeType right = (NodeType) children.get(1);

        if (!left.type.equals(Type.BOOLEAN)) {
            System.err.println("ERROR: <" + left + "> en <OP AND> no es de tipo esperado (boolean).");
            System.err.println("Tipo recibido: " + left.type);
            Globals.error = true;
        }

        if (!right.type.equals(Type.BOOLEAN)) {
            System.err.println("ERROR: <" + right + "> en <OP AND> no es de tipo esperado (boolean).");
            System.err.println("Tipo recibido: " + right.type);
            Globals.error = true;
        }

    }
}
