program func;
var
  a, b, c, res: integer;

function hola(): boolean;
var
  edad: integer;
begin
  write('Hola!');
  write('Cual es tu edad? ');
  read(edad);
  if edad < 18 then begin
    write('Aun no puedes manejar!');
    hola := false;
  end else begin
    write('Ya puedes empezar a manejar!');
  end;
  hola := 'A';
end;

function maths(a, b, c: integer): integer;
var
  resultado: boolean;
begin
  resultado := c and a = a and b;
  resultado := c = a and a = b;
  resultado := c = a or a = b;
  resultado := c or a = a or b;
  resultado := c and a = a or b;
  resultado := c or a = a and b;
  resultado := c = 2 and a + 3;
  resultado := c = 2 and a + 3 or b <= 10;

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
