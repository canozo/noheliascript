program jumps;
var
  count: integer;

begin
  count := 100;
  while count <> -1 do
    begin
      if count = 0 then
        write('count: ', count);
    end;
end.
