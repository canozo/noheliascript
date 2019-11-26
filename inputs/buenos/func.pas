program func;
var
  a, b, c, res: integer;

function hola(): boolean;
var
  edad: integer;
begin
  write('Hola! Cual es tu edad? ');
  read(edad);
  if edad < 18 then begin
    write('Aun no puedes manejar!');
    hola := false;
  end else begin
    write('Ya puedes empezar a manejar!');
    hola := true;
  end;
end;

function maths(a, b, c: integer): integer;
var
  resultado: integer;
begin
  resultado := c + a * b;
  maths := resultado;
end;

begin
  hola();

  a := 3;
  b := 5;
  c := 10;
  res := maths(a, b, c);

  write('c = ', c);
  write('a = ', a);
  write('b = ', b);
  write('c + a * b = ', res);
end.
