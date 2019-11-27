package minipascal.test;

import minipascal.lexer.Lexer;
import minipascal.cup_parser.parser;
import minipascal.tree.Node;
import minipascal.util.Globals;

import java.io.*;

// Paginas para ver los JSON:
// https://jsoneditoronline.org/
// https://vanya.jp.net/vtree/

public class Test {

    private static final boolean PRINT_ARBOL = false;
    private static final boolean PRINT_TABLAS = false;
    private static final boolean PRINT_CUADRUPLOS = true;

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        String[] programas = {
//                "inputs/buenos/factorial.pas",
//                "inputs/buenos/func.pas",
//                "inputs/buenos/rel.pas",
//                "inputs/buenos/psuma.pas",
                "inputs/test/test.pas",
//                "inputs/test/vacio.pas",
//                "inputs/test/jumps.pas",
//                "inputs/test/caseinsensitive.pas",
//                "inputs/test/records.pas",
//                "inputs/test/quads.pas",
//                "inputs/test/ifElse.pas",
//                "inputs/test/booleans.pas",
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

                // iniciar variables globales
                Globals.create();

                // crear el arbol
                cupParser.parse();
                root = cupParser.root;
                if (PRINT_ARBOL) {
                    System.out.println(root);
                }

                // verificar que no hay errores de parseo
                assert !cupParser.ERROR;

                // recorrer el arbol
                root.visit();
                if (PRINT_TABLAS) {
                    Globals.printSimbolos();
                }

                // verificar que no hay errores lexicos
                assert !Globals.error;

                // compilar
                root.compile();

                if (PRINT_CUADRUPLOS) {
                    Globals.printCuadruplos();
                }

            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            } catch (AssertionError e) {
                System.err.println("No se pasaron todas las pruebas! Saliendo.");
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("No se pasaron todas las pruebas! Saliendo.");
                System.exit(0);
            }
            System.out.println(String.format("%80s", "").replaceAll(" ", "_"));
        }
        System.out.println("Se pasaron todas las pruebas con exito!");
    }
}
