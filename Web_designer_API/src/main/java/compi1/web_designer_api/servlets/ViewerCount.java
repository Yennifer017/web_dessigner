
package compi1.web_designer_api.servlets;

import compi1.web_designer_api.services.CountVisitService;
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
@WebServlet(name = "ViewerCount", urlPatterns = {"/ViewerCount"})
public class ViewerCount extends HttpServlet {
    
    private CountVisitService service;
    public ViewerCount(){
        service = new CountVisitService();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service.countVisit(request, response);
    }

}
