package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.database.LabelDB;
import compi1.web_designer_api.database.PageDB;
import compi1.web_designer_api.database.SiteDB;
import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.HTMLgenerator;
import compi1.web_designer_api.htmltraductor.models.ModifyPageModel;
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
public class ModifyPageTraductor extends StmTraductor {

    private LabelDB labelDB;
    private SiteDB siteDB;
    private PageDB pageDB;
    private HTMLgenerator htmlGen;
    private FilesUtil filesUtil;

    public ModifyPageTraductor(Connection connection) {
        super.semanticErrors = new ArrayList<>();
        super.name = "Modificar pagina";
        labelDB = new LabelDB(connection);
        siteDB = new SiteDB(connection);
        pageDB = new PageDB(connection, siteDB );
        htmlGen = new HTMLgenerator();
        filesUtil = new FilesUtil();
    }

    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        ModifyPageModel model = new ModifyPageModel();
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
            if (index.get() < tokens.size()) {
                currentTkn = tokens.get(index.get());
            }
        }
        return model;
    }

    @Override
    protected void internalTranslate(XMLmodel model) throws ModelException {
        ModifyPageModel mModel = (ModifyPageModel) model;
        if (!mModel.getLabels().isEmpty()) {
            replaceLabels(mModel);
        }
        if (mModel.getTitle() != null) {
            try {
                changeTitle(mModel);
            } catch (IOException | SQLException ex) {
                semanticErrors.add("Error inesperado, no se pudo actualizar el titulo");
                throw new ModelException();
            }
        }
    }

    private void replaceLabels(ModifyPageModel model) {
        int idPage = pageDB.getId(model.getId());
        labelDB.deleteAllInPage(idPage);
        for (String label : model.getLabels()) {
            if (!labelDB.exist(label)) {
                labelDB.insertIntoDB(label);
            }
            try {
                labelDB.addLabel(label, idPage);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    private void changeTitle(ModifyPageModel model) throws FileNotFoundException, IOException, SQLException {
        StringBuilder contenido = new StringBuilder();
        String path = FilesUtil.SITES_PATH_SERVER + FilesUtil.getSeparator() 
                + siteDB.getName(pageDB.getIdSite(model.getId())) + FilesUtil.getSeparator()
                + model.getId() + FilesUtil.HTML_EXTENSION;
        File archivo = new File(path); //creando el archivo
        FileReader lector = new FileReader(archivo); //lector del archivo
        BufferedReader buffer = new BufferedReader(lector); //para leer mas rapido el archivo
        String linea;
        boolean inTitle = false;
        while ((linea = buffer.readLine()) != null) {
            if (!inTitle) {
                contenido.append(linea).append("\n");
            } else {
                contenido.append(htmlGen.getCodeTitle(model.getTitle()));
                inTitle = false;
            }
            if (linea.equals(HTMLgenerator.TITLE_INDICATOR)) {
                inTitle = true;
            }
        }
        buffer.close();
        lector.close();
        filesUtil.saveFile(contenido.toString(), archivo);
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
                    recoveryId(model, nameParamTkn, valueParamTkn);
                    break;
                case sym.TITULO:
                    if (model.getTitle() != null) {
                        super.addRepetedParamError(nameParamTkn);
                    } else {
                        model.setTitle(valueParamTkn.getLexem().toString());
                    }
                    break;
                default:
                    throw new AssertionError("No se esperaba eso, error sintactico");
            } //switch end
            currentTkn = tokens.get(index.get());
        }
        if (index.get() < tokens.size()) {
            index.increment(); //pasar de parametros
        }
    }

    private void recoveryId(ModifyPageModel model, Token nameParamTkn, Token valueParamTkn) {
        if (model.getId() != null) {
            super.addRepetedParamError(nameParamTkn);
        } else {
            model.setId(valueParamTkn.getLexem().toString());
            if (!pageDB.exist(model.getId())) {

            }
        }
    }

    private void recoveryLabels(List<Token> tokens, Index index, ModifyPageModel model) {
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
        if (!semanticErrors.isEmpty()) {
            throw new ModelException();
        } else if (!model.hasEnoughParams()) {
            semanticErrors.add(model.getMissingParams());
            throw new ModelException();
        }
        this.internalTranslate(model);
        return "Modificacion de la pagina <" + model.getId() + "> completada exitosamente.";
    }

}
