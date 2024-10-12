package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Habitacion;
import Logica.Entidades.TematicaHabitacion;
import Logica.Entidades.TipoHabitacion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvAgregarHabitacion", urlPatterns = {"/SvAgregarHabitacion"})
public class SvAgregarHabitacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
        
            Habitacion habitacion = new Habitacion();

            TematicaHabitacion tematica = TematicaHabitacion.valueOf(request.getParameter("tematica"));
            TipoHabitacion tipo = TipoHabitacion.valueOf(request.getParameter("tipo"));
            Integer piso = Integer.parseInt(request.getParameter("piso"));
            Double precio = Double.parseDouble(request.getParameter("precio"));

            habitacion.setTematica(tematica);
            habitacion.setTipo(tipo);
            habitacion.setPiso(piso);
            habitacion.setPrecio(precio);

            boolean exito = controladoraLogica.crearHabitacion(habitacion);

            if (exito) {
                response.sendRedirect("Exito/exito.jsp");
            } else {
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
