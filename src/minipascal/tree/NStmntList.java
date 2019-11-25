package minipascal.tree;

import minipascal.util.Globals;

public class NStmntList<T> extends Node<T> {

    public NStmntList(Node<T> expr) {
        super((T) "stmnt_list");
        add(expr);
    }

    public void visit() {
        // lista de statements
        for (Node<T> child : children) {
            child.visit();
        }
    }

    public void compile() {
        int sigCuad;
        Node<T> anterior;

        if (children.size() > 0) {
            Node<T> primero = children.get(0);
            primero.compile();
            anterior = primero;

            for (int i = 1; i < children.size(); i += 1) {
                Node<T> child = children.get(i);
                sigCuad = Globals.cuadruplos.size() + 1;
                child.compile();
                Globals.completar(anterior.listaSig, sigCuad);
                anterior = child;
            }
            listaSig = anterior.listaSig;
        }
    }

    public String rebuild() {
        // no es necesario el rebuild
        return "NStmntList";
    }
}
