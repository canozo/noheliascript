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

            NodeType var = (NodeType) children.get(1);
            if (var.type == null) {
                // tipo de variable no definido
                return;
            }

            if (!Type.INTEGER.equals(var.type) && !Type.CHAR.equals(var.type)) {
                System.err.println("ERROR: La variable <" + var + "> no es de tipo integer o char (para funcion write).");
                Globals.error = true;
            }
        }
    }
}
