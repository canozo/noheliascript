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
            System.err.println("ERROR PROPAGADO: Se recibio tipo 'null' en <OP ASIGNACION '" + data + "'>.");
            System.err.println("Tipos recibido: <" + left.type + "> <OP ASIGNACION '" + data + "'> <" + right.type + ">");
            return;
        }

        if (!left.type.equals(right.type)) {
            System.err.println("ERROR: Se esperaban tipos iguales en <OP ASIGNACION '" + data + "'>.");
            System.err.println("Tipos recibido: <" + left.type + "> vs <" + right.type + ">");
            Globals.error = true;
        }
    }
}

