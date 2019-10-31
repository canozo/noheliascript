package minipascal.tree;

import minipascal.util.Globals;

public class NAssign<T> extends Node<T> {

    public NAssign(Node<T> var, Node<T> expr) {
        super((T) ":=");
        add(var);
        add(expr);
    }

    public void visit() {
        // tiene un var o un var.var
        children.get(0).visit();
        children.get(1).visit();
/*
        if (children.get(0) instanceof NID) {
            if (!(((NID) children.get(0)).type).equals(((NodeType)children.get(1)).type) ){
                System.err.println("Los tipos no concuerdan: " + ((NID) children.get(0)).type + " y " + ((NodeType)children.get(1)).type);
                Globals.error = true;
            }

        } else if (children.get(0) instanceof NRecordField) {
            System.out.println(((NodeType)children.get(1)).type + " eeeeeh");

        }*/
    }
}

