package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.GeneroPaper;
import Logica.Entidades.IdiomaPaper;
import Logica.Entidades.Lector;
import Logica.Entidades.Usuario;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author patricio.perrone
 */
@WebServlet(name = "SvSeguirUsuario", urlPatterns = {"/SvSeguirUsuario"})
public class SvSeguirUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén el ID del perfil del formulario
        int perfilId = Integer.parseInt(request.getParameter("perfilId"));
        int perfilPropioId = Integer.parseInt(request.getParameter("perfilPropioId"));
        // Obtén el lector actual desde la sesión
        ControladoraLogica controladora = new ControladoraLogica();
        Lector lector = controladora.obtenerLectorPorID(perfilPropioId);
        Usuario usuario_seguido = controladora.obtenerUsuarioPorIdLector(perfilId);

        // Verifica si el lector ya sigue al perfil
        try {
            if (controladora.lectorSiguePefil(perfilPropioId, perfilId)) {
                // Si ya lo sigue, dejar de seguir
                for (int i=0; i < lector.getSeguidos().size(); i++) {
                    if (Objects.equals(lector.getSeguidos().get(i).getId(), usuario_seguido.getId())) {
                        lector.getSeguidos().remove(i);
                        break;
                    }
                }     
            } else {
                // Si no lo sigue, empezar a seguir
                lector.getSeguidos().add(usuario_seguido);
            }
            controladora.modificarLector(lector);
            // Redirige al perfil actual para actualizar la vista
            request.getContextPath();
            response.setHeader("Refresh", "0; URL=" + request.getContextPath()+"/perfilOtroLector.jsp?perfilId="+perfilId);
        } catch (Exception e) {
            // Manejo de errores
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al intentar seguir al usuario.");
        }
    }
}

