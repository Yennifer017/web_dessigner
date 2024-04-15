
package compi1.web_designer_api.htmltraductor.models;

import compi1.web_designer_api.util.Token;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yennifer
 */
@Getter @Setter
public class AddCompModel extends XMLmodel{

    private String page;
    private Token classTkn;
    private List<Attribute> attributes;
    
    public AddCompModel(){
        attributes = new ArrayList<>();
    }
    
    @Override
    public boolean isCompleate() {
        return super.id != null
                && page != null
                && classTkn != null
                && !attributes.isEmpty();
    }

    @Override
    public boolean hasEnoughParams() {
        return isCompleate();
    }

    @Override
    public String getMissingParams() {
        String mss = id == null ? super.getMissingParamMss("id") : "";
        mss += page ==  null ? super.getMissingParamMss("id de la pagina") : "";
        mss += classTkn == null ? super.getMissingParamMss("clase de componente") : "";
        mss += attributes.isEmpty() ? super.getMissingParamMss("atributos") : "";
        return mss;
    }
    
}
