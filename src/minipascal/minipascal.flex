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
id = [a-z][a-z0-9_]*
endline = \r|\n|\r\n
espacios = [ \t]+

%%

<YYINITIAL> {
  "{"         { yybegin(COMMENT); }
  {espacios}  {  }
  {endline}   {  }

  "const"     { System.out.println("Token <const>"); }
  "integer"   { System.out.println("Token <integer>"); }
  "boolean"   { System.out.println("Token <boolean>"); }
  "char"      { System.out.println("Token <char>"); }
  "record"    { System.out.println("Token <record>"); }

  "if"         { System.out.println("Token <if>"); }
  "then"       { System.out.println("Token <then>"); }
  "else"       { System.out.println("Token <else>"); }
  "var"        { System.out.println("Token <var>"); }
  "begin"      { System.out.println("Token <begin>"); }
  "end"        { System.out.println("Token <end>"); }
  "true"       { System.out.println("Token <constbool, true>"); }
  "false"      { System.out.println("Token <constbool, false>"); }

  "read"      { System.out.println("Token <func_id, read>"); }
  "write"     { System.out.println("Token <func_id, write>"); }

  ":="        { System.out.println("Token <asig>"); }
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


  {id}        { System.out.println("Token <identificador, " + yytext() + ">"); }
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
