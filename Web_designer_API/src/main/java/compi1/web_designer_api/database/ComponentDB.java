
package compi1.web_designer_api.database;

import compi1.web_designer_api.database.models.ComponentModelDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yennifer
 */
public class ComponentDB {
    private Connection connection;
    public ComponentDB(Connection connection){
        this.connection = connection;
    }
    
    public void insertIntoDB(ComponentModelDB component) throws SQLException{
        String query = "INSERT INTO component (name, id_page, class) VALUES (?, ?, ?);";
        PreparedStatement insert = connection.prepareStatement(query);
        insert.setString(1, component.getName());
        insert.setInt(2, component.getIdPage());
        insert.setString(3, component.getClassName());
        insert.executeUpdate();
    }
    
    public boolean exist(int idPage, String nameComp){
        ComponentModelDB component = new ComponentModelDB(idPage, nameComp);
        return getId(component) != 0;
    }
    
    public boolean exist(ComponentModelDB component){
        return getId(component) != 0;
    }
    
    public ComponentModelDB get(int id){
        String query = "SELECT * FROM component WHERE id = ?";
        
        ComponentModelDB component = new ComponentModelDB();
        return null;
    }
    
    public void deleteComponent(int id) throws SQLException{
        String query = "DELETE FROM component WHERE id = ?";
        PreparedStatement delete = connection.prepareStatement(query);
        delete.setInt(1, id);
        delete.executeUpdate();
    }
    
    public void deleteComponent(String name, int idPage) throws SQLException{
        String query = "DELETE FROM component WHERE name = ? AND id_page = ?";
        PreparedStatement delete = connection.prepareStatement(query);
        delete.setString(1, name);
        delete.setInt(2, idPage);
        delete.executeUpdate();
    }
    
    public void updateClass(ComponentModelDB component) throws SQLException{
        String query = "UPDATE component SET class = ? WHERE (name = ? AND id_page = ?);";
        PreparedStatement update = connection.prepareStatement(query);
        update.setString(1, component.getClassName());
        update.setString(2, component.getName());
        update.setInt(3, component.getIdPage());
        update.executeUpdate();
    }
    
    public int getId(ComponentModelDB component){
        String query = "SELECT id FROM component WHERE name = ? AND id_page = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, component.getName());
            ps.setInt(2, component.getIdPage());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    
}
