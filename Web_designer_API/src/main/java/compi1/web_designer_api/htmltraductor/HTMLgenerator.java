
package compi1.web_designer_api.htmltraductor;

import compi1.web_designer_api.util.FilesUtil;

/**
 *
 * @author yennifer
 */
public class HTMLgenerator {
    private static final String SPACE = " ";
    private static final String ENTER = "\n";
    private static final String COMPONENT_END = "\"-->";
    
    public static final String END_COMPONENTS = "<!--END-->";
    public static final String COMPONENT_INIT = "<!--COMP:\"";
    
    
    public String getCodePageHtml(String title){
        if(title == null){
            title = "Pagina";
        }else if (title.isEmpty() || title.isBlank()){
            title = "Pagina";
        }
        String code = "<!Doctype html>" + ENTER;
        code += "<html>" + ENTER;
        code += SPACE.repeat(4) + "<head>" + ENTER;
        code += SPACE.repeat(8) + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" + ENTER;
        code += SPACE.repeat(8) + "<script src=\"" + FilesUtil.AJAX_FILE_PATH + "\"></script>" + ENTER;
        code += SPACE.repeat(8) + "<title>" + title + "</title>" + ENTER;
        code += SPACE.repeat(4) + "</head>" + ENTER;
        code += SPACE.repeat(4) + "<body>" + ENTER;
        code += END_COMPONENTS + ENTER;
        code += SPACE.repeat(4) + "</body>" + ENTER;
        code += "</html>";
        return code;
    }
    
    
}

