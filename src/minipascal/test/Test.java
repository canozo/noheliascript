package minipascal.test;

import minipascal.lexer.Lexer;
import minipascal.cup_parser.parser;
import minipascal.tree.Node;
import minipascal.util.CodigoFinal;
import minipascal.util.Globals;
import minipascal.util.optimizacion.TempsPacker;
import minipascal.util.optimizacion.VariableRenamer;

import java.io.*;

// Paginas para ver los JSON:
// https://jsoneditoronline.org/
// https://vanya.jp.net/vtree/

public class Test {

    private static final boolean PRINT_ARBOL = false;
    private static final boolean PRINT_TABLAS = false;
    private static final boolean PRINT_CUADRUPLOS = true;
    private static final boolean CODIGO_FINAL = true;

    public static void main(String[] args) {
        String[] programas = {
//                "inputs/buenos/factorial.pas",
//                "inputs/buenos/func.pas",
//                "inputs/buenos/rel.pas",
//                "inputs/buenos/psuma.pas",
//                "inputs/test/qsuma.pas",
//                "inputs/test/vacio.pas",
//                "inputs/test/jumps.pas",
//                "inputs/test/caseinsensitive.pas",
//                "inputs/test/records.pas",
//                "inputs/test/quads.pas",
//                "inputs/test/ifElse.pas",
//                "inputs/test/forloops.pas",
//                "inputs/test/booleans.pas",
//                "inputs/test/test.pas",
                "inputs/test/test2.pas",
        };
        Node root;
        Reader reader;
        Lexer lexer;
        parser cupParser;
        for (String programa : programas) {
            System.out.println("Corriendo test: " + programa);
            try {
                reader = new BufferedReader(new FileReader(programa));
                lexer = new Lexer(reader);
                cupParser = new parser(lexer);

                // iniciar variables globales
                Globals.create();

                // crear el arbol
                cupParser.parse();
                root = cupParser.root;

                // verificar que no hay errores de parseo
                assert !cupParser.ERROR;

                // recorrer el arbol
                root.visit();

                // verificar que no hay errores lexicos
                assert !Globals.error;

                // reset ambito y compilar ci
                Globals.ambito = 0;
                root.compile();

                // verificar que no hay errores al momento de crear codigo intermedio
                assert !Globals.errCodigoIntermedio();

                // optimizar el codigo
                TempsPacker tp = new TempsPacker(Globals.cuadruplos);
                tp.pack();

                VariableRenamer vr = new VariableRenamer(Globals.cuadruplos);
                vr.renameGlobals();

                // verificar que no hay errores al momento de optimizar
                assert !tp.error();

                if (PRINT_ARBOL) {
                    System.out.println(root);
                }

                if (PRINT_TABLAS) {
                    Globals.printSimbolos();
                }

                if (PRINT_CUADRUPLOS) {
                    Globals.printCuadruplos();
                }

                // compilar codigo final
                if (CODIGO_FINAL) {
                    CodigoFinal cf = new CodigoFinal();
                    cf.compilar(Globals.cuadruplos);
                    System.out.println("\nPrograma en MIPS:\n");
                    System.out.println(cf.toString());
                }

            } catch (FileNotFoundException ex) {
                System.err.println(ex.toString());
            } catch (AssertionError e) {
                System.err.println("No se pasaron todas las pruebas! Saliendo.");
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("No se pasaron todas las pruebas! Saliendo.");
                System.exit(0);
            }
            System.out.println(String.format("%80s", "").replaceAll(" ", "_"));
        }
        System.out.println("Se pasaron todas las pruebas con exito!");
    }
}
