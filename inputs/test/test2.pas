program test2;

var
  global: integer;

function suma(a, b: integer): integer;
var
  temp: integer;
begin
  global := 69;
  temp := a + b;
  suma := temp;
end;

var
  left, right: integer;
  res: integer;

begin
  global := 12;
  left := 2;
  right := 3;
  res := suma(suma(left, right), 2);
  write('2 + 3 + 2 = ', res);
  write('\nglobal = ', global);
end.
