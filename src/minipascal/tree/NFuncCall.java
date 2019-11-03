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
            System.err.println("ERROR: El identificador  \"" + data + "\" no hace referencia a una funcion.");
            Globals.error = true;

            // visitar children y volver
            for (Node<T> child : children) {
                child.visit();
            }
            return;
        }

        // TODO ver si sirve
        TFunc candidato = Globals.funciones.get(funcName);
        TFunc nuevo = new TFunc(funcName, candidato.returnType);
        if (children.get(0) != null) {
            // expresiones enviadas a la funcion como argumentos
            Node<T> exprList = children.get(0);
            for (Node<T> child : exprList.children) {
                child.visit();
                nuevo.addArg(((NodeType) child).type);
            }
        }
        if (!candidato.equals(nuevo)) {
            System.err.println("ERROR: El llamado a funcion \"" + data + "\" no concuerda con el esperado.");
            System.err.println("Se obtuvo: <" + nuevo + ">, se esperaba: <" + candidato + ">");
            return;
        }
        // el llamado es valido
        type = candidato.returnType;
    }
}
