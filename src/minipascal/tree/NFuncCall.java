package minipascal.tree;

public class NFuncCall<T> extends Node<T> {

    public NFuncCall(T data, Node<T> exprList) {
        super(data);
        add(exprList);
    }

    public void visit() {
        System.out.println("Begin Function Call: " + data.toString());
        System.out.println("Expresions:");
        for (Node<T> child : children) {
            child.visit();
        }
        System.out.println("End Function Call.");
    }
}
