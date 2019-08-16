package minipascal;

%%

%unicode
%int
%public
%line
%column
%ignorecase
%cup
%class Lexer
%state COMMENT

%{
%}

%eof{
%eof}

constchar = \'[a-z]\'
conststr = \'[a-z]+\'
id = [a-z]([a-z]|[0-9]|_)*
finParrafo = \r|\n|\r\n
espacios = (" "|\t)*

%%

<YYINITIAL> {
  "{"         { yybegin(COMMENT); }
  {espacios}  {  }

  "const"     { System.out.println("Token <const>"); }
  "integer"   { System.out.println("Token <integer>"); }
  "boolean"   { System.out.println("Token <boolean>"); }
  "char"      { System.out.println("Token <char>"); }
  "record"    { System.out.println("Token <record>"); }
  ":="        { System.out.println("Token <asig>"); }

  "if"      { System.out.println("Token <func_id, read>"); }
  "then"      { System.out.println("Token <func_id, read>"); }
  "else"      { System.out.println("Token <func_id, read>"); }
  "var"      { System.out.println("Token <func_id, read>"); }
  "begin"      { System.out.println("Token <func_id, read>"); }
  "end"      { System.out.println("Token <func_id, read>"); }


  "read"      { System.out.println("Token <func_id, read>"); }
  "write"     { System.out.println("Token <func_id, write>"); }

  "("         { System.out.println("Token <paren_izq>"); }
  ")"         { System.out.println("Token <paren_der>"); }
  ";"         { System.out.println("Token <punto_coma>"); }

  "<>"        { System.out.println("Token <op_rel, " + yytext() + ">"); }
  "="         { System.out.println("Token <op_rel, " + yytext() + ">"); }
  ">"         { System.out.println("Token <op_rel, " + yytext() + ">"); }
  "<"         { System.out.println("Token <op_rel, " + yytext() + ">"); }
  ">="        { System.out.println("Token <op_rel, " + yytext() + ">"); }
  "<="        { System.out.println("Token <op_rel, " + yytext() + ">"); }
  "and"       { System.out.println("Token <op_rel, " + yytext() + ">"); }
  "or"        { System.out.println("Token <op_rel, " + yytext() + ">"); }
  "not"       { System.out.println("Token <op_rel, " + yytext() + ">"); }

  "+"         { System.out.println("Token <op_suma, " + yytext() + ">"); }
  "-"         { System.out.println("Token <op_suma, " + yytext() + ">"); }
  "*"         { System.out.println("Token <op_multi, " + yytext() + ">"); }
  "/"         { System.out.println("Token <op_multi, " + yytext() + ">"); }
  "div"       { System.out.println("Token <op_multi, " + yytext() + ">"); }
  "mod"       { System.out.println("Token <op_multi, " + yytext() + ">"); }





  {constchar} { System.out.println("Token <constchar, " + yytext() + ">"); }
  {conststr}  { System.out.println("Token <conststr, " + yytext() + ">"); }

  .           { throw new Error("Error, caracter no reconocido: '" + yytext() + "'!"); }
}

<COMMENT> {
  "{" { throw new Error("No se permiten comentarios anidados!"); }
  "}" { yybegin(YYINITIAL); }
}

[^] {
  System.out.println("Error!");
  System.out.println("Linea " + yyline);
  System.out.println("Columna " + yycolumn);
  System.out.println("Input " + yytext());

  throw new Error("Error, caracter ilegal!");
}
