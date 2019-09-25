package minipascal;

import minipascal.Lexer.Lexer;
import minipascal.Parser.parser;

import java.io.*;

// Paginas para ver los JSON:
// https://jsoneditoronline.org/
// https://vanya.jp.net/vtree/

public class Main {

    public static void main(String[] args) {
        String[] programas = {
                "inputs/buenos/factorial.pas",
                "inputs/buenos/func.pas",
                "inputs/buenos/rel.pas",
                "inputs/malos/coma.pas",
                "inputs/malos/loop.pas",
                "inputs/malos/funcs_procs.pas",
                "inputs/test/test.pas",
                "inputs/test/vacio.pas",
        };
        Reader reader;
        Lexer lexer;
        parser cupParser;
        for (String programa : programas) {
            System.out.println("Corriendo programa: " + programa);
            try {
                reader = new BufferedReader(new FileReader(programa));
                lexer = new Lexer(reader);
                cupParser = new parser(lexer);
                cupParser.parse();
                System.out.println("-------------------------------------------------------------------");
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
