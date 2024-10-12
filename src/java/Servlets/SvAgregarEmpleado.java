package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Empleado;
import Logica.Entidades.Usuario;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvAgregarEmpleado", urlPatterns = {"/SvAgregarEmpleado"})
public class SvAgregarEmpleado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
        
            Empleado empleado = new Empleado();
            Usuario usuario = new Usuario();

            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            Integer dni = Integer.parseInt(request.getParameter("dni"));
            String direccion = request.getParameter("direccion");
            Date nacimiento = ControladoraLogica.convertirStringADate(request.getParameter("nacimiento"));
            String cargo = request.getParameter("cargo");
            String nombreUsuario = request.getParameter("nombreUsuario");
            String contrasenia = request.getParameter("contrasenia");

            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasenia(contrasenia);

            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setDni(dni);
            empleado.setDireccion(direccion);
            empleado.setNacimiento(nacimiento);
            empleado.setCargo(cargo);
            empleado.setUsuario(usuario);

            boolean exito = controladoraLogica.crearEmpleadoUsuario(empleado,usuario);

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
