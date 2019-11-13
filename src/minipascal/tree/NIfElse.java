package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NIfElse<T> extends Node<T> {

    public NIfElse(Node<T> ifExpr, Node<T> thenStmnt) {
        super((T) "if");
        add(ifExpr);
        add(thenStmnt);
    }

    public NIfElse(Node<T> ifExpr, Node<T> thenStmnt, Node<T> elseStmnt) {
        super((T) "if");
        add(ifExpr);
        add(thenStmnt);
        add(elseStmnt);
    }

    public void visit() {
        // if
        children.get(0).visit();
        // then
        children.get(1).visit();
        if (children.size() > 2) {
            // else
            children.get(2).visit();
        }

        NodeType<T> bo = (NodeType<T>) children.get(0);
        if (!Type.BOOLEAN.equals(bo.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println("La expresion " + bo.rebuild() + " no es de tipo boolean.");
            System.err.println();
            Globals.error = true;
        }
    }

    public void compile() {
        // TODO crear codigo intermedio
        Node<T> nIf = children.get(0);
        Node<T> then = children.get(1);
        Node<T> nElse;

        nIf.compile();
        then.compile();
        if (children.size() > 2) {
            nElse = children.get(2);
            nElse.compile();
        }
    }

    public String rebuild() {
        Node<T> ifExpr = children.get(0);
        return String.format("if %s then ...", ifExpr.rebuild());
    }
}
