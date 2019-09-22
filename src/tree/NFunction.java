package tree;

public class NFunction<T> extends Node<T> {

    public NFunction(Node<T> id, Node<T> returnType, Node<T> maybeArgs, Node<T> maybeVars, Node<T> maybeStmntList) {
        super((T) "function");
        add(id);
        add(returnType);
        add(maybeArgs);
        add(maybeVars);
        add(maybeStmntList);
    }

    public void visit() {
        System.out.println("Begin Function: Name:");
        children.get(0).visit();
        System.out.println("Return type:");
        children.get(1).visit();
        if (children.get(2) != null) {
            System.out.println("Function args:");
            children.get(2).visit();
        }
        if (children.get(3) != null) {
            System.out.println("Function vars:");
            children.get(3).visit();
        }
        if (children.get(4) != null) {
            System.out.println("Function statements:");
            children.get(4).visit();
        }
        System.out.println("End.");
    }
}
