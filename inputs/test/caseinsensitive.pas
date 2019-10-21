PROGRAM caseinsensitive;

FUNCTION PROBAR(edad: integer): boolean;
VAR err: boolean;
BEGIN
  IF edad <> EDAD THEN BEGIN
    WRITE('Deberia de haber un error aqui');
    err := TRUE;
  END ELSE BEGIN
    WRITE('To bien');
    err := FALSE;
  END;
  probar := ERR;
END;

BEGIN
  probar(22);
END.
