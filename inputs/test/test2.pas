program test2;

var
  a, b: integer;
  bool: boolean;

begin
  write('num a: ');
  read(a);

  write('num b: ');
  read(b);

  bool := a > b;
  if bool then
    write('a > b')
  else
    write('a <= b');
end.
