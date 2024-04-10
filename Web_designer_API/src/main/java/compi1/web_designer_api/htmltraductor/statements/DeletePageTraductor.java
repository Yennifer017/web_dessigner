
package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.models.DeletePageModel;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.htmltraductor.sym;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class DeletePageTraductor extends StmTraductor{

    public DeletePageTraductor() {
        super.name = "Eliminar pagina";
        super.semanticErrors = new ArrayList<>();
        
    }
    
    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        DeletePageModel model = new DeletePageModel();
        Token currentTkn = tokens.get(index.get());
        if(currentTkn.getType() == sym.PARAMETROS){
            index.increment(); //pasar de parametros
            currentTkn = tokens.get(index.get());
            while (currentTkn.getType() != sym.PARAMETROS) {                
                recoveryParams(tokens, index, model);
                currentTkn = tokens.get(index.get());
            }
            index.increment(); //pasar del token parametros y finalizar
        } else {
            recoveryParams(tokens, index, model);
        }
        return model;
    }

    @Override
    protected void internalTranslate(XMLmodel model) throws ModelException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void recoveryParams(List<Token> tokens, Index index, XMLmodel xmlmodel) {
        Token nameParamTkn = tokens.get(index.get());
        index.increment();
        Token valueParamTkn = tokens.get(index.get());
        index.increment();
        if(nameParamTkn.getType() == sym.ID){
            xmlmodel.setId(valueParamTkn.getLexem().toString());
        }
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        super.semanticErrors.clear();
        DeletePageModel model = (DeletePageModel) this.getModel(tokens, index);
        if(!semanticErrors.isEmpty()){
            throw new ModelException();
        }else if(!model.hasEnoughParams()){
            semanticErrors.add(model.getMissingParams());
            throw new ModelException();
        }
        internalTranslate(model);
        return "Sitio <" + model.getId() + "> eliminado exitosamente";
    }
    
}
