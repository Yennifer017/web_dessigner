package compi1.web_designer_api.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;

/**
 *
 * @author yennifer
 */
public class FilesUtil {
    
    public static final String HTML_EXTENSION = "html";

    /** crea una carpeta
     * @param rootPath el path donde se creara la carpeta
     * @param name nombre de la carpeta
     * @return el path de la carpeta creada
     * @throws java.io.IOException
     * @throws java.nio.file.DirectoryNotEmptyException
    */
    public String createDirectory(String rootPath, String name) 
            throws IOException, DirectoryNotEmptyException {
        String path = rootPath + getCarpetSeparator() + name;
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
            return path;
        } else {
            throw new DirectoryNotEmptyException(name);
        }
    }
    
    /** 
     * @return el separador de carpetas dependiendo del sistema operativo
    */
    public String getCarpetSeparator() {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            return "\\";
        } else {
            return "/";
        }
    }
}
