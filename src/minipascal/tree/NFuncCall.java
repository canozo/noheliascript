package minipascal.tree;

public class NFuncCall<T> extends NodeType<T> {

    public NFuncCall(T data, Node<T> exprList) {
        super(data, null);
        add(exprList);
    }

    public void visit() {
        // TODO buscar si la funcion esta definida
        // llamado de funcion
        if (children.get(0) != null) {
            // expresiones enviadas a la funcion como argumentos
            for (Node<T> child : children) {
                // TODO si esta definida, guardar el candidato y verificar que todos sus args se enviaron correctamente
                child.visit();
            }
        }
        // TODO setear el tipo de la funcion
    }
}
