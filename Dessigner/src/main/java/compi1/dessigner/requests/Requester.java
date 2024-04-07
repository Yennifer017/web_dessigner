package compi1.dessigner.requests;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
            URL url = new URL(API_URL + XML_EXECUTOR_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(POST_METHOD);
            con.setRequestProperty("Content-Type", "text/plain");
            con.setDoOutput(true);
            
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(text);
            out.flush();
            out.close();
            
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println("Response Body: " + response.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Requester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Requester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
