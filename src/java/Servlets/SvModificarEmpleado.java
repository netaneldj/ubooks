package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Empleado;
import Logica.Entidades.Usuario;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvModificarEmpleado", urlPatterns = {"/SvModificarEmpleado"})
public class SvModificarEmpleado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie empleado = new Cookie("id_empleado", request.getParameter("id_empleado"));
        empleado.setMaxAge(60*60);
        response.addCookie(empleado);
        response.sendRedirect("actualizarEmpleado.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id_empleado = "";
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id_empleado")) { 
                    id_empleado = cookie.getValue();
                    break;
                }
            }
            int id = Integer.parseInt(id_empleado);
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            Integer dni = Integer.parseInt(request.getParameter("dni"));
            String direccion = request.getParameter("direccion");
            Date nacimiento = ControladoraLogica.convertirStringADate(request.getParameter("nacimiento"));
            String cargo = request.getParameter("cargo");
            String nombreUsuario = request.getParameter("nombreUsuario");
            String contrasenia = request.getParameter("contrasenia");

            ControladoraLogica controladoraLogica = new ControladoraLogica();
            boolean existeEmpleado = controladoraLogica.verificarEmpleadoPorID(id);

            if(!existeEmpleado){
                response.sendRedirect("Error/noExisteID.jsp");
            } else {
                Empleado empleado =  controladoraLogica.obtenerEmpleadoPorID(id);
                Usuario usuario = controladoraLogica.obtenerUsuarioPorID(empleado.getId());
                empleado.setNombre(nombre);
                empleado.setApellido(apellido);
                empleado.setDni(dni);
                empleado.setDireccion(direccion);
                empleado.setNacimiento(nacimiento);
                empleado.setCargo(cargo);
                usuario.setNombreUsuario(nombreUsuario);
                usuario.setContrasenia(contrasenia);
                controladoraLogica.modificarUsuario(usuario);
                empleado.setUsuario(usuario);
                controladoraLogica.modificarEmpleado(empleado);

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
