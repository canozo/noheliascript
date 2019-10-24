package minipascal.tree;

import minipascal.util.Globals;

public class NFunction<T> extends Node<T> {

    public NFunction(Node<T> id, Node<T> returnType, Node<T> maybeArgs, Node<T> maybeVars, Node<T> maybeStmntList) {
        super((T) "function");
        add(id);
        add(returnType);
        add(maybeArgs);
        add(maybeVars);
        add(maybeStmntList);
    }

    public void visit() {
        // con cada llamado, hay un nuevo ambito (de funcion o procedure)
        // antes de visitar los argumentos o las variables locales de la funcion,
        // cambiamos el id del ambito
        Globals.ambito += 1;

        // agregar a la tabla de simbolos:
        // nombre de la funcion con su tipo

        // return type
//        children.get(1).visit();
//        String id = (String) children.get(0).data;
//        Globals.addSimbolo(id, new Type());

        // argumentos de la funcion con su tipo
        if (children.get(2) != null) {
            children.get(2).visit();
        }

        // variables locales de la funcion con su tipo
        if (children.get(3) != null) {
            children.get(3).visit();
        }

        // statements de la funcion
//        if (children.get(4) != null) {
//            children.get(4).visit();
//        }
    }
}
