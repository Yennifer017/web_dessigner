package compi1.web_designer_api.sqcmstraductor.statements;

import compi1.web_designer_api.database.SiteDB;
import compi1.web_designer_api.database.StatisticsDB;
import compi1.web_designer_api.database.models.Page;
import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class PopularPages extends StatementSQcms {

    private String nameSite;
    private SiteDB siteDB;

    public PopularPages(Connection connection) {
        super.semanticErrors = new ArrayList<>();
        super.statisticsDB = new StatisticsDB(connection);
        siteDB = new SiteDB(connection);
    }

    @Override
    public void recoveryInformation(List<Token> tokens, Index index) {
        Token currentTkn = tokens.get(index.get());
        nameSite = currentTkn.getLexem().toString();
        if (!siteDB.exist(nameSite)) {
            super.addNoFoundError(currentTkn, "no se puede consultar el sitio");
        }
        index.increment(); //pasar al punto y coma
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        super.semanticErrors.clear();
        nameSite = null;
        recoveryInformation(tokens, index);
        if (!semanticErrors.isEmpty()) {
            throw new ModelException();
        }
        String mss = "Top 10 paginas populares del sitio <" + nameSite + ">\n";
        try {
            List<Page> pages = statisticsDB.getPopularPages(nameSite);
            if (pages.isEmpty()) {
                mss += "Sin informacion para mostrar";
            } else {
                for (int i = 0; i < pages.size(); i++) {
                    Page page = pages.get(i);
                    mss += (i + 1) + "-";
                    mss += "Pagina: <" + page.getName() + ">\t";
                    mss += "Visitas: " + page.getVisits() + "\n";
                }
            }
        } catch (SQLException ex) {
            throw new ModelException();
        }
        return mss;
    }

}
