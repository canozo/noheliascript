package tree;

public class NFuncWrite<T> extends Node<T> {

    public NFuncWrite(Node<T> string) {
        super((T) "write");
        add(string);
    }

    public NFuncWrite(Node<T> string, Node<T> var) {
        super((T) "write");
        add(string);
        add(var);
    }

    public void visit() {
        System.out.println("Begin Write Function:");
        System.out.println("(Param 1) String:");
        children.get(0).visit();
        if (children.size() > 1) {
            System.out.println("(Param 2) Var Id:");
            children.get(1).visit();
        }
        System.out.println("End Write Function.");
    }
}
