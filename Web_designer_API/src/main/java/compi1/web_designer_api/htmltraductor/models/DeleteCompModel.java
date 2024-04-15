
package compi1.web_designer_api.htmltraductor.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yennifer
 */
@Getter @Setter
public class DeleteCompModel extends XMLmodel{

    private String page;
    @Override
    public boolean isCompleate() {
        return super.id != null
                && page != null;
    }

    @Override
    public boolean hasEnoughParams() {
        return isCompleate();
    }

    @Override
    public String getMissingParams() {
        String mss = id == null ? super.getMissingParamMss("id") : "";
        mss += page == null ? super.getMissingParamMss("id de la pagina") : "";
        return mss;
    }
    
}
