package minipascal.util.types;

public class TVar extends Type {

    public TVar(String type) {
        super(type);
    }

    @Override
    public String toString() {
        return type;
    }
}
