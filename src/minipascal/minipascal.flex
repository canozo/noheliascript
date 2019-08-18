package minipascal;

%%

%unicode
%int
%public
%line
%column
%caseless
%ignorecase
%class Lexer
%state COMMENT

%{
  public static String archivoInput = "inputs/rel.pas";
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

  "integer"   { System.out.println("Token <type, 'integer'>"); }
  "boolean"   { System.out.println("Token <type, 'boolean'>"); }
  "char"      { System.out.println("Token <type, 'char'>"); }
  "record"    { System.out.println("Token <type, 'record'>"); }

  "program"   { System.out.println("Token <keyword, 'program'>"); }
  "if"        { System.out.println("Token <keyword, 'if'>"); }
  "then"      { System.out.println("Token <keyword, 'then'>"); }
  "else"      { System.out.println("Token <keyword, 'else'>"); }
  "const"     { System.out.println("Token <keyword, 'const'>"); }
  "var"       { System.out.println("Token <keyword, 'var'>"); }
  "begin"     { System.out.println("Token <keyword, 'begin'>"); }
  "end"       { System.out.println("Token <keyword, 'end'>"); }
  "while"     { System.out.println("Token <keyword, 'while'>"); }
  "do"        { System.out.println("Token <keyword, 'do'>"); }
  "for"       { System.out.println("Token <keyword, 'for'>"); }
  "to"        { System.out.println("Token <keyword, 'to'>"); }
  "repeat"    { System.out.println("Token <keyword, 'repeat'>"); }
  "until"     { System.out.println("Token <keyword, 'until'>"); }

  "true"      { System.out.println("Token <constbool, true>"); }
  "false"     { System.out.println("Token <constbool, false>"); }

  "read"      { System.out.println("Token <func_id, read>"); }
  "write"     { System.out.println("Token <func_id, write>"); }

  ":="        { System.out.println("Token <asig>"); }
  "("         { System.out.println("Token <paren_izq>"); }
  ")"         { System.out.println("Token <paren_der>"); }
  ","         { System.out.println("Token <coma>"); }
  "."         { System.out.println("Token <punto>"); }
  ";"         { System.out.println("Token <punto_coma>"); }
  ":"         { System.out.println("Token <dos_puntos>"); }

  "<>"        { System.out.println("Token <op_rel, '" + yytext() + "'>"); }
  "="         { System.out.println("Token <op_rel, '" + yytext() + "'>"); }
  ">"         { System.out.println("Token <op_rel, '" + yytext() + "'>"); }
  "<"         { System.out.println("Token <op_rel, '" + yytext() + "'>"); }
  ">="        { System.out.println("Token <op_rel, '" + yytext() + "'>"); }
  "<="        { System.out.println("Token <op_rel, '" + yytext() + "'>"); }
  "and"       { System.out.println("Token <op_rel, '" + yytext() + "'>"); }
  "or"        { System.out.println("Token <op_rel, '" + yytext() + "'>"); }
  "not"       { System.out.println("Token <op_rel, '" + yytext() + "'>"); }

  "+"         { System.out.println("Token <op_suma, '" + yytext() + "'>"); }
  "-"         { System.out.println("Token <op_suma, '" + yytext() + "'>"); }
  "*"         { System.out.println("Token <op_multi, '" + yytext() + "'>"); }
  "/"         { System.out.println("Token <op_multi, '" + yytext() + "'>"); }
  "div"       { System.out.println("Token <op_multi, '" + yytext() + "'>"); }
  "mod"       { System.out.println("Token <op_multi, '" + yytext() + "'>"); }

  {id}        { System.out.println("Token <identificador, '" + yytext() + "'>"); }
  {integer}   { System.out.println("Token <integer, '" + yytext() + "'>"); }
  {float}     { System.out.println("Token <float, '" + yytext() + "'>"); }

  {constchar} { System.out.println("Token <constchar, " + yytext() + ">"); }
  {conststr}  { System.out.println("Token <conststr, " + yytext() + ">"); }

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
