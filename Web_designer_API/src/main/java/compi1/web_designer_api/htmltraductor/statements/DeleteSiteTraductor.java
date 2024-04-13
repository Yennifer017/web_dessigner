package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.database.SiteDB;
import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.models.DeleteSiteModel;
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
public class DeleteSiteTraductor extends StmTraductor {

    private FilesUtil filesUtil;
    private SiteDB siteDB;

    public DeleteSiteTraductor(Connection connection) {
        super.name = "Borrar sitio";
        super.semanticErrors = new ArrayList<>();
        filesUtil = new FilesUtil();
        siteDB = new SiteDB(connection);
    }

    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        DeleteSiteModel model = new DeleteSiteModel();
        Token currentTkn = tokens.get(index.get());
        if (currentTkn.getType() == sym.PARAMETROS) {
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
        String path = FilesUtil.SITES_PATH_SERVER + FilesUtil.getSeparator() + model.getId();
        boolean deletedFromDB = siteDB.onDelete(model.getId());
        if (!deletedFromDB) {
            semanticErrors.add("Error inesperado, no se pudo eliminar el sitio de la base de datos,"
                    + " id <" + model.getId() + ">");
            throw new ModelException();
        }
        boolean deleteFromFiles = filesUtil.deleteDirectory(path);
        if (!deleteFromFiles) {
            semanticErrors.add("Error inesperado, no se pudieron eliminar los archivos del sitio con "
                    + "id <" + model.getId() + ">");
            throw new ModelException();
        }
    }

    @Override
    protected void recoveryParams(List<Token> tokens, Index index, XMLmodel xmlmodel) {
        DeleteSiteModel model = (DeleteSiteModel) xmlmodel;
        Token nameParamTkn = tokens.get(index.get());
        index.increment();
        Token valueParamTkn = tokens.get(index.get());
        index.increment();
        if (nameParamTkn.getType() == sym.ID) {
            model.setId(valueParamTkn.getLexem().toString());
            if (!siteDB.exist(model.getId())) {
                super.addNoFoundError(valueParamTkn, " no se puede eliminar el sitio");
            }
        }
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        super.semanticErrors.clear();
        DeleteSiteModel model = (DeleteSiteModel) this.getModel(tokens, index);
        if (!semanticErrors.isEmpty()) {
            throw new ModelException();
        } else if (!model.hasEnoughParams()) {
            semanticErrors.add(model.getMissingParams());
            throw new ModelException();
        }
        internalTranslate(model);
        return "Sitio <" + model.getId() + "> eliminado exitosamente";
    }

}
