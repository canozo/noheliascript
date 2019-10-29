package minipascal.tree;

import minipascal.util.types.Type;

public abstract class NodeType<T> extends Node<T> {

    Type type;

    public NodeType(T data, Type type) {
        super(data);
        this.type = type;
    }

    public abstract void visit();

}
