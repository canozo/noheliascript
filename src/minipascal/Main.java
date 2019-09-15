package minipascal;

import minipascal.Lexer.Lexer;
import minipascal.Parser.parser;

import java.io.FileReader;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) {
        try {
            parser p = new parser(new Lexer(new FileReader("inputs/rel.pas")));
            Object result = p.parse().value;
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
