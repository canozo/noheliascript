package minipascal.tree;

import minipascal.util.Globals;

public class NAssign<T> extends Node<T> {

    public NAssign(Node<T> var, Node<T> expr) {
        super((T) ":=");
        add(var);
        add(expr);
    }

    public void visit() {
        // tiene un var o un var.var
        children.get(0).visit();
        children.get(1).visit();

        NodeType left = (NodeType) children.get(0);
        NodeType right = (NodeType) children.get(1);

        // Puede ser record field = algo, variable = algo, funcion = algo
        if (left.type == null) {
            // ERROR PROPAGADO
            return;
        }

        if (!left.type.equals(right.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println("Se esperaban tipos iguales en la expresion.");
            System.err.println("Tipos recibidos: <" + left.type + "> vs <" + right.type + ">");
            System.err.println();
            Globals.error = true;
        }
    }

    public String rebuild() {
        Node<T> left = children.get(0);
        Node<T> right = children.get(1);
        return String.format("%s := %s", left.rebuild(), right.rebuild());
    }
}

