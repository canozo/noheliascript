package minipascal.tree;

public class NProcedure<T> extends Node<T> {

    public NProcedure(Node<T> id, Node<T> maybeArgs, Node<T> maybeVars, Node<T> maybeStmntList) {
        super((T) "procedure");
        add(id);
        add(maybeArgs);
        add(maybeVars);
        add(maybeStmntList);
    }

    public void visit() {
        System.out.println("Begin Procedure: Name:");
        children.get(0).visit();
        if (children.get(1) != null) {
            System.out.println("Procedure args:");
            children.get(1).visit();
        }
        if (children.get(2) != null) {
            System.out.println("Procedure vars:");
            children.get(2).visit();
        }
        if (children.get(3) != null) {
            System.out.println("Procedure statements:");
            children.get(3).visit();
        }
        System.out.println("End.");
    }
}
