program showRelations;
var
a, b: integer;

{
Comentario multilinea

Wow
o
w
}

begin
   a := 21;
   b := 10;

   if a = b then
      write('Line 1 - a is equal to b' )
   else
      write('Line 1 - a is not equal to b' );

   { otro comentario entre codigo }
   if  a < b then
      write('Line 2 - a is less than b' )
   else
      write('Line 2 - a is not less than b' );

   if  a > b then
      write('Line 3 - a is greater than b' )
   else
      write('Line 3 - a is greater than b' );

   { Lets change value of a and b }
   a := 5;
   b := 20;

   if  a <= b then
      write('Line 4 - a is either less than or equal to b' );

   if ( b >= a ) then
      write('Line 5 - b is either greater than  or equal to ' );
end.