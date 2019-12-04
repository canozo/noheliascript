program test2;

var
  a, b, c, d: integer;

begin
  write('a: ');
  read(a);

  write('b: ');
  read(b);

  c := a + (2 - b) * 2;
  d := c - (a + 3) / 2;

  write('d: ', d);
  write('\n');
end.
