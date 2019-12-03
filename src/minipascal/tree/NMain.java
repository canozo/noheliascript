package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Cuadruplo;
import minipascal.util.cuadruplo.Marcador;

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
        Globals.ambito = 0;

        Globals.cuadruplos.add(new Cuadruplo("init_main"));

        if (stmntList != null) {
            stmntList.compile();

            // completar los que quedan al final del main a una linea vacia
            Marcador sigCuad = new Marcador(true);
            Globals.cuadruplos.add(new Cuadruplo("end_main"));
            Globals.completar(stmntList.listaSig, sigCuad);
        } else {
            Globals.cuadruplos.add(new Cuadruplo("end_main"));
        }
    }

    public String rebuild() {
        // no es necesario el rebuild
        return "NMain";
    }
}
