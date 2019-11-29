package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Marcador;
import minipascal.util.types.Type;

public class NAnd<T> extends NodeType<T> {

    public NAnd(Node<T> left, Node<T> right) {
        super((T) "and", Type.BOOLEAN);
        add(left);
        add(right);
    }

    @SuppressWarnings("Duplicates")
    public void visit() {
        // operador and
        children.get(0).visit();
        children.get(1).visit();

        NodeType<T> left = (NodeType<T>) children.get(0);
        NodeType<T> right = (NodeType<T>) children.get(1);

        if (!Type.BOOLEAN.equals(left.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(left.rebuild() + " no es de tipo esperado (boolean).");
            System.err.println("Tipo recibido: " + left.type);
            System.err.println();
            Globals.error = true;
        }

        if (!Type.BOOLEAN.equals(right.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(right.rebuild() + " no es de tipo esperado (boolean).");
            System.err.println("Tipo recibido: " + right.type);
            System.err.println();
            Globals.error = true;
        }
    }

    @SuppressWarnings("Duplicates")
    public void compile() {
        Node<T> left = children.get(0);
        Node<T> right = children.get(1);

        left.compile();
        Marcador sigCuad = new Marcador(true);
        right.compile();

        Globals.completar(left.listaV, sigCuad);
        listaV = right.listaV;
        listaF = Globals.fusionar(left.listaF, right.listaF);
    }

    public String rebuild() {
        Node<T> left = children.get(0);
        Node<T> right = children.get(1);
        return String.format("%s and %s", left.rebuild(), right.rebuild());
    }
}
