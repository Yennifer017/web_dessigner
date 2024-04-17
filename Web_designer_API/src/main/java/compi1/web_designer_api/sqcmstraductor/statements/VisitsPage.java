
package compi1.web_designer_api.sqcmstraductor.statements;

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
public class VisitsPage extends StatementSQcms{
    
    private PathValitator pathValitator;
    private List<String> namesPages;
    
    public VisitsPage(Connection connection){
        super.semanticErrors = new ArrayList<>();
        super.statisticsDB = new StatisticsDB(connection);
        pathValitator = new PathValitator(connection);
        this.namesPages = new ArrayList<>();
        
    }

    @Override
    public void recoveryInformation(List<Token> tokens, Index index) {
        Token currentTkn = tokens.get(index.get());
        while (currentTkn.getType() != sym.PUNTO_Y_COMA && index.get() < tokens.size()) {  
            namesPages.add(pathValitator.getNamePage(currentTkn.getLexem().toString(), semanticErrors));
            index.increment();
            currentTkn = tokens.get(index.get());
        }
    }
    

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        super.semanticErrors.clear();
        this.namesPages.clear();
        recoveryInformation(tokens, index);
        String mms = "";
        if(!semanticErrors.isEmpty()){
            throw new ModelException();
        }
        for (String name : namesPages) {
            try {
                mms += "Visitas a la pagina <" + name + ">\t" + statisticsDB.getVisitsPage(name) + "\n";
            } catch (SQLException ex) {
                throw new ModelException(ex.toString());
            } catch (NoDataException ex) {
                //controlado
            }
        }
        return mms;
    }
    
}
