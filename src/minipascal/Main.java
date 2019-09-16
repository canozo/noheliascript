package minipascal;

import minipascal.Lexer.Lexer;
import minipascal.Parser.parser;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.FileReader;
import java.io.StringReader;

import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.Reader;

import java.io.*;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class Main {

    public static void main(String[] args) {
        try {
            Reader reader;
            try {
                reader = new BufferedReader(new FileReader("inputs/buenos/func.pas"));

                Lexer lexer = new Lexer(reader);

                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                        .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                        .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                        .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
                mapper.setSerializationInclusion(Include.NON_NULL);
                mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);

                parser cupParser = new parser(lexer);
                cupParser.parse();
                mapper.writeValue(new File("AST.json"), cupParser.root);


            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
