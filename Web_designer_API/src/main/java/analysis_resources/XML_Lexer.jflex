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
    errorsList = new LinkedList();
    tokens = new LinkedList();
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
%}

%% // separador de areas


/*----------------------------------------------------
                    reglas lexicas 
------------------------------------------------------*/

  /*etiquetas XML reservadas*/
    <YYINITIAL> "ACCION"        { return symbol(sym.ACCION); }
    <YYINITIAL> "ACCIONES"      { return symbol(sym.ACCIONES); }
    <YYINITIAL> "ATRIBUTO"      { return symbol(sym.ATRIBUTO); }
    <YYINITIAL> "ATRIBUTOS"     { return symbol(sym.ATRIBUTOS); }
    <YYINITIAL> "ETIQUETA"      { return symbol(sym.ETIQUETA); }
    <YYINITIAL> "ETIQUETAS"     { return symbol(sym.ETIQUETAS); }
    <YYINITIAL> "NOMBRE"        { return symbol(sym.NOMBRE); }
    <YYINITIAL> "PARAMETRO"     { return symbol(sym.PARAMETRO); }
    <YYINITIAL> "PARAMETROS"    { return symbol(sym.PARAMETROS); }
    <YYINITIAL> "VALOR"         { return symbol(sym.VALOR); }
    
    <YYINITIAL> {
        /* symbols */
        "<"             { return symbol(sym.OPEN); }
        ">"             { return symbol(sym.CLOSE); }
        "/"             { return symbol(sym.FINISHER); }
        "="             { return symbol(sym.EQUALS); }

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
                    switch(input.toString()){  /*-------------ACCIONES------------------*/
                        case "CENTRAR":
                            return symbol(sym.CENTRAR, input.toString()); 
                        case "DERECHA":
                            return symbol(sym.DERECHA, input.toString());
                        case "IZQUIERDA":
                            return symbol(sym.IZQUIERDA, input.toString());
                        case "JUSTIFICAR":
                            return symbol(sym.JUSTIFICAR, input.toString());
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

        [^\n\r\"\\]+                   { input.append( yytext() ); }
        \\t                            { input.append('\t'); }
        \\n                            { input.append('\n'); }

        \\r                            { input.append('\r'); }
        \\\"                           { input.append('\"'); }
        \\                             { input.append('\\'); }
        
    }
    
    
  /* error fallback */
    [^]             { addError("Simbolo invalido <"+ yytext()+">");}
    <<EOF>>         { return symbol(sym.EOF); }
