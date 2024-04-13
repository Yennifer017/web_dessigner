
package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.exceptions.InvalidAttributeException;
import compi1.web_designer_api.htmltraductor.models.Attribute;
import compi1.web_designer_api.htmltraductor.models.comp.ComponentHtml;
import compi1.web_designer_api.htmltraductor.models.comp.ImageComp;
import compi1.web_designer_api.htmltraductor.models.comp.MenuComp;
import compi1.web_designer_api.htmltraductor.models.comp.ParagraphComp;
import compi1.web_designer_api.htmltraductor.models.comp.TitleComp;
import compi1.web_designer_api.htmltraductor.models.comp.VideoComp;
import compi1.web_designer_api.htmltraductor.sym;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class ComponentCreator {
    
    public ComponentHtml createModel(int type, List<Attribute> attributes, List<String> warnings){
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
        return component;
    }
    
    private ComponentHtml getEmptyModel(int type){
        switch (type) {
            case sym.TITULO:
                return new TitleComp();
            case sym.PARRAFO:
                return new ParagraphComp();
            case sym.IMAGEN:
                return new ImageComp();
            case sym.VIDEO:
                return new VideoComp();
            case sym.MENU:
                return new MenuComp();
            default:
                throw new AssertionError();
        }
    }
}
