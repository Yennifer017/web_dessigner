package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.database.PageDB;
import compi1.web_designer_api.database.SiteDB;
import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.exceptions.OverWrittingFileException;
import compi1.web_designer_api.htmltraductor.HTMLgenerator;
import compi1.web_designer_api.htmltraductor.models.CreateSiteModel;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.htmltraductor.sym;
import compi1.web_designer_api.util.FilesUtil;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class CreateSiteTraductor extends StmTraductor {
    
    private FilesUtil filesUtil;
    private HTMLgenerator htmlGen;
    private SiteDB siteDB;
    private PageDB pageDB;

    public CreateSiteTraductor(Connection connection) {
        super.semanticErrors = new ArrayList<>();
        super.name = "Crear sitio";
        filesUtil = new FilesUtil();
        htmlGen = new HTMLgenerator();
        
        siteDB = new SiteDB(connection);
        pageDB = new PageDB(connection, siteDB);
    }

    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        CreateSiteModel model = new CreateSiteModel();
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
    protected void internalTranslate(XMLmodel model) throws ModelException{
        String sitePath = "";
        try {
            siteDB.insertIntoDB(model.getId());
            sitePath = filesUtil.createDirectory(FilesUtil.SITES_PATH_SERVER, model.getId());
        } catch (IOException | OverWrittingFileException ex) {
            semanticErrors.add("Ocurrio un error inesperado al crear el sitio <" + model.getId() + ">");
            throw new ModelException();
        }
        
        try {
            filesUtil.saveAs(
                    htmlGen.getCodePageHtml("index" + model.getId()), 
                    FilesUtil.HTML_EXTENSION, 
                    model.getId(), 
                    sitePath
            );
            pageDB.insertIntoDB(model.getId(), model.getId());
        } catch (IOException | OverWrittingFileException ex) {
            semanticErrors.add("Ocurrio un error inesperado al crear el index del sitio <" 
                    + model.getId() + "> se recomienda borrarlo y volverlo a crear");
            throw new ModelException();
        } catch (SQLException ex) {
            System.out.println(ex);
            semanticErrors.add("Ocurrio un error inesperado al agregar el index del sitio <" 
                    + model.getId() + "> a la base de datos, se recomienda borrar el sitio "
                    + "y volverlo a crear o crear una pagina personalizada que sea el index");
            throw new ModelException();
        } 
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
                recoveryId(model, nameParamTkn, valueParamTkn);
                break;
            case sym.USUARIO_CREACION:
                if(model.getUserCreateId() != null){
                    super.addRepetedParamError(nameParamTkn);
                }
                model.setUserCreateId(valueParamTkn.getLexem().toString());
                break;
            case sym.USUARIO_MODIFICACION:
                if(model.getUserModifyId() != null){
                    super.addRepetedParamError(nameParamTkn);
                }
                model.setUserModifyId(valueParamTkn.getLexem().toString());
                break;
            case sym.FECHA_CREACION:
                if(model.getDateCreated() != null){
                    super.addRepetedParamError(nameParamTkn);
                }
                model.setDateCreated(LocalDate.parse(valueParamTkn.getLexem().toString()));
                break;
            case sym.FECHA_MODIFICACION:
                if(model.getDateModify() != null){
                    super.addRepetedParamError(nameParamTkn);
                }
                model.setDateModify(LocalDate.parse(valueParamTkn.getLexem().toString()));
                break;
            default:
                throw new AssertionError("No se esperaba eso, error sintactico");
        }
    }
    
    private void recoveryId(CreateSiteModel model, Token nameParamTkn, Token valueParamTkn){
        if (model.getId() != null) {
            super.addRepetedParamError(nameParamTkn);
        } else {
            model.setId(valueParamTkn.getLexem().toString());
            if (siteDB.exist(model.getId())) {
                super.addOverWrittingError(valueParamTkn, " del sitio");
            }
        }
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        semanticErrors.clear();
        CreateSiteModel model = (CreateSiteModel) this.getModel(tokens, index);
        if(!semanticErrors.isEmpty()){
            throw new ModelException();
        } else if (!model.hasEnoughParams()) {
            semanticErrors.add(model.getMissingParams());
            throw new ModelException();
        } else if (!model.isCompleate()) {
            model.autocompleate();
        }
        internalTranslate(model);
        return "Sitio <" + model.getId() + "> creado exitosamente, url: ";
    }

}
