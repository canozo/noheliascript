package minipascal.tree;

@Deprecated
public class NContinue<T> extends Node<T> {

    public NContinue() {
        super((T) "continue");
    }

    public void visit() {
        // continue
    }

    public String rebuild() {
        return "continue";
    }

    public void compile() {
    }

    @Override
    public String toString() {
        return "\"continue\"";
    }
}
