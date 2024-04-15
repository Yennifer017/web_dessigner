
package compi1.web_designer_api.htmltraductor.models.comp;

import compi1.web_designer_api.database.LabelDB;
import compi1.web_designer_api.database.PageDB;
import compi1.web_designer_api.database.models.Page;
import compi1.web_designer_api.exceptions.InvalidAttributeException;
import compi1.web_designer_api.exceptions.NoCodeException;
import compi1.web_designer_api.htmltraductor.HTMLgenerator;
import compi1.web_designer_api.htmltraductor.models.Attribute;
import compi1.web_designer_api.htmltraductor.sym;
import compi1.web_designer_api.util.FilesUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class MenuComp extends ComponentHtml{
    
    private String father, labels;

    public MenuComp() {
        super.htmlGen = new HTMLgenerator();
        initPermitedAttrs();
    }
    
    @Override
    protected final void initPermitedAttrs() {
        super.permitedAttrs = new Integer[2];
        permitedAttrs[0] = sym.PADRE;
        permitedAttrs[1] = sym.ETIQUETAS;
    }

    @Override
    public boolean canCreate() {
        return father != null || labels != null;
    }

    @Override
    public void set(Attribute attribute) throws InvalidAttributeException {
        switch (attribute.getContentTkn().getType()) {
            case sym.PADRE:
                this.father = attribute.getContentTkn().getLexem().toString();
                break;
            case sym.ETIQUETAS:
                this.labels = attribute.getContentTkn().getLexem().toString().toLowerCase();
                break;
            default:
                throw new InvalidAttributeException();
        }
    }

    @Override
    public String getHtmlCode() {
        throw new AssertionError("Plese use the special method");
    }
    
    public String getHtmlCode(PageDB pageDB, LabelDB labelDB) throws NoCodeException, SQLException{
        List<Page> pages = new ArrayList<>();
        String path = "";
        if(father != null && labels != null){
            path = FilesUtil.WEB_SERVER_SITES + father + FilesUtil.getSeparator();
            pages = pageDB.getFiltredPages(labels.split("\\|"), father);
        }else if(father != null){
            path = FilesUtil.WEB_SERVER_SITES + father + FilesUtil.getSeparator();
            pages = pageDB.getChildren(super.id);
        }else if(labels != null){
            path = FilesUtil.WEB_SERVER_SITES;
            pages = pageDB.getFiltredPages(labels.split("\\|"));
        }
        return super.htmlGen.getCodeForMenu(path, pages, super.id);
    }

    @Override
    public String getMissingAttributes() {
        if(father == null && labels == null){
            return "Se necesita especificar un id de una pagina padre o una(s) etiqueta(s)";
        }else{
            return "";
        }
    }
    
}
