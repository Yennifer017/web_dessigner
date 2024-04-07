
package compi1.web_designer_api.servlets.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author yennifer
 */
@Getter @Setter
public class Token {
    private int line, column, type;
    private Object lexem;
    
    public Token(Object lexem, int line, int column, int type) {
        this.lexem = lexem;
        this.line = line;
        this.column = column;
        this.type = type;
    }

    public Token(int line, int column, int type) {
        this.line = line;
        this.column = column;
        this.type = type;
    }
    
}
