package minipascal.tree;

import minipascal.util.Globals;

public class NVars<T> extends Node<T> {

    public boolean globalBlock = false;

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
                        String id = (String) innerArg.data;
                        if (globalBlock) {
                            Globals.addSimboloGlobal(id, type);
                        } else {
                            Globals.addSimbolo(id, type);
                        }
                    }
                } else {
                    // solo tiene un arg de este tipo
                    String id = (String) arg.data;
                    if (globalBlock) {
                        Globals.addSimboloGlobal(id, type);
                    } else {
                        Globals.addSimbolo(id, type);
                    }
                }
            }
        }
    }

    @SuppressWarnings("Duplicates")
    public String rebuild() {
        // tal vez no es necesario
        StringBuilder builder = new StringBuilder();
        boolean firstType = true;
        for (Node<T> child : children) {
            // cada child es un NType o NNewType
            String type = (String) child.data;

            if (firstType) {
                builder.append(String.format("%s: ", type));
                firstType = false;
            } else {
                builder.append(String.format("; %s: ", type));
            }

            // el nodo contiene todos los args (pueden ser muchos)
            for (Node<T> arg : child.children) {
                if (arg.children.size() > 1) {
                    // tiene varios args de este tipo
                    boolean firstArg = true;
                    for (Node<T> innerArg : arg.children) {
                        String id = (String) innerArg.data;
                        if (firstArg) {
                            firstArg = false;
                            builder.append(String.format("%s ", id));
                        } else {
                            builder.append(String.format(", %s", id));
                        }
                    }
                } else {
                    // solo tiene un arg de este tipo
                    String id = (String) arg.data;
                    builder.append(id);
                }
            }
        }
        return builder.toString();
    }
}
