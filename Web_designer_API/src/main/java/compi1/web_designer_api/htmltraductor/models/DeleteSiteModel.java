
package compi1.web_designer_api.htmltraductor.models;

/**
 *
 * @author yennifer
 */
public class DeleteSiteModel extends XMLmodel {

    @Override
    public boolean isCompleate() {
        return super.id != null;
    }

    @Override
    public boolean hasEnoughParams() {
        return isCompleate();
    }

    @Override
    public String getMissingParams() {
        return id == null ? super.getMissingParamMss(ID_PARAM) : "";
    }
    
}
