
package compi1.web_designer_api.htmltraductor.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yennifer
 */
@Getter @Setter
public class ModifyPageModel extends XMLmodel{

    private List<String> labels;
    private String title;
    
    public ModifyPageModel() {
        this.labels = new ArrayList<>();
    }
    

    @Override
    public boolean isCompleate() {
        return super.id != null 
                && (!labels.isEmpty() || title != null);
    }

    @Override
    public boolean hasEnoughParams() {
        return this.isCompleate();
    }

    @Override
    public String getMissingParams() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
