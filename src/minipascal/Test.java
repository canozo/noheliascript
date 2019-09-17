package minipascal;

import tree.Node;

public class Test {

    public static void main(String[] args) {
        Node<Object> root = new Node<>("+");
        root.add(2);

        Node<Object> nested = new Node<>("*");
        nested.add(5);
        nested.add(10);

        root.add(nested);

        System.out.println(root.toString());
    }
}
