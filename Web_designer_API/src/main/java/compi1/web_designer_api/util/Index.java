
package compi1.web_designer_api.util;

import lombok.Setter;

/**
 *
 * @author yennifer
 */
@Setter
public class Index {
    int index;
    
    public Index(){
        this.index = 0;
    }
    
    public void increment(){
        index++;
    }
    
    public void increment(int number){
        index += number;
    }
    
    public int get(){
        return index;
    }
}
