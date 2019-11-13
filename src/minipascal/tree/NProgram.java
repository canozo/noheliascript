package minipascal.tree;

public class NProgram<T> extends Node<T> {

    public NProgram(T data) {
        super(data);
    }

    public void visit() {
        for (Node<T> child : children) {
            child.visit();
        }
    }

    public void compile() {
        for (Node<T> child : children) {
            child.compile();
        }
    }

    public String rebuild() {
        return String.format("program %s", data);
    }
}
