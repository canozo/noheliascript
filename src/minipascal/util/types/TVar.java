package minipascal.util.types;

public class TVar extends Type {

    public TVar(String type, int size) {
        super(type, size);
    }

    public TVar(String type) {
        super(type);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TVar)) {
            return false;
        }

        TVar other = (TVar) obj;
        return this.type.equals(other.type);
    }


    @Override
    public String toString() {
        return type;
    }
}
