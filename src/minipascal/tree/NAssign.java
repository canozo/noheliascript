package minipascal.tree;

public class NAssign<T> extends Node<T> {

    public NAssign(Node<T> id, Node<T> expr) {
        super((T) ":=");
        add(id);
        add(expr);
    }

    public void visit() {
        System.out.println("Begin Assign: ");
        children.get(0).visit();
        System.out.println(data.toString());
        children.get(1).visit();
        System.out.println("End Assign.");
    }
}
