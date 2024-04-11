
package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.models.AddCompModel;
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
public class AddCompTraduct extends StmTraductor{

    private AttributeTrans attrRecover;
    
    public AddCompTraduct(){
        super.semanticErrors = new ArrayList<>();
        super.name = "Agregar componente";
        attrRecover = new AttributeTrans();
    }
    
    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        AddCompModel model = new AddCompModel();
        Token currentTkn = tokens.get(index.get()); //params or attributes
        index.increment(); //pasar la etiqueta
        
        boolean inStatement = true;
        while (index.get() < tokens.size() && inStatement) {
            switch (currentTkn.getType()) {
                case sym.PARAMETROS:
                    recoveryParams(tokens, index, model);
                    break;
                case sym.ATRIBUTOS:
                    model.getAttributes().addAll(attrRecover.recoveryAttributes(tokens, index));
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
        throw new UnsupportedOperationException("Not supported yet.");
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
                    if (model.getId() != null) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    }
                    model.setId(valueParamTkn.getLexem().toString());
                    break;
                case sym.PAGINA:
                    if (model.getPage()!= null) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    }
                    model.setPage(valueParamTkn.getLexem().toString());
                    break;
                case sym.CLASE:
                    if (model.getClassCode() > 0) {
                        semanticErrors.add(super.getRepetedParamError(nameParamTkn));
                    }
                    model.setClassCode(valueParamTkn.getType());
                    break;
                default:
                    throw new AssertionError("No se esperaba eso, error sintactico");
            } //switch end
            currentTkn = tokens.get(index.get());
        }
        index.increment(); //salir de parametros
    }
    
    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
