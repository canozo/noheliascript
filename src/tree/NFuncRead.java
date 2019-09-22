package tree;

public class NFuncRead<T> extends Node<T> {

    public NFuncRead(Node<T> exprList) {
        super((T) "read");
        add(exprList);
    }

    public void visit() {
        System.out.println("Begin Read Function:");
        System.out.println("Expresions:");
        for (Node<T> child : children) {
            child.visit();
        }
        System.out.println("End Function Read.");
    }
}
