program showRelations;

var
a, b, c: integer;

{
  Comentario multilinea
  Wow
}

begin
  a := 21;
  b := 10;

  if a = b then
    write('Line 1 - a is equal to b')
  else
    write('Line 1 - a is not equal to b');

  { otro comentario entre codigo }
  if a < b then
    write('Line 2 - a is less than b')
  else
    write('Line 2 - a is not less than b');

  if a > b then
    write('Line 3 - a is greater than b')
  else
    write('Line 3 - a is greater than b');

  { Lets change value of a and b }
  a := 5;
  b := 20;

  if a <= b then
    write('Line 4 - a is either less than or equal to b');

  if (b >= a) then
    write('Line 5 - b is either greater than  or equal to ');

  { while loop }
  c := 10;
  while c < 20 do
  begin
    write('[while] value of c: ', c);
    c := c + 1;
  end;

  { for loop }
  for c := 10 to 20 do
  begin
    write('(for) value of c: ', c);
  end;

  { repeat until }
  c := 10;
  repeat
    write('#repeat# value of c: ', c);
    c := c + 1;
  until c = 20;
end.