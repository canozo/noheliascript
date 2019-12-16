program test2;

function suma(a, b: integer): integer;
{
var
  temp: integer;
}
begin
  {
  temp := a + b;
  suma := temp;
  }
  suma := a + b;
end;

var
  {
  left, right: integer;
  }
  res: integer;

begin
  {
  left := 2;
  right := 3;
  }
  res := suma(2, 3);
  write('2 + 3 = : ', res);
end.
