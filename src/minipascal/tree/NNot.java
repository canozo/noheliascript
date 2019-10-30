package minipascal.tree;

import minipascal.util.types.Type;

public class NNot<T> extends NodeType<T> {

    public NNot(Node<T> some) {
        super((T) "not", Type.BOOLEAN);
        add(some);
    }

    public void visit() {
        // operador not
        // TODO verificar que el tipo es boolean
        children.get(0).visit();
    }
}
