package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NSuma<T> extends NodeType<T> {

    public NSuma(Node<T> left, T data, Node<T> right) {
        super(data, Type.INTEGER);
        add(left);
        add(right);
    }

    public NSuma(T data, Node<T> term) {
        super(data, Type.INTEGER);
        add(term);
    }

    public void visit() {
        // operador suma
        if (children.size() == 1) {
            // suma unaria (-5)
            children.get(0).visit();

            NodeType num = (NodeType) children.get(0);
            if (!num.type.equals(Type.INTEGER)) {
                System.err.println("ERROR: <" + num + "> en <OP SUMA> no es de tipo esperado (integer).");
                System.err.println("Tipo recibido: " + num.type);
                Globals.error = true;
            }
        } else if (children.size() == 2) {
            // suma binaria (5-3)
            children.get(0).visit();
            children.get(1).visit();

            NodeType left = (NodeType) children.get(0);
            NodeType right = (NodeType) children.get(1);

            if (!left.type.equals(Type.INTEGER)) {
                System.err.println("ERROR: <" + left + "> en <OP SUMA> no es de tipo esperado (integer).");
                System.err.println("Tipo recibido: " + left.type);
                Globals.error = true;
            }

            if (!right.type.equals(Type.INTEGER)) {
                System.err.println("ERROR: <" + right + "> en <OP SUMA> no es de tipo esperado (integer).");
                System.err.println("Tipo recibido: " + right.type);
                Globals.error = true;
            }
        }
    }
}
