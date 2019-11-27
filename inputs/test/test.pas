program test;

type persona = record
  edad: integer;
  sexo: char;
  casado: boolean;
end;

var
  yo: persona;
  contador: integer;

begin
  yo.edad := 23;
  for contador := 0 to yo.edad do
    write('Mi edad es de ', contador);

  for yo.edad := 0 to contador do
    write('Mi edad es de ', yo.edad);
end.
