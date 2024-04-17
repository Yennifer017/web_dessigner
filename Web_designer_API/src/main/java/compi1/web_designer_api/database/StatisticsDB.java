package compi1.web_designer_api.database;

import compi1.web_designer_api.database.models.Page;
import compi1.web_designer_api.exceptions.NoDataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class StatisticsDB {

    private Connection connection;

    public StatisticsDB(Connection connection) {
        this.connection = connection;
    }

    public int getVisitsPage(String name) throws SQLException, NoDataException {
        String query = "SELECT visits FROM page WHERE name = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else { 
            throw new NoDataException();
        }
    }

    public int getVisitsSite(String name) throws SQLException, NoDataException {
        String query = "SELECT SUM(page.visits) FROM site "
                + "JOIN page ON site.id = page.id_site "
                + "WHERE site.name = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else { 
            throw new NoDataException();
        }
    }

    public List<Page> getPopularPages(String nameSite) throws SQLException {
        List<Page> pages = new ArrayList<>();
        String query = "SELECT page.name, page.visits FROM page "
                + "JOIN site ON page.id_site = site.id "
                + "WHERE site.name = ? "
                + "ORDER BY page.visits DESC LIMIT 10 ;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nameSite);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Page page = new Page();
            page.setName(rs.getString(1));
            page.setVisits(rs.getInt(2));
            pages.add(page);
        }
        return pages;
    }

    public int getTotalComponents(String namePage) 
            throws SQLException, NoDataException {
        String query = "SELECT COUNT(*) FROM component "
                + "JOIN page ON page.id = component.id_page "
                + "WHERE page.name = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, namePage);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else { 
            throw new NoDataException();
        }
    }

    public int getComponents(String namePage, String typeComponent) 
            throws SQLException, NoDataException {
        String query = "SELECT COUNT(*) FROM component "
                + "JOIN page ON page.id = component.id_page "
                + "WHERE page.name = ? AND component.class = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, namePage);
        ps.setString(2, typeComponent);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else { 
            throw new NoDataException();
        }
    }

}
