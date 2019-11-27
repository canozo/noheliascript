package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Cuadruplo;
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
        if (children.get(2) != null) {
            children.get(2).visit();
        }

        NodeType<T> to = (NodeType<T>) children.get(1);
        if (!Type.INTEGER.equals(to.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println("La expresion " + to.rebuild() + " no es de tipo integer.");
            System.err.println("Tipo de la expresion: <" + to.type + ">");
            System.err.println();
            Globals.error = true;
        }
    }

    @SuppressWarnings("Duplicates")
    public void compile() {
        Node<T> asign = children.get(0);
        Node<T> to = children.get(1);
        Node<T> maybeStmnt = children.get(2);

        asign.compile();
        to.compile();

        int comparacion = Globals.cuadruplos.size() + 1;
        listaV = Globals.crearLista(comparacion);
        listaF = Globals.crearLista(Globals.cuadruplos.size() + 2);

        Globals.cuadruplos.add(new Cuadruplo("if= goto", asign.place, to.place, null));
        Globals.cuadruplos.add(new Cuadruplo("goto", null));

        Globals.completar(listaV, Globals.cuadruplos.size() + 1);

        if (maybeStmnt != null) {
            maybeStmnt.compile();
            Globals.completar(maybeStmnt.listaSig, comparacion);
        }

        Globals.cuadruplos.add(new Cuadruplo("goto", Integer.toString(comparacion)));
        Globals.completar(listaF, Globals.cuadruplos.size() + 1);

        listaSig = listaF;
    }

    public String rebuild() {
        Node<T> assign = children.get(0);
        Node<T> toFactor = children.get(1);

        // for c := 10 to 20 do
        return String.format("for %s to %s do ...", assign.rebuild(), toFactor.rebuild());
    }
}
