
package compi1.web_designer_api.util;

import compi1.web_designer_api.exceptions.ModelException;
import java.util.List;

/**
 *
 * @author yennifer
 */
public interface Transladable {
    public abstract String translate(List<Token> tokens, Index index) throws ModelException;
    public abstract void addFailMss();
    public abstract List<String> getSemanticErrors();
}
