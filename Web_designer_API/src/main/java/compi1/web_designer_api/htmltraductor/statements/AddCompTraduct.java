package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.database.ComponentDB;
import compi1.web_designer_api.database.LabelDB;
import compi1.web_designer_api.database.PageDB;
import compi1.web_designer_api.database.SiteDB;
import compi1.web_designer_api.database.models.ComponentModelDB;
import compi1.web_designer_api.exceptions.IncompleateCompException;
import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.exceptions.NoCodeException;
import compi1.web_designer_api.htmltraductor.HTMLgenerator;
import compi1.web_designer_api.htmltraductor.models.AddCompModel;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.htmltraductor.models.comp.ComponentHtml;
import compi1.web_designer_api.htmltraductor.models.comp.MenuComp;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private LabelDB labelDB;
    private ComponentCreator componentCreator;
    private List<String> warnings;

    public AddCompTraduct(Connection connection) {
        super.semanticErrors = new ArrayList<>();
        super.name = "Agregar componente";
        attrRecover = new AttributeRecolecor();
        filesUtil = new FilesUtil();

        this.siteDB = new SiteDB(connection);
        this.pageDB = new PageDB(connection, siteDB);
        this.componentDB = new ComponentDB(connection);
        this.labelDB = new LabelDB(connection);
        this.componentCreator = new ComponentCreator();
        this.warnings = new ArrayList<>();
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
            ComponentHtml component = componentCreator.createModel(
                    model.getClassTkn().getType(), model.getAttributes(), warnings, model.getId()
            );
            if(component.canCreate()){
                try {
                    ComponentModelDB componentModel = new ComponentModelDB(
                            idPage, model.getId(), model.getClassTkn().getLexem().toString()
                    );
                    componentDB.insertIntoDB(componentModel);
                    addComponentToFile(model, component);
                } catch (SQLException ex) {
                    System.out.println(ex);
                    semanticErrors.add("Ocurrio un erro inesperado al escribir/consultar "
                            + "en la base de datos al tratar de crear un componente");
                    throw new ModelException();
                } catch (IOException ex) {
                    System.out.println(ex);
                    semanticErrors.add("Ocurrio un error inesperado al intentar "
                            + "sobreescribir el html y agregar un componente.");
                    throw new ModelException();
                } catch (IncompleateCompException ex) {
                    Logger.getLogger(AddCompTraduct.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoCodeException ex) {
                    semanticErrors.add("Los filtros para crear el menu no retornaron ninguna pagina,"
                            + " no se puede crear el menu.");
                    throw new ModelException();
                }
            }else{
                semanticErrors.add("Faltan los atributos:" + component.getMissingAttributes());
                throw new ModelException();
            }
            
        }else {
            semanticErrors.add("No se puede agregar <" + model.getId() 
                    + "> a la pagina <" + model.getPage() + "> porque su id esta repetido");
            throw new ModelException();
        }
    }
    
    private void addComponentToFile(AddCompModel model, ComponentHtml component) 
            throws SQLException, FileNotFoundException, IOException, IncompleateCompException, NoCodeException{
        StringBuilder contenido = new StringBuilder();
        String path = FilesUtil.SITES_PATH_SERVER + FilesUtil.getSeparator() 
                + siteDB.getName(pageDB.getIdSite(model.getPage())) 
                + FilesUtil.getSeparator()
                + model.getPage() + FilesUtil.HTML_EXTENSION;
        System.out.println(path);
        File archivo = new File(path); //creando el archivo
        FileReader lector = new FileReader(archivo); //lector del archivo
        BufferedReader buffer = new BufferedReader(lector); //para leer mas rapido el archivo
        String linea;
        boolean inEndOfBody = false;
        while ((linea = buffer.readLine()) != null) {
            if (linea.equals(HTMLgenerator.END_COMPONENTS)) {
                inEndOfBody = true;
            }
            if (inEndOfBody) {
                if(component instanceof MenuComp){
                    MenuComp menuComp = (MenuComp) component;
                    contenido.append(menuComp.getHtmlCode(pageDB, labelDB))
                            .append(HTMLgenerator.END_COMPONENTS);
                }else{
                    contenido.append(component.getHtmlCode())
                            .append(HTMLgenerator.END_COMPONENTS);
                }
                contenido.append("\n");
                inEndOfBody = false;
            } else {
                contenido.append(linea).append("\n");
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
                    if (model.getClassTkn() != null) {
                        super.addRepetedParamError(nameParamTkn);
                    }else{
                        model.setClassTkn(valueParamTkn);
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
        this.warnings.clear();
        AddCompModel model = (AddCompModel) getModel(tokens, index);
        if(!semanticErrors.isEmpty()){
            throw new ModelException();
        } else if(!model.isCompleate()){
            semanticErrors.add(model.getMissingParams());
            throw new ModelException();
        }
        internalTranslate(model);
        return "Componente <" + model.getId() + "> agregado exitosamente";
    }
    
    public List<String> getWarnings(){
        return this.warnings;
    }

}
