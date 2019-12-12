package minipascal.util.cuadruplo;

public class ArrayVar {

    public String var;
    public int offset;

    public ArrayVar(String direccion) {
        // recibe una variable con direccionamiento
        // ej: persona[5]
        String temp = direccion.replaceAll("]", "");
        String[] splitted = temp.split("\\[");
        var = splitted[0];
        offset = Integer.parseInt(splitted[1]);
    }
}
