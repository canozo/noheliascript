program test;

var
  contador, sesion: integer;

begin
  for contador := 0 to 100 do
    for sesion := 0 to 50 do
    begin
      if contador mod 3 = 0 then
        write('fizz');
      if contador mod 5 = 0 then
        write('buzz');
    end;
end.
