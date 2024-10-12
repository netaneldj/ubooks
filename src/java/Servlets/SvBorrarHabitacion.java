package Servlets;

import Logica.Controlador.ControladoraLogica;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvBorrarHabitacion", urlPatterns = {"/SvBorrarHabitacion"})
public class SvBorrarHabitacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
            int id = Integer.parseInt(request.getParameter("id"));
            boolean existeHabitacion = controladoraLogica.verificarHabitacionPorID(id);

            if(!existeHabitacion){
                response.sendRedirect("Error/noExisteID.jsp");
            } else {
                if (controladoraLogica.borrarHabitacion(id))
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
