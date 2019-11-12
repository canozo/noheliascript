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

        if (!Type.INTEGER.equals(var.type) && !Type.CHAR.equals(var.type)) {
            System.err.println("ERROR EN: " + this.rebuild());
            System.err.println("La variable " + var + " no es de tipo integer o char (funcion read).");
            System.err.println("Tipo recibido: <" + var.type + ">.");
            System.err.println();
            Globals.error = true;
        }
    }

    public void compile() {
    }

    public String rebuild() {
        Node<T> var = children.get(0);
        return String.format("read(%s)", var.rebuild());
    }
}
