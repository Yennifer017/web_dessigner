
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package compi1.web_designer_api.sqcmstraductor;

import java_cup.runtime.*;
import java.util.*;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class SQcmsParser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public SQcmsParser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public SQcmsParser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public SQcmsParser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\027\000\002\002\004\000\002\002\004\000\002\003" +
    "\003\000\002\003\002\000\002\004\005\000\002\005\003" +
    "\000\002\005\003\000\002\005\003\000\002\005\003\000" +
    "\002\006\005\000\002\007\005\000\002\007\002\000\002" +
    "\010\005\000\002\011\005\000\002\011\002\000\002\012" +
    "\003\000\002\012\003\000\002\012\003\000\002\012\003" +
    "\000\002\012\003\000\002\012\003\000\002\013\004\000" +
    "\002\014\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\044\000\004\004\006\001\002\000\004\002\046\001" +
    "\002\000\006\002\ufffe\004\006\001\002\000\012\005\007" +
    "\006\012\007\017\010\013\001\002\000\004\021\037\001" +
    "\002\000\004\020\ufffa\001\002\000\004\020\ufffc\001\002" +
    "\000\004\022\032\001\002\000\016\011\024\012\022\013" +
    "\030\014\025\015\027\016\023\001\002\000\004\020\021" +
    "\001\002\000\004\020\ufff9\001\002\000\004\020\ufffb\001" +
    "\002\000\004\021\020\001\002\000\004\020\uffec\001\002" +
    "\000\006\002\ufffd\004\ufffd\001\002\000\004\022\ufff1\001" +
    "\002\000\004\022\uffed\001\002\000\004\022\ufff2\001\002" +
    "\000\004\022\uffef\001\002\000\004\022\031\001\002\000" +
    "\004\022\uffee\001\002\000\004\022\ufff0\001\002\000\004" +
    "\020\uffeb\001\002\000\006\017\033\020\ufff3\001\002\000" +
    "\004\022\035\001\002\000\004\020\ufff5\001\002\000\006" +
    "\017\033\020\ufff3\001\002\000\004\020\ufff4\001\002\000" +
    "\006\017\040\020\ufff6\001\002\000\004\021\042\001\002" +
    "\000\004\020\ufff8\001\002\000\006\017\040\020\ufff6\001" +
    "\002\000\004\020\ufff7\001\002\000\004\002\uffff\001\002" +
    "\000\004\002\000\001\002\000\004\002\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\044\000\006\002\003\004\004\001\001\000\002\001" +
    "\001\000\006\003\044\004\043\001\001\000\014\005\013" +
    "\006\010\010\015\013\007\014\014\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\004\012\025\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\011" +
    "\033\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\011\035\001\001\000\002\001\001\000\004\007\040\001" +
    "\001\000\002\001\001\000\002\001\001\000\004\007\042" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$SQcmsParser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$SQcmsParser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$SQcmsParser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    //fields
    SQcmsLexer lex;
    private List<String> errors;


    //conect the parser with the lexer
    public SQcmsParser(SQcmsLexer lex){
        super(lex);
        errors = new LinkedList<>();
    }

    //getters
    public SQcmsLexer getLexer(){
        return this.lex;
    }
    public List<String> getErrors(){
        return this.errors;
    }
    
    /*----------------------------------------------------
            SOBREESCRIBIENDO LOS METODOS DE ERROR
    ------------------------------------------------------*/
    public void syntax_error(Symbol cur_token) {
        String mss = "Simbolo: " + symbl_name_from_id(cur_token.sym)
                    + ", linea: " + cur_token.left
                    + ", columna: " + cur_token.right;
        if (expected_token_ids().isEmpty()) {
            mss += " -- ya no se esperaba ningun simbolo";
        }
        errors.add(mss);
    }

    public void unrecovered_syntax_error(Symbol cur_token) {
        errors.add("Errores de sintaxis severos detectados, revisa municiosamente el codigo");
    }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$SQcmsParser$actions {
  private final SQcmsParser parser;

  /** Constructor */
  CUP$SQcmsParser$actions(SQcmsParser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$SQcmsParser$do_action_part00000000(
    int                        CUP$SQcmsParser$act_num,
    java_cup.runtime.lr_parser CUP$SQcmsParser$parser,
    java.util.Stack            CUP$SQcmsParser$stack,
    int                        CUP$SQcmsParser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$SQcmsParser$result;

      /* select the action based on the action number */
      switch (CUP$SQcmsParser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= instructions EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.elementAt(CUP$SQcmsParser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.elementAt(CUP$SQcmsParser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$SQcmsParser$stack.elementAt(CUP$SQcmsParser$top-1)).value;
		RESULT = start_val;
              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.elementAt(CUP$SQcmsParser$top-1)), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$SQcmsParser$parser.done_parsing();
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // instructions ::= instruction more_instructions 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("instructions",0, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.elementAt(CUP$SQcmsParser$top-1)), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // more_instructions ::= instruction 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("more_instructions",1, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // more_instructions ::= 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("more_instructions",1, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // instruction ::= CONSULTAR statements PUNTO_Y_COMA 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("instruction",2, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.elementAt(CUP$SQcmsParser$top-2)), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // statements ::= view_sites 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("statements",3, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // statements ::= view_pages 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("statements",3, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // statements ::= popular_pages 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("statements",3, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // statements ::= components_view 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("statements",3, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // view_sites ::= VISITAS_SITIO IDENTIFIER more_ids 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("view_sites",4, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.elementAt(CUP$SQcmsParser$top-2)), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // more_ids ::= COMA IDENTIFIER more_ids 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("more_ids",5, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.elementAt(CUP$SQcmsParser$top-2)), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // more_ids ::= 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("more_ids",5, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // view_pages ::= VISITAS_PAGINA PATH more_paths 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("view_pages",6, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.elementAt(CUP$SQcmsParser$top-2)), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // more_paths ::= COMA PATH more_paths 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("more_paths",7, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.elementAt(CUP$SQcmsParser$top-2)), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // more_paths ::= 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("more_paths",7, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // type ::= TODOS 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("type",8, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // type ::= TITULO 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("type",8, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // type ::= PARRAFO 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("type",8, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // type ::= IMAGEN 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("type",8, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // type ::= VIDEO 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("type",8, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // type ::= MENU 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("type",8, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // popular_pages ::= PAGINAS_POPULARES IDENTIFIER 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("popular_pages",9, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.elementAt(CUP$SQcmsParser$top-1)), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // components_view ::= COMPONENTE type PATH 
            {
              Object RESULT =null;

              CUP$SQcmsParser$result = parser.getSymbolFactory().newSymbol("components_view",10, ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.elementAt(CUP$SQcmsParser$top-2)), ((java_cup.runtime.Symbol)CUP$SQcmsParser$stack.peek()), RESULT);
            }
          return CUP$SQcmsParser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$SQcmsParser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$SQcmsParser$do_action(
    int                        CUP$SQcmsParser$act_num,
    java_cup.runtime.lr_parser CUP$SQcmsParser$parser,
    java.util.Stack            CUP$SQcmsParser$stack,
    int                        CUP$SQcmsParser$top)
    throws java.lang.Exception
    {
              return CUP$SQcmsParser$do_action_part00000000(
                               CUP$SQcmsParser$act_num,
                               CUP$SQcmsParser$parser,
                               CUP$SQcmsParser$stack,
                               CUP$SQcmsParser$top);
    }
}

}