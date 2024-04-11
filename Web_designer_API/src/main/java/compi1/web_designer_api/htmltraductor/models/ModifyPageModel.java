
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
        String mss = super.id == null ? super.getMissingParamMss("Id") : "";
        if(labels.isEmpty() && title == null){
            mss += "\nSe esperaba la especificacion del nuevo titulo o una lista de etiquetas";
        }
        return mss;
    }
    
}
