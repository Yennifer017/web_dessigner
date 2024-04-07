
package compi1.web_designer_api.servlets;

import compi1.web_designer_api.services.XMLservice;
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
@WebServlet(name = "Executor", urlPatterns = {"/Executor"})
public class Executor extends HttpServlet {

    private XMLservice service;
    public Executor(){
        service = new XMLservice();
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
        service.executeXML(request, response);
    }


}
