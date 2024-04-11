package compi1.web_designer_api.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author yennifer
 */
public class SiteDB {

    private Connection connection;

    public SiteDB(Connection connection) {
        this.connection = connection;
    }

    public void insertIntoDB(String name) {
        String query = "INSERT INTO site (name) VALUES (?);";
        try {
            PreparedStatement insert = connection.prepareStatement(query);
            insert.setString(1, name);
            insert.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void onDelete(String name) {
        String query = "DELETE FROM site WHERE id=?;";
    }

    public int getId(String name) {
        String query = "SELECT id FROM site WHERE name = ? ;";
        return 0;
    }

}
