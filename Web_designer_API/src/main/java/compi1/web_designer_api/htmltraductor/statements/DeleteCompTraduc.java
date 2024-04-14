
package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.database.ComponentDB;
import compi1.web_designer_api.database.PageDB;
import compi1.web_designer_api.database.SiteDB;
import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.htmltraductor.models.DeleteCompModel;
import compi1.web_designer_api.htmltraductor.models.XMLmodel;
import compi1.web_designer_api.util.FilesUtil;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class DeleteCompTraduc extends StmTraductor{

    private SiteDB siteDB;
    private PageDB pageDB;
    private ComponentDB componentDB;
    
    public DeleteCompTraduc() {
        
    }

    @Override
    protected XMLmodel getModel(List<Token> tokens, Index index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void internalTranslate(XMLmodel model) throws ModelException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void deleteCompInFile(DeleteCompModel model){
        StringBuilder contenido = new StringBuilder();
        String path = FilesUtil.SITES_PATH_SERVER + FilesUtil.getSeparator() 
                + siteDB.getName(pageDB.getIdSite(model.getId())) + FilesUtil.getSeparator()
                + model.getId() + FilesUtil.HTML_EXTENSION;
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
                contenido.append(component.getHtmlCode())
                        .append(HTMLgenerator.END_COMPONENTS);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
