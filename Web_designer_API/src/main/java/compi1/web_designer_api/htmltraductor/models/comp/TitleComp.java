package compi1.web_designer_api.htmltraductor.models.comp;

import compi1.web_designer_api.exceptions.InvalidAttributeException;
import compi1.web_designer_api.htmltraductor.HTMLgenerator;
import compi1.web_designer_api.htmltraductor.models.Attribute;
import compi1.web_designer_api.htmltraductor.sym;

/**
 *
 * @author yennifer
 */
public class TitleComp extends ComponentHtml {

    private String text;
    private String align;
    private String color;

    public TitleComp() {
        super.htmlGen = new HTMLgenerator();
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void set(Attribute attribute) throws InvalidAttributeException {
        switch (attribute.getContentTkn().getType()) {
            case sym.TEXTO:
                this.text = attribute.getContentTkn().getLexem().toString();
                break;
            case sym.ALINEACION:
                this.align = attribute.getContentTkn().getLexem().toString();
                break;
            case sym.COLOR:
                this.color = attribute.getContentTkn().getLexem().toString();
                break;
            default:
                throw new InvalidAttributeException();
        }
    }

    @Override
    public String getHtmlCode() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getMissingAttributes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
