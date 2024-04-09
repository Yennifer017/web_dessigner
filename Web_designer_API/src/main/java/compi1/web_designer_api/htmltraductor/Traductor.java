
package compi1.web_designer_api.htmltraductor;

import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.statements.CreateSiteTraductor;
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
    private List<String> errors;
    
    public Traductor(){
        index = new Index();
        createSiteT = new CreateSiteTraductor(errors);
    }
    
    public void traducir(List<Token> tokens){
        index.setIndex(0);
        for (index.get(); index.get() < tokens.size(); index.increment()) {
            Token currentTkn = tokens.get(index.get());
            index.increment(); //para pasar al siguiente token
            
            switch (currentTkn.getType()) {
                case sym.NUEVO_SITIO_WEB:
                    try {
                        createSiteT.translate(tokens, index);
                    } catch (ModelException e) {
                        //agregar error
                    }
                    break;
                case sym.BORRAR_SITIO_WEB:
                    
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
                    /*do nothing*/
            }
        }
    }
}
