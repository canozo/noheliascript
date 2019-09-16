/* The following code was generated by JFlex 1.7.0 */

package minipascal.Lexer;

import java_cup.runtime.*;
import minipascal.Parser.sym;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>/Users/neuceda/Documents/OneDrive - Laureate Education/noheliascript/src/minipascal/Lexer/minipascal.flex</tt>
 */
public class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COMMENT = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\2\1\10\1\7\1\52\1\52\1\6\22\2\1\10\6\2\1\1"+
    "\1\42\1\43\1\51\1\50\1\44\1\50\1\5\1\51\12\4\1\40"+
    "\1\45\1\46\1\41\1\47\2\2\1\30\1\31\1\20\1\26\1\25"+
    "\1\16\1\27\1\32\1\14\2\3\1\15\1\35\1\12\1\22\1\23"+
    "\1\3\1\24\1\34\1\21\1\17\1\36\1\37\3\3\4\2\1\3"+
    "\1\2\1\30\1\31\1\20\1\26\1\25\1\16\1\27\1\32\1\14"+
    "\2\3\1\15\1\35\1\12\1\22\1\23\1\3\1\24\1\34\1\21"+
    "\1\17\1\36\1\37\3\3\1\11\1\2\1\54\2\2\5\0\1\53"+
    "\252\0\2\13\115\0\1\33\u1ea8\0\1\53\1\53\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\2\1\1\2\1\3\1\4\2\5\1\6\1\7"+
    "\1\2\1\1\14\2\1\1\4\2\1\10\1\11\1\12"+
    "\1\13\1\14\1\15\2\11\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\3\0\2\2\1\0\1\24\1\2\1\24"+
    "\6\2\1\25\2\2\1\26\4\2\1\0\1\2\1\27"+
    "\3\2\1\0\5\2\1\30\1\31\1\0\1\32\2\33"+
    "\1\34\1\0\2\2\1\35\12\2\1\36\1\0\1\2"+
    "\1\17\1\37\2\2\1\0\1\2\1\40\1\0\1\2"+
    "\1\0\1\2\1\41\1\0\2\2\1\0\1\2\1\0"+
    "\1\2\1\0\1\2\1\42\1\43\1\44\4\2\1\45"+
    "\1\46\2\47\1\2\1\0\1\2\1\0\1\2\1\0"+
    "\1\2\1\0\1\2\1\0\2\2\2\50\2\51\2\52"+
    "\5\2\2\53\1\0\1\2\2\54\2\55\1\0\1\2"+
    "\1\0\3\2\1\56\1\57\1\2\2\60\2\61\1\0"+
    "\2\2\1\62\1\63\2\64\1\2\1\65";

  private static int [] zzUnpackAction() {
    int [] result = new int[188];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\55\0\132\0\207\0\264\0\341\0\132\0\u010e"+
    "\0\132\0\u013b\0\132\0\u0168\0\u0195\0\u01c2\0\u01ef\0\u021c"+
    "\0\u0249\0\u0276\0\u02a3\0\u02d0\0\u02fd\0\u032a\0\u0357\0\u0384"+
    "\0\u03b1\0\u03de\0\u040b\0\u0438\0\u0465\0\u0492\0\u04bf\0\132"+
    "\0\132\0\132\0\132\0\132\0\u04ec\0\u0519\0\132\0\132"+
    "\0\132\0\132\0\132\0\132\0\u0546\0\u0573\0\u05a0\0\u05cd"+
    "\0\u05fa\0\u0627\0\132\0\u0654\0\264\0\u0681\0\u06ae\0\u06db"+
    "\0\u0708\0\u0735\0\u0762\0\264\0\u078f\0\u07bc\0\264\0\u07e9"+
    "\0\u0816\0\u0843\0\u0870\0\u089d\0\u08ca\0\264\0\u08f7\0\u0924"+
    "\0\u0951\0\u097e\0\u09ab\0\u09d8\0\u0a05\0\u0a32\0\u0a5f\0\132"+
    "\0\132\0\u0a8c\0\u0573\0\132\0\264\0\264\0\u0ab9\0\u0ae6"+
    "\0\u0b13\0\264\0\u0b40\0\u0b6d\0\u0b9a\0\u0bc7\0\u0bf4\0\u0c21"+
    "\0\u0c4e\0\u0c7b\0\u0ca8\0\u0cd5\0\264\0\u0d02\0\u0d2f\0\264"+
    "\0\264\0\u0d5c\0\u0d89\0\u0db6\0\u0de3\0\264\0\u0e10\0\u0e3d"+
    "\0\u0e6a\0\u0e97\0\132\0\u0ec4\0\u0ef1\0\u0f1e\0\u0f4b\0\u0f78"+
    "\0\u0fa5\0\u0fd2\0\u0fff\0\u102c\0\264\0\264\0\264\0\u1059"+
    "\0\u1086\0\u10b3\0\u10e0\0\264\0\264\0\132\0\264\0\u110d"+
    "\0\u113a\0\u1167\0\u1194\0\u11c1\0\u11ee\0\u121b\0\u1248\0\u1275"+
    "\0\u12a2\0\u12cf\0\u12fc\0\132\0\264\0\132\0\264\0\132"+
    "\0\264\0\u1329\0\u1356\0\u1383\0\u13b0\0\u13dd\0\132\0\264"+
    "\0\u140a\0\u1437\0\132\0\264\0\132\0\264\0\u1464\0\u1491"+
    "\0\u14be\0\u14eb\0\u1518\0\u1545\0\264\0\264\0\u1572\0\132"+
    "\0\264\0\132\0\264\0\u159f\0\u15cc\0\u15f9\0\264\0\264"+
    "\0\132\0\264\0\u1626\0\264";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[188];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\3\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\5\1\17\1\20"+
    "\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\5"+
    "\1\30\1\31\1\5\1\32\1\33\1\34\1\35\1\36"+
    "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46"+
    "\1\47\1\50\2\51\1\3\6\52\1\10\1\11\1\52"+
    "\1\53\40\52\2\51\1\54\57\0\11\55\1\0\17\55"+
    "\1\0\17\55\1\0\1\55\3\0\2\5\5\0\1\5"+
    "\1\0\17\5\1\0\4\5\21\0\1\6\1\56\56\0"+
    "\1\11\55\0\1\12\47\0\2\5\5\0\1\5\1\57"+
    "\1\60\5\5\1\61\10\5\1\0\4\5\27\0\1\62"+
    "\3\0\1\63\41\0\2\5\5\0\1\64\1\0\2\5"+
    "\1\65\14\5\1\0\4\5\20\0\2\5\5\0\1\5"+
    "\1\0\3\5\1\66\2\5\1\67\5\5\1\70\2\5"+
    "\1\0\4\5\20\0\2\5\5\0\1\71\1\0\17\5"+
    "\1\0\4\5\20\0\2\5\5\0\1\5\1\0\6\5"+
    "\1\72\7\5\1\73\1\0\4\5\20\0\2\5\5\0"+
    "\1\5\1\0\6\5\1\74\1\5\1\75\5\5\1\76"+
    "\1\0\4\5\20\0\2\5\5\0\1\5\1\0\10\5"+
    "\1\77\6\5\1\0\4\5\20\0\2\5\5\0\1\5"+
    "\1\0\10\5\1\100\6\5\1\0\4\5\20\0\2\5"+
    "\5\0\1\5\1\0\11\5\1\101\5\5\1\0\4\5"+
    "\20\0\2\5\5\0\1\102\1\0\1\5\1\103\15\5"+
    "\1\0\4\5\20\0\2\5\5\0\1\5\1\104\1\105"+
    "\5\5\1\106\10\5\1\0\4\5\20\0\2\5\5\0"+
    "\1\107\1\0\17\5\1\0\4\5\20\0\2\5\5\0"+
    "\1\5\1\0\6\5\1\110\2\5\1\111\5\5\1\0"+
    "\4\5\36\0\1\112\36\0\2\5\5\0\1\5\1\0"+
    "\5\5\1\113\11\5\1\0\4\5\20\0\2\5\5\0"+
    "\1\5\1\0\6\5\1\114\10\5\1\0\4\5\20\0"+
    "\2\5\5\0\1\5\1\0\14\5\1\115\2\5\1\0"+
    "\4\5\20\0\2\5\5\0\1\5\1\0\10\5\1\116"+
    "\5\5\1\117\1\0\4\5\56\0\1\120\54\0\1\40"+
    "\5\0\1\40\46\0\1\40\14\0\1\121\11\122\1\0"+
    "\17\122\1\0\17\122\1\0\1\122\4\0\1\123\65\0"+
    "\1\124\42\0\2\5\5\0\1\5\1\0\1\5\1\125"+
    "\15\5\1\0\4\5\20\0\2\5\5\0\1\5\1\0"+
    "\5\5\1\126\11\5\1\0\4\5\36\0\1\127\36\0"+
    "\2\5\5\0\1\5\1\0\5\5\1\130\11\5\1\0"+
    "\4\5\20\0\2\5\5\0\1\131\1\0\17\5\1\0"+
    "\4\5\20\0\2\5\5\0\1\5\1\0\10\5\1\132"+
    "\6\5\1\0\4\5\20\0\2\5\5\0\1\5\1\0"+
    "\1\5\1\133\15\5\1\0\4\5\20\0\2\5\5\0"+
    "\1\5\1\0\5\5\1\134\11\5\1\0\4\5\20\0"+
    "\2\5\5\0\1\135\1\0\17\5\1\0\4\5\20\0"+
    "\2\5\5\0\1\5\1\0\14\5\1\136\2\5\1\0"+
    "\4\5\20\0\2\5\5\0\1\5\1\0\3\5\1\137"+
    "\13\5\1\0\4\5\20\0\2\5\5\0\1\5\1\0"+
    "\11\5\1\140\5\5\1\0\4\5\20\0\2\5\5\0"+
    "\1\5\1\0\6\5\1\141\10\5\1\0\4\5\20\0"+
    "\2\5\5\0\1\5\1\0\4\5\1\142\2\5\1\143"+
    "\4\5\1\144\2\5\1\0\4\5\20\0\2\5\5\0"+
    "\1\5\1\0\12\5\1\145\4\5\1\0\4\5\20\0"+
    "\2\5\5\0\1\5\1\0\17\5\1\146\1\147\3\5"+
    "\53\0\1\50\21\0\2\5\5\0\1\5\1\0\17\5"+
    "\1\0\2\5\1\150\1\5\20\0\2\5\5\0\1\5"+
    "\1\0\12\5\1\151\4\5\1\0\4\5\20\0\2\5"+
    "\5\0\1\5\1\0\6\5\1\152\10\5\1\0\4\5"+
    "\20\0\2\5\5\0\1\5\1\0\13\5\1\153\3\5"+
    "\1\0\4\5\41\0\1\154\33\0\2\5\5\0\1\5"+
    "\1\0\10\5\1\155\6\5\1\0\4\5\20\0\2\5"+
    "\5\0\1\5\1\0\12\5\1\150\4\5\1\0\4\5"+
    "\20\0\2\5\5\0\1\5\1\0\10\5\1\156\6\5"+
    "\1\0\4\5\20\0\2\5\5\0\1\5\1\157\1\160"+
    "\16\5\1\0\4\5\20\0\2\5\5\0\1\5\1\161"+
    "\1\162\16\5\1\0\4\5\16\0\1\163\11\122\1\0"+
    "\17\122\1\0\17\122\1\0\1\122\25\0\1\164\32\0"+
    "\2\5\5\0\1\5\1\0\11\5\1\165\5\5\1\0"+
    "\4\5\20\0\2\5\5\0\1\5\1\0\4\5\1\166"+
    "\12\5\1\0\4\5\20\0\2\5\5\0\1\5\1\0"+
    "\17\5\1\167\1\170\3\5\20\0\2\5\5\0\1\5"+
    "\1\171\1\172\16\5\1\0\4\5\20\0\2\5\5\0"+
    "\1\5\1\0\17\5\1\173\1\174\3\5\20\0\2\5"+
    "\5\0\1\5\1\0\10\5\1\175\6\5\1\0\4\5"+
    "\20\0\2\5\5\0\1\5\1\0\11\5\1\176\5\5"+
    "\1\0\4\5\20\0\2\5\5\0\1\177\1\0\17\5"+
    "\1\0\4\5\20\0\2\5\5\0\1\5\1\0\4\5"+
    "\1\200\6\5\1\201\3\5\1\0\4\5\20\0\2\5"+
    "\5\0\1\5\1\0\6\5\1\202\10\5\1\0\4\5"+
    "\20\0\2\5\5\0\1\5\1\0\11\5\1\203\5\5"+
    "\1\0\4\5\20\0\2\5\5\0\1\5\1\0\1\5"+
    "\1\204\10\5\1\205\4\5\1\0\4\5\42\0\1\206"+
    "\32\0\2\5\5\0\1\5\1\0\11\5\1\207\5\5"+
    "\1\0\4\5\20\0\2\5\5\0\1\5\1\0\1\5"+
    "\1\210\15\5\1\0\4\5\20\0\2\5\5\0\1\5"+
    "\1\211\1\212\16\5\1\0\4\5\30\0\2\213\43\0"+
    "\2\5\5\0\1\5\1\213\1\214\16\5\1\0\4\5"+
    "\36\0\1\215\36\0\2\5\5\0\1\5\1\0\5\5"+
    "\1\216\11\5\1\0\4\5\32\0\1\217\42\0\2\5"+
    "\5\0\1\5\1\0\1\5\1\220\15\5\1\0\4\5"+
    "\44\0\1\221\30\0\2\5\5\0\1\5\1\0\13\5"+
    "\1\222\3\5\1\0\4\5\20\0\2\5\5\0\1\5"+
    "\1\0\5\5\1\223\11\5\1\0\4\5\42\0\1\224"+
    "\32\0\2\5\5\0\1\5\1\0\11\5\1\225\5\5"+
    "\1\0\4\5\32\0\1\226\42\0\2\5\5\0\1\5"+
    "\1\0\1\5\1\227\15\5\1\0\4\5\36\0\1\230"+
    "\36\0\2\5\5\0\1\5\1\0\5\5\1\231\11\5"+
    "\1\0\4\5\20\0\2\5\5\0\1\5\1\0\11\5"+
    "\1\232\5\5\1\0\4\5\20\0\2\5\5\0\1\5"+
    "\1\0\10\5\1\233\6\5\1\0\4\5\20\0\2\5"+
    "\5\0\1\5\1\0\10\5\1\234\6\5\1\0\4\5"+
    "\20\0\2\5\5\0\1\5\1\0\14\5\1\235\2\5"+
    "\1\0\4\5\20\0\2\5\5\0\1\5\1\0\11\5"+
    "\1\236\5\5\1\0\4\5\27\0\1\237\45\0\2\5"+
    "\5\0\1\240\1\0\17\5\1\0\4\5\27\0\1\241"+
    "\45\0\2\5\5\0\1\242\1\0\17\5\1\0\4\5"+
    "\42\0\1\243\32\0\2\5\5\0\1\5\1\0\11\5"+
    "\1\244\5\5\1\0\4\5\42\0\1\245\32\0\2\5"+
    "\5\0\1\5\1\0\11\5\1\246\5\5\1\0\4\5"+
    "\42\0\1\247\32\0\2\5\5\0\1\5\1\0\11\5"+
    "\1\250\5\5\1\0\4\5\20\0\2\5\5\0\1\5"+
    "\1\251\1\252\16\5\1\0\4\5\20\0\2\5\5\0"+
    "\1\5\1\0\12\5\1\253\4\5\1\0\4\5\20\0"+
    "\2\5\5\0\1\5\1\0\14\5\1\254\2\5\1\0"+
    "\4\5\20\0\2\5\5\0\1\5\1\0\12\5\1\255"+
    "\4\5\1\0\4\5\20\0\2\5\5\0\1\5\1\0"+
    "\5\5\1\256\11\5\1\0\4\5\20\0\2\5\5\0"+
    "\1\5\1\0\14\5\1\257\2\5\1\0\4\5\44\0"+
    "\1\260\30\0\2\5\5\0\1\5\1\0\13\5\1\261"+
    "\3\5\1\0\4\5\41\0\1\262\33\0\2\5\5\0"+
    "\1\5\1\0\10\5\1\263\6\5\1\0\4\5\37\0"+
    "\1\264\35\0\2\5\5\0\1\5\1\0\6\5\1\265"+
    "\10\5\1\0\4\5\20\0\2\5\5\0\1\5\1\0"+
    "\3\5\1\266\13\5\1\0\4\5\20\0\2\5\5\0"+
    "\1\5\1\0\17\5\1\0\1\5\1\267\2\5\20\0"+
    "\2\5\5\0\1\270\1\0\17\5\1\0\4\5\27\0"+
    "\1\271\45\0\2\5\5\0\1\272\1\0\17\5\1\0"+
    "\4\5\20\0\2\5\5\0\1\5\1\0\10\5\1\273"+
    "\6\5\1\0\4\5\20\0\2\5\5\0\1\5\1\0"+
    "\11\5\1\274\5\5\1\0\4\5\15\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5715];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\3\1\1\11\1\1\1\11\1\1\1\11"+
    "\24\1\5\11\2\1\6\11\3\0\2\1\1\0\1\11"+
    "\20\1\1\0\5\1\1\0\5\1\2\11\1\0\1\1"+
    "\1\11\2\1\1\0\16\1\1\0\5\1\1\0\2\1"+
    "\1\0\1\1\1\0\1\1\1\11\1\0\2\1\1\0"+
    "\1\1\1\0\1\1\1\0\12\1\1\11\2\1\1\0"+
    "\1\1\1\0\1\1\1\0\1\1\1\0\1\1\1\0"+
    "\2\1\1\11\1\1\1\11\1\1\1\11\6\1\1\11"+
    "\1\1\1\0\1\1\1\11\1\1\1\11\1\1\1\0"+
    "\1\1\1\0\6\1\1\11\1\1\1\11\1\1\1\0"+
    "\4\1\1\11\3\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[188];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true iff the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
  public static String archivoInput = "inputs/rel.pas";
  private final boolean DEBUG = true;

  private Symbol symbol(int type) {
    if (DEBUG) {
      //System.out.println("Token: " + yytext());
      //System.out.println("Line: " + yyline);
      //System.out.println("Col:  " + yycolumn);
      //System.out.println();
    }
    return new Symbol(type, yyline, yycolumn);
  }

  private Symbol symbol(int type, Object value) {
    if (DEBUG) {
      //System.out.println("Token: " + yytext());
      //System.out.println("Line: " + yyline);
      //System.out.println("Col:  " + yycolumn);
      //System.out.println();
    }
    return new Symbol(type, yyline, yycolumn, value);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 208) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { throw new Error("Error, caracter no reconocido: '" + yytext() + "'!");
            } 
            // fall through
          case 54: break;
          case 2: 
            { return symbol(sym.ID, yytext());
            } 
            // fall through
          case 55: break;
          case 3: 
            { return symbol(sym.NUM_INTEGER, new Integer(yytext()));
            } 
            // fall through
          case 56: break;
          case 4: 
            { return symbol(sym.PUNTO);
            } 
            // fall through
          case 57: break;
          case 5: 
            { /* skip saltos de linea */
            } 
            // fall through
          case 58: break;
          case 6: 
            { /* skip espacios blancos y tabs */
            } 
            // fall through
          case 59: break;
          case 7: 
            { yybegin(COMMENT);
            } 
            // fall through
          case 60: break;
          case 8: 
            { return symbol(sym.DOSPUNTOS);
            } 
            // fall through
          case 61: break;
          case 9: 
            { return symbol(sym.OP_RELACIONAL, yytext());
            } 
            // fall through
          case 62: break;
          case 10: 
            { return symbol(sym.PAR_IZQ);
            } 
            // fall through
          case 63: break;
          case 11: 
            { return symbol(sym.PAR_DER);
            } 
            // fall through
          case 64: break;
          case 12: 
            { return symbol(sym.COMA);
            } 
            // fall through
          case 65: break;
          case 13: 
            { return symbol(sym.PUNTOCOMA);
            } 
            // fall through
          case 66: break;
          case 14: 
            { return symbol(sym.OP_SUMA, yytext());
            } 
            // fall through
          case 67: break;
          case 15: 
            { return symbol(sym.OP_MULT, yytext());
            } 
            // fall through
          case 68: break;
          case 16: 
            { System.out.println("Error!");
  System.out.println("Linea " + yyline);
  System.out.println("Columna " + yycolumn);
  System.out.println("Input " + yytext());

  throw new Error("Error, caracter ilegal!");
            } 
            // fall through
          case 69: break;
          case 17: 
            { /* skip comentario */
            } 
            // fall through
          case 70: break;
          case 18: 
            { throw new Error("No se permiten comentarios anidados!");
            } 
            // fall through
          case 71: break;
          case 19: 
            { yybegin(YYINITIAL);
            } 
            // fall through
          case 72: break;
          case 20: 
            { return symbol(sym.IF);
            } 
            // fall through
          case 73: break;
          case 21: 
            { return symbol(sym.TO);
            } 
            // fall through
          case 74: break;
          case 22: 
            { return symbol(sym.OR);
            } 
            // fall through
          case 75: break;
          case 23: 
            { return symbol(sym.DO);
            } 
            // fall through
          case 76: break;
          case 24: 
            { return symbol(sym.ASIGNACION);
            } 
            // fall through
          case 77: break;
          case 25: 
            { return symbol(sym.CONSTCHAR, new Character(yycharat(1)));
            } 
            // fall through
          case 78: break;
          case 26: 
            { return symbol(sym.NUM_FLOAT, new Float(yytext()));
            } 
            // fall through
          case 79: break;
          case 27: 
            { return symbol(sym.NIL);
            } 
            // fall through
          case 80: break;
          case 28: 
            { return symbol(sym.NOT);
            } 
            // fall through
          case 81: break;
          case 29: 
            { return symbol(sym.FOR);
            } 
            // fall through
          case 82: break;
          case 30: 
            { return symbol(sym.END);
            } 
            // fall through
          case 83: break;
          case 31: 
            { return symbol(sym.AND);
            } 
            // fall through
          case 84: break;
          case 32: 
            { return symbol(sym.VAR);
            } 
            // fall through
          case 85: break;
          case 33: 
            { return symbol(sym.CONSTSTR, yytext().substring(1, yylength() - 2));
            } 
            // fall through
          case 86: break;
          case 34: 
            { return symbol(sym.CHAR);
            } 
            // fall through
          case 87: break;
          case 35: 
            { return symbol(sym.CONSTBOOL, Boolean.TRUE);
            } 
            // fall through
          case 88: break;
          case 36: 
            { return symbol(sym.THEN);
            } 
            // fall through
          case 89: break;
          case 37: 
            { return symbol(sym.REAL);
            } 
            // fall through
          case 90: break;
          case 38: 
            { return symbol(sym.FUNC_READ);
            } 
            // fall through
          case 91: break;
          case 39: 
            { return symbol(sym.ELSE);
            } 
            // fall through
          case 92: break;
          case 40: 
            { return symbol(sym.CONSTBOOL, Boolean.FALSE);
            } 
            // fall through
          case 93: break;
          case 41: 
            { return symbol(sym.UNTIL);
            } 
            // fall through
          case 94: break;
          case 42: 
            { return symbol(sym.CONST);
            } 
            // fall through
          case 95: break;
          case 43: 
            { return symbol(sym.BEGIN);
            } 
            // fall through
          case 96: break;
          case 44: 
            { return symbol(sym.FUNC_WRITE);
            } 
            // fall through
          case 97: break;
          case 45: 
            { return symbol(sym.WHILE);
            } 
            // fall through
          case 98: break;
          case 46: 
            { return symbol(sym.RECORD);
            } 
            // fall through
          case 99: break;
          case 47: 
            { return symbol(sym.REPEAT);
            } 
            // fall through
          case 100: break;
          case 48: 
            { return symbol(sym.STRING);
            } 
            // fall through
          case 101: break;
          case 49: 
            { return symbol(sym.INTEGER);
            } 
            // fall through
          case 102: break;
          case 50: 
            { return symbol(sym.PROGRAM);
            } 
            // fall through
          case 103: break;
          case 51: 
            { return symbol(sym.BOOLEAN);
            } 
            // fall through
          case 104: break;
          case 52: 
            { return symbol(sym.FUNCTION);
            } 
            // fall through
          case 105: break;
          case 53: 
            { return symbol(sym.PROCEDURE);
            } 
            // fall through
          case 106: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
