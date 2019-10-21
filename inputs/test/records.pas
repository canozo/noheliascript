program records;

type persona = record
  nombre, apellido: string;
  edad: integer;
end;

type carro = record
  modelo: string;
  hp: integer;
end;

var
  otro: string;
  yo: persona;
  nave: carro;

begin
  otro := 'Otro';
  yo.nombre := 'Javier';
  yo.apellido := 'Cano';
  yo.edad := 22;

  read(nave.modelo);
  read(nave.hp);

  write('Mi nombre es ', yo.nombre);
  write('Tengo ', yo.edad);
  write('Mi carro es un ', nave.modelo);
  write('Mi nave tiene un horse power de ', nave.hp);
end.
