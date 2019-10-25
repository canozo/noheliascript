package minipascal.tree;

public class NNewType<T> extends Node<T> {

    public NNewType(T data) {
        super(data);
    }

    public void visit() {
        // tipo creado por el usuario (record)
        // TODO revisar que el tipo ya fue definido anteriormente
    }
}
