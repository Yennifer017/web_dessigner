package compi1.web_designer_api.util;

import java.util.List;

/**
 *
 * @author yennifer
 */
public class ResponserGen {

    public static final int RESPONSE = 0;
    public static final int LEXICAL_ERRORS = 1;
    public static final int SYNTAX_ERRORS = 2;
    public static final int SEMANTIC_ERRORS = 3;
    public static final int WARNNIGS = 4;

    private final String INDENTADO = " ";
    private final String COMILLA = "\"";

    public String generateError(List<String> errors, int type) {
        if (errors.isEmpty()) {
            return "";
        } else {
            String xml = "<errors>\n";
            xml += INDENTADO.repeat(4) + COMILLA + this.getTypeError(type) + "\n";
            for (String error : errors) {
                xml += INDENTADO.repeat(4) + error + "\n";
            }
            xml += COMILLA;
            xml += "\n</errors>\n";
            return xml;
        }
    }
    
    public String generateWarnings(List<String> warnnings){
        if (warnnings.isEmpty()) {
            return "";
        } else {
            String xml = "<warnings>\n";
            xml += INDENTADO.repeat(4) + COMILLA + this.getTypeError(WARNNIGS) + "\n";
            for (String warnning : warnnings) {
                xml += INDENTADO.repeat(4) + warnning + "\n";
            }
            xml += COMILLA;
            xml += "\n</warnings>\n";
            return xml;
        }
    }

    public String generateResponse(String response) {
        if (response.isEmpty() || response.isBlank()) {
            throw new AssertionError("No se puede enviar una respuesta vacia");
        } else {
            String xml = "<response>\n";
            xml += INDENTADO.repeat(4) + COMILLA + this.getTypeError(RESPONSE) + "\n";
            xml += INDENTADO.repeat(4) + response + "\n";
            xml += COMILLA;
            xml += "\n</response>\n";
            return xml;
        }
    }

    private String getTypeError(int type) {
        switch (type) {
            case RESPONSE:
                return "Output:";
            case LEXICAL_ERRORS:
                return "Errores Lexicos:";
            case SYNTAX_ERRORS:
                return "Errores Sintacticos:";
            case SEMANTIC_ERRORS:
                return "Errores Semanticos: ";
            case WARNNIGS:
                return "Advertencias:";
            default:
                throw new AssertionError();
        }
    }
}
