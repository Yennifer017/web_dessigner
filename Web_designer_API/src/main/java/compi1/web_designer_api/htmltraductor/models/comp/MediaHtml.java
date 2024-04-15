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
@Getter
@Setter
public class MediaHtml extends ComponentHtml {

    public static final String IMAGE_HTML_LABEL = "img", VIDEO_HTML_LABEL = "video";

    private String label;
    private String origin, align;
    private int width, height;
    private boolean allowAlign;

    public MediaHtml(String htmlLabel, boolean allowAlign) {
        super.htmlGen = new HTMLgenerator();
        this.label = htmlLabel;
        this.allowAlign = allowAlign;
        initPermitedAttrs();
    }

    @Override
    protected final void initPermitedAttrs() {
        if (allowAlign) {
            super.permitedAttrs = new Integer[4];
        } else {
            super.permitedAttrs = new Integer[3];
        }
        permitedAttrs[0] = sym.ORIGEN;
        permitedAttrs[1] = sym.ALTURA;
        permitedAttrs[2] = sym.ANCHO;
        if (allowAlign) {
            permitedAttrs[3] = sym.ALINEACION;
        }
    }

    @Override
    public boolean canCreate() {
        return this.origin != null
                && this.width > 0
                && this.height > 0;
    }

    @Override
    public void set(Attribute attribute) throws InvalidAttributeException, NumberFormatException {
        switch (attribute.getTypeAttrTkn().getType()) {
            case sym.ORIGEN:
                this.origin = attribute.getContentTkn().getLexem().toString();
                break;
            case sym.ALINEACION:
                if (allowAlign) {
                    this.align = attribute.getContentTkn().getLexem().toString().toLowerCase();
                } else {
                    throw new InvalidAttributeException();
                }
                break;
            case sym.ALTURA:
                this.height = Integer.parseInt(attribute.getContentTkn().getLexem().toString());
                break;
            case sym.ANCHO:
                this.width = Integer.parseInt(attribute.getContentTkn().getLexem().toString());
                break;
            default:
                throw new InvalidAttributeException();
        }
    }

    @Override
    public String getHtmlCode() throws IncompleateCompException {
        if(canCreate()){
            if(allowAlign){
                return htmlGen.getCodeForImg(this);
            }else {
                return htmlGen.getCodeForVideo(this);
            }
        }else{
            throw new IncompleateCompException();
        }
    }

    @Override
    public String getMissingAttributes() {
        String mss = origin == null ? "Origen" : "";
        mss += width <=0 ? ", ancho" : "";
        mss += height<=0 ? ", alto" : "";
        return mss;
    }

}
