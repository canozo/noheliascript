package minipascal;

import minipascal.Lexer.Lexer;
import minipascal.Parser.parser;

import java.io.FileReader;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) {
        try {
//            parser p = new parser(new Lexer(new FileReader(Lexer.archivoInput)));
            parser p = new parser(new Lexer(new StringReader("program showRelations;")));
            p.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
