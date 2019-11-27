program psuma;
var
  i, suma: integer;

procedure print();
begin
  if 1 > 2 then
    write('khe');
end;

begin
  i := 0;
  suma := 0;
  while i < 10 do
  begin
    if i <> 5 and i <> 3 then
      suma := suma + i
    else
      suma := suma + i * 2;
    i := i * i;
  end;
  print();
end.
