package minipascal.tree;

public class NBlockList<T> extends Node<T> {

    public NBlockList(Node<T> block) {
        super((T) "global_block_list");
        add(block);
    }

    public void visit() {
        // lista de bloques en el nivel mas alto
        for (Node<T> child : children) {
            child.visit();
        }
    }
}
