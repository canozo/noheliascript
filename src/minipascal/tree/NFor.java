package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NFor<T> extends Node<T> {

    public NFor(Node<T> asign, Node<T> toFactor, Node<T> doStmnt) {
        super((T) "for");
        add(asign);
        add(toFactor);
        add(doStmnt);
    }

    public void visit() {
        // for loop:
        // asign:
        children.get(0).visit();
        // to:
        children.get(1).visit();
        // statements:
        children.get(2).visit();

        NodeType to = (NodeType) children.get(1);
        if (!Type.INTEGER.equals(to.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println("La expresion " + to.rebuild() + " no es de tipo integer.");
            System.err.println("Tipo de la expresion: <" + to.type + ">");
            System.err.println();
            Globals.error = true;
        }
    }

    public String rebuild() {
        Node<T> assign = children.get(0);
        Node<T> toFactor = children.get(1);

        // for c := 10 to 20 do
        return String.format("for %s to %s do ...", assign.rebuild(), toFactor.rebuild());
    }
}
