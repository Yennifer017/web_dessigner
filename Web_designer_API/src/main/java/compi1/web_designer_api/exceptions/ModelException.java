
package compi1.web_designer_api.exceptions;

/**
 *
 * @author yennifer
 */
public class ModelException extends Exception{
    
    private String mss;
    
    public ModelException(){
    }
    
    public ModelException(String mms){
        this.mss = mms;
    }
    
    public String getMss(){
        return this.mss;
    }
}
