package minipascal.tree;
import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NOr<T> extends NodeType<T> {

    public NOr(Node<T> left, Node<T> right) {
        super((T) "or", Type.BOOLEAN);
        add(left);
        add(right);
    }

    @SuppressWarnings("Duplicates")
    public void visit() {
        // operador or
        children.get(0).visit();
        children.get(1).visit();

        NodeType left = (NodeType) children.get(0);
        NodeType right = (NodeType) children.get(1);

        if (!Type.BOOLEAN.equals(left.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(left.rebuild() + " no es de tipo esperado (boolean).");
            System.err.println("Tipo recibido: " + left.type);
            System.err.println();
            Globals.error = true;
        }

        if (!Type.BOOLEAN.equals(right.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println(right.rebuild() + " no es de tipo esperado (boolean).");
            System.err.println("Tipo recibido: " + right.type);
            System.err.println();
            Globals.error = true;
        }
    }

    public void compile() {
    }

    public String rebuild() {
        Node<T> left = children.get(0);
        Node<T> right = children.get(1);
        return String.format("%s %s %s", left.rebuild(), data, right.rebuild());
    }
}
