
package compi1.web_designer_api.htmltraductor.models;

/**
 *
 * @author yennifer
 */
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
