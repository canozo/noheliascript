program factorial;
var
  prueba, result: integer;

function fact(num: integer): integer;
begin
  if num = 0 then
    fact := desconocido
  else
    fact := num * fact(num - 1);
end;

begin
  write('Ingrese un numero: ');
  read(prueba);
  result := fact(prueba) and true;
  write('Factorial de ', prueba);
  write('Resultado: ', result);
end.