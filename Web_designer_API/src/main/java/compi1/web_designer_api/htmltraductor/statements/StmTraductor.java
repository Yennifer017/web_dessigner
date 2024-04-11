
package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author yennifer
 */
public abstract class StmTraductor {
    protected String name;
    protected List<String> semanticErrors;
    protected Connection connection;
    
    protected abstract XMLmodel getModel(List<Token> tokens, Index index);
    protected abstract void internalTranslate(XMLmodel model) throws ModelException;
    protected abstract void recoveryParams(List<Token> tokens, Index index, XMLmodel xmlmodel);
    
    public abstract String translate(List<Token> tokens, Index index) throws ModelException;
    
    protected String getRepetedParamError(Token param){
        return "Se esta intentando sobreescribir el parametro <" + param.getLexem().toString()
                + "> linea: " + param.getLine() + " - columna:" + param.getColumn(); 
    }
    
    public List<String> getSemanticErrors(){
        return this.semanticErrors;
    }
    
    public void addFailMss(){
        this.semanticErrors.add("No se pudo ejecutar una instruccion " + name);
    };
    
}
