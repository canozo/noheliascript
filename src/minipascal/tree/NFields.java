package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.TRecord;

public class NFields<T> extends Node<T> {

    public NFields(Node<T> type, Node<T> ids) {
        super((T) "fields");
        type.add(ids);
        add(type);
    }

    public void visit() {
        // info para el record
        String recordName = (String) parent.children.get(0).data;
        TRecord recType = new TRecord(recordName);

        // agregar todos los argumentos al record
        for (Node<T> child : children) {
            // cada child es un NType (no puede ser NNewType)
            String type = (String) child.data;

            // el nodo contiene todos los fields (pueden ser muchos)
            for (Node<T> arg : child.children) {
                if (arg.children.size() > 1) {
                    // tiene varios args de este tipo
                    for (Node<T> innerArg : arg.children) {
                        String id = (String) innerArg.data;
                        recType.addField(id, type);
                    }
                } else {
                    // solo tiene un arg de este tipo
                    String id = (String) arg.data;
                    recType.addField(id, type);
                }
            }
        }
        Globals.addRecord(recordName, recType);
    }

    public void compile() {
        // TODO crear codigo intermedio
    }

    public String rebuild() {
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
