
package compi1.web_designer_api.htmltraductor.models;

import compi1.web_designer_api.htmltraductor.models.comp.RecordHtmlModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yennifer
 */
@Getter @Setter
public class CreatePageModel extends XMLmodel implements Autocompletable{

    private String userCreateId, userModifyId;
    private LocalDate dateCreated, dateModify;
    
    private String title, site, father;
    
    private List<String> labels;

    public CreatePageModel() {
        labels = new ArrayList<>();
    }
    
    @Override
    public boolean isCompleate() {
        return userCreateId != null
                && userModifyId != null
                && dateCreated != null
                && dateModify != null
                && title != null
                && site != null;
    }

    @Override
    public boolean hasEnoughParams() {
        return id != null
                && site != null;
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
        String mss = id == null ? super.getMissingParamMss("Id") : "";
        mss += site == null ? super.getMissingParamMss("id sitio") : "";
        mss += father == null ? super.getMissingParamMss("id pag padre") : "";
        return mss;
    }

    @Override
    public RecordHtmlModel getRecord() {
        return new RecordHtmlModel(userModifyId, userCreateId, dateCreated, dateModify);
    }
    
}
