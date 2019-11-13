package minipascal.tree;

import minipascal.util.types.Type;

public class NChar<T> extends NodeType<T> {

    public NChar(T data) {
        super(data, Type.CHAR);
    }

    public void visit() {
        // constchar
    }

    public String rebuild() {
        return data.toString();
    }

    public void compile() {
        // TODO crear codigo intermedio
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", data.toString());
    }
}
