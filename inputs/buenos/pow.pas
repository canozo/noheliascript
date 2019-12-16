program pow;

function power(num, e: integer): integer;
begin
  if e = 0 then
    power := 1
  else
    power := num * power(num, e - 1);
end;

var
  a, elevado, res: integer;

begin
  write('a: ');
  read(a);

  write('elevado a: ');
  read(elevado);

  res := power(a, elevado);
  write('resultado: ', res);
end.