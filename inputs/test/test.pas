program test;

type persona = record
  sexo, sangre: char;
  edad: integer;
end;

var
  edad, edad2: integer;
  genero: char;
  genero2: char;
  yo: persona;

function test1(a, b: integer; c, d: char): integer;
begin end;

procedure test2(a, b: integer; rep: boolean);
begin end;

begin
  test2();
  edad2 := 10 + 12;
end.
