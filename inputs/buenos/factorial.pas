program factorial;
var
  prueba, result: integer;

function fact(num: integer): integer;
begin
  if num = 0 then
    fact := 1
  else
    fact := num * fact(num - 1);
end;

begin
  write('Ingrese un numero: ');
  read(prueba);
  result := fact(prueba);
  write('Factorial de ', prueba);
  write('Resultado: ', result);
end.