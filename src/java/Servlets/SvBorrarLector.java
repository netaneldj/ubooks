package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Lector;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvBorrarLector", urlPatterns = {"/SvBorrarLector"})
public class SvBorrarLector extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
                ControladoraLogica controladoraLogica = new ControladoraLogica();
                int id = Integer.parseInt(request.getParameter("id"));
                boolean existeLector = controladoraLogica.verificarLectorPorID(id);

                if(!existeLector){
                    response.sendRedirect("noExisteID.jsp");
                } else {
                    Lector lector = controladoraLogica.obtenerLectorPorID(id);
                    int id_usuario = lector.getUsuario().getId();
                    if (controladoraLogica.borrarLector(id) && controladoraLogica.borrarUsuario(id_usuario))
                        response.sendRedirect("Exito/exito.jsp");
                    else
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
