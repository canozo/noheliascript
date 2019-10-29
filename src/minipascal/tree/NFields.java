package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.TRecord;
import minipascal.util.types.TVar;

public class NFields<T> extends Node<T> {

    public NFields(Node<T> type, Node<T> ids) {
        super((T) "fields");
        type.add(ids);
        add(type);
    }

    public void visit() {
        // info para tipo de la funcion
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
                        recType.addField(id, new TVar(type));
                    }
                } else {
                    // solo tiene un arg de este tipo
                    String id = (String) arg.data;
                    recType.addField(id, new TVar(type));
                }
            }
        }
        Globals.addRecord(recordName, recType);
        Globals.addSimbolo(recordName, 0, recType);
    }
}
