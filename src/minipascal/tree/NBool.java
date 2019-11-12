package minipascal.tree;

import minipascal.util.types.Type;

public class NBool<T> extends NodeType<T> {

    public NBool(T data) {
        super(data, Type.BOOLEAN);
    }

    public void visit() {
        // true o false
    }

    public void compile() {
    }

    public String rebuild() {
        return data.toString();
    }
}
