package minipascal.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Table;
import minipascal.util.types.TFunc;
import minipascal.util.types.TRecord;
import minipascal.util.types.Type;

import java.util.HashMap;
import java.util.Map;

public class Globals {

    public static Table<String, Integer, Type> simbolos;
    public static Map<String, TFunc> funciones;
    public static Map<String, TRecord> records;
    public static BiMap<Integer, String> nombreAmbitos;
    public static int ambito;
    public static boolean error;

    public static void create() {
        // esta funcion debe de llamarse antes de iniciar la construccion del arbol
        simbolos = HashBasedTable.create();
        funciones = new HashMap<>();
        records = new HashMap<>();
        nombreAmbitos = HashBiMap.create();
        ambito = 0; // 0 esta reservado para variables globales
        addNombreAmbito("global");
        error = false;
    }

    public static void addNombreAmbito(String nombre) {
        if (!nombreAmbitos.containsKey(ambito)) {
            nombreAmbitos.put(ambito, nombre);
        } else {
            String p = String.format("ERROR: Ya existe ambito con id <%d>.\n", ambito);
            p += String.format("Actual: \"%s\" vs conflicto: \"%s\"", nombreAmbitos.get(ambito), nombre);
            System.err.println(p);
            error = true;
        }
    }

    public static void addSimboloMain(String id, String type) {
        Type resType = findType(type);
        if (resType == null) {
            return;
        }

        if (!simbolos.contains(id, 0)) {
            simbolos.put(id, 0, resType);
        } else {
            System.err.println("ERROR: Identificador <" + id + "> ya existe en el main.");
            error = true;
        }
    }

    public static void addSimbolo(String id, String type) {
        Type resType = findType(type);
        if (resType == null) {
            return;
        }

        if (!simbolos.contains(id, ambito)) {
            simbolos.put(id, ambito, resType);
        } else {
            System.err.println("ERROR: Combinacion de <" + id + ", " + ambito + "> ya existe.");
            error = true;
        }
    }

    public static void addFuncion(String id, TFunc type) {
        if (!funciones.containsKey(id)) {
            funciones.put(id, type);
        } else {
            System.err.println("ERROR: La funcion con nombre <" + id + "> ya fue definida.");
            error = true;
        }
    }

    public static void addRecord(String id, TRecord type) {
        if (!records.containsKey(id)) {
            records.put(id, type);
        } else {
            System.err.println("ERROR: El record nombre <" + id + "> ya fue definido.");
            error = true;
        }
    }

    public static Type findType(String type) {
        // ver si el tipo es un primitivo
        switch(type) {
            case "boolean":
                return Type.BOOLEAN;
            case "integer":
                return Type.INTEGER;
            case "string":
                return Type.STRING;
            case "char":
                return Type.CHAR;
            case "void":
                return Type.VOID;
            default:
                if (records.containsKey(type)) {
                    return records.get(type);
                } else {
                    System.err.println("ERROR: No existe el tipo \"" + type + "\".");
                    error = true;
                    return null;
                }
        }
    }


    public static void printSimbolos() {
        System.out.println("\nTabla de simbolos:");
        for (Table.Cell<String, Integer, Type> cell : simbolos.cellSet()) {
            String p = String.format("<\"%s\", %d> = %s", cell.getRowKey(), cell.getColumnKey(), cell.getValue());
            System.out.println(p);
        }
        System.out.println("\nTabla de funciones:");
        for (String key : funciones.keySet()) {
            String p = String.format("<\"%s\"> = %s", key, funciones.get(key));
            System.out.println(p);
        }
        System.out.println("\nTabla de records:");
        for (String key : records.keySet()) {
            String p = String.format("<\"%s\"> = %s", key, records.get(key));
            System.out.println(p);
        }
        System.out.println("\nTabla de ambitos:");
        for (int key : nombreAmbitos.keySet()) {
            String p = String.format("<%d> = \"%s\"", key, nombreAmbitos.get(key));
            System.out.println(p);
        }
    }
}
