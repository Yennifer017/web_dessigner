
package compi1.web_designer_api.htmltraductor.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class AddCompModel extends XMLmodel{

    private String page;
    private int classCode;
    private List<Attribute> attributes;
    
    public AddCompModel(){
        attributes = new ArrayList<>();
        classCode = -1;
    }
    
    @Override
    public boolean isCompleate() {
        return super.id != null
                && page != null
                && classCode > 0
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
