package minipascal.tree;

public class NFuncWrite<T> extends Node<T> {

    public NFuncWrite(Node<T> string) {
        super((T) "write");
        add(string);
    }

    public NFuncWrite(Node<T> string, Node<T> var) {
        super((T) "write");
        add(string);
        add(var);
    }

    public void visit() {
        // TODO verificar que el tipo de var es integer o char
        // funcion especial write
        // primer parametro string
        children.get(0).visit();
        if (children.size() > 1) {
            // segundo parametro var
            children.get(1).visit();
        }
    }
}
