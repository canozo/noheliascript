package tree;

public class NMain<T> extends Node<T> {

    public NMain(Node<T> stmntList) {
        super((T) "main");
        add(stmntList);
    }

    public void visit() {
        System.out.println("Begin main:");
        if (children.get(0) != null) {
            children.get(0).visit();
        }
        System.out.println("End.");
    }
}
