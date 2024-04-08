
package compi1.web_designer_api.htmltraductor.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class ModifyCompModel extends XMLmodel{
    
    private String page;
    private int clase;
    private List<Attribute> attributes;

    public ModifyCompModel() {
        clase = -1;
        attributes = new ArrayList<>();
    }
    
    @Override
    public boolean isCompleate() {
        return super.id != null
                && page != null
                && clase > 0
                && !attributes.isEmpty();
    }

    @Override
    public boolean hasEnoughParams() {
        return isCompleate();
    }

    @Override
    public String getMissingParams() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
