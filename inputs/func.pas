program func;
var
  main_vars, a, b, c: integer;

procedure the_proc(argument: integer);
var
  local: integer;
  nombre: string;
begin
  read(nombre);
  write('Hola ', nombre, '!');
  local := nil;
  local := argument;
end;

function another_one(argument: integer): integer;
var
  local: integer;
begin
  local := nil;
  another_one := local + argument - 5;
end;

begin
end.
