package minipascal.tree;

public class NBreak<T> extends Node<T> {

    public NBreak() {
        super((T) "break");
    }

    public void visit() {
        // break
    }

    public String rebuild() {
        return "break";
    }

    @Override
    public String toString() {
        return "\"break\"";
    }
}
