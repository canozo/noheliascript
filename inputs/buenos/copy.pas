program copy;

type persona = record
    edad, puntos: integer;
    sexo: char;
  end;

var
  yo, otro: persona;

begin
  yo.edad := 89;
  yo.puntos := 1000;
  yo.sexo := 'M';

  otro := yo;

  write('\nyo.edad = ', yo.edad);
  write('\notro.edad = ', otro.edad);

  write('\nyo.puntos = ', yo.puntos);
  write('\notro.puntos = ', otro.puntos);

  write('\nyo.sexo = ', yo.sexo);
  write('\notro.sexo = ', otro.sexo);
end.
