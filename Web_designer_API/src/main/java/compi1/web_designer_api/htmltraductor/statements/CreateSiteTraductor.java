package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.models.CreateSiteModel;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.htmltraductor.sym;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class CreateSiteTraductor extends StmTraductor {

    public CreateSiteTraductor(List<String> semanticErrors) {
        super.semanticErrors = semanticErrors;
    }

    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        CreateSiteModel model = new CreateSiteModel();
        index.increment(); //pasar el token parametros
        if (index.get() >= tokens.size()) {
            throw new AssertionError("Error sintactico inesperado en createSite");
        }
        Token currentTkn = tokens.get(index.get());
        while (index.get() < tokens.size() && currentTkn.getType() != sym.PARAMETROS) {
            recoveryParams(tokens, index, model);
            currentTkn = tokens.get(index.get());
        }
        index.increment(); //finalizar el token de parametros y tambien el create site
        return model;
    }

    @Override
    protected void internalTranslate(XMLmodel model) throws ModelException{
        
    }

    @Override
    protected void recoveryParams(List<Token> tokens, Index index, XMLmodel xmlmodel) {
        CreateSiteModel model = (CreateSiteModel) xmlmodel;
        Token nameParamTkn = tokens.get(index.get());
        index.increment();
        Token valueParamTkn = tokens.get(index.get());
        index.increment();
        switch (nameParamTkn.getType()) {
            case sym.ID:
                if(model.getId() != null){
                    //agregar error
                }
                model.setId(valueParamTkn.getLexem().toString());
                break;
            case sym.USUARIO_CREACION:
                if(model.getUserCreateId() != null){
                    
                }
                model.setUserCreateId(valueParamTkn.getLexem().toString());
                break;
            case sym.USUARIO_MODIFICACION:
                if(model.getUserModifyId() != null){
                    
                }
                model.setUserModifyId(valueParamTkn.getLexem().toString());
                break;
            case sym.FECHA_CREACION:
                if(model.getDateCreated() != null){
                    
                }
                model.setDateCreated(LocalDate.parse(valueParamTkn.getLexem().toString()));
                break;
            case sym.FECHA_MODIFICACION:
                if(model.getDateModify() != null){
                    
                }
                model.setDateModify(LocalDate.parse(valueParamTkn.getLexem().toString()));
                break;
            default:
                throw new AssertionError("No se esperaba eso, error sintactico");
        }
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        CreateSiteModel model = (CreateSiteModel) this.getModel(tokens, index);
        if (!model.hasEnoughParams()) {
            throw new ModelException();
        } else if (!model.isCompleate()) {
            model.autocompleate();
        }
        internalTranslate(model);
        return "Sitio <" + model.getId() + "> creado exitosamente, url: ";
    }

}
