package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NFuncWrite<T> extends NodeType<T> {

    public NFuncWrite(Node<T> string) {
        super((T) "write", Type.VOID);
        add(string);
    }

    public NFuncWrite(Node<T> string, Node<T> var) {
        super((T) "write", Type.VOID);
        add(string);
        add(var);
    }

    public void visit() {
        // funcion especial write
        // primer parametro string
        if (children.size() > 1) {
            // segundo parametro var
            children.get(1).visit();

            NodeType<T> var = (NodeType<T>) children.get(1);
            if (var.type == null) {
                // tipo de variable no definido
                return;
            }

            if (!Type.INTEGER.equals(var.type) && !Type.CHAR.equals(var.type)) {
                System.err.println("ERROR EN: " + this.rebuild());
                System.err.println("La variable " + var + " no es de tipo integer o char (funcion write).");
                System.err.println("Tipo recibido: <" + var.type + ">.");
                Globals.error = true;
            }
        }
    }

    public void compile() {
        // TODO crear codigo intermedio
    }

    public String rebuild() {
        Node<T> string = children.get(0);
        Node<T> var = children.get(1);
        return String.format("write('%s', %s)", string.rebuild(), var.rebuild());
    }
}
