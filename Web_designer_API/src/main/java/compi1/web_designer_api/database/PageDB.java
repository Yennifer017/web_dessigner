package compi1.web_designer_api.database;

import java.sql.Connection;

/**
 *
 * @author yennifer
 */
public class PageDB {

    Connection connection;

    public PageDB(Connection connection) {
        this.connection = connection;
    }

    public void insertIntoDB() {
        String query;
        String father = null;
        if (father != null) {
            query = "INSERT INTO page (name, id_site) VALUES (?, ?);";
        } else {
            query = "INSERT INTO page (name, id_site, id_father) VALUES (?, ?, ?);";
        }

    }

    public boolean exist(String name) {
        String query = "SELECT id FROM page WHERE name = ? ;";
        return false;
    }

    /**
     * elimina todas las paginas hijas y los componentes de una pagina
     * especificada por su nombre
     *
     * @param name
     */
    public void onDelete(String name) {
        
    }

    public int getId(String name) {
        return 0;
    }

}
