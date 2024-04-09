/* codigo de usuario */
package compi1.web_designer_api.htmltraductor;

import java_cup.runtime.*;
import java.util.*;
import compi1.web_designer_api.util.Token;

%% //separador de area

/* ------------------------------------------------
        opciones y declaraciones de jflex 
---------------------------------------------------*/
%public
%unicode
%class XMLlexer
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
    input = new StringBuffer();
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

%state STRING, INPUT


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
        if(save){
            tokens.add(new Token(value, yyline+1, yycolumn+1 - value.toString().length(), type));
        }
        return new Symbol(type, yyline+1, yycolumn+1 - value.toString().length() , value);
    }
    
    private Symbol symbolInputWithoutObject(int type, Object value, boolean save){
        if(save){
            tokens.add(new Token(yyline+1, yycolumn+1 - value.toString().length(), type));
        }
        return new Symbol(type, yyline+1, yycolumn+1- value.toString().length());
    }

    private void addError(String message) {
        errorsList.add("Error en la linea: " + (yyline+1) + ", columna: " + (yycolumn+1) + " : "+message);
    }
%}

%% // separador de areas


/*----------------------------------------------------
                    reglas lexicas 
------------------------------------------------------*/

  /*etiquetas XML reservadas*/
    <YYINITIAL> "ACCION"        { return symbol(sym.ACCION, false); }
    <YYINITIAL> "ACCIONES"      { return symbol(sym.ACCIONES, false); }
    <YYINITIAL> "ATRIBUTO"      { return symbol(sym.ATRIBUTO, false); }
    <YYINITIAL> "ATRIBUTOS"     { return symbol(sym.ATRIBUTOS, true); }
    <YYINITIAL> "ETIQUETA"      { return symbol(sym.ETIQUETA, false); }
    <YYINITIAL> "ETIQUETAS"     { return symbol(sym.ETIQUETAS, true); }
    <YYINITIAL> "NOMBRE"        { return symbol(sym.NOMBRE, false); }
    <YYINITIAL> "PARAMETRO"     { return symbol(sym.PARAMETRO, false); }
    <YYINITIAL> "PARAMETROS"    { return symbol(sym.PARAMETROS, true); }
    <YYINITIAL> "VALOR"         { return symbol(sym.VALOR, false); }
    
    <YYINITIAL> {
        /* symbols */
        "<"             { return symbol(sym.OPEN, false); }
        ">"             { return symbol(sym.CLOSE, false); }
        "/"             { return symbol(sym.FINISHER, false); }
        "="             { return symbol(sym.EQUALS, false); }

        \"              { string.setLength(0); yybegin(STRING); }
        "["             { input.setLength(0); yybegin(INPUT);  }    

        /* ignored */
        {WhiteSpace} 	{/* ignore */}
    }

    <STRING> {
        \"      {   
                    yybegin(YYINITIAL); //volver al estado de jflex
                    
                    switch(string.toString()){  /*-------------ACCIONES------------------*/
                        case "AGREGAR_COMPONENTE":
                            return symbolInputWithoutObject(sym.AGREGAR_COMPONENTE, string.toString(), true); 
                        case "BORRAR_COMPONENTE":
                            return symbolInputWithoutObject(sym.BORRAR_COMPONENTE, string.toString(), true);
                        case "BORRAR_PAGINA":
                            return symbolInputWithoutObject(sym.BORRAR_PAGINA, string.toString(), true); 
                        case "BORRAR_SITIO_WEB":
                            return symbolInputWithoutObject(sym.BORRAR_SITIO_WEB, string.toString(), true); 
                        case "MODIFICAR_COMPONENTE":
                            return symbolInputWithoutObject(sym.MODIFICAR_COMPONENTE, string.toString(), true); 
                        case "MODIFICAR_PAGINA":
                            return symbolInputWithoutObject(sym.MODIFICAR_PAGINA, string.toString(), true);
                        case "NUEVA_PAGINA":
                            return symbolInputWithoutObject(sym.NUEVA_PAGINA, string.toString(), true); 
                        case "NUEVO_SITIO_WEB":
                            return symbolInputWithoutObject(sym.NUEVO_SITIO_WEB, string.toString(), true);  

                        case "CLASE":            /*-------------PARAMETROS------------------*/
                            return symbolInputWithObject(sym.CLASE, string.toString(), true); 
                        case "FECHA_CREACION":
                            return symbolInputWithObject(sym.FECHA_CREACION, string.toString(), true);
                        case "FECHA_MODIFICACION":
                            return symbolInputWithObject(sym.FECHA_MODIFICACION, string.toString(), true);
                        case "ID":
                            return symbolInputWithObject(sym.ID, string.toString(), true);
                        case "PADRE":
                            return symbolInputWithObject(sym.PADRE, string.toString(), true);
                        case "PAGINA":
                            return symbolInputWithObject(sym.PAGINA, string.toString(), true);
                        case "SITIO":
                            return symbolInputWithObject(sym.SITIO, string.toString(), true);
                        case "TITULO":
                            return symbolInputWithObject(sym.TITULO, string.toString(), true);
                        case "USUARIO_CREACION":
                            return symbolInputWithObject(sym.USUARIO_CREACION, string.toString(), true);
                        case "USUARIO_MODIFICACION":
                            return symbolInputWithObject(sym.USUARIO_MODIFICACION, string.toString(), true); 

                        case "ALINEACION":            /*-------------ATRIBUTOS------------------*/
                            return symbolInputWithoutObject(sym.ALINEACION, string.toString(), true); 
                        case "ALTURA":
                            return symbolInputWithoutObject(sym.ALTURA, string.toString(), true);
                        case "ANCHO":
                            return symbolInputWithoutObject(sym.ANCHO, string.toString(), true);
                        case "COLOR":
                            return symbolInputWithoutObject(sym.COLOR, string.toString(), true);
                        case "ETIQUETAS_MENU":
                            return symbolInputWithoutObject(sym.ETIQUETAS_MENU, string.toString(), true);
                        case "ORIGEN":
                            return symbolInputWithoutObject(sym.ORIGEN, string.toString(), true);
                        case "TEXTO":
                            return symbolInputWithoutObject(sym.TEXTO, string.toString(), true);
                        
                        default:
                            if(string.toString().matches(ID_REGEX)){
                                return symbolInputWithObject(sym.IDENTIFIER, string.toString(), true);
                            }else{
                                return symbolInputWithObject(sym.STRING_TKN, string.toString(), true);
                            }
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

    <INPUT> {
        \"      {
                    yybegin(YYINITIAL); //volver al estado de jflex
                    switch(input.toString()){  /*---------------NOMBRE DE ALINEACIONES----------------*/
                        case "CENTRAR":
                            return symbolInputWithoutObject(sym.CENTRAR, input.toString(), true); 
                        case "DERECHA":
                            return symbolInputWithoutObject(sym.DERECHA, input.toString(), true);
                        case "IZQUIERDA":
                            return symbolInputWithoutObject(sym.IZQUIERDA, input.toString(), true);
                        case "JUSTIFICAR":
                            return symbolInputWithoutObject(sym.JUSTIFICAR, input.toString(), true);
                            
                        case "TITULO": /*---------------NOMBRE DE CLASES----------------*/
                            return symbolInputWithoutObject(sym.TITULO_CLASS, input.toString(), true); 
                        case "PARRAFO":
                            return symbolInputWithoutObject(sym.PARRAFO, input.toString(), true);
                        case "IMAGEN":
                            return symbolInputWithoutObject(sym.IMAGEN, input.toString(), true);
                        case "VIDEO":
                            return symbolInputWithoutObject(sym.VIDEO, input.toString(), true);
                        case "MENU":
                            return symbolInputWithoutObject(sym.MENU, input.toString(), true);
                            
                        default:
                            break;
                    }
                    if(input.toString().matches(ID_REGEX)){
                        return symbolInputWithObject(sym.IDENTIFIER, input.toString(), true);
                    } else if(input.toString().matches("[0-9][0-9][0-9][0-9]-([0][1-9]|[1][0-2])-([0][1-9]|[1-2][0-9]|[3][0-1])")){
                        return symbolInputWithObject(sym.DATE_TKN, input.toString(), true);
                    } else if(input.toString().matches("[0-9]+")){
                        return symbolInputWithObject(sym.INTEGER_TKN, input.toString(), true);
                    } else if(input.toString().matches("#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})")){
                        return symbolInputWithObject(sym.COLOR_HEX, input.toString(), true);
                    } else {
                        return symbolInputWithObject(sym.STRING_TKN, input.toString(), true);
                    }
                }

        [^\n\r\"\\]+                   { input.append( yytext() ); }
        \\t                            { input.append('\t'); }
        \\n                            { input.append('\n'); }

        \\r                            { input.append('\r'); }
        \\\"                           { input.append('\"'); }
        \\                             { input.append('\\'); }
        
    }
    
    
  /* error fallback */
    [^]             { addError("Simbolo invalido <"+ yytext()+">");}
    <<EOF>>         { return symbol(sym.EOF, false); }
