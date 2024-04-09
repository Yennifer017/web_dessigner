
package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.util.List;

/**
 *
 * @author yennifer
 */
public abstract class StmTraductor {
    protected List<String> semanticErrors;
    
    protected abstract XMLmodel getModel(List<Token> tokens, Index index);
    protected abstract void internalTranslate(XMLmodel model) throws ModelException;
    protected abstract void recoveryParams(List<Token> tokens, Index index, XMLmodel xmlmodel);
    
    public abstract String translate(List<Token> tokens, Index index) throws ModelException;
    
    
}