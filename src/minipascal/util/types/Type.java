package minipascal.util.types;

public class Type {

    public String type;
    public int size;

    public static Type BOOLEAN = new TVar("boolean", 4);
    public static Type INTEGER = new TVar("integer", 4);
    public static Type STRING = new TVar("string", 4);
    public static Type CHAR = new TVar("char", 1);
    public static Type VOID = new TVar("void", 0);

    Type(String type, int size) {
        this.type = type;
        this.size = size;
    }

    Type(String type) {
        this.type = type;
        this.size = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Type)) {
            return false;
        }

        Type other = (Type) obj;
        return this.type.equals(other.type);
    }
}
