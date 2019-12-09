program matriz;

var
  x, y, i, j, contador: integer;

begin
  write('Columnas: ');
  read(x);

  write('Filas: ');
  read(y);

  write('\nMatriz de ', x);
  write(' columnas y ', y);
  write(' filas.\n');

  contador := 1;

  for i := 1 to y do
  begin
    for j := 1 to x do
    begin
      if j = 1 then
        write('  ', contador)
      else
        write(', ', contador);
      contador := contador + 1;
    end;
    write('\n');
  end;
end.
