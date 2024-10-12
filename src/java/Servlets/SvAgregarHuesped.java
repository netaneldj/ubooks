package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Huesped;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvAgregarHuesped", urlPatterns = {"/SvAgregarHuesped"})
public class SvAgregarHuesped extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
        
            Huesped huesped = new Huesped();

            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            Integer dni = Integer.parseInt(request.getParameter("dni"));
            String direccion = request.getParameter("direccion");
            Date nacimiento = ControladoraLogica.convertirStringADate(request.getParameter("nacimiento"));
            String profesion = request.getParameter("profesion");
            String nombreUsuario = request.getParameter("nombreUsuario");
            String contrasenia = request.getParameter("contrasenia");

            huesped.setNombre(nombre);
            huesped.setApellido(apellido);
            huesped.setDni(dni);
            huesped.setDireccion(direccion);
            huesped.setNacimiento(nacimiento);
            huesped.setProfesion(profesion);

            boolean exito = controladoraLogica.crearHuesped(huesped);

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
