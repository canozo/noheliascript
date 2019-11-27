package minipascal;

import minipascal.lexer.Lexer;
import minipascal.cup_parser.parser;
import minipascal.tree.Node;
import minipascal.util.Globals;

import java.io.*;

// Paginas para ver los JSON:
// https://jsoneditoronline.org/
// https://vanya.jp.net/vtree/

public class Main {

    private static final boolean PRINT_ARBOL = false;
    private static final boolean PRINT_TABLAS = false;
    private static final boolean PRINT_CUADRUPLOS = false;

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        String[] programas = {
                "inputs/buenos/factorial.pas",
                "inputs/buenos/func.pas",
                "inputs/buenos/rel.pas",
                "inputs/buenos/psuma.pas",
        };
        Reader reader;
        Lexer lexer;
        parser cupParser;
        Node root;
        for (String programa : programas) {
            System.out.println("Corriendo programa: " + programa);
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

                if (!cupParser.ERROR) {
                    // recorrer el arbol
                    root.visit();
                    if (PRINT_TABLAS) {
                        Globals.printSimbolos();
                    }
                }

                // compilar
                root.compile();

                if (PRINT_CUADRUPLOS) {
                    Globals.printCuadruplos();
                }

                System.out.println(String.format("%80s", "").replaceAll(" ", "_"));
            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
