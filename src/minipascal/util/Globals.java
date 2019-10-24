package minipascal.util;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import minipascal.util.types.Type;

import java.util.HashMap;
import java.util.Map;

public class Globals {

    public static Table<String, Integer, TypeVal> simbolos;
    public static Map<String, RecordFields> records;
    public static int ambito;

    public static void create() {
        // esta funcion debe de llamarse antes de iniciar la construccion del arbol
        simbolos = HashBasedTable.create();
        records = new HashMap<>();
        ambito = 0;
    }

    public static void addSimbolo(String id, Type type) {
        if (!simbolos.contains(id, ambito)) {
            simbolos.put(id, ambito, new TypeVal(type));
        } else {
            System.out.println("Combinacion de <" + id + ", " + ambito + "> ya existe.");
        }
    }

//    public static void addRecord(String type, List<Node> children) {
//        if (!records.containsKey(type)) {
//        } else {
//            System.out.println("Ya existe el tipo de record <" + type + ">.");
//        }
//    }
}
