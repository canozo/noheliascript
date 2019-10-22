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
        // argumentos de la funcion con su tipo (*)
        // variables locales de la funcion con su tipo

        String id = (String) children.get(0).data;

//        Globals.addSimbolo(id, );
        System.out.println("Return type:");
        children.get(1).visit();
        if (children.get(2) != null) {
            System.out.println("Function args:");
            children.get(2).visit();
        }
        if (children.get(3) != null) {
            System.out.println("Function vars:");
            children.get(3).visit();
        }
        if (children.get(4) != null) {
            System.out.println("Function statements:");
            children.get(4).visit();
        }
        System.out.println("End.");
    }
}
