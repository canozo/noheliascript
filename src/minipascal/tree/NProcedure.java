package minipascal.tree;

import minipascal.util.Globals;
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

    @SuppressWarnings("Duplicates")
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
    }

    public String rebuild() {
        // no es necesario el rebuild
        return "NProcedure";
    }
}
