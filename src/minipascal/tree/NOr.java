package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NOr<T> extends NodeType<T> {

    public NOr(Node<T> left, Node<T> right) {
        super((T) "or", Type.BOOLEAN);
        add(left);
        add(right);
    }

    public void visit() {
        // operador or
        children.get(0).visit();
        children.get(1).visit();

        NodeType left = (NodeType) children.get(0);
        NodeType right = (NodeType) children.get(1);

        if (!left.type.equals(Type.BOOLEAN)) {
            System.err.println("ERROR: <" + left + "> en <OP OR> no es de tipo esperado (boolean).");
            System.err.println("Tipo recibido: " + left.type);
            Globals.error = true;
        }

        if (!right.type.equals(Type.BOOLEAN)) {
            System.err.println("ERROR: <" + right + "> en <OP OR> no es de tipo esperado (boolean).");
            System.err.println("Tipo recibido: " + right.type);
            Globals.error = true;
        }
    }
}
