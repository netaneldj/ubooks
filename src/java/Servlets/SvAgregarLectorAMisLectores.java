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

@WebServlet(name = "SvAgregarLectorAMisLectores", urlPatterns = {"/SvAgregarLectorAMisLectores"})
public class SvAgregarLectorAMisLectores extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie mi_lector = new Cookie("id_mi_usuario", request.getParameter("id_mi_usuario"));
        Cookie otro_lector = new Cookie("id_otro_usuario", request.getParameter("id_otro_usuario"));

        mi_lector.setMaxAge(60*60);
        response.addCookie(mi_lector);
        
        otro_lector.setMaxAge(60*60);
        response.addCookie(otro_lector);
        
        response.sendRedirect("perfilOtroLector.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA");
            String id_mi_usuario = "";
            String id_otro_usuario = "";

            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id_mi_usuario")) { 
                    id_mi_usuario = cookie.getValue();
                }
                if(cookie.getName().equals("id_otro_usuario")) { 
                    id_otro_usuario = cookie.getValue();
                }
            }

            System.out.println("DESPUES DE LAS COOKIES");

            ControladoraLogica controladoraLogica = new ControladoraLogica();
            
            System.out.println("DESPUES DE LA CONTROLADORA (TOXICA)");
            System.out.println(id_mi_usuario);
            System.out.println(id_otro_usuario);

            
            int id_mi_usuario_int = Integer.parseInt(id_mi_usuario);
            int id_otro_usuario_int = Integer.parseInt(id_otro_usuario);  
            
            System.out.println("DESPUES DE LOS ENTS");

            Lector mi_lector = controladoraLogica.obtenerLectorPorIdUsuario(id_mi_usuario_int);
            Usuario otro_lector = controladoraLogica.obtenerUsuarioPorID(id_otro_usuario_int);
            
            System.out.println("DESPUES DE LOS OBTENER");
            
            mi_lector.addMisLectores(otro_lector);
            System.out.println("DESPUES DE ANIADIR");
            int id = mi_lector.getId();
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            Date nacimiento = ControladoraLogica.convertirStringADate(request.getParameter("nacimiento"));
            String nombreUsuario = request.getParameter("nombreUsuario");
            String contrasenia = request.getParameter("contrasenia");
            String email = request.getParameter("email");
            IdiomaPaper dioma = IdiomaPaper.valueOf(request.getParameter("idioma"));
            GeneroPaper genero = GeneroPaper.valueOf(request.getParameter("genero"));
            Boolean esAutor = request.getParameter("autor") != null;

            
            boolean existeLector = controladoraLogica.verificarLectorPorID(id);

            if(!existeLector){
                System.out.println("EN EL IF");

                response.sendRedirect("Error/noExisteID.jsp");
            } else {
                System.out.println("EN EL ELSE");

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
