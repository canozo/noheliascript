program jumps;
var
  count: integer;

begin
  count := 100;
  while true do
    begin
      if count = 0 then
        continue;

      if count < 0 then begin
        break;
      end;

      write('Count: ', count);
    end;
end.
