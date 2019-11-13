package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.TFunc;

public class NFuncCall<T> extends NodeType<T> {

    public NFuncCall(T data, Node<T> exprList) {
        super(data, null);
        add(exprList);
    }

    public void visit() {
        // llamado de funcion
        String funcName = (String) data;
        if (!Globals.funciones.containsKey(funcName)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println("El identificador  \"" + data + "\" no hace referencia a una funcion.");
            System.err.println();
            Globals.error = true;

            // visitar children y volver
            if (children.get(0) != null) {
                Node<T> exprList = children.get(0);
                for (Node<T> child : exprList.children) {
                    child.visit();
                }
            }
            return;
        }

        TFunc candidato = Globals.funciones.get(funcName);
        TFunc nuevo = new TFunc(funcName, candidato.returnType);
        if (children.get(0) != null) {
            // expresiones enviadas a la funcion como argumentos
            Node<T> exprList = children.get(0);
            for (Node<T> child : exprList.children) {
                child.visit();
                nuevo.addArg(((NodeType<T>) child).type);
            }
        }
        if (!candidato.equals(nuevo)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println("El llamado a funcion \"" + data + "\" no concuerda con el esperado.");
            System.err.println("Se obtuvo: " + nuevo);
            System.err.println("Se esperaba: " + candidato);
            System.err.println();
            return;
        }
        // el llamado es valido
        type = candidato.returnType;
    }

    public void compile() {
        // TODO crear codigo intermedio
        Node<T> exprList = children.get(0);
        if (exprList != null) {
            // expresiones enviadas a la funcion como argumentos
            for (Node<T> child : exprList.children) {
                child.compile();
            }
        }
    }

    public String rebuild() {
        Node<T> exprList = children.get(0);
        String id = (String) data;

        if (exprList == null) {
            return String.format("%s()", id);
        } else {
            return String.format("%s(%s)", id, exprList.rebuild());
        }
    }
}
