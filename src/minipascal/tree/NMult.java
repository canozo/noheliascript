package minipascal.tree;

import minipascal.util.types.Type;

public class NMult<T> extends NodeType<T> {

    public NMult(Node<T> left, T data, Node<T> right) {
        super(data, Type.INTEGER);
        add(left);
        add(right);
    }

    public void visit() {
        // TODO verificar que los tipos sean integer
        children.get(0).visit();
        children.get(1).visit();
    }
}
