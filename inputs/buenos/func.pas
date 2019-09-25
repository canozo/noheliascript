program func;
var
  nombre, a, b, c: integer;

procedure hola(nombre: string);
var
  edad: integer;
begin
  write('Hola ', nombre, '! Cual es tu edad? ');
  read(edad);
  if edad < 18 and nombre <> 'JOH' then begin
    write('Aun no puedes manejar, ', nombre, '!');
  end else begin
    write('Ya puedes empezar a manejar, ', nombre, '!');
  end;
end;

function maths(a, b, c: integer): integer;
var
  resultado: integer;
begin
  resultado := c + a * b;
  maths := resultado
end;

begin
  nombre := 'Jose';
  hola(nombre);

  a := 3;
  b := 5;
  c := 10;

  write('El resultado de ', c, '+', a, '*', b, '=', maths(a, b, c));
end.
