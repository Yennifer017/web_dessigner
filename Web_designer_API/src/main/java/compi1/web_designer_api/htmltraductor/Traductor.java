package compi1.web_designer_api.htmltraductor;

import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.statements.AddCompTraduct;
import compi1.web_designer_api.htmltraductor.statements.CreatePageTraductor;
import compi1.web_designer_api.htmltraductor.statements.CreateSiteTraductor;
import compi1.web_designer_api.htmltraductor.statements.DeleteCompTraduc;
import compi1.web_designer_api.htmltraductor.statements.DeletePageTraductor;
import compi1.web_designer_api.htmltraductor.statements.DeleteSiteTraductor;
import compi1.web_designer_api.htmltraductor.statements.ModifyCompTraduc;
import compi1.web_designer_api.htmltraductor.statements.ModifyPageTraductor;
import compi1.web_designer_api.htmltraductor.statements.StmTraductor;
import compi1.web_designer_api.services.ResponserGen;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class Traductor {

    private Index index;

    private CreateSiteTraductor createSiteT;
    private DeleteSiteTraductor deleteSiteT;
    private CreatePageTraductor createPageT;
    private DeletePageTraductor deletePageT;
    private ModifyPageTraductor modifyPageT;
    private AddCompTraduct addCompT;
    private DeleteCompTraduc deleteCompT;
    private ModifyCompTraduc modifyCompT;

    private ResponserGen responserGen;

    public Traductor(Connection connection) {
        index = new Index();

        createSiteT = new CreateSiteTraductor(connection);
        deleteSiteT = new DeleteSiteTraductor(connection);
        createPageT = new CreatePageTraductor(connection);
        deletePageT = new DeletePageTraductor(connection);
        modifyPageT = new ModifyPageTraductor(connection);
        addCompT = new AddCompTraduct();
        deleteCompT = new DeleteCompTraduc();
        modifyCompT = new ModifyCompTraduc();
        
        responserGen = new ResponserGen();
    }

    public String traducir(List<Token> tokens) {
        String response = "";
        index.setIndex(0);
        for (index.get(); index.get() < tokens.size(); ) {
            Token currentTkn = tokens.get(index.get());
            index.increment(); //para pasar al siguiente token

            switch (currentTkn.getType()) {
                case sym.NUEVO_SITIO_WEB:
                    response += startTranslate(createSiteT, tokens);
                    break;
                case sym.BORRAR_SITIO_WEB:
                    response += startTranslate(deleteSiteT, tokens);
                    break;
                case sym.NUEVA_PAGINA:
                    response += startTranslate(createPageT, tokens);
                    break;
                case sym.BORRAR_PAGINA:
                    response += startTranslate(deletePageT, tokens);
                    break;
                case sym.MODIFICAR_PAGINA:
                    response += startTranslate(modifyPageT, tokens);
                    break;
                case sym.AGREGAR_COMPONENTE:
                    response += startTranslate(addCompT, tokens);
                    break;
                case sym.BORRAR_COMPONENTE:
                    response += startTranslate(deleteCompT, tokens);
                    break;
                case sym.MODIFICAR_COMPONENTE:
                    response += startTranslate(modifyCompT, tokens);
                    break;
                default:
            }
        }
        return response;
    }

    private String startTranslate(StmTraductor traductor, List<Token> tokens) {
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
