package minipascal;

import minipascal.Lexer.Lexer;
import minipascal.Parser.parser;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            Reader reader = new BufferedReader(new FileReader("inputs/malos/coma.pas"));
            Lexer lexer = new Lexer(reader);
            parser cupParser = new parser(lexer);
            cupParser.parse();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
