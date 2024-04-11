package compi1.web_designer_api.database;

import compi1.web_designer_api.htmltraductor.models.CreatePageModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yennifer
 */
public class PageDB {

    private Connection connection;
    private SiteDB siteDB;

    public PageDB(Connection connection, SiteDB siteDB) {
        this.connection = connection;
        this.siteDB = siteDB;
    }

    public void insertIntoDB(CreatePageModel model) throws SQLException{
        String query;
        if (model.getFather() != null) {
            query = "INSERT INTO page (name, id_site) VALUES (?, ?);";
        } else {
            query = "INSERT INTO page (name, id_site, id_father) VALUES (?, ?, ?);";
        }
        PreparedStatement insert = connection.prepareStatement(query);
        insert.setString(1, model.getId());
        int idSite = siteDB.getId(model.getSite());
        insert.setInt(2, idSite);
        if(model.getFather() == null){
            int idIndex = this.getId(model.getSite());
            insert.setInt(3, idIndex);
        }
        insert.setInt(2, 0);
        insert.executeUpdate();
    }
    
    public void insertIntoDB(String name, String nameSite) throws SQLException{
        String query = "INSERT INTO page (name, id_site) VALUES (?, ?);";
        PreparedStatement insert = connection.prepareStatement(query);
        insert.setString(1, name);
        int idSite = siteDB.getId(nameSite);
        insert.setInt(2, idSite);
        insert.executeUpdate();
    }

    public boolean exist(String name) {
        return this.getId(name) != 0;
    }

    /**
     * elimina todas las paginas hijas y los componentes de una pagina
     * especificada por su nombre
     *
     * @param name
     * @return true si fue eliminado, de lo contrario false
     */
    public boolean onDelete(String name) {
        String query = "DELETE FROM page WHERE name = ?";
        try {
            PreparedStatement delete = connection.prepareStatement(query);
            delete.setString(1, name);
            int row = delete.executeUpdate();
            return row != 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public int getId(String name) {
        try {
            String query = "SELECT id FROM page WHERE name = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

}
