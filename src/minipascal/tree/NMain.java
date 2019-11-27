package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Cuadruplo;

public class NMain<T> extends Node<T> {

    public NMain(Node<T> stmntList) {
        super((T) "main");
        add(stmntList);
    }

    public void visit() {
        // statements del main
        // cuando se visita el main, cambiamos el ambito a las variables globales
        Globals.ambito = 0;
        if (children.get(0) != null) {
            children.get(0).visit();
        }
    }

    public void compile() {
        Node<T> stmntList = children.get(0);
        if (stmntList != null) {
            stmntList.compile();

            // completar los que quedan al final del main a una linea vacia
            int sigCuad = Globals.cuadruplos.size() + 1;
            Globals.cuadruplos.add(new Cuadruplo());
            Globals.completar(stmntList.listaSig, sigCuad);
        }
    }

    public String rebuild() {
        // no es necesario el rebuild
        return "NMain";
    }
}
