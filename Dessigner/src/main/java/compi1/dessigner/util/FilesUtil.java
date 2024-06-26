package compi1.dessigner.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author yennifer
 */
public class FilesUtil {

    private String currentPath;
    private boolean changed;
    private boolean openFile;
    
    public FilesUtil(){
        changed = false;
        openFile = false;
    }

    /**
     * Obtiene un texto de un archivo y lo devuelve
     */
    public String readTextFile(String path) { //obtiene el texto contenido en un archivo y lo devuelve
        String texto = "";
        try {
            File archivo = new File(path); //creando el archivo
            FileReader lector = new FileReader(archivo); //lector del archivo
            BufferedReader buffer = new BufferedReader(lector); //para leer mas rapido el archivo
            String linea;
            while ((linea = buffer.readLine()) != null) {
                texto += linea + "\n";
            }
            buffer.close();
            lector.close();
        } catch (IOException error) {
            System.out.println(error);
        }
        return texto;
    }

    public String getPath() {
        JFileChooser buscador = new JFileChooser(); //creando el buscador de archivos
        buscador.showOpenDialog(null); //abrir el buscador
        return buscador.getSelectedFile().getAbsolutePath();
    }

    /**
     * Reescribe un archivo a partir de un texto
     * @param texto
     * @param ruta
     */
    public void saveFile(String texto, String ruta) throws IOException { 
        File archivo = new File(ruta); //obtiene el archivo de la ruta
        FileWriter escritor = new FileWriter(archivo, false);
        BufferedWriter buffer = new BufferedWriter(escritor);
        buffer.write(texto);
        buffer.close();
        escritor.close();
    }

    public void deleteFile(String path) {
        File archivo = new File(path);
        archivo.delete();
    }

    public String saveAs(String text, String extension) throws IOException {
        String path = JOptionPane.showInputDialog(null, "Ingresa un nombre para guardar el archivo",
                "Guardando un nuevo archivo", JOptionPane.QUESTION_MESSAGE);
        if (path != null && !path.equals("")) {
            File file = new File(path + extension);
            if (!file.exists()) {
                this.saveFile(text, path + extension);
                JOptionPane.showMessageDialog(null, "Se ha guardado el archivo", ""
                        + "Guardado exitoso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int opcion = JOptionPane.showConfirmDialog(null, """
                        Se ha encontrado un archivo con el mismo nombre especificado.
                        ¿Deseas sobreescribirlo?""", "sobreescribiendo archivo...",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION);
                if (opcion == 0) {
                    this.saveFile(text, path + extension);
                    JOptionPane.showMessageDialog(null, "Se ha guardado el archivo", ""
                            + "Guardado exitoso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (path != null && path.equals("")) {
            JOptionPane.showMessageDialog(null, """
                                            No has ingresado un nombre de archivo valido,
                                            no se ha podido guardar, intentalo de nuevo.""",
                    "Se ha producido un error", JOptionPane.ERROR_MESSAGE);
        }
        return path + extension;
    }

    public void saveFromExistentPath(String text, String path, String fileName) throws IOException {
        int option = JOptionPane.showConfirmDialog(null, "¿Guardar el archivo "
                + fileName + "?", "Guardando archivo..",
                JOptionPane.CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION);
        if (option == 0) { //cuando confirma
            this.saveFile(text, path);
            JOptionPane.showMessageDialog(null, "Se ha guardado el archivo", ""
                    + "Guardado exitoso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setCurrentPath(String path) {
        currentPath = path;
    }

    public void closeCurrentFile() {
        currentPath = null;
    }

    public String getCurrentPath() {
        return this.currentPath;
    }
    
    public void setChanged(boolean changed){
        this.changed = changed;
    }
    
    public boolean isChanged(){
        return this.changed;
    }
    
    public void setOpenFile(boolean openFile){
        this.openFile = openFile;
    }
    
    public boolean isOpenFile(){
        return this.openFile;
    }

}
