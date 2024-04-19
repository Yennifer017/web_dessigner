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
import compi1.web_designer_api.htmltraductor.models.ModifyCompModel;
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
public class ModifyCompTraduc extends StmTraductor {

    private ComponentDB componentDB;
    private FilesUtil filesUtil;
    private List<String> warnings;
    private AttributeRecolecor attrRecover;
    private SiteDB siteDB;
    private PageDB pageDB;
    private LabelDB labelDB;
    private ComponentCreator componentCreator;

    public ModifyCompTraduc(Connection connection) {
        super.semanticErrors = new ArrayList<>();
        super.name = "Modificar Componente";
        componentDB = new ComponentDB(connection);
        filesUtil = new FilesUtil();
        siteDB = new SiteDB(connection);
        pageDB = new PageDB(connection, siteDB);
        labelDB = new LabelDB(connection);
        attrRecover = new AttributeRecolecor();
        componentCreator = new ComponentCreator();
        warnings = new ArrayList<>();
    }

    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        ModifyCompModel model = new ModifyCompModel();
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
        ModifyCompModel model = (ModifyCompModel) xmlModel;
        int idPage = pageDB.getId(model.getPage());
        if (componentDB.exist(idPage, model.getId())) {
            ComponentHtml component = componentCreator.createModel(
                    model.getClassTkn().getType(), model.getAttributes(), warnings, model.getId()
            );
            if (component.canCreate()) {
                try {
                    ComponentModelDB componentModelDB = new ComponentModelDB(
                            idPage, model.getId(), model.getClassTkn().getLexem().toString()
                    );
                    if(!(component instanceof MenuComp)){ //porque puede tirar noCodeException
                        componentDB.updateClass(componentModelDB);
                    }
                    modifyComponentInFile(model, component);
                    if(component instanceof MenuComp){
                        componentDB.updateClass(componentModelDB);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                    semanticErrors.add("Ocurrio un error inesperado al consultar"
                            + "/actualizar en la base de datos");
                    throw new ModelException();
                } catch (IOException ex) {
                    System.out.println(ex);
                    semanticErrors.add("Ocurrio un error inesperado al intentar "
                            + "sobreescribir el html y modificar el componente.");
                    throw new ModelException();
                } catch (NoCodeException ex) {
                    semanticErrors.add("Los filtros para crear el menu no retornaron ninguna pagina,"
                            + " no se puede crear el menu.");
                    throw new ModelException();
                } catch (IncompleateCompException ex) {
                    Logger.getLogger(ModifyCompTraduc.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                semanticErrors.add("Faltan los atributos:" + component.getMissingAttributes());
                throw new ModelException();
            }
        } else {
            semanticErrors.add("El componente <" + model.getId() + "> no existe en la pagina");
            throw new ModelException();
        }
    }

    private void modifyComponentInFile(ModifyCompModel model, ComponentHtml component)
            throws SQLException, FileNotFoundException, IOException, NoCodeException, IncompleateCompException {
        StringBuilder contenido = new StringBuilder();
        String path = FilesUtil.SITES_PATH_SERVER + FilesUtil.getSeparator()
                + siteDB.getName(pageDB.getIdSite(model.getPage()))
                + FilesUtil.getSeparator()
                + model.getPage() + FilesUtil.HTML_EXTENSION;
        File archivo = new File(path); //creando el archivo
        FileReader lector = new FileReader(archivo); //lector del archivo
        BufferedReader buffer = new BufferedReader(lector); //para leer mas rapido el archivo
        String linea;
        boolean find = false;
        while ((linea = buffer.readLine()) != null) {
            if (linea.equals(HTMLgenerator.COMPONENT_INIT + model.getId() + HTMLgenerator.COMPONENT_END)) {
                contenido.append(linea).append("\n");
                if (component instanceof MenuComp) {
                    MenuComp menuComp = (MenuComp) component;
                    contenido.append(menuComp.getHtmlCode(pageDB, labelDB));
                } else {
                    contenido.append(component.getHtmlCode());
                }
                contenido.append("\n");
                find = true;
            } else if (find && linea.startsWith(HTMLgenerator.COMPONENT_INIT)) { //antes del otro componente
                find = false;
            }
            if (!find) {
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
            ModifyCompModel model = (ModifyCompModel) xmlmodel;
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
                    } else {
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

    private void recoveryId(ModifyCompModel model, Token nameParamTkn, Token valueParamTkn) {
        if (model.getId() != null) {
            super.addRepetedParamError(nameParamTkn);
        } else {
            model.setId(valueParamTkn.getLexem().toString());
        }
    }

    private void recoveryPageId(ModifyCompModel model, Token nameParamTkn, Token valueParamTkn) {
        if (model.getPage() != null) {
            super.addRepetedParamError(nameParamTkn);
        } else {
            model.setPage(valueParamTkn.getLexem().toString());
            if (!pageDB.exist(model.getPage())) {
                super.addNoFoundError(valueParamTkn, " no se puede agregar el componente a una pagina invalida");
            }
        }
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        super.semanticErrors.clear();
        this.warnings.clear();
        ModifyCompModel model = (ModifyCompModel) getModel(tokens, index);
        if (!semanticErrors.isEmpty()) {
            throw new ModelException();
        } else if (!model.isCompleate()) {
            semanticErrors.add(model.getMissingParams());
            throw new ModelException();
        }
        internalTranslate(model);
        return "Componente <" + model.getId() + "> editado exitosamente.";
    }

    public List<String> getWarnings() {
        return this.warnings;
    }

}
