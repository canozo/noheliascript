program records;

type persona = record
  sexo, sangre: char;
  edad: integer;
end;

type carro = record
  modelo: char;
  num_modelo: integer;
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
  read(nave.num_modelo);
  read(nave.hp);

  write('Mi sexo es ', yo.sexo);
  write('Tengo ', yo.edad);
  write('Mi carro es un modelo ', nave.modelo);
  write('Mi carro es un num de modelo ', nave.num_modelo);
  write('Mi nave tiene un horse power de ', nave.hp);
end.
