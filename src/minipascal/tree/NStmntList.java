package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Marcador;

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
        Node<T> anterior;

        if (children.size() > 0) {
            Node<T> primero = children.get(0);
            primero.compile();
            anterior = primero;

            for (int i = 1; i < children.size(); i += 1) {
                Node<T> child = children.get(i);
                Marcador sigCuad = new Marcador(true);
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
