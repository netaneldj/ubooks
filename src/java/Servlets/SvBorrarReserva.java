package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Reserva;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvBorrarReserva", urlPatterns = {"/SvBorrarReserva"})
public class SvBorrarReserva extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
            int id = Integer.parseInt(request.getParameter("id"));
            boolean existeReserva = controladoraLogica.verificarReservaPorID(id);

            if(!existeReserva){
                response.sendRedirect("Error/noExisteID.jsp");
            }else{
                Reserva reserva = controladoraLogica.obtenerReservaPorID(id);
                if (controladoraLogica.borrarReserva(id))
                    response.sendRedirect("Exito/exito.jsp");
                else
                    response.sendRedirect("Error/error.jsp");
            }   
        } catch(Exception e) {
            response.sendRedirect("Error/error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
