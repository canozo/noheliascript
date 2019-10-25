package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.TVar;

public class NVars<T> extends Node<T> {

    public NVars(Node<T> type, Node<T> ids) {
        super((T) "vars");
        type.add(ids);
        add(type);
    }

    public void visit() {
        // agregar todos los argumentos a la tabla de simbolos
        for (Node<T> child : children) {
            // cada child es un NType o NNewType
            String type = (String) child.data;

            // si es NNewType, verificar que el tipo existe
            if (child instanceof NNewType) {
                child.visit();
            }

            // el nodo contiene todos los args (pueden ser muchos)
            for (Node<T> arg : child.children) {
                if (arg.children.size() > 1) {
                    // tiene varios args de este tipo
                    for (Node<T> innerArg : arg.children) {
                        String id = (String) innerArg .data;
                        Globals.addSimbolo(id, new TVar(type));
                    }
                } else {
                    // solo tiene un arg de este tipo
                    String id = (String) arg.data;
                    Globals.addSimbolo(id, new TVar(type));
                }
            }
        }
    }
}
