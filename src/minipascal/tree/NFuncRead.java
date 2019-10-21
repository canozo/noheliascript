package minipascal.tree;

public class NFuncRead<T> extends Node<T> {

    public NFuncRead(Node<T> var) {
        super((T) "read");
        add(var);
    }

    public void visit() {
        System.out.println("Begin Read Function:");
        children.get(0).visit();
        System.out.println("End Read Function.");
    }
}
