// DO NOT EDIT
// Generated by JFlex 1.9.1 http://jflex.de/
// source: Web_designer_API/src/main/java/analysis_resources/XML_Lexer.jflex

/* codigo de usuario */
package compi1.web_designer_api.htmltraductor;

import java_cup.runtime.*;
import java.util.*;
import compi1.web_designer_api.util.Token;


@SuppressWarnings("fallthrough")
public class XMLlexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;
  public static final int INPUT = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1,  1,  2, 2
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\1\u0100\u10fe\u0200";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
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
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\0\1\1\1\3\22\0\1\1"+
    "\1\0\1\4\14\0\1\5\14\0\1\6\1\7\1\10"+
    "\2\0\1\11\1\12\1\13\1\0\1\14\3\0\1\15"+
    "\2\0\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\26\1\27\1\30\4\0\1\31\1\32\4\0"+
    "\1\11\1\12\1\13\1\0\1\14\3\0\1\15\2\0"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\271\0\2\15\115\0\1\25\u0180\0";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[768];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
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
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\2\2\1\3\1\4\1\5\1\6\1\7"+
    "\5\1\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\6\0\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\21\0\1\27\1\30\2\0\1\31\5\0\1\32"+
    "\1\33\1\34\1\0\1\35\1\36\1\37\1\40";

  private static int [] zzUnpackAction() {
    int [] result = new int[72];
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
    "\0\0\0\33\0\66\0\121\0\121\0\154\0\121\0\121"+
    "\0\121\0\121\0\121\0\207\0\242\0\275\0\330\0\363"+
    "\0\121\0\u010e\0\121\0\u0129\0\u0144\0\121\0\u015f\0\u017a"+
    "\0\u0195\0\u01b0\0\u01cb\0\u01e6\0\u0201\0\121\0\121\0\121"+
    "\0\121\0\121\0\121\0\121\0\121\0\u021c\0\u0237\0\u0252"+
    "\0\u026d\0\u0288\0\u02a3\0\u02be\0\u02d9\0\u02f4\0\u030f\0\u032a"+
    "\0\u0345\0\u0360\0\u037b\0\u0396\0\u03b1\0\u03cc\0\121\0\u03e7"+
    "\0\u0402\0\u041d\0\121\0\u0438\0\u0453\0\u046e\0\u0489\0\u04a4"+
    "\0\121\0\u04bf\0\u04da\0\u04f5\0\121\0\121\0\u0510\0\121";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[72];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\2\5\1\6\1\7\1\10\1\11\1\12\1\13"+
    "\1\14\2\4\1\15\3\4\1\16\1\4\1\17\5\4"+
    "\1\20\1\21\1\4\2\22\2\4\1\23\25\22\1\24"+
    "\2\25\2\4\1\26\25\25\1\27\35\0\1\5\43\0"+
    "\1\30\12\0\1\31\32\0\1\32\25\0\1\33\22\0"+
    "\1\34\32\0\1\35\21\0\2\22\3\0\25\22\5\0"+
    "\1\36\13\0\1\37\3\0\1\40\1\0\1\41\4\0"+
    "\2\25\3\0\25\25\5\0\1\42\13\0\1\43\3\0"+
    "\1\44\1\0\1\45\17\0\1\46\43\0\1\47\23\0"+
    "\1\50\34\0\1\51\37\0\1\52\24\0\1\53\31\0"+
    "\1\54\32\0\1\55\40\0\1\56\21\0\1\57\31\0"+
    "\1\60\42\0\1\61\32\0\1\62\23\0\1\63\47\0"+
    "\1\64\27\0\1\65\25\0\1\66\37\0\1\67\26\0"+
    "\1\70\41\0\1\71\17\0\1\72\32\0\1\73\32\0"+
    "\1\74\32\0\1\75\44\0\1\76\32\0\1\77\32\0"+
    "\1\100\31\0\1\101\26\0\1\102\22\0\1\103\45\0"+
    "\1\104\33\0\1\105\32\0\1\106\26\0\1\107\36\0"+
    "\1\110\5\0";

  private static int [] zzUnpacktrans() {
    int [] result = new int[1323];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
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


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\2\11\1\1\5\11\5\1\1\11\1\1\1\11"+
    "\2\1\1\11\1\1\6\0\10\11\21\0\1\11\1\1"+
    "\2\0\1\11\5\0\1\11\2\1\1\0\2\11\1\1"+
    "\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[72];
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

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen())];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */

    /*--------------------------------------------
                    UTIL
    ---------------------------------------------*/
    private List<String> errorsList;
    private List<Token> tokens;
    private final String ID_REGEX;

    public void reset(){
        errorsList.clear();
    }

    public List<Token> getTokens(){
        return this.tokens;
    }


  /*--------------------------------------------
    CODIGO PARA EL MANEJO DE ERRORES
  ----------------------------------------------*/

    public List<String> getErrors(){
        return this.errorsList;
    }

    /*--------------------------------------------
        CODIGO PARA EL PARSER
    ----------------------------------------------*/
    StringBuffer string;
    StringBuffer input;

    private Symbol symbol(int type) {
        tokens.add(new Token(type, yyline+1, yycolumn+1));
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol symbol(int type, Object value) {
        tokens.add(new Token(value, type, yyline+1, yycolumn+1));
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }

    private void addError(String message) {
        errorsList.add("Error en la linea: " + (yyline+1) + ", columna: " + (yycolumn+1) + " : "+message);
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public XMLlexer(java.io.Reader in) {
      errorsList = new LinkedList();
    tokens = new LinkedList();
    string = new StringBuffer();
    input = new StringBuffer();
    ID_REGEX = "(_|-|\\$)([a-zA-Z]|[0-9]|_|-|\\$)*";
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate && zzCanGrow()) {
      /* if not, and it can grow: blow it up */
      char newBuffer[] = new char[Math.min(zzBuffer.length * 2, zzMaxBufferLen())];
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
      if (requested == 0) {
        throw new java.io.EOFException("Scan buffer limit reached ["+zzBuffer.length+"]");
      }
      else {
        throw new java.io.IOException(
            "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
      }
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    int initBufferSize = Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen());
    if (zzBuffer.length > initBufferSize) {
      zzBuffer = new char[initBufferSize];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
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
    
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  @Override  public java_cup.runtime.Symbol next_token() throws java.io.IOException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

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
        // peek one character ahead if it is
        // (if we have counted one line too much)
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
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
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
              {
                return symbol(sym.EOF);
              }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { addError("Simbolo invalido <"+ yytext()+">");
            }
          // fall through
          case 33: break;
          case 2:
            { /* ignore */
            }
          // fall through
          case 34: break;
          case 3:
            { string.setLength(0); yybegin(STRING);
            }
          // fall through
          case 35: break;
          case 4:
            { return symbol(sym.FINISHER);
            }
          // fall through
          case 36: break;
          case 5:
            { return symbol(sym.OPEN);
            }
          // fall through
          case 37: break;
          case 6:
            { return symbol(sym.EQUALS);
            }
          // fall through
          case 38: break;
          case 7:
            { return symbol(sym.CLOSE);
            }
          // fall through
          case 39: break;
          case 8:
            { input.setLength(0); yybegin(INPUT);
            }
          // fall through
          case 40: break;
          case 9:
            { string.append( yytext() );
            }
          // fall through
          case 41: break;
          case 10:
            { yybegin(YYINITIAL); //volver al estado de jflex
                    
                    switch(string.toString()){  /*-------------ACCIONES------------------*/
                        case "AGREGAR_COMPONENTE":
                            return symbol(sym.AGREGAR_COMPONENTE, string.toString()); 
                        case "BORRAR_COMPONENTE":
                            return symbol(sym.BORRAR_COMPONENTE, string.toString());
                        case "BORRAR_PAGINA":
                            return symbol(sym.BORRAR_PAGINA, string.toString()); 
                        case "BORRAR_SITIO_WEB":
                            return symbol(sym.BORRAR_SITIO_WEB, string.toString()); 
                        case "MODIFICAR_COMPONENTE":
                            return symbol(sym.MODIFICAR_COMPONENTE, string.toString()); 
                        case "MODIFICAR_PAGINA":
                            return symbol(sym.MODIFICAR_PAGINA, string.toString());
                        case "NUEVA_PAGINA":
                            return symbol(sym.NUEVA_PAGINA, string.toString()); 
                        case "NUEVO_SITIO_WEB":
                            return symbol(sym.NUEVO_SITIO_WEB, string.toString());  

                        case "CLASE":            /*-------------PARAMETROS------------------*/
                            return symbol(sym.CLASE, string.toString()); 
                        case "FECHA_CREACION":
                            return symbol(sym.FECHA_CREACION, string.toString());
                        case "FECHA_MODIFICACION":
                            return symbol(sym.FECHA_MODIFICACION, string.toString());
                        case "ID":
                            return symbol(sym.ID, string.toString());
                        case "PADRE":
                            return symbol(sym.PADRE, string.toString());
                        case "PAGINA":
                            return symbol(sym.PAGINA, string.toString());
                        case "SITIO":
                            return symbol(sym.SITIO, string.toString());
                        case "TITULO":
                            return symbol(sym.TITULO, string.toString());
                        case "USUARIO_CREACION":
                            return symbol(sym.USUARIO_CREACION, string.toString());
                        case "USUARIO_MODIFICACION":
                            return symbol(sym.USUARIO_MODIFICACION, string.toString()); 

                        case "ALINEACION":            /*-------------ATRIBUTOS------------------*/
                            return symbol(sym.ALINEACION, string.toString()); 
                        case "ALTURA":
                            return symbol(sym.ALTURA, string.toString());
                        case "ANCHO":
                            return symbol(sym.ANCHO, string.toString());
                        case "COLOR":
                            return symbol(sym.COLOR, string.toString());
                        case "ETIQUETAS_MENU":
                            return symbol(sym.ETIQUETAS_MENU, string.toString());
                        case "ORIGEN":
                            return symbol(sym.ORIGEN, string.toString());
                        case "TEXTO":
                            return symbol(sym.TEXTO, string.toString());
                        
                        default:
                            if(string.toString().matches(ID_REGEX)){
                                return symbol(sym.IDENTIFIER, string.toString());
                            }else{
                                return symbol(sym.STRING_TKN, string.toString());
                            }
                    }
            }
          // fall through
          case 42: break;
          case 11:
            { string.append('\\');
            }
          // fall through
          case 43: break;
          case 12:
            { input.append( yytext() );
            }
          // fall through
          case 44: break;
          case 13:
            { yybegin(YYINITIAL); //volver al estado de jflex
                    switch(input.toString()){  /*---------------NOMBRE DE ALINEACIONES----------------*/
                        case "CENTRAR":
                            return symbol(sym.CENTRAR, input.toString()); 
                        case "DERECHA":
                            return symbol(sym.DERECHA, input.toString());
                        case "IZQUIERDA":
                            return symbol(sym.IZQUIERDA, input.toString());
                        case "JUSTIFICAR":
                            return symbol(sym.JUSTIFICAR, input.toString());
                            
                        case "TITULO": /*---------------NOMBRE DE CLASES----------------*/
                            return symbol(sym.TITULO_CLASS, input.toString()); 
                        case "PARRAFO":
                            return symbol(sym.PARRAFO, input.toString());
                        case "IMAGEN":
                            return symbol(sym.IMAGEN, input.toString());
                        case "VIDEO":
                            return symbol(sym.VIDEO, input.toString());
                        case "MENU":
                            return symbol(sym.MENU, input.toString());
                            
                        default:
                            break;
                    }
                    if(input.toString().matches(ID_REGEX)){
                        return symbol(sym.IDENTIFIER, input.toString());
                    } else if(input.toString().matches("[0-9][0-9][0-9][0-9]-([0][1-9]|[1][0-2])-([0][1-9]|[1-2][0-9]|[3][0-1])")){
                        return symbol(sym.DATE_TKN, input.toString());
                    } else if(input.toString().matches("[0-9]+")){
                        return symbol(sym.INTEGER_TKN, input.toString());
                    } else if(input.toString().matches("#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})")){
                        return symbol(sym.COLOR_HEX, input.toString());
                    } else {
                        return symbol(sym.STRING_TKN, input.toString());
                    }
            }
          // fall through
          case 45: break;
          case 14:
            { input.append('\\');
            }
          // fall through
          case 46: break;
          case 15:
            { string.append('\"');
            }
          // fall through
          case 47: break;
          case 16:
            { string.append('\n');
            }
          // fall through
          case 48: break;
          case 17:
            { string.append('\r');
            }
          // fall through
          case 49: break;
          case 18:
            { string.append('\t');
            }
          // fall through
          case 50: break;
          case 19:
            { input.append('\"');
            }
          // fall through
          case 51: break;
          case 20:
            { input.append('\n');
            }
          // fall through
          case 52: break;
          case 21:
            { input.append('\r');
            }
          // fall through
          case 53: break;
          case 22:
            { input.append('\t');
            }
          // fall through
          case 54: break;
          case 23:
            { return symbol(sym.VALOR);
            }
          // fall through
          case 55: break;
          case 24:
            { return symbol(sym.ACCION);
            }
          // fall through
          case 56: break;
          case 25:
            { return symbol(sym.NOMBRE);
            }
          // fall through
          case 57: break;
          case 26:
            { return symbol(sym.ACCIONES);
            }
          // fall through
          case 58: break;
          case 27:
            { return symbol(sym.ATRIBUTO);
            }
          // fall through
          case 59: break;
          case 28:
            { return symbol(sym.ETIQUETA);
            }
          // fall through
          case 60: break;
          case 29:
            { return symbol(sym.ATRIBUTOS);
            }
          // fall through
          case 61: break;
          case 30:
            { return symbol(sym.ETIQUETAS);
            }
          // fall through
          case 62: break;
          case 31:
            { return symbol(sym.PARAMETRO);
            }
          // fall through
          case 63: break;
          case 32:
            { return symbol(sym.PARAMETROS);
            }
          // fall through
          case 64: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
