package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NNot<T> extends NodeType<T> {

    public NNot(Node<T> some) {
        super((T) "not", Type.BOOLEAN);
        add(some);
    }

    public void visit() {
        // operador not
        children.get(0).visit();

        NodeType expr = (NodeType) children.get(0);
        if (!Type.BOOLEAN.equals(expr.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(expr + "> en <OP NOT> no es de tipo esperado (boolean).");
            System.err.println("Tipo recibido: " + expr.type);
            System.err.println();
            Globals.error = true;
        }
    }

    public String rebuild() {
        // TODO revisar el not y not()
        Node<T> boolExpr = children.get(0);
        return String.format("not %s", boolExpr.rebuild());
    }
}
