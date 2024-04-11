package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.models.CreatePageModel;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.htmltraductor.sym;
import compi1.web_designer_api.util.FilesUtil;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class CreatePageTraductor extends StmTraductor {

    private FilesUtil filesUtil;
    public CreatePageTraductor(){
        super.semanticErrors = new ArrayList<>();
        super.name = "Crear pagina";
        filesUtil = new FilesUtil();
    }
    
    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        CreatePageModel model = new CreatePageModel();
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
            CreatePageModel model = (CreatePageModel) xmlmodel;
            Token nameParamTkn = tokens.get(index.get());
            index.increment();
            Token valueParamTkn = tokens.get(index.get());
            index.increment(); //pasar al siguiente parametro o salir
            
            switch (nameParamTkn.getType()) {
                case sym.ID:
                    if (model.getId() != null) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    }
                    model.setId(valueParamTkn.getLexem().toString());
                    break;
                case sym.TITULO:
                    if (model.getTitle()!= null) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    }
                    model.setTitle(valueParamTkn.getLexem().toString());
                    break;
                case sym.SITIO:
                    if (model.getSite()!= null) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    }
                    model.setSite(valueParamTkn.getLexem().toString());
                    break;
                case sym.PADRE:
                    if (model.getFather()!= null) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    }
                    model.setFather(valueParamTkn.getLexem().toString());
                    break;
                case sym.USUARIO_CREACION:
                    if (model.getUserCreateId() != null) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    }
                    model.setUserCreateId(valueParamTkn.getLexem().toString());
                    break;
                case sym.USUARIO_MODIFICACION:
                    if (model.getUserModifyId() != null) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    }
                    model.setUserModifyId(valueParamTkn.getLexem().toString());
                    break;
                case sym.FECHA_CREACION:
                    if (model.getDateCreated() != null) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    }
                    model.setDateCreated(LocalDate.parse(valueParamTkn.getLexem().toString()));
                    break;
                case sym.FECHA_MODIFICACION:
                    if (model.getDateModify() != null) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    }
                    model.setDateModify(LocalDate.parse(valueParamTkn.getLexem().toString()));
                    break;
                default:
                    throw new AssertionError("No se esperaba eso, error sintactico");
            } //switch end
            currentTkn = tokens.get(index.get());
        }
        index.increment(); //salir de parametros
    }

    private void recoveryLabels(List<Token> tokens, Index index, CreatePageModel model) {
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
        CreatePageModel model = (CreatePageModel) this.getModel(tokens, index);
        if(!semanticErrors.isEmpty()){
            throw new ModelException();
        }else if(!model.hasEnoughParams()){
            semanticErrors.add(model.getMissingParams());
            throw new ModelException();
        }else if(model.isCompleate()){
            model.autocompleate();
        }
        this.internalTranslate(model);
        return "Pagina <" + model.getId() + "> creada exitosamente.";
    }

}
