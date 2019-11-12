package minipascal.tree;

import minipascal.util.types.Type;

public class NInteger<T> extends NodeType<T> {

    public NInteger(T data) {
        super(data, Type.INTEGER);
    }

    public void visit() {
        // integer
    }

    public void compile() {
    }

    public String rebuild() {
        return data.toString();
    }
}
