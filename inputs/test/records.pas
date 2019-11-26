program records;

var
  hola: integer;
  adios: char;

type persona = record
  sexo, sangre: char;
  edad: integer;
end;

procedure hola_mundo();
begin write('hola mundo'); end;

var
  ok: boolean;
  bye: persona;

type carro = record
  modelo: char;
  num_mod: integer;
  hp: integer;
end;

var
  yo: persona;
  nave: carro;

begin
  yo.sexo := 'M';
  yo.sangre := 'O';
  yo.edad := 22;

  read(nave.modelo);
  read(nave.num_mod);
  read(nave.hp);

  write('Mi sexo es ', yo.sexo);
  write('Tengo ', yo.edad);
  write('Mi carro es un modelo ', nave.modelo);
  write('Mi carro es un num de modelo ', nave.num_mod);
  write('Mi nave tiene un horse power de ', nave.hp);
end.
