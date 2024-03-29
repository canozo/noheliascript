package minipascal.tree;

import minipascal.util.types.Type;

public class NChar<T> extends NodeType<T> {

    public NChar(T data) {
        super(data, Type.CHAR);
    }

    public void visit() {
        // constchar
    }

    public void compile() {
        place = rebuild();
    }

    public String rebuild() {
        return String.format("'%s'", data.toString());
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", data.toString());
    }
}
