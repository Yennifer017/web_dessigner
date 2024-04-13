
package compi1.web_designer_api.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author yennifer
 */
@Getter @Setter @NoArgsConstructor
public class Component {
    private int id, idPage, classId;
    private String name, className;
    
    public Component(int idPage, String name, String className){
        this.idPage = idPage;
        this.name = name;
        this.className = className;
    }
    
    public Component(int idPage, String name){
        this.idPage = idPage;
        this.name = name;
    }
}
