package compi1.web_designer_api.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public boolean insertIntoDB(String name) {
        String query = "INSERT INTO site (name) VALUES (?);";
        try {
            PreparedStatement insert = connection.prepareStatement(query);
            insert.setString(1, name);
            insert.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean onDelete(String name) {
        String query = "DELETE FROM site WHERE name = ?;";
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
        String query = "SELECT id FROM site WHERE name = ? ;";
        try {
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

    public boolean exist(String name) {
        return this.getId(name) != 0;
    }

    public String getName(int id) throws SQLException {
        String query = "SELECT name FROM site WHERE id = ? ;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        } else {
            throw new SQLException("No se encontro el id");
        }
    }

}
