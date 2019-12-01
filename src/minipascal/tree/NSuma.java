package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Cuadruplo;
import minipascal.util.types.Type;

public class NSuma<T> extends NodeType<T> {

    public NSuma(Node<T> left, T data, Node<T> right) {
        super(data, Type.INTEGER);
        add(left);
        add(right);
    }

    public NSuma(T data, Node<T> term) {
        super(data, Type.INTEGER);
        add(term);
    }

    @SuppressWarnings("Duplicates")
    public void visit() {
        // operador suma
        if (children.size() == 1) {
            // suma unaria (-5)
            children.get(0).visit();

            NodeType<T> num = (NodeType<T>) children.get(0);
            if (!Type.INTEGER.equals(num.type)) {
                System.err.println("ERROR EN: " + this.rebuild());
                System.err.println(num.rebuild() + " no es de tipo esperado (integer).");
                System.err.println("Tipo recibido: " + num.type);
                System.err.println();
                Globals.error = true;
            }
        } else if (children.size() == 2) {
            // suma binaria (5-3)
            children.get(0).visit();
            children.get(1).visit();

            NodeType<T> left = (NodeType<T>) children.get(0);
            NodeType<T> right = (NodeType<T>) children.get(1);

            if (!Type.INTEGER.equals(left.type)) {
                System.err.println("ERROR EN: " + this.rebuild());
                System.err.println(left.rebuild() + " no es de tipo esperado (integer).");
                System.err.println("Tipo recibido: " + left.type);
                System.err.println();
                Globals.error = true;
            }

            if (!Type.INTEGER.equals(right.type)) {
                System.err.println("ERROR EN: " + this.rebuild());
                System.err.println(right.rebuild() + " no es de tipo esperado (integer).");
                System.err.println("Tipo recibido: " + right.type);
                System.err.println();
                Globals.error = true;
            }
        }
    }

    @SuppressWarnings("Duplicates")
    public void compile() {
        place = Globals.temporalNuevo();
        if (children.size() == 1) {
            // suma unaria
            Node<T> num = children.get(0);
            num.compile();
            Globals.cuadruplos.add(new Cuadruplo((String) data, num.place, place));

        } else if (children.size() == 2) {
            // suma binaria
            Node<T> left = children.get(0);
            Node<T> right = children.get(1);

            left.compile();
            right.compile();

            if (left.hasRawInt && right.hasRawInt) {
                // optimizacion para sumas de constantes (2 + 3)
                this.hasRawInt = true;
                int leftInt = Integer.parseInt(left.place);
                int rightInt = Integer.parseInt(right.place);

                if (data.equals("+")) {
                    this.place = Integer.toString(leftInt + rightInt);
                } else {
                    this.place = Integer.toString(leftInt - rightInt);
                }
            } else {
                Globals.cuadruplos.add(new Cuadruplo((String) data, left.place, right.place, place));
            }
        }
    }

    public String rebuild() {
        if (children.size() == 1) {
            Node<T> num = children.get(0);
            return String.format("%s%s", data, num.rebuild());
        } else {
            Node<T> left = children.get(0);
            Node<T> right = children.get(1);
            return String.format("%s %s %s", left.rebuild(), data, right.rebuild());
        }
    }
}
