package compi1.dessigner.requests;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;

/**
 *
 * @author yennifer
 */
public class Requester {

    private JTextPane console;
    private final String API_URL = "http://localhost:8080/";
    private final String XML_EXECUTOR_URL = "Web_designer_API/Executor";
    private final String POST_METHOD = "POST";

    public Requester(JTextPane console) {
        this.console = console;
    }

    public void reqExecute(String text) {
        try {
            HttpURLConnection connection = sendRequest(text, API_URL + XML_EXECUTOR_URL, POST_METHOD);
            int responseCode = connection.getResponseCode();
            String responseBody = getResponseBody(connection);
            console.setText(responseBody);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Requester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Requester.class.getName()).log(Level.SEVERE, null, ex);
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
