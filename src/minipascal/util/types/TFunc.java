package minipascal.util.types;

import minipascal.util.Globals;

import java.util.ArrayList;
import java.util.List;

public class TFunc extends Type {

    public Type returnType;
    public List<Type> args;
    public List<String> argNames;
    public List<String> varNames;


    public TFunc(String id, String returnType) {
        super(id);
        this.returnType = Globals.findType(returnType);
        args = new ArrayList<>();
        argNames = new ArrayList<>();
        varNames = new ArrayList<>();
    }

    public TFunc(String id, Type returnType) {
        super(id);
        this.returnType = returnType;
        args = new ArrayList<>();
    }

    public void addArg(String id, String type) {
        Type resType = Globals.findType(type);
        if (resType == null) {
            return;
        }

        args.add(resType);
        argNames.add("_" + id);
    }

    public void addArg(Type type) {
        if (type == null) {
            return;
        }
        args.add(type);
    }

    public String getArgVar(String argVar) {
        // si nos referimos a un argumento en una funcion
        // ocupamos obtener la variable $s0 que la guarda
        int sp = 12;
        int s = 0;
        for (String argName : argNames) {
            if (argVar.equals(argName)) {
                return String.format("$s%d", s);
            }
            s += 1;
            sp += 4;
//            sp += arg.size;
        }

        // si nos referimos a una variable en una funcion
        // ocupamos obtener la direccion en la pila que la guarda
        return "";
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
