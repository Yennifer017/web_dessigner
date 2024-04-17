package compi1.web_designer_api.sqcmstraductor.statements;

import compi1.web_designer_api.database.StatisticsDB;
import compi1.web_designer_api.util.Index;
import compi1.web_designer_api.util.Token;
import compi1.web_designer_api.util.Transladable;
import java.util.List;

/**
 *
 * @author yennifer
 */
public abstract class StatementSQcms implements Transladable {

    protected StatisticsDB statisticsDB;
    protected List<String> semanticErrors;

    @Override
    public List<String> getSemanticErrors() {
        return this.semanticErrors;
    }

    @Override
    public void addFailMss() {
        this.semanticErrors.add("Error al realizar una consulta");
    }

    protected void addNoFoundError(Token param, String mss) {
        this.semanticErrors.add("El id <" + param.getLexem().toString() + "> no existe,"
                + mss + " Linea: " + param.getLine() + " - columna: " + param.getColumn());
    }

    public abstract void recoveryInformation(List<Token> tokens, Index index);
}
