package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NWhile<T> extends Node<T> {

    public NWhile(Node<T> whileExpr, Node<T> doStmnt) {
        super((T) "while");
        add(whileExpr);
        add(doStmnt);
    }

    @SuppressWarnings("Duplicates")
    public void visit() {
        // while
        children.get(0).visit();
        // do
        children.get(1).visit();

        NodeType<T> bo = (NodeType<T>) children.get(0);
        if (!Type.BOOLEAN.equals(bo.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(bo.rebuild() + " no es de tipo boolean.");
            System.err.println("Tipo recibido: " + bo.type);
            System.err.println();
            Globals.error = true;
        }
    }

    public void compile() {
        // TODO crear codigo intermedio
        Node<T> nWhile = children.get(0);
        Node<T> nDo = children.get(1);

        nWhile.compile();
        nDo.compile();
    }

    public String rebuild() {
        Node<T> whileExpr = children.get(0);
        return String.format("while %s do ...", whileExpr.rebuild());
    }
}
