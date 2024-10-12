package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvModificarUsuario", urlPatterns = {"/SvModificarUsuario"})
public class SvModificarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie usuario = new Cookie("id_usuario", request.getParameter("id_usuario"));
        usuario.setMaxAge(60*60);
        response.addCookie(usuario);
        response.sendRedirect("actualizarUsuario.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id_usuario = "";
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id_usuario")) { 
                    id_usuario = cookie.getValue();
                    break;
                }
            }
            int id = Integer.parseInt(id_usuario);
            String contrasenia = request.getParameter("contrasenia");
            ControladoraLogica controladoraLogica = new ControladoraLogica();
            boolean existeUsuario = controladoraLogica.verificarUsuarioPorID(id);

            if(!existeUsuario){
                response.sendRedirect("Error/noExisteID.jsp");
            } else {
                Usuario usuario= controladoraLogica.obtenerUsuarioPorID(id);
                usuario.setContrasenia(contrasenia);
                controladoraLogica.modificarUsuario(usuario);

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
