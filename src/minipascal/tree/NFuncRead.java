package minipascal.tree;

public class NFuncRead<T> extends Node<T> {

    public NFuncRead(Node<T> var) {
        super((T) "read");
        add(var);
    }

    public void visit() {
        // funcion especial read
        children.get(0).visit();
    }
}
