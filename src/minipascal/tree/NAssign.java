package minipascal.tree;

public class NAssign<T> extends Node<T> {

    public NAssign(Node<T> id, Node<T> expr) {
        super((T) ":=");
        add(id);
        add(expr);
    }

    public void visit() {
        // TODO revisar que el tipo de ID es el mismo de la expresion
    }
}
