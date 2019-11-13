package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NMult<T> extends NodeType<T> {

    public NMult(Node<T> left, T data, Node<T> right) {
        super(data, Type.INTEGER);
        add(left);
        add(right);
    }

    @SuppressWarnings("Duplicates")
    public void visit() {
        children.get(0).visit();
        children.get(1).visit();

        NodeType<T> left = (NodeType<T>) children.get(0);
        NodeType<T> right = (NodeType<T>) children.get(1);

        if (!Type.INTEGER.equals(left.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(left.rebuild() + " no es del tipo esperado (integer).");
            System.err.println("Tipo recibido: " + left.type);
            System.err.println();
            Globals.error = true;
        }

        if (!Type.INTEGER.equals(right.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(right.rebuild() + " no es del tipo esperado (integer).");
            System.err.println("Tipo recibido: " + right.type);
            System.err.println();
            Globals.error = true;
        }
    }

    public void compile() {
        // TODO crear codigo intermedio
        Node<T> left = children.get(0);
        Node<T> right = children.get(1);

        left.compile();
        right.compile();
    }

    public String rebuild() {
        Node<T> left = children.get(0);
        Node<T> right = children.get(1);
        return String.format("%s %s %s", left, data, right);
    }
}
