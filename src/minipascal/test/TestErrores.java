package minipascal.test;

import minipascal.lexer.Lexer;
import minipascal.cup_parser.parser;
import minipascal.tree.Node;
import minipascal.util.Globals;

import java.io.*;

// Paginas para ver los JSON:
// https://jsoneditoronline.org/
// https://vanya.jp.net/vtree/

public class TestErrores {

    private static final boolean PRINT_ARBOL = true;
    private static final boolean PRINT_TABLAS = false;

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        String[] programas = {
                "inputs/errLexicos/factorial.pas",
                "inputs/errLexicos/func.pas",
                "inputs/errLexicos/rel.pas",
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
            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            } catch (AssertionError e) {
                System.err.println("No se pasaron todas las pruebas! Saliendo.");
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("__________________________________________________________________");
        }
        System.out.println("Se pasaron todas las pruebas con exito!");
    }
}
