/* codigo de usuario */
package compi1.dessigner.resources;

import java.util.*;
import java.awt.Color;
import javax.swing.text.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.BadLocationException;

%% //separador de area

/* ------------------------------------------------
        opciones y declaraciones de jflex 
---------------------------------------------------*/
%public
%unicode
%intwrap
%class ResponserLexer

/* ------------------------------------------------
            codigo en el constructor
---------------------------------------------------*/
%init{
    string = new StringBuffer();
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
    StringBuffer string;
    private HTMLDocument doc;
    private SimpleAttributeSet attrs;

    public void init(HTMLDocument doc, SimpleAttributeSet attrs){
        this.doc = doc;
        this.attrs = attrs;
    }

    private void addText(){
        try {
            doc.insertString(doc.getLength(), string.toString(), this.attrs);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    
%}

%% // separador de areas


/*----------------------------------------------------
                    reglas lexicas 
------------------------------------------------------*/

  /*etiquetas XML reservadas*/
    <YYINITIAL> "<errors>"      { StyleConstants.setForeground(attrs, Color.RED); return 1;}
    <YYINITIAL> "</errors>"     { addText(); return 1;}
    <YYINITIAL> "<response>"    { StyleConstants.setForeground(attrs, Color.GREEN);return 1;}
    <YYINITIAL> "</response>"   { addText(); return 1;}
    <YYINITIAL> "<warnings>"   { StyleConstants.setForeground(attrs, Color.ORANGE); return 1;}
    <YYINITIAL> "</warnings>"  { addText(); return 1;}

    
    <YYINITIAL> {
        \"[^\"]*\"        { string.setLength(0); string.append(yytext().replace("\"", ""));}
        {WhiteSpace} 	{/* ignore */}
    }

    
    
    
  /* error fallback */
    [^]             { /*ignore*/ }

