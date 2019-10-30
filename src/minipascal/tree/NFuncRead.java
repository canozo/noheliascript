package minipascal.tree;

import minipascal.util.Globals;
import minipascal.util.types.Type;

public class NFuncRead<T> extends NodeType<T> {

    public NFuncRead(Node<T> var) {
        super((T) "read", Type.VOID);
        add(var);
    }

    public void visit() {
        // funcion especial read
        children.get(0).visit();

        NodeType var = (NodeType) children.get(0);
        if (var.type == null) {
            // tipo de variable no definido
            return;
        }

        if (!var.type.equals(Type.INTEGER) && !var.type.equals(Type.CHAR)) {
            System.err.println("ERROR: La variable <" + var + "> no es de tipo integer o char (para funcion read).");
            Globals.error = true;
        }
    }
}
