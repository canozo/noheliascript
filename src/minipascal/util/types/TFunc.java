package minipascal.util.types;

import minipascal.util.Globals;

import java.util.ArrayList;
import java.util.List;

public class TFunc extends Type {

    public String returnType;
    public List<Type> args;

    public TFunc(String id, String returnType) {
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
        return String.format("%s: %s -> %s", type, strArgs, returnType);
    }
}
