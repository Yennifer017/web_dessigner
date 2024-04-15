package compi1.web_designer_api.htmltraductor;

import compi1.web_designer_api.database.models.Page;
import compi1.web_designer_api.exceptions.NoCodeException;
import compi1.web_designer_api.htmltraductor.models.comp.DisplayHtmlComp;
import compi1.web_designer_api.htmltraductor.models.comp.MediaHtml;
import compi1.web_designer_api.util.FilesUtil;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class HTMLgenerator {

    private static final String SPACE = " ";
    private static final String ENTER = "\n";
    private static final String MENU_CSS_CLASS = "menu";

    public static final String END_COMPONENTS = "<!--END-->";
    public static final String COMPONENT_INIT = "<!--COMP:";
    public static final String TITLE_INDICATOR = "<!--TITLE-->";
    public static final String COMPONENT_END = "\"-->";

    public String getCodePageHtml(String title) {
        if (title == null) {
            title = "Pagina";
        } else if (title.isEmpty() || title.isBlank()) {
            title = "Pagina";
        }
        String code = "<!Doctype html>" + ENTER;
        code += "<html>" + ENTER;
        code += SPACE.repeat(4) + "<head>" + ENTER;
        code += SPACE.repeat(8) + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" + ENTER;
        code += SPACE.repeat(8) + "<link rel=\"stylesheet\" href=\"" + FilesUtil.STYLESHEET_PATH + "\">" + ENTER;
        code += SPACE.repeat(8) + "<script src=\"" + FilesUtil.AJAX_FILE_PATH + "\"></script>" + ENTER;
        code += TITLE_INDICATOR + ENTER;
        code += SPACE.repeat(8) + "<title>" + title + "</title>" + ENTER;
        code += SPACE.repeat(4) + "</head>" + ENTER;
        code += SPACE.repeat(4) + "<body>" + ENTER;
        code += END_COMPONENTS + ENTER;
        code += SPACE.repeat(4) + "</body>" + ENTER;
        code += "</html>";
        return code;
    }
    
    public String getCodeTitle(String title){
        return SPACE.repeat(8) + "<title>" + title + "</title>" + ENTER;
    }

    public String getCodeForDisplay(DisplayHtmlComp display) {
        String code = SPACE.repeat(8) + "<" + display.getLabel();
        code += getIdCodeHtml(display.getId());
        code += getAlignCode(display.getAlign());
        code += display.getColor() != null ? (" style=\"color:" + display.getColor() + "\"") : "";
        code += ">" + ENTER;
        code += SPACE.repeat(12) + display.getText() + ENTER;
        code += SPACE.repeat(8) + "</" + display.getLabel() + ">" + ENTER;
        return code;
    }

    public String getCodeForImg(MediaHtml media) {
        String code = SPACE.repeat(8) + "<img"; 
        code += getIdCodeHtml(media.getId());
        code += " src=\"" + media.getOrigin() + "\"";
        code += getAlignCode(media.getAlign());
        code += "style=\"width:" + media.getWidth() + "px; height:" + media.getHeight() + "px;";
        code += "/>";
        return code;
    }

    public String getCodeForVideo(MediaHtml media) {
        String code = SPACE.repeat(8) + "<video";
        code += getIdCodeHtml(media.getId());
        code += " width=\"" + media.getWidth();
        code += "\" height=\"" + media.getHeight() + "\" controls>" + ENTER;
        code += SPACE.repeat(12) + "<source src=\"" + media.getOrigin() + "\" ";
        code += "type=\"video/mp4\">" + ENTER;
        code += SPACE.repeat(12) + "Tu navegador no soporta el elemento video" + ENTER;
        code += SPACE.repeat(8) + "</video>" + ENTER;
        return code;
    }

    private String getAlignCode(String align) {
        return align != null ? (" class=\"" + align + "\"") : "";
    }
    
    private String getIdCodeHtml(String id){
        return " id=\"" + id + "\"";
    }
    
    public String getCodeForMenu(String finalPath, List<Page> pages, String id) throws NoCodeException{
        if(pages.isEmpty()){
            throw new NoCodeException();
        }
        String code = SPACE.repeat(8) + "<div class=\"" + MENU_CSS_CLASS + "\">" + ENTER;
        code += SPACE.repeat(12) + "<ul>" + ENTER; 
        for (Page page : pages) {
            code += SPACE.repeat(16) + "<li><a href=\">";
            code += finalPath;
            code += page.getName();
            code += "\">";
            code += page.getName() + "</a></li>" + ENTER;
        }
        code += SPACE.repeat(12) + "</ul>" + ENTER; 
        code += SPACE.repeat(8) + "</div>" + ENTER;
        return code;
    }

}
