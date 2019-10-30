package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NOpRel<T> extends NodeType<T> {

    public NOpRel(Node<T> left, T data, Node<T> right) {
        super(data, Type.BOOLEAN);
        add(left);
        add(right);
    }

    public void visit() {
        // operador relacional (data)
        children.get(0).visit();
        children.get(1).visit();

        NodeType left = (NodeType) children.get(0);
        NodeType right = (NodeType) children.get(1);

        String op = (String) data;
        if (op.equals("=") || op.equals("<>")) {
            // ambos tipos deben de ser iguales (char, integer o boolean)
            if (!left.type.equals(right.type)) {
                System.err.println("ERROR: Se esperaban tipos iguales en <OP REL " + data + ">.");
                System.err.println("Tipos recibido: <" + left.type + "> vs <" + right.type + ">");
                Globals.error = true;
                return;
            }

            Type t = left.type;
            if (!t.equals(Type.CHAR) && !t.equals(Type.INTEGER) && !t.equals(Type.BOOLEAN)) {
                System.err.print("ERROR: <" + left + "> y <" + right + "> en <OP REL " + data + ">");
                System.err.println("no son de los tipos esperados (integer, char, boolean).");
                System.err.println("Tipos recibidos: <" + left.type + ">");
                Globals.error = true;
            }
        } else {
            // ambos tipos deben de ser integer
            if (!left.type.equals(Type.INTEGER)) {
                System.err.println("ERROR: <" + left + "> en <OP REL " + data + "> no es de tipo esperado (integer).");
                System.err.println("Tipo recibido: " + left.type);
                Globals.error = true;
            }

            if (!right.type.equals(Type.INTEGER)) {
                System.err.println("ERROR: <" + right + "> en <OP REL " + data + "> no es de tipo esperado (integer).");
                System.err.println("Tipo recibido: " + right.type);
                Globals.error = true;
            }
        }
    }
}
