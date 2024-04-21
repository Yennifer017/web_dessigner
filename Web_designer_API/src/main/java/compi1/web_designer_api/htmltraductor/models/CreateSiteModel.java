
package compi1.web_designer_api.htmltraductor.models;

import compi1.web_designer_api.htmltraductor.models.comp.RecordHtmlModel;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author yennifer
 */
@Getter @Setter @NoArgsConstructor
public class CreateSiteModel extends XMLmodel implements Autocompletable{

    private String userCreateId, userModifyId;
    private LocalDate dateCreated, dateModify;
    
    @Override
    public boolean isCompleate() {
        return super.id != null
                && userModifyId != null
                && userModifyId != null
                && dateCreated != null
                && dateModify != null;
    }

    @Override
    public void autocompleate() {
        userModifyId = userModifyId == null ? UNKNOWN : userModifyId;
        userCreateId = userCreateId == null ? UNKNOWN : userCreateId;
        dateCreated = dateCreated == null ? LocalDate.now() : dateCreated;
        dateModify = dateModify == null ? LocalDate.now() : dateModify;
    }

    @Override
    public String getMissingParams() {
        return id == null ? super.getMissingParamMss(ID_PARAM) : "";
    }

    @Override
    public boolean hasEnoughParams() {
        return super.id != null;
    }

    @Override
    public RecordHtmlModel getRecord() {
        return new RecordHtmlModel(userModifyId, userCreateId, dateCreated, dateModify);
    }
    
}
