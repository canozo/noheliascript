package minipascal.tree;

import minipascal.util.types.Type;

public class NString<T> extends NodeType<T> {

    public NString(T data) {
        super(data, Type.STRING);
    }

    public void visit() {
        // conststr
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", data.toString());
    }
}
