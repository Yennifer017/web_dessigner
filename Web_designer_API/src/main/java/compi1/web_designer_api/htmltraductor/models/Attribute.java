
package compi1.web_designer_api.htmltraductor.models;

import compi1.web_designer_api.util.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author yennifer
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class Attribute {
    private int codeName;
    private Token contentTkn;
}
