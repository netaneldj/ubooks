package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Habitacion;
import Logica.Entidades.TematicaHabitacion;
import Logica.Entidades.TipoHabitacion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvModificarHabitacion", urlPatterns = {"/SvModificarHabitacion"})
public class SvModificarHabitacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie habitacion = new Cookie("id_habitacion", request.getParameter("id_habitacion"));
        habitacion.setMaxAge(60*60);
        response.addCookie(habitacion);
        response.sendRedirect("actualizarHabitacion.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id_habitacion = "";
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id_habitacion")) { 
                    id_habitacion = cookie.getValue();
                    break;
                }
            }
            int id = Integer.parseInt(id_habitacion);
            TematicaHabitacion tematica = TematicaHabitacion.valueOf(request.getParameter("tematica"));
            TipoHabitacion tipo = TipoHabitacion.valueOf(request.getParameter("tipo"));
            Integer piso = Integer.parseInt(request.getParameter("piso"));
            Double precio = Double.parseDouble(request.getParameter("precio"));

            ControladoraLogica controladoraLogica = new ControladoraLogica();
            boolean existeHabitacion = controladoraLogica.verificarHabitacionPorID(id);

            if(!existeHabitacion){
                response.sendRedirect("Error/noExisteID.jsp");
            } else {
                Habitacion habitacion =  controladoraLogica.obtenerHabitacionPorID(id);
                habitacion.setTematica(tematica);
                habitacion.setTipo(tipo);
                habitacion.setPiso(piso);
                habitacion.setPrecio(precio);
                controladoraLogica.modificarHabitacion(habitacion);

                response.sendRedirect("Exito/exito.jsp");
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
