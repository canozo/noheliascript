package minipascal.util.types;

public class Type {

    public String type;

    public static Type BOOLEAN = new TVar("boolean");
    public static Type INTEGER = new TVar("integer");
    public static Type STRING = new TVar("string");
    public static Type CHAR = new TVar("char");
    public static Type VOID = new TVar("void");

    Type(String type) {
        this.type = type;
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
