program test2;

function suma(a, b: integer): integer;
begin
  suma := a + b;
end;

var
  left, right: integer;
  res: integer;

begin
  left := 2;
  right := 3;
  res := suma(suma(left, right), 2);
  write('2 + 3 + 2 = ', res);
end.
