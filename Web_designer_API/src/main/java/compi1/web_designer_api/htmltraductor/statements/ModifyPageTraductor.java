
package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.models.ModifyPageModel;
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
public class ModifyPageTraductor extends StmTraductor{

    
    public ModifyPageTraductor(){
        super.semanticErrors =  new ArrayList<>();
        super.name = "Modificar pagina";
    }
    
    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        ModifyPageModel model = new ModifyPageModel();
        Token currentTkn = tokens.get(index.get()); //params or labels
        index.increment(); //pasar la etiqueta

        boolean inStatement = true;
        while (index.get() < tokens.size() && inStatement) {
            switch (currentTkn.getType()) {
                case sym.PARAMETROS:
                    recoveryParams(tokens, index, model);
                    break;
                case sym.ETIQUETAS:
                    recoveryLabels(tokens, index, model);
                    break;
                default:
                    inStatement = false;
                    break;
            }
        }
        return model;
    }

    @Override
    protected void internalTranslate(XMLmodel model) throws ModelException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void recoveryParams(List<Token> tokens, Index index, XMLmodel xmlmodel) {
        Token currentTkn = tokens.get(index.get());
        while (currentTkn.getType() != sym.PARAMETROS) {    
            ModifyPageModel model = (ModifyPageModel) xmlmodel;
            Token nameParamTkn = tokens.get(index.get());
            index.increment();
            Token valueParamTkn = tokens.get(index.get());
            index.increment(); //pasar al siguiente parametro o salir
            
            switch (nameParamTkn.getType()) {
                case sym.ID:
                    if (model.getId() != null) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    } else {
                        model.setId(valueParamTkn.getLexem().toString());
                    }
                    break;
                case sym.TITULO:
                    if (model.getTitle()!= null) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    } else {
                        model.setTitle(valueParamTkn.getLexem().toString());
                    }
                    break;
                default:
                    throw new AssertionError("No se esperaba eso, error sintactico");
            } //switch end
            currentTkn = tokens.get(index.get());
        }
        index.increment(); //pasar de parametros
    }
    
    private void recoveryLabels(List<Token> tokens, Index index, ModifyPageModel model){
        Token currentTkn = tokens.get(index.get());
        while (currentTkn.getType() != sym.ETIQUETAS) {            
            model.getLabels().add(currentTkn.getLexem().toString());
            index.increment();
            currentTkn = tokens.get(index.get());
        }
        index.increment(); //pasar de las etiquetas
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        super.semanticErrors.clear();
        ModifyPageModel model = (ModifyPageModel) this.getModel(tokens, index);
        if(!semanticErrors.isEmpty()){
            throw new ModelException();
        }else if(!model.hasEnoughParams()){
            semanticErrors.add(model.getMissingParams());
            throw new ModelException();
        }
        this.internalTranslate(model);
        return "Modificacion de la pagina <" + model.getId() + "> completada exitosamente.";
    }
    
}
