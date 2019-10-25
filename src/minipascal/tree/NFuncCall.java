package minipascal.tree;

public class NFuncCall<T> extends Node<T> {

    public NFuncCall(T data, Node<T> exprList) {
        super(data);
        add(exprList);
    }

    public void visit() {
        // llamado de funcion
        if (children.get(0) != null) {
            // expresiones enviadas a la funcion como argumentos
            for (Node<T> child : children) {
                child.visit();
            }
        }
    }
}
