package minipascal.tree;

public class NProgram<T> extends Node<T> {

    public NProgram(T data) {
        super(data);
    }

    public void visit() {
        for (Node child : children) {
            child.visit();
        }
    }
}
