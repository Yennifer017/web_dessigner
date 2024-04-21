
package compi1.web_designer_api.htmltraductor.models.comp;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yennifer
 */
@Getter @Setter @AllArgsConstructor
public class RecordHtmlModel {
    private String userModify, userCreated;
    private LocalDate dateCreated, dateModify;
}
