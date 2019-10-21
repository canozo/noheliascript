package minipascal.util.types;

// TODO types de funciones
// TODO types de procedures
// TODO types de records

public class Type {

    public String type;

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
