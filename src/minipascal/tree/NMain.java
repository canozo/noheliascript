package minipascal.tree;

import minipascal.util.Globals;

public class NMain<T> extends Node<T> {

    public NMain(Node<T> stmntList) {
        super((T) "main");
        add(stmntList);
    }

    public void visit() {
        // statements del main
        // XXX chanchada
        // cuando se visita el main, cambiamos el ambito a las variables del main denuevo
        Globals.ambito = 0;
        if (children.get(0) != null) {
            children.get(0).visit();
        }
    }

    public String rebuild() {
        // no es necesario el rebuild
        return "NMain";
    }
}
