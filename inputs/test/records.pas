program records;
{
type persona = record
  nombre: string;
  edad: integer;
end;

type carro = record
  modelo: string;
  hp: integer;
end;
}

var
  otro: string;
  yo: persona;

begin
  otro := 'Otro';
  yo.nombre := 'Javier';
  yo.edad := 22;
  write('Mi nombre es ', yo.nombre, ' y tengo ', yo.edad);
end.
