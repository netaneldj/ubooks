package Servlets;

import Logica.Controlador.ControladoraLogica;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvBorrarHuesped", urlPatterns = {"/SvBorrarHuesped"})
public class SvBorrarHuesped extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
            int id = Integer.parseInt(request.getParameter("id"));
            boolean existeHuesped = controladoraLogica.verificarHuespedPorID(id);

            if(!existeHuesped){
                response.sendRedirect("Error/noExisteID.jsp");
            }else{
                if (controladoraLogica.borrarHuesped(id))
                    response.sendRedirect("Exito/exito.jsp");
                else 
                    response.sendRedirect("Error/error.jsp");

            }  
        } catch (Exception e) {
            response.sendRedirect("Error/error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
