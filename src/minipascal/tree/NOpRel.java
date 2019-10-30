package minipascal.tree;

import minipascal.util.types.Type;

public class NOpRel<T> extends NodeType<T> {

    public NOpRel(Node<T> left, T data, Node<T> right) {
        super(data, Type.BOOLEAN);
        add(left);
        add(right);
    }

    public void visit() {
        // operador relacional (data)
        children.get(0).visit();
        children.get(1).visit();

        // TODO verificar tipos
        String op = (String) data;
        if (op.equals("=") || op.equals("<>")) {
            // ambos tipos deben de ser iguales (char, integer o boolean)
        } else {
            System.out.println("alo wenas");
            // ambos tipos deben de ser integer
        }
    }
}
