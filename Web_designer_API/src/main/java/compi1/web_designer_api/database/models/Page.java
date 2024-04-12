
package compi1.web_designer_api.database.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author yennifer
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Page {
    int id, idSite, idFather, visits;
    String name;
}
