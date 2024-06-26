package compi1.web_designer_api.services;

import compi1.web_designer_api.util.ResponserGen;
import compi1.web_designer_api.database.DBManager;
import compi1.web_designer_api.htmltraductor.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class XMLservice {

    private Mensajero mensajero;
    private ResponserGen responserGen;
    private Traductor traductor;

    public XMLservice() {
        mensajero = new Mensajero();
        responserGen = new ResponserGen();
        traductor = new Traductor(new DBManager().getConnection());
    }
    
    public void executeXML(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String textUser = mensajero.getRequestBody(request).replace(']', '"');
            StringReader reader = new StringReader(textUser);
            XMLlexer lexer = new XMLlexer(reader);
            XMLparser parser = new XMLparser(lexer);
            parser.parse();
            if (lexer.getErrors().isEmpty() && parser.getSyntaxErrors().isEmpty()) {
                //genera de una vez el xml que manda
                mensajero.sendResponse(traductor.traducir(lexer.getTokens()), response);
            } else {
                String responseMss = responserGen.generateError(lexer.getErrors(), ResponserGen.LEXICAL_ERRORS);
                responseMss += responserGen.generateError(parser.getSyntaxErrors(), ResponserGen.SYNTAX_ERRORS);
                mensajero.sendResponse(responseMss, response);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            response.setStatus(500);
        }
    }

    private String showErrors(String mss, List<String> errors) {
        String content = "--------------------------------------------\n" + mss + "\n";
        if (errors.isEmpty()) {
            content += "     __ningun_error__\n";
        } else {
            for (int i = 0; i < errors.size(); i++) {
                content += errors.get(i);
                content += "\n";
            }
        }
        return content;
    }

    private void showTkns(XMLlexer lexer) {
        for (int i = 0; i < lexer.getTokens().size(); i++) {
            System.out.print(lexer.getTokens().get(i).getType());
            if (lexer.getTokens().get(i).getLexem() != null) {
                System.out.println(lexer.getTokens().get(i).getLexem().toString());
            } else {
                System.out.println();
            }
        }
    }
}
