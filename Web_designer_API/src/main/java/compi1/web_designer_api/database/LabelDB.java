
package compi1.web_designer_api.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yennifer
 */
public class LabelDB {
    
    private Connection connection;
    public LabelDB(Connection connection){
        this.connection = connection;
    }
    
    public int getId(String name){
        try {
            String query = "SELECT id FROM label WHERE name = ?";
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
    
    public void insertIntoDB(String name){
        try {
            String query = "INSERT INTO label (name) VALUES (?)";
            PreparedStatement insert = connection.prepareStatement(query);
            insert.setString(1, name);
            insert.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public boolean exist(String name){
        return getId(name) != 0;
    }
    
    /**
     * Relaciona una etiqueta a una pagina web
     * @param nameLabel
     * @param idPage
     */
    public void addLabel(String nameLabel, int idPage) throws SQLException{
        int idLabel = this.getId(nameLabel);
        String query = "INSERT INTO page_label (id_label, id_page) VALUES (?, ?);";
        PreparedStatement insert = connection.prepareStatement(query);
        insert.setInt(1, idLabel);
        insert.setInt(2, idPage);
        insert.executeUpdate();
    }
}
