package compi1.web_designer_api.database;

import compi1.web_designer_api.database.models.Page;
import compi1.web_designer_api.exceptions.NoCodeException;
import compi1.web_designer_api.htmltraductor.models.CreatePageModel;
import compi1.web_designer_api.util.FilesUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void insertIntoDB(CreatePageModel model) throws SQLException {
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
        if (model.getFather() == null) {
            int idIndex = this.getId(model.getSite());
            insert.setInt(3, idIndex);
        }
        insert.executeUpdate();
    }

    public void insertIntoDB(String name, String nameSite) throws SQLException {
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
     * elimina todas las paginas hijas (recursivamente) y los componentes de una
     * pagina especificada por su nombre
     *
     * @param name
     * @return true si fue eliminado, de lo contrario false
     */
    public boolean onDelete(String name) throws SQLException {
        String query = "DELETE FROM page WHERE name = ?";
        PreparedStatement delete = connection.prepareStatement(query);
        delete.setString(1, name);
        int row = delete.executeUpdate();
        return row != 0;
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
            System.out.println("No se pudo obtener un id de una pagina");
            System.out.println(ex);
        }
        return 0;
    }

    /**
     * Busca los hijos de una pagina especifica y retorna sus nombres
     *
     * @param name nombre de la pagina
     * @return una lista de los ids encontrados
     * @throws java.sql.SQLException
     */
    public List<Page> getChildren(String name) throws SQLException {
        List<Page> pages = new ArrayList<>();
        int idFather = this.getId(name);
        String query = "SELECT id, name FROM page WHERE id_father = ? ;";
        PreparedStatement select = connection.prepareStatement(query);
        select.setInt(1, idFather);
        ResultSet rs = select.executeQuery();
        while (rs.next()) {
            Page page = new Page();
            page.setId(rs.getInt(1));
            page.setName(rs.getString(2));
            pages.add(page);
        }
        return pages;
    }

    /**
     * Busca en toda la base de datos las paginas que contengan las etiquetas
     * especificadas
     *
     * @param labels, lista de etiquetas
     * @return una lista con las paginas encontradas incluyendo en su nombre
     * __nombreSito + separador+ nombrePagina__
     */
    public List<Page> getFiltredPages(String[] labels) throws NoCodeException, SQLException {
        List<Page> pages = new ArrayList<>();
        String query = "SELECT page.id, page.name, page.id_site, site.name FROM page_label "
                + "JOIN label ON label.id = page_label.id_label "
                + "JOIN page ON page.id = page_label.id_page "
                + "JOIN site ON page.id_site = site.id "
                + "WHERE (" + this.getQueryComp(labels.length) + ");";
        PreparedStatement ps = connection.prepareStatement(query);
        for (int i = 0; i < labels.length; i++) {
            ps.setString(i+1, labels[i]);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Page page = new Page();
            page.setId(rs.getInt(1));
            page.setName(rs.getString(4) + FilesUtil.getSeparator() + rs.getString(2));
            page.setIdSite(rs.getInt(3));
            pages.add(page);
        }
        return pages;
    }

    /**
     * Busca las paginas que contengan las etiquetas especificadas y que sean
     * hijas de un padre especifico
     *
     * @param labels, lista de etiquetas
     * @param nameFather, nombre de la pagina padre
     * @return una lista con las paginas encontradas
     * @throws compi1.web_designer_api.exceptions.NoCodeException
     * @throws java.sql.SQLException
     */
    public List<Page> getFiltredPages(String[] labels, String nameFather) 
            throws NoCodeException, SQLException {
        List<Page> pages = new ArrayList<>();
        int idFather = this.getId(nameFather);
        String query = "SELECT page.id, page.name, page.id_site FROM page_label "
                + "JOIN label ON label.id = page_label.id_label "
                + "JOIN page ON page.id = page_label.id_page "
                + "WHERE (" + getQueryComp(labels.length) + ") "
                + "AND page.id_father = ? ;";
        PreparedStatement ps = connection.prepareStatement(query);
        for (int i = 0; i < labels.length; i++) {
            ps.setString(i+1, labels[i]);
        }
        ps.setInt(labels.length+1, idFather);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Page page = new Page();
            page.setId(rs.getInt(1));
            page.setName(rs.getString(2));
            page.setIdSite(rs.getInt(3));
            pages.add(page);
        }
        return pages;
    }

    private String getQueryComp(int totalLabels) throws NoCodeException {
        if (totalLabels == 0) {
            throw new NoCodeException();
        }
        String code = "";
        for (int i = 0; i < totalLabels; i++) {
            code += "label.name = ? ";
            if (i != totalLabels - 1) { //si no es el ultimo
                code += "OR ";
            }
        }
        return code;
    }

    public int getIdSite(String namePage) {
        try {
            String query = "SELECT id_site FROM page WHERE name = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, namePage);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PageDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateVisit(String namePage) throws SQLException {
        String query = "UPDATE page SET visits=visits+1 WHERE name = ? ;";
        PreparedStatement update = connection.prepareStatement(query);
        update.setString(1, namePage);
        update.executeUpdate();
    }

}
