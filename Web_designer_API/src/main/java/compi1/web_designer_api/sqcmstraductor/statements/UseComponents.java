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
public class UseComponents extends StatementSQcms {

    private Token typeComponent;
    private String namePage;
    private PathValitator pathValitator;

    public UseComponents(Connection connection) {
        super.semanticErrors = new ArrayList<>();
        super.statisticsDB = new StatisticsDB(connection);
        pathValitator = new PathValitator(connection);
    }

    @Override
    public void recoveryInformation(List<Token> tokens, Index index) {
        typeComponent = tokens.get(index.get());
        index.increment();
        String superficialPath = tokens.get(index.get()).getLexem().toString();
        namePage = pathValitator.getNamePage(superficialPath, semanticErrors);
        index.increment();
    }

    @Override
    public String translate(List<Token> tokens, Index index) throws ModelException {
        super.semanticErrors.clear();
        namePage = null;
        recoveryInformation(tokens, index);
        if (!semanticErrors.isEmpty()) {
            throw new ModelException();
        }
        String mss = "";
        try {
            switch (typeComponent.getType()) {
                case sym.TODOS:
                    mss = "Total de componentes en <" + namePage + ">:" + statisticsDB.getTotalComponents(namePage);
                    break;
                default:
                    mss = "Total de componentes en <" + namePage + ">:" + statisticsDB.getTotalComponents(namePage);
            }
        } catch (SQLException e) {
            throw new ModelException();
        } catch (NoDataException e){
        }
        return mss;
    }

}
