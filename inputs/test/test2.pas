program test2;

type wow = record
  edad: integer;
end;

var
  a: integer;
  xd, xd2: wow;

begin
  a := 69;

  read(xd.edad);

  xd.edad := a;
  xd2.edad := xd.edad;

  write('xd.edad = ', xd.edad);
  write('xd2.edad = ', xd2.edad);

  {
  xd := xd2;
  xd.edad := xd2.edad;
  }
end.
