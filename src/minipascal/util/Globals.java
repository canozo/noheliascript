package minipascal.util;

import com.google.common.collect.HashBasedTable;
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
    public static int ambito;
    public static boolean error;

    public static void create() {
        // esta funcion debe de llamarse antes de iniciar la construccion del arbol
        simbolos = HashBasedTable.create();
        funciones = new HashMap<>();
        records = new HashMap<>();
        ambito = 1; // 0 esta reservado para funciones
        error = false;
    }

    public static void addSimbolo(String id, Type type) {
        if (!simbolos.contains(id, ambito)) {
            simbolos.put(id, ambito, type);
        } else {
            System.err.println("ERROR: Combinacion de <" + id + ", " + ambito + "> ya existe.");
            error = true;
        }
    }

    public static void addSimbolo(String id, int amb, Type type) {
        if (!simbolos.contains(id, amb)) {
            simbolos.put(id, amb, type);
        } else {
            System.err.println("ERROR: Combinacion de <" + id + ", " + amb + "> ya existe.");
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

    public static void printSimbolos() {
        for (Table.Cell<String, Integer, Type> cell : simbolos.cellSet()) {
            String p = String.format("<\"%s\", %d> = %s", cell.getRowKey(), cell.getColumnKey(), cell.getValue());
            System.out.println(p);
        }
    }
}
