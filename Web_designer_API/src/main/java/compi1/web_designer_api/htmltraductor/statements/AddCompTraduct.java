package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.database.ComponentDB;
import compi1.web_designer_api.database.PageDB;
import compi1.web_designer_api.database.SiteDB;
import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.models.AddCompModel;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.htmltraductor.sym;
import compi1.web_designer_api.util.FilesUtil;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class AddCompTraduct extends StmTraductor {

    private AttributeRecolecor attrRecover;
    private FilesUtil filesUtil;
    private SiteDB siteDB;
    private PageDB pageDB;
    private ComponentDB componentDB;
    private ComponentCreator componentCreator;

    public AddCompTraduct(Connection connection) {
        super.semanticErrors = new ArrayList<>();
        super.name = "Agregar componente";
        attrRecover = new AttributeRecolecor();
        filesUtil = new FilesUtil();

        this.siteDB = new SiteDB(connection);
        this.pageDB = new PageDB(connection, siteDB);
        this.componentDB = new ComponentDB(connection);
        this.componentCreator = new ComponentCreator();
    }

    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        AddCompModel model = new AddCompModel();
        Token currentTkn = tokens.get(index.get()); //params or attributes

        boolean inStatement = true;
        while (index.get() < tokens.size() && inStatement) {
            switch (currentTkn.getType()) {
                case sym.PARAMETROS:
                    index.increment();
                    recoveryParams(tokens, index, model);
                    break;
                case sym.ATRIBUTOS:
                    index.increment();
                    model.getAttributes().addAll(attrRecover.recoveryAttributes(tokens, index));
                    break;
                default:
                    inStatement = false;
                    break;
            }
            if (index.get() < tokens.size()) {
                currentTkn = tokens.get(index.get()); //params, attributes or finish
            }
        }
        return model;
    }

    @Override
    protected void internalTranslate(XMLmodel xmlModel) throws ModelException {
        AddCompModel model = (AddCompModel) xmlModel;
        int idPage = pageDB.getId(model.getPage());
        if(!componentDB.exist(idPage, model.getId())){
            
        }else {
            semanticErrors.add("No se puede agregar <" + model.getId() 
                    + "> a la pagina <" + model.getPage() + "> porque su id esta repetido");
            throw new ModelException();
        }
    }
    
    private void rewriteHtmlFile(){
        //agregar el componente en el html
    }

    @Override
    protected void recoveryParams(List<Token> tokens, Index index, XMLmodel xmlmodel) {
        Token currentTkn = tokens.get(index.get());
        while (currentTkn.getType() != sym.PARAMETROS) {
            AddCompModel model = (AddCompModel) xmlmodel;

            Token nameParamTkn = tokens.get(index.get());
            index.increment();
            Token valueParamTkn = tokens.get(index.get());
            index.increment(); //pasar al siguiente parametro o salir

            switch (nameParamTkn.getType()) {
                case sym.ID:
                    recoveryId(model, nameParamTkn, valueParamTkn);
                    break;
                case sym.PAGINA:
                    recoveryPageId(model, nameParamTkn, valueParamTkn);
                    break;
                case sym.CLASE:
                    if (model.getClassCode() > 0) {
                        super.addRepetedParamError(nameParamTkn);
                    }else{
                        model.setClassCode(valueParamTkn.getType());
                    }
                    
                    break;
                default:
                    throw new AssertionError("No se esperaba eso, error sintactico");
            } //switch end
            currentTkn = tokens.get(index.get());
        }
        index.increment(); //salir de parametros
    }

    private void recoveryId(AddCompModel model, Token nameParamTkn, Token valueParamTkn) {
        if (model.getId() != null) {
            super.addRepetedParamError(nameParamTkn);
        } else {
            model.setId(valueParamTkn.getLexem().toString());
        }
    }

    private void recoveryPageId(AddCompModel model, Token nameParamTkn, Token valueParamTkn) {
        if (model.getPage() != null) {
            super.addRepetedParamError(nameParamTkn);
        } else {
            model.setPage(valueParamTkn.getLexem().toString());
            if(!pageDB.exist(model.getPage())){
                super.addNoFoundError(valueParamTkn, " no se puede agregar el componente a una pagina invalida");
            }
        }
        
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        super.semanticErrors.clear();
        AddCompModel model = (AddCompModel) getModel(tokens, index);
        //crear el componente y asegurarse que no este vacio
        return "Componente <" + model.getId() + "> agregado exitosamente";
    }

}
