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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvAgregarLector", urlPatterns = {"/SvAgregarLector"})
public class SvAgregarLector extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
        
            Lector lector = new Lector();
            Usuario usuario = new Usuario();

            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String biografia = request.getParameter("biografia");
            Date nacimiento = ControladoraLogica.convertirStringADate(request.getParameter("nacimiento"));
            String nombreUsuario = request.getParameter("nombreUsuario");
            String contrasenia = request.getParameter("contrasenia");
            String email = request.getParameter("email");
            IdiomaPaper dioma = IdiomaPaper.valueOf(request.getParameter("idioma"));
            GeneroPaper genero = GeneroPaper.valueOf(request.getParameter("genero"));
            Boolean esAutor = request.getParameter("autor") != null;
            int imagenPerfil = Integer.parseInt(request.getParameter("imagenPerfil"));

            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasenia(contrasenia);
            usuario.setEmail(email);

            lector.setNombre(nombre);
            lector.setApellido(apellido);
            lector.setBiografia(biografia);
            lector.setNacimiento(nacimiento);
            lector.setUsuario(usuario);
            lector.setIdioma(dioma);
            lector.setGenero(genero);
            lector.setEsAutor(esAutor);
            lector.setImagenPerfil(imagenPerfil);

            boolean exito = controladoraLogica.crearLectorUsuario(lector,usuario);

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
