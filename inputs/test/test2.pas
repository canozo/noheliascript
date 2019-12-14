program test2;

type coordenada = record
  x, y: integer;
end;

type variables = record
  i, j, contador: integer;
end;

var
  c: coordenada;
  v: variables;

begin
  write('Columnas: ');
  read(c.x);

  write('Filas: ');
  read(c.y);

  write('\nMatriz de ', c.x);
  write(' columnas y ', c.y);
  write(' filas.\n');

  v.contador := 1;

  for v.i := 1 to c.y do
  begin
    for v.j := 1 to c.x do
    begin
{
      if v.j = 1 then
        write('  ', v.contador)
      else
        write(', ', v.contador);
      v.contador := v.contador + 1;
}
    end;
    write('\n');
  end;
  write('\ntermino');
end.
