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

type otro = record
  sexo, sangre: char;
  edad: integer;
end;

function test1(a, b: integer; c, d: char): integer;
var x: boolean;
begin write('edad: ', yo.edad); end;

procedure test2(a, b: integer; rep: boolean);
begin end;

function test3(): char;
begin test3 := 'x'; end;

begin
  if ((-3 < 5 + 2 * 0) = false) = (genero2 = test3()) then
    write('c mamo');
  edad2 := 10 + 12;
  for edad := 0 to 10 do write('edad: ', edad);
end.
