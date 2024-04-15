package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.database.PageDB;
import compi1.web_designer_api.database.SiteDB;
import compi1.web_designer_api.database.models.Page;
import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.models.DeletePageModel;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.htmltraductor.sym;
import compi1.web_designer_api.util.FilesUtil;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class DeletePageTraductor extends StmTraductor {

    private PageDB pageDB;
    private SiteDB siteDB;
    private FilesUtil filesUtil;

    public DeletePageTraductor(Connection connection) {
        super.name = "Eliminar pagina";
        super.semanticErrors = new ArrayList<>();
        filesUtil = new FilesUtil();
        siteDB = new SiteDB(connection);
        pageDB = new PageDB(connection, siteDB);
    }

    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        DeletePageModel model = new DeletePageModel();
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
        try {
            int idSite = pageDB.getIdSite(model.getId());
            String sitePath = FilesUtil.SITES_PATH_SERVER + FilesUtil.getSeparator()
                    + siteDB.getName(idSite);
            onDeletePage(sitePath, model.getId());
            pageDB.onDelete(model.getId());
        } catch (SQLException e) {
            System.out.println(e);
            semanticErrors.add("Error inesperado al tratar de eliminar la pagina <" + model.getId()
                    + "> por favor contacta con adminsitracion");
            throw new ModelException("error inesperado");
        }
    }

    private void onDeletePage(String sitePath, String namePage) throws SQLException {
        String pathFather = sitePath + FilesUtil.getSeparator() + namePage + FilesUtil.HTML_EXTENSION;
        List<Page> childrenPages = pageDB.getChildren(namePage);
        if (!childrenPages.isEmpty()) {
            for (Page child : childrenPages) {
                onDeletePage(sitePath, child.getName());
            }
        }
        filesUtil.deleteFile(pathFather);
    }

    @Override
    protected void recoveryParams(List<Token> tokens, Index index, XMLmodel xmlmodel) {
        Token nameParamTkn = tokens.get(index.get());
        index.increment();
        Token valueParamTkn = tokens.get(index.get());
        index.increment();
        if (nameParamTkn.getType() == sym.ID) {
            xmlmodel.setId(valueParamTkn.getLexem().toString());
            if (!pageDB.exist(xmlmodel.getId())) {
                super.addNoFoundError(valueParamTkn, " no se puede eliminar");
            }
        }
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        super.semanticErrors.clear();
        DeletePageModel model = (DeletePageModel) this.getModel(tokens, index);
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
