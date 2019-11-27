{ TODO correr bien }

program booleans;

var
  a, b, c: integer;
  d, e, f: boolean;

begin
  a := 1;
  b := 2;
  d := a = b;

  if d then
    write('d es true')
  else
    write('d es false');
end.
