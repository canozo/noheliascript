program quads;

type persona = record
  edad: integer;
end;

var
  a, b, c, d, e: integer;
  yo: persona;

begin
  yo.edad := 23;
  a := 2;
  b := a + 3;
  c := a + b;
  d := yo.edad + a * b / (c + 1);
  e := yo.edad + 3;
end.
