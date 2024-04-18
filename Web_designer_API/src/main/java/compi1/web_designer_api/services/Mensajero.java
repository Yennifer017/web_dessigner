
package compi1.web_designer_api.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author yennifer
 */
public class Mensajero {
    
    public String getRequestBody(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
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
