package minipascal.test;

import minipascal.lexer.Lexer;
import minipascal.cup_parser.parser;

import java.io.*;

// Paginas para ver los JSON:
// https://jsoneditoronline.org/
// https://vanya.jp.net/vtree/

public class TestRecuperacion {

    public static void main(String[] args) {
        String[] programas = {
                "inputs/malos/coma.pas",
                "inputs/malos/loop.pas",
                "inputs/malos/funcs_procs.pas",
                "inputs/malos/recuperacion.pas",
                "inputs/malos/records.pas",
                "inputs/malos/error_var.pas",
                "inputs/malos/bad_proc.pas",
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
                System.out.println("_________________________________________________________________");
            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
