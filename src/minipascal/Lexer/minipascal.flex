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
  private static final boolean DEBUG = false;

  private Symbol symbol(int type) {
    if (DEBUG) {
      System.out.println("Symbol: " + sym.terminalNames[type]);
      System.out.println("Token: " + yytext());
      System.out.println("Line: " + yyline);
      System.out.println("Col:  " + yycolumn);
      System.out.println();
    }
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    if (DEBUG) {
      System.out.println("Symbol: " + sym.terminalNames[type]);
      System.out.println("Token: " + yytext());
      System.out.println("Line: " + yyline);
      System.out.println("Col:  " + yycolumn);
      System.out.println();
    }
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }
%}

%eof{
%eof}

constchar = \'[\x00-\x26\x28-\x7F]\'
conststr = \'[\x00-\x26\x28-\x7F]+\'
id = [a-zA-Z_][a-zA-Z0-9_]*
integer = [0-9]+
reDouble = {integer}"."{integer}
endline = \r|\n|\r\n
espacios = [ \t]+

%%

<YYINITIAL> {
  "{"         { yybegin(COMMENT); }
  {espacios}  { /* skip espacios blancos y tabs */ }
  {endline}   { /* skip saltos de linea */ }

  "nil"       { return symbol(sym.NIL); }

  "function"  { return symbol(sym.FUNCTION); }
  "procedure" { return symbol(sym.PROCEDURE); }

  "integer"   { return symbol(sym.INTEGER); }
  "real"      { return symbol(sym.REAL); }
  "boolean"   { return symbol(sym.BOOLEAN); }
  "char"      { return symbol(sym.CHAR); }
  "string"    { return symbol(sym.STRING); }
  "record"    { return symbol(sym.RECORD); }

  "program"   { return symbol(sym.PROGRAM); }
  "if"        { return symbol(sym.IF); }
  "then"      { return symbol(sym.THEN); }
  "else"      { return symbol(sym.ELSE); }
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

  "or"        { return symbol(sym.OR); }
  "and"       { return symbol(sym.AND); }
  "not"       { return symbol(sym.NOT); }

  "+"         { return symbol(sym.OP_SUMA, yytext()); }
  "-"         { return symbol(sym.OP_SUMA, yytext()); }

  "*"         { return symbol(sym.OP_MULT, yytext()); }
  "/"         { return symbol(sym.OP_MULT, yytext()); }
  "div"       { return symbol(sym.OP_MULT, yytext()); }
  "mod"       { return symbol(sym.OP_MULT, yytext()); }

  "break"     { return symbol(sym.BREAK); }
  "continue"  { return symbol(sym.CONTINUE); }

  {id}        { return symbol(sym.ID, yytext().toLowerCase()); }
  {reDouble}  { return symbol(sym.NUM_DOUBLE, new Double(yytext())); }
  {integer}   { return symbol(sym.NUM_INTEGER, new Integer(yytext())); }

  {constchar} { return symbol(sym.CONSTCHAR, new Character(yycharat(1))); }
  {conststr}  { return symbol(sym.CONSTSTR, yytext().substring(1, yylength() - 1)); }

  .           { return symbol(sym.UNKNOWN, yytext()); }
}

<COMMENT> {
//   "{"       { throw new Error("No se permiten comentarios anidados!"); }
  "}"       { yybegin(YYINITIAL); }
  {endline} { /* skip saltos de linea */ }
   .        { /* skip comentario */ }
}
