package minipascal.tree;

public class NOpRel<T> extends Node<T> {

    public NOpRel(Node<T> left, T data, Node<T> right) {
        super(data);
        add(left);
        add(right);
    }

    public void visit() {
        System.out.println("Begin OpRel:");
        children.get(0).visit();
        System.out.println("Operador: " + data.toString());
        children.get(1).visit();
        System.out.println("End OpRel.");
    }
}
