package compi1.web_designer_api.sqcmstraductor.statements;

import compi1.web_designer_api.database.PageDB;
import compi1.web_designer_api.database.SiteDB;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class PathValitator {
    
    private PageDB pageDB;

    protected PathValitator(Connection connection) {
        pageDB = new PageDB(connection, new SiteDB(connection));
    }

    public String getNamePage(String path, List<String> semanticErrors) {
        String[] folders = path.split("\\.");
        String name = "";
        switch (folders.length) {
            case 1:
                if (pageDB.exist(folders[0])) {
                    name = folders[0];
                } else {
                    addError(semanticErrors, path);
                }
                break;
            case 2:
                name = verifyName(folders[1], folders[0], semanticErrors, path);
                break;
            case 3:
                if (!folders[0].equalsIgnoreCase("sites")) {
                    addError(semanticErrors, path);
                } else {
                    name = verifyName(folders[2], folders[1], semanticErrors, path);
                }
                break;
            default:
                addError(semanticErrors, path);
        }
        return name;
    }

    private String verifyName(String namePage, String nameSite, List<String> semanticErrors, String path) {
        if (pageDB.corresponds(namePage, nameSite)) {
            return namePage;
        } else {
            addError(semanticErrors, path);
        }
        return "";
    }
    private void addError(List<String> semanticErrors, String path){
        semanticErrors.add("El path especificado <" + path + "> no existe o esta mal formado");
    }
}
