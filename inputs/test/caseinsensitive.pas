PROGRAM caseinsensitive;

FUNCTION PROBAR(nombre: string): boolean;
VAR err: boolean;
BEGIN
  IF nombre <> NOMBRE THEN BEGIN
    WRITE('Deberia de haber un error aqui');
    err := TRUE;
  END ELSE BEGIN
    WRITE('To bien');
    err := FALSE;
  END;
  probar := ERR;
END;

BEGIN
  probar('Billie');
END.
