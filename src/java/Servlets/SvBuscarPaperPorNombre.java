package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Lector;
import Logica.Entidades.Paper;
import Logica.Entidades.Usuario;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvBuscarPaperPorNombre", urlPatterns = {"/SvBuscarPaperPorNombre"})
public class SvBuscarPaperPorNombre extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombrePaper = request.getParameter("nombre_paper").replace(' ', '-');
        Cookie paper_name = new Cookie("nombre_paper", nombrePaper);
        String autorNombre  = request.getParameter("nombre_autor").replace(' ', '-');
        Cookie author_name = new Cookie("nombre_autor", autorNombre);
        Cookie paper_genre = new Cookie("genero", request.getParameter("genero"));
        paper_name.setMaxAge(60*60);
        author_name.setMaxAge(60*60);
        paper_genre.setMaxAge(60*60);
        response.addCookie(paper_name);
        response.addCookie(author_name);
        response.addCookie(paper_genre);
        response.sendRedirect("mostrarPapers.jsp");
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
