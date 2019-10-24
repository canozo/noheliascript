package minipascal.test;

import minipascal.lexer.Lexer;
import minipascal.cup_parser.parser;
import minipascal.tree.Node;
import minipascal.util.Globals;

import java.io.*;

public class Test {

    public static void main(String[] args) {
        String[] programas = {
//                "inputs/buenos/factorial.pas",
//                "inputs/buenos/func.pas",
//                "inputs/buenos/rel.pas",
                "inputs/test/test.pas",
//                "inputs/test/vacio.pas",
//                "inputs/test/jumps.pas",
//                "inputs/test/caseinsensitive.pas",
//                "inputs/test/records.pas",
//                "inputs/test/acento.pas",
        };
        Node root;
        Reader reader;
        Lexer lexer;
        parser cupParser;
        for (String programa : programas) {
            System.out.println("Corriendo test: " + programa);
            try {
                reader = new BufferedReader(new FileReader(programa));
                lexer = new Lexer(reader);
                cupParser = new parser(lexer);
                Globals.create();
                cupParser.parse();
                root = cupParser.root;
                root.visit();
                System.out.println("------------------------------------------------------------------");
                assert !cupParser.ERROR;
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } catch (AssertionError e) {
                System.out.println("No se pasaron todas las pruebas! Saliendo.");
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Se pasaron todas las pruebas con exito!");
    }
}
