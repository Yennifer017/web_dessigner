
package compi1.web_designer_api.htmltraductor.statements;

import compi1.web_designer_api.htmltraductor.models.Attribute;
import compi1.web_designer_api.htmltraductor.sym;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class AttributeRecolecor {
    
    protected AttributeRecolecor(){
    }
    
    public List<Attribute> recoveryAttributes(List<Token> tokens, Index index)
        throws IndexOutOfBoundsException {
        Token currentTkn = tokens.get(index.get());
        List<Attribute> attributes = new ArrayList<>();
        while (currentTkn.getType() != sym.ATRIBUTOS ) {            
            Token typeTkn = tokens.get(index.get());
            index.increment();
            Token valueTkn = tokens.get(index.get());
            index.increment();
            attributes.add(new Attribute(typeTkn, valueTkn));
            currentTkn = tokens.get(index.get());
        }
        return attributes;
    }
    
}
