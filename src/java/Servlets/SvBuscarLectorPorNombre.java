package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Lector;
import Logica.Entidades.Paper;
import Logica.Entidades.Usuario;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvBuscarLectorPorNombre", urlPatterns = {"/SvBuscarLectorPorNombre"})
public class SvBuscarLectorPorNombre extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie usuario = new Cookie("nombre_usuario", request.getParameter("nombre_usuario"));
        Cookie esAutor = new Cookie("es_autor", Objects.isNull(request.getParameter("autor")) ? "No" : "Si");
                 
        usuario.setMaxAge(60*60);
        response.addCookie(usuario);
        response.addCookie(esAutor);

        response.sendRedirect("mostrarLectores.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
