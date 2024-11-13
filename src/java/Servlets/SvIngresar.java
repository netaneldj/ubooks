package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Lector;
import Logica.Entidades.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvIngresar", urlPatterns = {"/SvIngresar"})
public class SvIngresar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
        
            String nombreUsuario = request.getParameter("nombreUsuario");
            String contrasenia = request.getParameter("contrasenia");

            boolean esUsuarioValido = controladoraLogica.esUsuarioValido(nombreUsuario, contrasenia);

            if (esUsuarioValido) {
                Usuario usuario = controladoraLogica.obtenerUsuarioPorCredenciales(nombreUsuario, contrasenia);
                HttpSession sesion = request.getSession();
                //sesion expira en 60 mins
                sesion.setMaxInactiveInterval(60*60);
                Cookie id_usuario = new Cookie("id_usuario", usuario.getId().toString());
                id_usuario.setMaxAge(60*60);
                response.addCookie(id_usuario);
                
                Lector lector = controladoraLogica.obtenerLectorPorIdUsuario(usuario.getId());
                Cookie id_lector = new Cookie("id_lector", String.valueOf(lector.getId()));
                id_lector.setMaxAge(60*60);
                response.addCookie(id_lector);

                response.sendRedirect("inicio.jsp");
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
