package minipascal.Lexer;

import java_cup.runtime.*;
import minipascal.Parser.sym;

%%

%unicode
%int
%public
%line
%column
%caseless
%ignorecase
%cup
%class Lexer
%state COMMENT

%{
  public static String archivoInput = "inputs/rel.pas";

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

%eof{
%eof}

constchar = \'[\x00-\x26\x28-\x7F]\'
conststr = \'[\x00-\x26\x28-\x7F]+\'
id = [a-zA-Z][a-zA-Z0-9_]*
integer = [0-9]+
float = {integer}"."{integer}
endline = \r|\n|\r\n
espacios = [ \t]+

%%

<YYINITIAL> {
  "{"         { yybegin(COMMENT); }
  {espacios}  { /* skip espacios blancos y tabs */ }
  {endline}   { /* skip saltos de linea */ }

  "integer"   { return symbol(sym.INTEGER); }
  "boolean"   { return symbol(sym.BOOLEAN); }
  "char"      { return symbol(sym.CHAR); }
  "record"    { return symbol(sym.RECORD); }

  "program"   { return symbol(sym.PROGRAM); }
  "if"        { return symbol(sym.IF); }
  "then"      { return symbol(sym.THEN); }
  "else"      { return symbol(sym.ELSE); }
  "const"     { return symbol(sym.CONST); }
  "var"       { return symbol(sym.VAR); }
  "begin"     { return symbol(sym.BEGIN); }
  "end"       { return symbol(sym.END); }
  "while"     { return symbol(sym.WHILE); }
  "do"        { return symbol(sym.DO); }
  "for"       { return symbol(sym.FOR); }
  "to"        { return symbol(sym.TO); }
  "repeat"    { return symbol(sym.REPEAT); }
  "until"     { return symbol(sym.UNTIL); }

  "read"      { return symbol(sym.FUNC_READ); }
  "write"     { return symbol(sym.FUNC_WRITE); }

  ":="        { return symbol(sym.ASIGNACION); }
  "("         { return symbol(sym.PAR_IZQ); }
  ")"         { return symbol(sym.PAR_DER); }
  ","         { return symbol(sym.COMA); }
  "."         { return symbol(sym.PUNTO); }
  ";"         { return symbol(sym.PUNTOCOMA); }
  ":"         { return symbol(sym.DOSPUNTOS); }

  "true"      { return symbol(sym.CONSTBOOL, Boolean.TRUE); }
  "false"     { return symbol(sym.CONSTBOOL, Boolean.FALSE); }

  "<>"        { return symbol(sym.OP_RELACIONAL, yytext()); }
  "="         { return symbol(sym.OP_RELACIONAL, yytext()); }
  ">"         { return symbol(sym.OP_RELACIONAL, yytext()); }
  "<"         { return symbol(sym.OP_RELACIONAL, yytext()); }
  ">="        { return symbol(sym.OP_RELACIONAL, yytext()); }
  "<="        { return symbol(sym.OP_RELACIONAL, yytext()); }

  "and"       { return symbol(sym.OP_BOOLEANO, yytext()); }
  "or"        { return symbol(sym.OP_BOOLEANO, yytext()); }
  "not"       { return symbol(sym.OP_BOOLEANO, yytext()); }

  "+"         { return symbol(sym.OP_SUMA, yytext()); }
  "-"         { return symbol(sym.OP_SUMA, yytext()); }

  "*"         { return symbol(sym.OP_MULT, yytext()); }
  "/"         { return symbol(sym.OP_MULT, yytext()); }
  "div"       { return symbol(sym.OP_MULT, yytext()); }
  "mod"       { return symbol(sym.OP_MULT, yytext()); }

  {id}        { return symbol(sym.IDENTIFICADOR, yytext()); }
  {float}     { return symbol(sym.NUM_FLOAT, new Float(yytext())); }
  {integer}   { return symbol(sym.NUM_INTEGER, new Integer(yytext())); }

  {constchar} { return symbol(sym.CONSTCHAR, new Character(yycharat(1))); }
  {conststr}  { return symbol(sym.CONSTSTR, yytext().substring(1, yylength() - 2)); }

  .           { throw new Error("Error, caracter no reconocido: '" + yytext() + "'!"); }
}

<COMMENT> {
  "{"       { throw new Error("No se permiten comentarios anidados!"); }
  "}"       { yybegin(YYINITIAL); }
  {endline} { /* skip saltos de linea */ }
   .        { /* skip comentario */ }
}

[^] {
  System.out.println("Error!");
  System.out.println("Linea " + yyline);
  System.out.println("Columna " + yycolumn);
  System.out.println("Input " + yytext());

  throw new Error("Error, caracter ilegal!");
}