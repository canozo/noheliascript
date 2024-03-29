package minipascal.tree;

import minipascal.util.types.Type;

public class NString<T> extends NodeType<T> {

    public NString(T data) {
        super(data, Type.STRING);
    }

    public void visit() {
        // conststr
    }

    public void compile() {
        place = rebuild();
    }

    public String rebuild() {
        return String.format("\"%s\"", data.toString());
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", data.toString());
    }
}
