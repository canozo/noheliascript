package minipascal;

import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Lexer lexer = new Lexer(new FileReader(Lexer.archivoInput));
            lexer.yylex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
