package minipascal.tree;

public class NContinue<T> extends Node<T> {

    public NContinue() {
        super((T) "continue");
    }

    public void visit() {
        System.out.println("Continue.");
    }

    @Override
    public String toString() {
        return "\"continue\"";
    }
}
