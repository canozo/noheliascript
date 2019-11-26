package minipascal.util.cuadruplo;

public class Cuadruplo {

    public String op;
    public String arg1;
    public String arg2;
    public String res;

    public Cuadruplo(String op, String arg1, String arg2, String res) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.res = res;
    }

    public Cuadruplo(String op, String arg1, String res) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = "";
        this.res = res;
    }

    public Cuadruplo(String op, String res) {
        this.op = op;
        this.arg1 = "";
        this.arg2 = "";
        this.res = res;
    }

    public Cuadruplo(String op) {
        this.op = op;
        this.arg1 = "";
        this.arg2 = "";
        this.res = "";
    }

    public Cuadruplo() {
        this.op = "";
        this.arg1 = "";
        this.arg2 = "";
        this.res = "";
    }

    @Override
    public String toString() {
        if (op.equals("write")) {
            int maxSize = Math.min(arg1.length(), 15);
            String shortStr = String.format("\"%s\"", arg1.substring(1, maxSize - 1));
            return String.format("%15s | %15s | %15s | %15s", op, shortStr, arg2, res);
        }
        return String.format("%15s | %15s | %15s | %15s", op, arg1, arg2, res);
    }
}
