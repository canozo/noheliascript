package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.TFunc;

public class NArgs<T> extends Node<T> {

    public NArgs(Node<T> type, Node<T> ids) {
        super((T) "args");
        type.add(ids);
        add(type);
    }

    public void visit() {
        // info para tipo de la funcion
        String funcName = (String) parent.children.get(0).data;
        String retType = (String) parent.children.get(1).data;
        retType = retType.substring(1, retType.length() - 1);
        TFunc funcType = new TFunc(funcName, retType);

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
                        Globals.addSimbolo(id, type);
                        funcType.addArg(type);
                    }
                } else {
                    // solo tiene un arg de este tipo
                    String id = (String) arg.data;
                    Globals.addSimbolo(id, type);
                    funcType.addArg(type);
                }
            }
        }
        Globals.addFuncion(funcName, funcType);
        Globals.addSimbolo(funcName, retType);
    }

    public void compile() {
        // no ocupamos hacer nada
    }

    public String rebuild() {
        // tal vez no es necesario
        StringBuilder buffer = new StringBuilder();
        boolean firstType = true;
        for (Node<T> child : children) {
            // cada child es un NType o NNewType
            String type = (String) child.data;

            if (firstType) {
                buffer.append(String.format("%s: ", type));
                firstType = false;
            } else {
                buffer.append(String.format("; %s: ", type));
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
                            buffer.append(String.format("%s ", id));
                        } else {
                            buffer.append(String.format(", %s", id));
                        }
                    }
                } else {
                    // solo tiene un arg de este tipo
                    String id = (String) arg.data;
                    buffer.append(id);
                }
            }
        }
        return buffer.toString();
    }
}
