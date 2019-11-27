program forloops;

var
  contador, sesion: integer;

begin
  for contador := 0 to 20 do
    for sesion := 0 to 5 do
    begin
      if contador mod 3 = 0 then
        write('fizz: ', sesion);
      if contador mod 5 = 0 then
        write('buzz: ', sesion);
    end;
end.
