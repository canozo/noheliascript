program jumps;
var
  count: integer;

begin
  count := 100;
  while count <> -1 do
  begin
    if count < 3 then
      write('count: ', count);
    count := count - 1;
  end;
end.
