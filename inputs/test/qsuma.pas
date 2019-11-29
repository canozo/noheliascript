program qsuma;

type t = record
    i, suma: integer;
  end;

var
  t: t;

procedure print();
begin
  if 1 > 2 then
    write('khe');
end;

begin
  t.i := 0;
  t.suma := 0;
  while t.i < 10 do
  begin
    if t.i <> 5 and t.i <> 3 then
      t.suma := t.suma + t.i
    else
      t.suma := t.suma + t.i * 2;
    t.i := t.i * t.i;
  end;
  print();
end.
