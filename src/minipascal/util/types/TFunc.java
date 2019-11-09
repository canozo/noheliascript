package minipascal.util.types;

import minipascal.util.Globals;

import java.util.ArrayList;
import java.util.List;

public class TFunc extends Type {

    public Type returnType;
    public List<Type> args;

    public TFunc(String id, String returnType) {
        super(id);
        this.returnType = Globals.findType(returnType);
        args = new ArrayList<>();
    }

    public TFunc(String id, Type returnType) {
        super(id);
        this.returnType = returnType;
        args = new ArrayList<>();
    }

    public void addArg(String type) {
        Type resType = Globals.findType(type);
        if (resType == null) {
            return;
        }

        args.add(resType);
    }

    public void addArg(Type type) {
        if (type == null) {
            return;
        }
        args.add(type);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TFunc)) {
            return false;
        }

        TFunc other = (TFunc) obj;
        return type.equals(other.type) && returnType.equals(other.returnType) && args.equals(other.args);
    }

    @Override
    public String toString() {
        StringBuilder strArgs = new StringBuilder();
        boolean first = true;
        for (Type arg : args) {
            if (first) {
                strArgs.append(arg.toString());
                first = false;
            } else {
                strArgs.append(" x ");
                strArgs.append(arg.toString());
            }
        }
        return String.format("%s: (%s) -> %s", type, strArgs, returnType);
    }
}
