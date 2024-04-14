
package compi1.web_designer_api.htmltraductor.models.comp;

import compi1.web_designer_api.exceptions.IncompleateCompException;
import compi1.web_designer_api.exceptions.InvalidAttributeException;
import compi1.web_designer_api.htmltraductor.HTMLgenerator;
import compi1.web_designer_api.htmltraductor.models.Attribute;
import compi1.web_designer_api.htmltraductor.sym;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yennifer
 */
@Getter @Setter
public class DisplayHtmlComp extends ComponentHtml{
    
    public static String TITLE_HTML_LABEL = "h1", PARAGRAPH_HTML_LABEL = "p";

    private String text;
    private String align;
    private String color;
    private String label;

    public DisplayHtmlComp(String htmlLabel) {
        super.htmlGen = new HTMLgenerator();
        this.label = htmlLabel;
        initPermitedAttrs();
    }

    @Override
    protected final void initPermitedAttrs() {
        super.permitedAttrs = new Integer[3];
        permitedAttrs[0] = sym.TEXTO;
        permitedAttrs[1] = sym.ALINEACION;
        permitedAttrs[2] = sym.COLOR;
    }

    @Override
    public boolean canCreate() {
        return text != null;
    }

    @Override
    public void set(Attribute attribute) throws InvalidAttributeException {
        switch (attribute.getContentTkn().getType()) {
            case sym.TEXTO:
                this.text = attribute.getContentTkn().getLexem().toString();
                break;
            case sym.ALINEACION:
                this.align = attribute.getContentTkn().getLexem().toString().toLowerCase();
                break;
            case sym.COLOR:
                this.color = attribute.getContentTkn().getLexem().toString();
                break;
            default:
                throw new InvalidAttributeException();
        }
    }

    @Override
    public String getHtmlCode() throws IncompleateCompException{
        if(canCreate()){
            return htmlGen.getCodeForDisplay(this);
        }else{
            throw new IncompleateCompException();
        }
    }

    @Override
    public String getMissingAttributes() {
        return text == null ? "Texto" : "";
    }
}
