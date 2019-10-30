package minipascal.tree;

import minipascal.util.types.Type;

public class NAnd<T> extends NodeType<T> {

    public NAnd(Node<T> left, Node<T> right) {
        super((T) "and", Type.BOOLEAN);
        add(left);
        add(right);
    }

    public void visit() {
        // TODO verificar que los tipos sean boolean
        children.get(0).visit();
        children.get(1).visit();
    }
}
