
package compi1.web_designer_api.services;

import java.util.List;

/**
 *
 * @author yennifer
 */
public class ResponserGen {
    
    public static final int ERROR = 4;
    public static final int SEMANTIC_ERRORS = 3;
    public static final int SYNTAX_ERRORS = 2;
    public static final int LEXICAL_ERRORS = 1;
    public static final int RESPONSE = 0;
    
    private final String OPEN = "<";
    private final String CLOSE = ">";
    private final String FINISHER = "/";
    private final String INDENTADO = " ";
    
    public String generateError(List<String> errors, int type){
        if(errors.isEmpty()){
            return "";
        } else {
            String xml = getTag(type, false);
            for (String error : errors) {
                xml += getInternalTag(ERROR, false);
                xml += INDENTADO.repeat(8) + "\"" + error + "\"\n";
                xml += getInternalTag(ERROR, true);
            }
            xml += getTag(type, true);
            return xml;
        }
    }
    
    public String generateResponse(List<String> responses){
        if(responses.isEmpty()){
            throw new AssertionError("No se puede enviar una respuesta vacia");
        } else {
            String xml = getTag(RESPONSE, false);
            for (String response : responses) {
                xml += getInternalTag(RESPONSE, false);
                xml += INDENTADO.repeat(8) + "\"" + response + "\"\n";
                xml += getInternalTag(RESPONSE, true);
            }
            xml += getTag(RESPONSE, true);
            return xml;
        }
    }
    
    public String generateResponse(String response){
        if(response.isEmpty() || response.isBlank()){
            throw new AssertionError("No se puede enviar una respuesta vacia");
        } else {
            //TODO: ARREGLAR AQUI
            return "";
        }
    }
    
    private String getTag(int type, boolean closing){
        String tag = OPEN;
        if(closing){
            tag += FINISHER;
        }
        tag += getTypeError(type);
        tag += CLOSE + "\n";
        return tag;
    }
    
    private String getInternalTag(int type, boolean closing){
        String tag = INDENTADO.repeat(4)  + OPEN;
        if(closing){
            tag += FINISHER;
        }
        tag += type == RESPONSE ? "mensaje" : "error";
        tag += CLOSE + "\n";
        return tag;
    }
    
    private String getTypeError(int type){
        switch(type){
            case 0:
                return "Response";
            case 1:
                return "LexicalErrors";
            case 2:
                return "SyntaxErrors";
            case 3:
                return "SemanticErrors";
            default:
                throw new AssertionError();   
        }
    }
}
