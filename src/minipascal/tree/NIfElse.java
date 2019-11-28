package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Cuadruplo;
import minipascal.util.cuadruplo.Marcador;
import minipascal.util.types.Type;

import java.util.List;

public class NIfElse<T> extends Node<T> {

    public NIfElse(Node<T> ifExpr, Node<T> thenStmnt) {
        super((T) "if");
        add(ifExpr);
        add(thenStmnt);
    }

    public NIfElse(Node<T> ifExpr, Node<T> thenStmnt, Node<T> elseStmnt) {
        super((T) "if");
        add(ifExpr);
        add(thenStmnt);
        add(elseStmnt);
    }

    public void visit() {
        // if
        children.get(0).visit();
        // then
        children.get(1).visit();
        if (children.size() > 2) {
            // else
            children.get(2).visit();
        }

        NodeType<T> bo = (NodeType<T>) children.get(0);
        if (!Type.BOOLEAN.equals(bo.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println("La expresion " + bo.rebuild() + " no es de tipo boolean.");
            System.err.println();
            Globals.error = true;
        }
    }

    @SuppressWarnings("Duplicates")
    public void compile() {
        if (children.size() == 2) {
            Node<T> nIf = children.get(0);
            Node<T> then = children.get(1);

            nIf.compile();
            Marcador sigCuad = new Marcador(true);
            then.compile();

            Globals.completar(nIf.listaV, sigCuad);
            listaSig = Globals.fusionar(nIf.listaF, then.listaSig);
        } else {
            Node<T> nIf = children.get(0);
            Node<T> then = children.get(1);
            Node<T> nElse = children.get(2);

            nIf.compile();
            Marcador sigCuad = new Marcador(true);
            then.compile();

            List<Marcador> listaN = Globals.crearLista(new Marcador(false));
            Globals.cuadruplos.add(new Cuadruplo("goto", "null"));
            Marcador sigCuad2 = new Marcador(true);

            nElse.compile();
            Globals.completar(nIf.listaV, sigCuad);
            Globals.completar(nIf.listaF, sigCuad2);
            List<Marcador> temp = Globals.fusionar(then.listaSig, nElse.listaSig);
            listaSig = Globals.fusionar(temp, listaN);
        }
    }

    public String rebuild() {
        Node<T> ifExpr = children.get(0);
        return String.format("if %s then ...", ifExpr.rebuild());
    }
}
