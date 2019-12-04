package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Cuadruplo;
import minipascal.util.cuadruplo.Marcador;
import minipascal.util.types.TFunc;

public class NProcedure<T> extends Node<T> {

    public NProcedure(Node<T> id, Node<T> maybeArgs, Node<T> maybeVars, Node<T> maybeStmntList) {
        super((T) "procedure");
        add(id);
        add(new NType("\"void\""));
        add(maybeArgs);
        add(maybeVars);
        add(maybeStmntList);
    }

    public void visit() {
        // con cada llamado, hay un nuevo ambito (de funcion o procedure)
        // antes de visitar los argumentos o las variables locales de la funcion,
        // cambiamos el id del ambito
        Globals.ambito += 1;

        // agregar nombre del ambito
        Globals.addNombreAmbito((String) children.get(0).data);

        // agregar a la tabla de simbolos:
        // nombre de la funcion con su tipo (argumentos)
        // argumentos de la funcion con su tipo
        if (children.get(2) != null) {
            children.get(2).visit();
        } else {
            // agregar el procedimiento a la tabla de simbolos
            String funcName = (String) children.get(0).data;
            String retType = (String) children.get(1).data;
            retType = retType.substring(1, retType.length() - 1);
            Globals.addFuncion(funcName, new TFunc(funcName, retType));
            Globals.addSimbolo(funcName, retType);
        }

        // variables locales de la funcion con su tipo
        if (children.get(3) != null) {
            children.get(3).visit();
        }

        // statements de la funcion
        if (children.get(4) != null) {
            children.get(4).visit();
        }
    }

    public void compile() {
        Globals.ambito += 1;

        Node<T> funcId = children.get(0);
        Node<T> maybeArgs = children.get(2);
        Node<T> maybeVars = children.get(3);
        Node<T> maybeStmntList = children.get(4);

        funcId.compile();

        Globals.cuadruplos.add(new Cuadruplo("init_func", funcId.place));

        if (maybeArgs != null) {
            maybeArgs.compile();
        }

        if (maybeVars != null) {
            maybeVars.compile();
        }

        if (maybeStmntList != null) {
            maybeStmntList.compile();

            // completar los que quedan al final del procedimiento a una linea vacia
            Marcador sigCuad = new Marcador(true);
            Globals.cuadruplos.add(new Cuadruplo("end_func", funcId.place));
            Globals.completar(maybeStmntList.listaSig, sigCuad);
        } else {
            Globals.cuadruplos.add(new Cuadruplo("end_func", funcId.place));
        }
    }

    public String rebuild() {
        // no es necesario el rebuild
        return "NProcedure";
    }
}
