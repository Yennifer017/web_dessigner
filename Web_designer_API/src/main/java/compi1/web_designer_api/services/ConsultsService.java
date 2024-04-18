
package compi1.web_designer_api.services;

import compi1.web_designer_api.database.DBManager;
import compi1.web_designer_api.sqcmstraductor.SQcmsLexer;
import compi1.web_designer_api.sqcmstraductor.SQcmsParser;
import compi1.web_designer_api.sqcmstraductor.TraductorSQcms;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.StringReader;

/**
 *
 * @author yennifer
 */
public class ConsultsService {
    private Mensajero mensajero;
    private ResponserGen responserGen;
    private TraductorSQcms traductor;
    
    public ConsultsService(){
        mensajero = new Mensajero();
        responserGen = new ResponserGen();
        traductor = new TraductorSQcms(new DBManager().getConnection());
    }
    
    public void execute(HttpServletRequest request, HttpServletResponse response){
        try {
            String textUser = mensajero.getRequestBody(request);
            StringReader reader = new StringReader(textUser);
            SQcmsLexer lexer = new SQcmsLexer(reader);
            SQcmsParser parser = new SQcmsParser(lexer);
            parser.parse();
            if (lexer.getErrors().isEmpty() && parser.getErrors().isEmpty()) {
                mensajero.sendResponse(traductor.traducir(lexer.getTokens()), response);
            } else {
                String responseMss = responserGen.generateError(lexer.getErrors(), ResponserGen.LEXICAL_ERRORS);
                responseMss += responserGen.generateError(parser.getErrors(), ResponserGen.SYNTAX_ERRORS);
                mensajero.sendResponse(responseMss, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
    }
}
