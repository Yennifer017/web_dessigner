
package compi1.web_designer_api.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author yennifer
 */
public class Mensajero {
    
    public String getRequestBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        String requestBody = sb.toString();
        return requestBody;
    }
    
    public void sendResponse(String message, HttpServletResponse response) throws IOException{
        response.setContentType("text/plain");
        response.getWriter().write(message);
    }
}
