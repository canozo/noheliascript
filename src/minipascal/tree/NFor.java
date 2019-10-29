package minipascal.tree;

public class NFor<T> extends Node<T> {

    public NFor(Node<T> asign, Node<T> toFactor, Node<T> doStmnt) {
        super((T) "for");
        add(asign);
        add(toFactor);
        add(doStmnt);
    }

    public void visit() {
        // TODO ver que el tipo de asign y to factor es integer, o que esten en la tabla de simbolos
        // for loop:
        // asign:
        children.get(0).visit();
        // to:
        children.get(1).visit();
        // statements:
        children.get(2).visit();
    }
}
