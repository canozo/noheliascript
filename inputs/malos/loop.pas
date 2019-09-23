program loop;
var
  c: integer;

begin
  { repeat until }
  c := 10;
  repeat
    write('#repeat# valor de c: ', c);
    c := c + 1;
  until c := 20;

  { while loop }
  c := 10;
  while c < 20 do
    write('[while] valor de c: ', c)

  if c > 12 then
    write('hola');

  { for loop }
  for c := 10 to 20 do
  begin
    write('(for) valor de c: ', c);
  end;
end.
