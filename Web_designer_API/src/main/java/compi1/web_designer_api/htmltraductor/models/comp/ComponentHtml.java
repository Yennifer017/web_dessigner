
package compi1.web_designer_api.htmltraductor.models.comp;

import compi1.web_designer_api.exceptions.InvalidAttributeException;
import compi1.web_designer_api.htmltraductor.HTMLgenerator;
import compi1.web_designer_api.htmltraductor.models.Attribute;

/**
 *
 * @author yennifer
 */
public abstract class ComponentHtml {
    protected HTMLgenerator htmlGen;
    protected Integer[] permitedAttrs;
    
    protected abstract void initPermitedAttrs();
    
    public abstract boolean canCreate();
    public abstract void set(Attribute attribute) throws InvalidAttributeException;
    public abstract String getHtmlCode();
    public abstract String getMissingAttributes();
    
    public boolean canSet(int typeAttr){
        for (int i = 0; i < permitedAttrs.length; i++) {
            if(typeAttr == permitedAttrs[i]){
                return true;
            }
        }
        return false;
    }
    
}
