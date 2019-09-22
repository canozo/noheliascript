package minipascal;

import tree.*;

public class Test {

    public static void main(String[] args) {
        Node num = new NFloat(new Float(3.14));
        Node not = new NNot(num);
        System.out.println("Tree: " + not.toString());
    }
}
