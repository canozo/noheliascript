package minipascal.util.types;

import minipascal.util.Globals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TFunc extends Type {

    // chanchada incoming:
    public class B {
        public String nombre;
        public Type type;
        public boolean arg;
        public String place;

        public B(String nombre, Type type, boolean arg) {
            this.nombre = nombre;
            this.type = type;
            this.arg = arg;
        }
    }

    public Type returnType;
    public List<Type> args;
    public List<String> argNames;
    public Map<String, Type> varsLocales;
    public List<B> names;

    public TFunc(String id, String returnType) {
        super(id);
        this.returnType = Globals.findType(returnType);
        args = new ArrayList<>();
        argNames = new ArrayList<>();
        varsLocales = new TreeMap<>();
        names = new ArrayList<>();
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

    public void addName(String name, Type type, boolean arg) {
        names.add(new B(name, type, arg));
    }

    public B getArgVar(String argVar) {
        for (B name : names) {
            if (name.nombre.equals(argVar)) {
                return name;
            }
        }

        System.err.println("Error: No se encontro el nombre " + argVar + ".");
        return null;
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
