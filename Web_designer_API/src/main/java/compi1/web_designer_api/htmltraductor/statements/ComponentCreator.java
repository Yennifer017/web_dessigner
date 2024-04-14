
package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.exceptions.InvalidAttributeException;
import compi1.web_designer_api.htmltraductor.models.Attribute;
import compi1.web_designer_api.htmltraductor.models.comp.ComponentHtml;
import compi1.web_designer_api.htmltraductor.models.comp.DisplayHtmlComp;
import compi1.web_designer_api.htmltraductor.models.comp.MediaHtml;
import compi1.web_designer_api.htmltraductor.models.comp.MenuComp;
import compi1.web_designer_api.htmltraductor.sym;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class ComponentCreator {
    
    public ComponentHtml createModel(int type, List<Attribute> attributes, 
            List<String> warnings, String id){
        ComponentHtml component = this.getEmptyModel(type);
        if(!attributes.isEmpty()){
            for (Attribute attribute : attributes) {
                if(component.canSet(attribute.getTypeAttrTkn().getType())){
                    try {
                        component.set(attribute);
                    } catch (InvalidAttributeException ex) {
                        //controlado
                    }
                } else {
                    warnings.add("attributo inutilizado");
                }
            }
        }
        component.setId(id);
        return component;
    }
    
    private ComponentHtml getEmptyModel(int type){
        switch (type) {
            case sym.TITULO:
                return new DisplayHtmlComp(DisplayHtmlComp.TITLE_HTML_LABEL);
            case sym.PARRAFO:
                return new DisplayHtmlComp(DisplayHtmlComp.PARAGRAPH_HTML_LABEL);
            case sym.IMAGEN:
                return new MediaHtml(MediaHtml.IMAGE_HTML_LABEL, true);
            case sym.VIDEO:
                return new MediaHtml(MediaHtml.VIDEO_HTML_LABEL, false);
            case sym.MENU:
                return new MenuComp();
            default:
                throw new AssertionError();
        }
    }
}
