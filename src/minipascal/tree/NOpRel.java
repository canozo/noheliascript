package minipascal.tree;

public class NOpRel<T> extends Node<T> {

    public NOpRel(Node<T> left, T data, Node<T> right) {
        super(data);
        add(left);
        add(right);
    }

    public void visit() {
        // operador relacional (data)
        children.get(0).visit();
        children.get(1).visit();
    }
}
