
package compi1.web_designer_api.sqcmstraductor.statements;

import compi1.web_designer_api.database.SiteDB;
import compi1.web_designer_api.database.StatisticsDB;
import compi1.web_designer_api.exceptions.ModelException;
import compi1.web_designer_api.exceptions.NoDataException;
import compi1.web_designer_api.sqcmstraductor.sym;
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
public class VisitsSite extends StatementSQcms{
    
    private List<String> idsSite;
    private SiteDB siteDB;
    
    public VisitsSite(Connection connection){
        super.statisticsDB = new StatisticsDB(connection);
        super.semanticErrors = new ArrayList<>();
        siteDB = new SiteDB(connection);
    }

    @Override
    public void recoveryInformation(List<Token> tokens, Index index) {
        Token currentTkn = tokens.get(index.get());
        while (currentTkn.getType() != sym.PUNTO_Y_COMA) {            
            if(siteDB.exist(currentTkn.getLexem().toString())){
                idsSite.add(currentTkn.getLexem().toString());
            } else {
                super.addNoFoundError(currentTkn, "No se puede consultar el sitio");
            }
            currentTkn = tokens.get(index.get());
        }
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        String mss = "";
        if(!semanticErrors.isEmpty()){
            throw new ModelException();
        }
        for (String id : idsSite) {
            try {
                mss += "Visitas al sitio <" + id + ">\t" + statisticsDB.getVisitsSite(id) + "\n";
            } catch (SQLException ex) {
                throw new ModelException(ex.toString());
            } catch (NoDataException ex) {
                //controlado
            }
        }
        return mss;
    }

    
}
