
package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import compi1.web_designer_api.util.Transladable;
import java.util.List;

/**
 *
 * @author yennifer
 */
public abstract class StmTraductor implements Transladable{
    protected String name;
    protected List<String> semanticErrors;
    
    protected abstract XMLmodel getModel(List<Token> tokens, Index index);
    protected abstract void internalTranslate(XMLmodel model) throws ModelException;
    protected abstract void recoveryParams(List<Token> tokens, Index index, XMLmodel xmlmodel);
    
    @Override
    public List<String> getSemanticErrors(){
        return this.semanticErrors;
    }
    
    @Override
    public void addFailMss(){
        this.semanticErrors.add("No se pudo ejecutar una instruccion " + name);
    };
    
    //ERRORS REPORT
 
    protected void addRepetedParamError(Token param){
        this.semanticErrors.add(
                "Se esta intentando sobreescribir el parametro <" + param.getLexem().toString()
                + "> linea: " + param.getLine() + " - columna:" + param.getColumn()
        ); 
    }
    
    /** 
     *  mensaje: El id <_id_> no existe, _mss_
     * @param param
     * @param mss
     */
    protected void addNoFoundError(Token param, String mss){
        this.semanticErrors.add("El id <" + param.getLexem().toString() + "> no existe," 
                + mss + " Linea: " + param.getLine() + " - columna: " + param.getColumn());
    }
    /** 
     *  mensaje: El id <_id_> _mss_ , ya existe 
     * @param param
     * @param mss
     */
    protected void addOverWrittingError(Token param, String mss) throws NullPointerException{
        this.semanticErrors.add("El id <" + param.getLexem().toString() + ">" + mss 
                + ", ya existe. Linea: " + param.getLine() + " - columna: " + param.getColumn());
    }
    
}
