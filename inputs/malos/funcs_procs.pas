program funcs_procs;
var
  a, b: integer;

function mi_func(hola);
begin
  write('Hola desde mi funcion!');
end;

procedure mi_proc();
begin
  write('Hola desde mi procedure!');
end;

begin
  a := 21;
  b := 10;

  if a = b then
    write('a y b son iguales!');
  else
    write('a y b son distintos!');

  { Ver cual es mayor }
  if a < b then
    write('a es menor que b!')
  else
    write('b es menor que a!');
end.
