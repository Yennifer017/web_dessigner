/* codigo de usuario */
package compi1.web_designer_api.sqcmstraductor;

import java_cup.runtime.*;
import java.util.*;
import compi1.web_designer_api.util.Token;

%% //separador de area

/* ------------------------------------------------
        opciones y declaraciones de jflex 
---------------------------------------------------*/
%public
%unicode
%class SQcmsLexer
%ignorecase
%cup
%line
%column

/* ------------------------------------------------
            codigo en el constructor
---------------------------------------------------*/
%init{
    errorsList = new ArrayList();
    tokens = new ArrayList();
    string = new StringBuffer();
    ID_REGEX = "(_|-|\\$)([a-zA-Z]|[0-9]|_|-|\\$)*";
%init}  

/*--------------------------------------------------
                macros o constantes
----------------------------------------------------*/

LineTerminator = \r|\n|\r\n 
WhiteSpace = {LineTerminator} | [ \t\f]

/* constants */
/* nothing here */

/*---------------------------------------------------
                estados del lexer
-----------------------------------------------------*/

%state STRING


%{ /****************CODIGO DE USUARIO*************/

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

    private Symbol symbol(int type, boolean save) {
        if(save){
            tokens.add(new Token(yyline+1, yycolumn+1, type));
        }
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol symbol(int type, Object value, boolean save) {
        if(save){
            tokens.add(new Token(value, type, yyline+1, yycolumn+1));
        }
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
    
    private Symbol symbolInputWithObject(int type, Object value, boolean save){
        int col = yycolumn+1 - value.toString().length() > 0 ? yycolumn+1 - value.toString().length() : yycolumn+1;
        if(save){
            tokens.add(new Token(value, yyline+1, col, type));
        }
        return new Symbol(type, yyline+1, col , value);
    }
    
    private Symbol symbolInputWithoutObject(int type, Object value, boolean save){
        int col = yycolumn+1 - value.toString().length() > 0 ? yycolumn+1 - value.toString().length() : yycolumn+1;
        if(save){
            tokens.add(new Token(yyline+1, col, type));
        }
        return new Symbol(type, yyline+1, col);
    }

    private void addError(String message) {
        errorsList.add("Error en la linea: " + (yyline+1) + ", columna: " + (yycolumn+1) + " : "+message);
    }
%}

%% // separador de areas


/*----------------------------------------------------
                    reglas lexicas 
------------------------------------------------------*/

  /*palabras reservadas*/
    <YYINITIAL> "CONSULTAR"         { return symbol(sym.CONSULTAR, false); }
    <YYINITIAL> "VISITAS_SITIO"     { return symbol(sym.VISITAS_SITIO, true); }
    <YYINITIAL> "VISITAS_PAGINA"    { return symbol(sym.VISITAS_PAGINA, true); }
    <YYINITIAL> "PAGINAS_POPULARES" { return symbol(sym.PAGINAS_POPULARES, true); }
    <YYINITIAL> "COMPONENTE"        { return symbol(sym.COMPONENTE, true); }
    <YYINITIAL> "TODOS"             { return symbol(sym.TODOS, true); }
    <YYINITIAL> "TITULO"            { return symbol(sym.TITULO, true); }
    <YYINITIAL> "PARRAFO"           { return symbol(sym.PARRAFO, true); }
    <YYINITIAL> "IMAGEN"            { return symbol(sym.IMAGEN, true); }
    <YYINITIAL> "VIDEO"             { return symbol(sym.VIDEO, true); }
    <YYINITIAL> "MENU"              { return symbol(sym.MENU, true); }
    
    <YYINITIAL> {
        /* symbols */
        ","             { return symbol(sym.COMA, false); }
        ";"             { return symbol(sym.PUNTO_Y_COMA, true); }

        \"              { string.setLength(0); yybegin(STRING); }   

        /* ignored */
        {WhiteSpace} 	{/* ignore */}
    }

    <STRING> {
        \"  {   
                yybegin(YYINITIAL); //volver al estado de jflex
                    
                if(string.toString().matches(ID_REGEX)){
                    return symbolInputWithObject(sym.IDENTIFIER, string.toString(), true);
                } else if(string.toString().matches(ID_REGEX + "(\\." + ID_REGEX + ")*" )){
                    return symbolInputWithObject(sym.PATH, string.toString(), true);
                } else {
                    return symbolInputWithObject(sym.STRING_TKN, string.toString(), true);
                }
            }
      
        /*para poder insertar saltos de linea*/
        [^\n\r\"\\]+                   { string.append( yytext() ); }
        \\t                            { string.append('\t'); }
        \\n                            { string.append('\n'); }

        \\r                            { string.append('\r'); }
        \\\"                           { string.append('\"'); }
        \\                             { string.append('\\'); }
    }
    
  /* error fallback */
    [^]             { addError("Simbolo invalido <"+ yytext()+">");}
    <<EOF>>         { return symbol(sym.EOF, false); }

