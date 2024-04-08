
package compi1.web_designer_api.htmltraductor.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class NewPageModel extends XMLmodel implements Autocompletable{

    private String userCreateId, userModifyId;
    private LocalDate dateCreated, dateModify;
    
    private String title, site, father;
    
    private List<String> labels;

    public NewPageModel() {
        labels = new ArrayList<>();
    }
    
    @Override
    public boolean isCompleate() {
        return userCreateId != null
                && userModifyId != null
                && dateCreated != null
                && dateModify != null
                && title != null
                && site != null
                && father != null;
    }

    @Override
    public boolean hasEnoughParams() {
        return id != null
                && site != null
                && father != null;
    }

    @Override
    public void autocompleate() {
        title = title == null ? id : title;
        userModifyId = userModifyId == null ? UNKNOWN : userModifyId;
        userCreateId = userCreateId == null ? UNKNOWN : userCreateId;
        dateCreated = dateCreated == null ? LocalDate.now() : dateCreated;
        dateModify = dateModify == null ? LocalDate.now() : dateModify;
    }

    @Override
    public String getMissingParams() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
