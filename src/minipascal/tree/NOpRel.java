package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.cuadruplo.Cuadruplo;
import minipascal.util.cuadruplo.Marcador;
import minipascal.util.types.Type;

public class NOpRel<T> extends NodeType<T> {

    public NOpRel(Node<T> left, T data, Node<T> right) {
        super(data, Type.BOOLEAN);
        add(left);
        add(right);
    }

    public void visit() {
        // operador relacional (data)
        children.get(0).visit();
        children.get(1).visit();

        NodeType<T> left = (NodeType<T>) children.get(0);
        NodeType<T> right = (NodeType<T>) children.get(1);

        String op = (String) data;
        if (op.equals("=") || op.equals("<>")) {
            // ambos tipos deben de ser iguales (char, integer o boolean)
            if (left.type == null || right.type == null) {
                // ERROR PROPAGADO
                return;
            }

            if (!left.type.equals(right.type)) {
                System.err.println("ERROR EN: " + this.rebuild());
                System.err.println("Se esperaban tipos iguales en la expresion.");
                System.err.println("Tipos recibidos: <" + left.type + "> y <" + right.type + ">");
                System.err.println();
                Globals.error = true;
                return;
            }

            Type t = left.type;
            if (!Type.CHAR.equals(t) && !Type.INTEGER.equals(t) && !Type.BOOLEAN.equals(t)) {
                System.err.println("ERROR EN: " + this.rebuild());
                System.err.print(left.rebuild() + " y " + right.rebuild());
                System.err.println(" no son de los tipos esperados (integer, char, boolean).");
                System.err.println("Tipo recibido: <" + left.type + ">");
                System.err.println();
                Globals.error = true;
            }
        } else {
            // ambos tipos deben de ser integer
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

    public void compile() {
        Node<T> left = children.get(0);
        Node<T> right = children.get(1);
        left.compile();
        right.compile();

        listaV = Globals.crearLista(new Marcador(false));
        listaF = Globals.crearLista(new Marcador(false, Globals.cuadruplos.size() + 2));

        Globals.cuadruplos.add(new Cuadruplo("if" + data + " goto", left.place, right.place, null));
        Globals.cuadruplos.add(new Cuadruplo("goto", "null"));
    }

    public String rebuild() {
        Node<T> left = children.get(0);
        Node<T> right = children.get(1);
        return String.format("%s %s %s", left.rebuild(), data, right.rebuild());
    }
}
