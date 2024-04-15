package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.database.ComponentDB;
import compi1.web_designer_api.database.PageDB;
import compi1.web_designer_api.database.SiteDB;
import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.HTMLgenerator;
import compi1.web_designer_api.htmltraductor.models.DeleteCompModel;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.htmltraductor.sym;
import compi1.web_designer_api.util.FilesUtil;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class DeleteCompTraduc extends StmTraductor {

    private SiteDB siteDB;
    private PageDB pageDB;
    private ComponentDB componentDB;

    private FilesUtil filesUtil;

    public DeleteCompTraduc(Connection connection) {
        super.semanticErrors = new ArrayList<>();
        super.name = "eliminar componente";

        siteDB = new SiteDB(connection);
        pageDB = new PageDB(connection, siteDB);
        componentDB = new ComponentDB(connection);
        filesUtil = new FilesUtil();
    }

    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        DeleteCompModel model = new DeleteCompModel();
        index.increment(); //pasar del token parametros
        Token currentTkn = tokens.get(index.get());
        while (index.get() < tokens.size() && currentTkn.getType() != sym.PARAMETROS) {
            recoveryParams(tokens, index, model);
            currentTkn = tokens.get(index.get());
        }
        index.increment(); //finalizar el token de parametros y tambien el create site
        return model;
    }

    @Override
    protected void internalTranslate(XMLmodel xmlModel) throws ModelException {
        DeleteCompModel model = (DeleteCompModel) xmlModel;
        int idPage = pageDB.getId(model.getPage());
        if(componentDB.exist(idPage, model.getId())){
            try {
                componentDB.deleteComponent(model.getId(), idPage);
                deleteCompInFile(model);
            } catch (SQLException e) {
                System.out.println(e);
                semanticErrors.add("Ocurrio un error inesperado al leer/editar la base de datos");
                throw new ModelException();
            } catch (IOException ex) {
                System.out.println(ex);
                semanticErrors.add("Ocurrio un error inesperado al sobreescribir el archivo");
                throw new ModelException();
            }
        }else{
            throw new ModelException();
        }
    }

    private void deleteCompInFile(DeleteCompModel model) throws SQLException, FileNotFoundException, IOException {
        StringBuilder contenido = new StringBuilder();
        String path = FilesUtil.SITES_PATH_SERVER 
                + FilesUtil.getSeparator() 
                + siteDB.getName(pageDB.getIdSite(model.getPage())) 
                + FilesUtil.getSeparator()
                + model.getPage() + FilesUtil.HTML_EXTENSION;
        File archivo = new File(path); //creando el archivo
        FileReader lector = new FileReader(archivo); //lector del archivo
        BufferedReader buffer = new BufferedReader(lector); //para leer mas rapido el archivo
        String linea;
        boolean inComponent = false;
        while ((linea = buffer.readLine()) != null) {
            if (linea.equals(HTMLgenerator.COMPONENT_INIT + model.getId() + HTMLgenerator.COMPONENT_END)) {
                inComponent = true;
            }
            if(inComponent && linea.startsWith(HTMLgenerator.COMPONENT_INIT)){ //otro componente
                inComponent = false;
            }
            if(!inComponent){
                contenido.append(linea).append("\n");
            }
        }
        buffer.close();
        lector.close();
        filesUtil.saveFile(contenido.toString(), archivo);
    }

    @Override
    protected void recoveryParams(List<Token> tokens, Index index, XMLmodel xmlmodel) {
        DeleteCompModel model = (DeleteCompModel) xmlmodel;
        Token nameParamTkn = tokens.get(index.get());
        index.increment();
        Token valueParamTkn = tokens.get(index.get());
        index.increment();
        switch (nameParamTkn.getType()) {
            case sym.ID:
                recoveryId(model, nameParamTkn, valueParamTkn);
                break;
            case sym.PAGINA:
                recoveryPageId(model, nameParamTkn, valueParamTkn);
                break;
            default:
                throw new AssertionError("No se esperaba eso, error sintactico");
        }
    }

    private void recoveryId(DeleteCompModel model, Token nameParamTkn, Token valueParamTkn) {
        if (model.getId() != null) {
            super.addRepetedParamError(nameParamTkn);
        } else {
            model.setId(valueParamTkn.getLexem().toString());
        }
    }

    private void recoveryPageId(DeleteCompModel model, Token nameParamTkn, Token valueParamTkn) {
        if (model.getPage() != null) {
            super.addRepetedParamError(nameParamTkn);
        } else {
            model.setPage(valueParamTkn.getLexem().toString());
            if(!pageDB.exist(model.getPage())){
                super.addNoFoundError(valueParamTkn, "id de la pagina");
            }
        }
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        super.semanticErrors.clear();
        DeleteCompModel model = (DeleteCompModel) getModel(tokens, index);
        if(!semanticErrors.isEmpty()){
            throw new ModelException();
        } else if(!model.isCompleate()){
            semanticErrors.add(model.getMissingParams());
            throw new ModelException();
        }
        internalTranslate(model);
        return "Componente <" + model.getId() + "> eliminado exitosamente";
    }

}
