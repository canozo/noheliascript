package minipascal.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Table;
import minipascal.util.cuadruplo.Cuadruplo;
import minipascal.util.cuadruplo.Marcador;
import minipascal.util.types.TFunc;
import minipascal.util.types.TRecord;
import minipascal.util.types.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Globals {

    public static Table<String, Integer, Type> simbolos;
    public static Map<String, TFunc> funciones;
    public static Map<String, TRecord> records;
    public static BiMap<Integer, String> nombreAmbitos;
    public static ArrayList<Cuadruplo> cuadruplos;
    public static int ambito;
    public static int temporal;
    public static boolean error;

    public static void create() {
        // esta funcion debe de llamarse antes de iniciar la construccion del arbol
        simbolos = HashBasedTable.create();
        funciones = new HashMap<>();
        records = new HashMap<>();
        nombreAmbitos = HashBiMap.create();
        cuadruplos = new ArrayList<>();
        ambito = 0; // 0 esta reservado para variables globales
        temporal = 0;
        addNombreAmbito("global");
        error = false;
    }

    public static String temporalNuevo() {
        temporal += 1;
        return String.format("t%d", temporal);
    }

    public static ArrayList<Marcador> crearLista(Marcador marcador) {
        ArrayList<Marcador> temp = new ArrayList<>();
        temp.add(marcador);
        return temp;
    }

    public static ArrayList<Marcador> fusionar(List<Marcador> a, List<Marcador> b) {
        ArrayList<Marcador> res = new ArrayList<>();
        if (a != null) {
            res.addAll(a);
        }
        if (b != null) {
            res.addAll(b);
        }
        return res;
    }

    public static void completar(List<Marcador> lista, Marcador completacion) {
        if (lista != null) {
            for (Marcador num : lista) {
                cuadruplos.get(num.sigCuad - 1).setRes(completacion);
            }
        }
    }

    public static void addNombreAmbito(String nombre) {
        if (nombreAmbitos.containsKey(ambito)) {
            String p = String.format("ERROR: Ya existe ambito con id <%d>.\n", ambito);
            p += String.format("Actual: \"%s\" vs conflicto: \"%s\"", nombreAmbitos.get(ambito), nombre);
            System.err.println(p);
            error = true;
        } else if (nombreAmbitos.containsValue(nombre)) {
            String p = String.format("ERROR: Ya existe ambito con nombre <%s>.\n", nombre);
            System.err.println(p);
            error = true;
        } else {
            nombreAmbitos.put(ambito, nombre);
        }
    }

    public static void addSimboloGlobal(String id, String type) {
        Type resType = findType(type);
        if (resType == null) {
            return;
        }

        if (!simbolos.contains(id, 0)) {
            simbolos.put(id, 0, resType);
        } else {
            System.err.println("ERROR: Identificador <" + id + "> ya existe en las variables globales.");
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

    public static boolean errCodigoIntermedio() {
        for (Cuadruplo c : cuadruplos) {
            if (c.op == null || c.arg1 == null || c.arg2 == null || (c.res == null && c.resM == null)) {
                return true;
            } else if (c.resM == null && c.res.equals("null")) {
                return true;
            }
        }
        return false;
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

    public static void printCuadruplos() {
        System.out.println("\nTabla de cuadruplos:");
        System.out.println(String.format("   # | %15s | %15s | %15s | %15s", "op", "arg1", "arg2", "res"));
        System.out.println(String.format("%76s", "").replaceAll(" ", "-"));
        int linea = 1;
        for (Cuadruplo cuadruplo : cuadruplos) {
            System.out.println(String.format("%3d: | %s", linea, cuadruplo));
            linea += 1;
        }
    }
}
