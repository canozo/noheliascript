package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NNot<T> extends NodeType<T> {

    public NNot(Node<T> some) {
        super((T) "not", Type.BOOLEAN);
        add(some);
    }

    @SuppressWarnings("Duplicates")
    public void visit() {
        // operador not
        children.get(0).visit();

        NodeType<T> expr = (NodeType<T>) children.get(0);
        if (!Type.BOOLEAN.equals(expr.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(expr.rebuild() + " no es de tipo esperado (boolean).");
            System.err.println("Tipo recibido: " + expr.type);
            System.err.println();
            Globals.error = true;
        }
    }

    public void compile() {
        // TODO crear codigo intermedio
        Node<T> expr = children.get(0);

        expr.compile();
    }

    public String rebuild() {
        Node<T> boolExpr = children.get(0);
        return String.format("not(%s)", boolExpr.rebuild());
    }
}
