package tree;

public class NBool<T> extends Node<T> {

    public NBool(T data) {
        super(data);
    }

    public void visit() {
        System.out.println("Boolean: " + data.toString());
    }
}
