
package compi1.web_designer_api.htmltraductor.models;

import static compi1.web_designer_api.htmltraductor.models.XMLmodel.ID_PARAM;

/**
 *
 * @author yennifer
 */
public class DeletePageModel extends XMLmodel{
    
    @Override
    public boolean isCompleate() {
        return super.id != null;
    }

    @Override
    public boolean hasEnoughParams() {
        return super.id != null;
    }

    @Override
    public String getMissingParams() {
        return super.getMissingParamMss(ID_PARAM);
    }
    
}
