
package compi1.web_designer_api.services;

import compi1.web_designer_api.database.DBManager;
import compi1.web_designer_api.database.PageDB;
import compi1.web_designer_api.database.SiteDB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author yennifer
 */
public class CountVisitService {
    
    private DBManager dbManager;
    private Connection connection;
    private PageDB pageDB;
    
    public CountVisitService(){
        connection = dbManager.getConnection();
        pageDB = new PageDB(connection, new SiteDB(connection));
    }
    
    public void countVisit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String name = request.getParameter("id");
            name = name.substring(0, name.length()-5);
            pageDB.updateVisit(name);
        } catch (Exception e) {
            System.out.println("No se pudo contabilizar la visita");
            System.out.println(e);
        }
    }
    
}
