package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Marcador;
import minipascal.util.types.Type;

public class NRepeat<T> extends Node<T> {

    public NRepeat(Node<T> repeatStmnt, Node<T> exprUntil) {
        super((T) "repeat");
        add(repeatStmnt);
        add(exprUntil);
    }

    @SuppressWarnings("Duplicates")
    public void visit() {
        // repeat
        children.get(0).visit();
        // until
        children.get(1).visit();

        NodeType<T> bo = (NodeType<T>) children.get(1);
        if (!Type.BOOLEAN.equals(bo.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(bo.rebuild() + " no es de tipo boolean.");
            System.err.println("Tipo recibido: " + bo.type);
            System.err.println();
            Globals.error = true;
        }
    }

    @SuppressWarnings("Duplicates")
    public void compile() {
        Node<T> repeat = children.get(0);
        Node<T> until = children.get(1);

        Marcador sigCuad = new Marcador(true);
        repeat.compile();
        Marcador sigCuad2 = new Marcador(true);
        until.compile();

        Globals.completar(until.listaF, sigCuad);
        Globals.completar(repeat.listaSig, sigCuad2);
        listaSig = until.listaV;
    }

    public String rebuild() {
        Node<T> until = children.get(1);
        return String.format("repeat ... until %s", until.rebuild());
    }
}
