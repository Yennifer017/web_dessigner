
package compi1.web_designer_api.htmltraductor.models;

/**
 *
 * @author yennifer
 */
public abstract class XMLmodel {
    protected static final String UNKNOWN = "Desconocido";
    protected static final String ID_PARAM = "ID", SITE_PARAM = "SITIO", FATHER_PARAM = "PADRE";
    
    
    protected String id;
    public abstract boolean isCompleate();
    public abstract boolean hasEnoughParams();
    public abstract String getMissingParams();
    
    protected String getMissingParamMss(String nameParam){
        return "Debe especificarse el parametro <" + nameParam + ">";
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
}
