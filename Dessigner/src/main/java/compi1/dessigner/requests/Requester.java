package compi1.dessigner.requests;

import compi1.dessigner.resources.ResponserLexer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author yennifer
 */
public class Requester {

    
    private static final String API_URL = "http://localhost:8080/";
    private final String NO_CONNECTION_MSS
            = "<errors>\n\"No se pudo establecer contacto con el servidor, intentalo de nuevo mas tarde.\"\n</errors>";

    public static final String XML_EXECUTOR_URL = API_URL + "Web_designer_API/Executor";
    public static final String SQcms_EXECUTOR_URL = API_URL + "Web_designer_API/ConsultStatistics";

    public static final String POST_METHOD = "POST";
    public static final String GET_METHOD = "GET";
    
    private JTextPane console;
    private ResponserLexer lexer;
    private HTMLDocument doc;
    private SimpleAttributeSet attrs;

    public Requester(JTextPane console) {
        this.console = console;
        initLexer(console);
    }

    private void initLexer(JTextPane console) {
        HTMLEditorKit kit = new HTMLEditorKit();
        console.setEditorKit(kit);

        doc = (HTMLDocument) console.getDocument();
        attrs = new SimpleAttributeSet();
        // Modificar el tamaño de la fuente
        StyleConstants.setFontSize(attrs, 16); // Tamaño de la fuente en puntos
        // Alinear el texto a la izquierda
        StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_LEFT);

        MutableAttributeSet inputAttributes = kit.getInputAttributes();
        StyleConstants.setLineSpacing(inputAttributes, 0);
    }

    public void request(String textBody, String pathToRequest, String method) throws IOException {
        try {
            HttpURLConnection connection = sendRequest(textBody, pathToRequest, method);
            int responseCode = connection.getResponseCode();
            if (responseCode >= 500) {
                showResponse(NO_CONNECTION_MSS);
            } else {
                String responseBody = getResponseBody(connection);
                showResponse(responseBody);
            }
        } catch (MalformedURLException ex) {
            showResponse(NO_CONNECTION_MSS);
            System.out.println(ex);
        } catch (IOException ex) {
            showResponse(NO_CONNECTION_MSS);
            System.out.println(ex);
        }
    }

    private void showResponse(String content) throws IOException {
        console.setText("");
        StringReader reader = new StringReader(content);
        lexer = new ResponserLexer(reader);
        lexer.init(doc, attrs);
        while (!lexer.yyatEOF()) {
            lexer.yylex();
        }
    }

    private HttpURLConnection sendRequest(String textBody, String serverUrl, String method)
            throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL(serverUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "text/plain");
        connection.setDoOutput(true);

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(textBody);
        out.flush();
        out.close();
        return connection;
    }

    private String getResponseBody(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            response.append("\n");
        }
        in.close();
        return response.toString();
    }
}
