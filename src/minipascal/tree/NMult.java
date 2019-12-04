package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Cuadruplo;
import minipascal.util.types.Type;

public class NMult<T> extends NodeType<T> {

    public NMult(Node<T> left, T data, Node<T> right) {
        super(data, Type.INTEGER);
        add(left);
        add(right);
    }

    public void visit() {
        children.get(0).visit();
        children.get(1).visit();

        NodeType<T> left = (NodeType<T>) children.get(0);
        NodeType<T> right = (NodeType<T>) children.get(1);

        if (!Type.INTEGER.equals(left.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(left.rebuild() + " no es del tipo esperado (integer).");
            System.err.println("Tipo recibido: " + left.type);
            System.err.println();
            Globals.error = true;
        }

        if (!Type.INTEGER.equals(right.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(right.rebuild() + " no es del tipo esperado (integer).");
            System.err.println("Tipo recibido: " + right.type);
            System.err.println();
            Globals.error = true;
        }
    }

    public void compile() {
        place = Globals.temporalNuevo();

        Node<T> left = children.get(0);
        Node<T> right = children.get(1);

        left.compile();
        right.compile();

        if (left.hasRawInt && right.hasRawInt) {
            // optimizacion para multiplicacion de constantes (2 * 3)
            this.hasRawInt = true;
            int leftInt = Integer.parseInt(left.place);
            int rightInt = Integer.parseInt(right.place);

            if (data.equals("*")) {
                this.place = Integer.toString(leftInt * rightInt);
            } else if (data.equals("/")) {
                this.place = Integer.toString(leftInt / rightInt);
            } else if (data.equals("div")) {
                this.place = Integer.toString(leftInt / rightInt);
            } else if (data.equals("mod")) {
                this.place = Integer.toString(leftInt % rightInt);
            }
        } else {
            Globals.cuadruplos.add(new Cuadruplo((String) data, left.place, right.place, place));
        }
    }

    public String rebuild() {
        Node<T> left = children.get(0);
        Node<T> right = children.get(1);
        return String.format("%s %s %s", left.rebuild(), data, right.rebuild());
    }
}
