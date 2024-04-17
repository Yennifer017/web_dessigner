
package compi1.web_designer_api.sqcmstraductor;

import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.services.ResponserGen;
import compi1.web_designer_api.sqcmstraductor.statements.StatementSQcms;
import compi1.web_designer_api.sqcmstraductor.statements.VisitsSite;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class TraductorSQcms {
    
    private Index index;
    private ResponserGen responserGen;
    
    private VisitsSite visitSite;
    
    public TraductorSQcms(Connection connection){
        index = new Index();
        responserGen = new ResponserGen();
        visitSite = new VisitsSite(connection);
    }
    
    public String traducir(List<Token> tokens){
        String response = "";
        index.setIndex(0);
        for (index.get(); index.get() < tokens.size(); ) {
            Token currentTkn = tokens.get(index.get());
            index.increment(); //para pasar al siguiente token
            switch (currentTkn.getType()) {
                case sym.VISITAS_SITIO:
                    response += startTranslate(visitSite, tokens);
                    break;
                case sym.VISITAS_PAGINA:
                    response += "";
                    break;
                case sym.PAGINAS_POPULARES:
                    response += "";
                    break;
                case sym.COMPONENTE:
                    response += "";
                    break;
                default:
            }
        }
        return response;
    }
    
    private String startTranslate(StatementSQcms traductor, List<Token> tokens) {
        String response = "";
        try {
            response
                    += //TODO: ARREGLAR AQUI                               
                    traductor.translate(tokens, index);
        } catch (ModelException e) {
            traductor.addFailMss();
            response += responserGen.generateError(
                    traductor.getSemanticErrors(),
                    ResponserGen.SEMANTIC_ERRORS
            );
        }
        return response;
    }
    
}