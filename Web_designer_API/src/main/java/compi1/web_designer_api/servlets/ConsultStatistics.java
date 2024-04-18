
package compi1.web_designer_api.servlets;

import compi1.web_designer_api.services.ConsultsService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author yennifer
 */
@WebServlet(name = "ConsultStatistics", urlPatterns = {"/ConsultStatistics"})
public class ConsultStatistics extends HttpServlet {

    private ConsultsService service;
    public ConsultStatistics(){
        service = new ConsultsService();
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service.execute(request, response);
    }

}
