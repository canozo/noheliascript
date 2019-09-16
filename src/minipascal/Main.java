package minipascal;

import minipascal.Lexer.Lexer;
import minipascal.Parser.parser;

import java.io.FileReader;
import java.io.StringReader;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ScannerBuffer;
import java_cup.runtime.XMLElement;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class Main {

    public static void main(String[] args) {
        try {
            ScannerBuffer lexer = new ScannerBuffer(new Lexer((new FileReader("inputs/rel.pas"))));
            parser p = new parser(lexer);
            //XMLElement e = (XMLElement)p.parse().value;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
