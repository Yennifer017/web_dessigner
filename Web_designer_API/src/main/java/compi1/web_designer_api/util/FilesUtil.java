package compi1.web_designer_api.util;

import compi1.web_designer_api.exceptions.NoFileException;
import compi1.web_designer_api.exceptions.OverWrittingFileException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author yennifer
 */
public class FilesUtil {

    /**
     * Extension para archivos html, incluye el punto
     */
    public static final String HTML_EXTENSION = ".html";

    /**
     * el path del servidor web
     */
    public static final String WEB_SERVER_PATH 
            = "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/webapp/";
    
    /**
     * el path donde se guardan los sitios, incluye el separador final
     */
    public static final String WEB_SERVER_SITES 
            = WEB_SERVER_PATH + "sites/";
    
    /**
     * El path donde se almacenan los sitios web, no incluye el separador al final
     */
    public static final String SITES_PATH_SERVER
            = WEB_SERVER_PATH + "sites";
    
    /**
     * La ruta para acceder al servidor, no incluye el separador final
     */
    public static final String WEB_ROUTE = "http://localhost:8080/Web_designer_API";
    
    /**
     * La ruta para acceder al sitio web, incluye el separador al final
     */
    public static final String WEB_ROUTE_SITES = WEB_ROUTE + "/sites/";
    
    /**
     * El path del archivo js para las paginas web creadas predeterminado.
     */
    public static final String AJAX_FILE_PATH = WEB_ROUTE + "/resources/viewPageReq.js";

    /**
     * El path del archivo css para estilos
     */
    public static final String STYLESHEET_PATH = WEB_ROUTE + "/resources/styles.css";
    
    /**
     * crea una carpeta
     *
     * @param rootPath el path donde se creara la carpeta, sin el separador al
     * final
     * @param name nombre de la carpeta
     * @return el path de la carpeta creada
     * @throws java.io.IOException
     * @throws compi1.web_designer_api.exceptions.OverWrittingFileException
     *      si la carpeta ya existe
     */
    public String createDirectory(String rootPath, String name)
            throws IOException, OverWrittingFileException {
        String path = rootPath + getSeparator() + name;
        File directory = new File(path);
        if (!directory.exists()) {
            if(directory.mkdir()){
                return path;
            }else{
                throw new IOException();
            }
        } else {
            throw new OverWrittingFileException();
        }
    }

    /**
     * @return el separador de carpetas dependiendo del sistema operativo
     */
    public static String getSeparator() {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            return "\\";
        } else {
            return "/";
        }
    }

    /**
     * Reescribe/Crea un archivo a partir de un texto
     *
     * @param texto
     * @param ruta
     * @throws java.io.IOException
     * @throws compi1.web_designer_api.exceptions.NoFileException
     */
    public void saveFile(String texto, String ruta) throws IOException, NoFileException {
        File archivo = new File(ruta); //obtiene el archivo de la ruta
        if(archivo.exists()){
            saveFile(texto, archivo);
        }else{
            throw new NoFileException();
        }
    }

    /**
     * Reescribe o guarda un archivo
     *
     * @param texto
     * @param file
     */
    public void saveFile(String texto, File file) throws IOException {
        FileWriter escritor = new FileWriter(file, false);
        BufferedWriter buffer = new BufferedWriter(escritor);
        buffer.write(texto);
        buffer.close();
        escritor.close();
    }

    /**
     * Elimina un archivo
     *
     * @param path
     * @return si fue correctamente eliminado
     */
    public boolean deleteFile(String path) {
        File archivo = new File(path);
        return archivo.delete();
    }
    
    public boolean deleteDirectory(String path){
        File folder = new File(path);
        if(folder.exists() && folder.isDirectory()) {
            return deleteFolder(folder);
        } else {
            return false;
        }
    }
    
    private boolean deleteFolder(File folder){
        File[] files = folder.listFiles();
        if(files != null) {
            for(File file : files) {
                if(file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        return folder.delete();
    }

    /**
     * Guarda un nuevo archivo como, solo si no existe
     * @param text
     * @param extension incluyendo el punto
     * @param nameFile
     * @param path donde se guardara el archivo, sin incluir al final el separador
     * @throws java.io.IOException si no se puede guardar bien
     * @throws compi1.web_designer_api.exceptions.OverWrittingFileException 
     *          si el archivo se intenta sobreescribir
     */
    public void saveAs(String text, String extension, String nameFile, String path) 
            throws IOException, OverWrittingFileException {
        String finalPath = path + getSeparator() + nameFile + extension;
        File file = new File(finalPath);
        if (!file.exists()) {
            saveFile(text, file);
        } else {
            throw new OverWrittingFileException();
        }
    }
    
    /**
     * Retorna la ruta de la pagina web recien creada
     * @param site, nombre del sitio
     * @param page, nombre de la pagina
     * @return ruta del servidor web
     */
    public String getDirectoryCreatedPage(String site, String page){
        return WEB_ROUTE_SITES + site + getSeparator() + page + HTML_EXTENSION;
    }
}
