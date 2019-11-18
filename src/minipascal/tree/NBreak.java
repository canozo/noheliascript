package minipascal.tree;

@Deprecated
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

    public void compile() {
    }

    @Override
    public String toString() {
        return "\"break\"";
    }
}
