package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.GeneroPaper;
import Logica.Entidades.IdiomaPaper;
import Logica.Entidades.Lector;
import Logica.Entidades.Usuario;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvSeleccionarLector", urlPatterns = {"/SvSeleccionarLector"})
public class SvSeleccionarLector extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie lector = new Cookie("id_lector", request.getParameter("id_lector"));
        lector.setMaxAge(60*60);
        response.addCookie(lector);
        response.sendRedirect("verPerfilLector.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id_lector = "";
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id_lector")) { 
                    id_lector = cookie.getValue();
                    break;
                }
            }
            int id = Integer.parseInt(id_lector);
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            Date nacimiento = ControladoraLogica.convertirStringADate(request.getParameter("nacimiento"));
            String nombreUsuario = request.getParameter("nombreUsuario");
            String contrasenia = request.getParameter("contrasenia");
            String email = request.getParameter("email");
            IdiomaPaper dioma = IdiomaPaper.valueOf(request.getParameter("idioma"));
            GeneroPaper genero = GeneroPaper.valueOf(request.getParameter("genero"));
            Boolean esAutor = request.getParameter("autor") != null;

            ControladoraLogica controladoraLogica = new ControladoraLogica();
            boolean existeLector = controladoraLogica.verificarLectorPorID(id);

            if(!existeLector){
                response.sendRedirect("Error/noExisteID.jsp");
            } else {
                Lector lector =  controladoraLogica.obtenerLectorPorID(id);
                Usuario usuario = controladoraLogica.obtenerUsuarioPorID(lector.getId());
                lector.setNombre(nombre);
                lector.setApellido(apellido);
                lector.setNacimiento(nacimiento);
                lector.setIdioma(dioma);
                lector.setGenero(genero);
                usuario.setNombreUsuario(nombreUsuario);
                usuario.setContrasenia(contrasenia);
                usuario.setEmail(email);
                controladoraLogica.modificarUsuario(usuario);
                lector.setUsuario(usuario);
                lector.setEsAutor(esAutor);
                controladoraLogica.modificarLector(lector);

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
