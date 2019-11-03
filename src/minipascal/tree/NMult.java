package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NMult<T> extends NodeType<T> {

    public NMult(Node<T> left, T data, Node<T> right) {
        super(data, Type.INTEGER);
        add(left);
        add(right);
    }

    public void visit() {
        children.get(0).visit();
        children.get(1).visit();

        NodeType left = (NodeType) children.get(0);
        NodeType right = (NodeType) children.get(1);

        if (!Type.INTEGER.equals(left.type)) {
            System.err.println("ERROR: <" + left + "> en <OP MULT> no es de tipo esperado (integer).");
            System.err.println("Tipo recibido: " + left.type);
            Globals.error = true;
        }

        if (!Type.INTEGER.equals(right.type)) {
            System.err.println("ERROR: <" + right + "> en <OP MULT> no es de tipo esperado (integer).");
            System.err.println("Tipo recibido: " + right.type);
            Globals.error = true;
        }
    }
}
