package tree;

public class NFuncWrite<T> extends Node<T> {

    public NFuncWrite(Node<T> exprList) {
        super((T) "write");
        add(exprList);
    }

    public void visit() {
        System.out.println("Begin Write Function:");
        System.out.println("Expresions:");
        for (Node<T> child : children) {
            child.visit();
        }
        System.out.println("End Function Write.");
    }
}
