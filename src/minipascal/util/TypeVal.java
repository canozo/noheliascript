package minipascal.util;

import minipascal.util.types.Type;

public class TypeVal {

    public Type type;
    public Object val;

    TypeVal(Type type, Object val) {
        this.type = type;
        this.val = val;
    }

    TypeVal(Type type) {
        this.type = type;
        this.val = null;
    }
}
