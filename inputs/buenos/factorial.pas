program factorial;
var
  prueba: integer;

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
  write('El factorial de ', prueba, ' es ', fact(prueba));
end.