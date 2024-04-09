package compi1.web_designer_api.htmltraductor;

import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.statements.CreateSiteTraductor;
import compi1.web_designer_api.htmltraductor.statements.DeleteSiteTraductor;
import compi1.web_designer_api.htmltraductor.statements.StmTraductor;
import compi1.web_designer_api.services.ResponserGen;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class Traductor {

    private Index index;

    private CreateSiteTraductor createSiteT;
    private DeleteSiteTraductor deleteSiteT;

    private ResponserGen responserGen;

    public Traductor() {
        index = new Index();

        createSiteT = new CreateSiteTraductor();
        deleteSiteT = new DeleteSiteTraductor();

        responserGen = new ResponserGen();
    }

    public String traducir(List<Token> tokens) {
        String response = "";
        index.setIndex(0);
        for (index.get(); index.get() < tokens.size(); index.increment()) {
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

                    break;
                case sym.BORRAR_PAGINA:

                    break;
                case sym.MODIFICAR_PAGINA:

                    break;
                case sym.AGREGAR_COMPONENTE:

                    break;
                case sym.BORRAR_COMPONENTE:

                    break;
                case sym.MODIFICAR_COMPONENTE:

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
