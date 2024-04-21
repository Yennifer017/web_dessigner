package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.database.LabelDB;
import compi1.web_designer_api.database.PageDB;
import compi1.web_designer_api.database.SiteDB;
import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.exceptions.OverWrittingFileException;
import compi1.web_designer_api.htmltraductor.HTMLgenerator;
import compi1.web_designer_api.htmltraductor.models.CreatePageModel;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.htmltraductor.models.comp.RecordHtmlModel;
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
public class CreatePageTraductor extends StmTraductor {

    private FilesUtil filesUtil;
    private HTMLgenerator htmlGen;
    private SiteDB siteDB;
    private PageDB pageDB;
    private LabelDB labelDB;

    public CreatePageTraductor(Connection connection) {
        super.semanticErrors = new ArrayList<>();
        super.name = "Crear pagina";
        filesUtil = new FilesUtil();
        htmlGen = new HTMLgenerator();

        this.siteDB = new SiteDB(connection);
        this.pageDB = new PageDB(connection, siteDB);
        this.labelDB = new LabelDB(connection);
    }

    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        CreatePageModel model = new CreatePageModel();
        Token currentTkn = tokens.get(index.get()); //params or labels

        boolean inStatement = true;
        while (index.get() < tokens.size() && inStatement) {
            switch (currentTkn.getType()) {
                case sym.PARAMETROS:
                    index.increment();
                    recoveryParams(tokens, index, model);
                    break;
                case sym.ETIQUETAS:
                    index.increment();
                    recoveryLabels(tokens, index, model);
                    break;
                default:
                    inStatement = false;
                    break;
            }
            if(index.get() < tokens.size()){
                currentTkn = tokens.get(index.get()); //params, labels or finish
            }
        }

        return model;
    }

    @Override
    protected void internalTranslate(XMLmodel model) throws ModelException {
        CreatePageModel cModel = (CreatePageModel) model;
        String path = FilesUtil.SITES_PATH_SERVER + FilesUtil.getSeparator() + cModel.getSite();
        String content = htmlGen.getCodePageHtml(
                cModel.getTitle(), 
                cModel.getRecord()
        );
        try {
            pageDB.insertIntoDB(cModel);
            registLabels(cModel);
            filesUtil.saveAs(content, FilesUtil.HTML_EXTENSION, model.getId(), path);
        } catch (SQLException ex) {
            System.out.println(ex);
            semanticErrors.add("Error inesperado, no se pudo agregar a la base de datos la pagina <"
                + model.getId() + ">");
            throw new ModelException();
        } catch (IOException | OverWrittingFileException ex) {
            System.out.println(ex);
            semanticErrors.add("Error inesperado, no se pudo crear la pagina <"
                + model.getId() + ">, se recomienda tratar de eliminarla");
            throw new ModelException();
        }
    }
    
    private void registLabels(CreatePageModel model){
        if(!model.getLabels().isEmpty()){
            int idPage = pageDB.getId(model.getId());
            for (String label : model.getLabels()) {
                if(!labelDB.exist(label)){
                    labelDB.insertIntoDB(label);
                }
                try {
                    labelDB.addLabel(label, idPage);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
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
                    recoveryId(model, nameParamTkn, valueParamTkn);
                    break;
                case sym.TITULO:
                    if (model.getTitle() != null) {
                        super.addRepetedParamError(nameParamTkn);
                    }
                    model.setTitle(valueParamTkn.getLexem().toString());
                    break;
                case sym.SITIO:
                    recoverySite(model, nameParamTkn, valueParamTkn);
                    break;
                case sym.PADRE:
                    recoveryFather(model, nameParamTkn, valueParamTkn);
                    break;
                case sym.USUARIO_CREACION:
                    if (model.getUserCreateId() != null) {
                        super.addRepetedParamError(nameParamTkn);
                    }
                    model.setUserCreateId(valueParamTkn.getLexem().toString());
                    break;
                case sym.USUARIO_MODIFICACION:
                    if (model.getUserModifyId() != null) {
                        super.addRepetedParamError(nameParamTkn);
                    }
                    model.setUserModifyId(valueParamTkn.getLexem().toString());
                    break;
                case sym.FECHA_CREACION:
                    if (model.getDateCreated() != null) {
                        super.addRepetedParamError(nameParamTkn);
                    }
                    model.setDateCreated(LocalDate.parse(valueParamTkn.getLexem().toString()));
                    break;
                case sym.FECHA_MODIFICACION:
                    if (model.getDateModify() != null) {
                        super.addRepetedParamError(nameParamTkn);
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

    private void recoveryId(CreatePageModel model, Token nameParamTkn, Token valueParamTkn) {
        if (model.getId() != null) {
            super.addRepetedParamError(nameParamTkn);
        } else {
            model.setId(valueParamTkn.getLexem().toString());
            if (pageDB.exist(model.getId())) {
                super.addOverWrittingError(valueParamTkn, " de la pagina no se puede usar");
            }
        }
    }

    private void recoverySite(CreatePageModel model, Token nameParamTkn, Token valueParamTkn) {
        if (model.getSite() != null) {
            super.addRepetedParamError(nameParamTkn);
        } else {
            model.setSite(valueParamTkn.getLexem().toString());
            if (!siteDB.exist(model.getSite())) {
                super.addNoFoundError(nameParamTkn, " la pagina no se puede crear");
            }
        }
    }

    private void recoveryFather(CreatePageModel model, Token nameParamTkn, Token valueParamTkn) {
        if (model.getFather() != null) {
            super.addRepetedParamError(nameParamTkn);
        } else {
            model.setFather(valueParamTkn.getLexem().toString());
            if (!pageDB.exist(model.getFather())) {
                super.addNoFoundError(nameParamTkn, 
                        " la pagina no se puede crear sin una pagina padre valida");
            }
        }
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
        if (!semanticErrors.isEmpty()) {
            throw new ModelException();
        } else if (!model.hasEnoughParams()) {
            semanticErrors.add(model.getMissingParams());
            throw new ModelException();
        } else if (!model.isCompleate()) {
            model.autocompleate();
        }
        this.internalTranslate(model);
        
        return "Pagina <" + model.getId() + "> creada exitosamente, url: "
                + filesUtil.getDirectoryCreatedPage(model.getFather(), model.getId());
    }

}
