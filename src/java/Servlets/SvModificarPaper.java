package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.GeneroPaper;
import Logica.Entidades.IdiomaPaper;
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

@WebServlet(name = "SvModificarPaper", urlPatterns = {"/SvModificarPaper"})
public class SvModificarPaper extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie paper = new Cookie("id_paper", request.getParameter("id_paper"));
        paper.setMaxAge(60*60);
        response.addCookie(paper);
        response.sendRedirect("actualizarPaper.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id_paper = "";
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id_paper")) { 
                    id_paper = cookie.getValue();
                    break;
                }
            }
            
            ControladoraLogica controladoraLogica = new ControladoraLogica();
            
            int id = Integer.parseInt(id_paper);
            String doi = request.getParameter("doi");
            String nombre = request.getParameter("nombre");
            Integer idLector = Integer.parseInt(request.getParameter("id_lector"));
            String resumen = request.getParameter("resumen");
            GeneroPaper genero = GeneroPaper.valueOf(request.getParameter("genero"));
            IdiomaPaper idioma = IdiomaPaper.valueOf(request.getParameter("idioma"));
            
            Lector lector = controladoraLogica.obtenerLectorPorID(idLector);

            boolean existePaper = controladoraLogica.verificarPaperPorId(id);

            if(!existePaper){
                response.sendRedirect("Error/noExisteID.jsp");
            } else {
                Paper paper =  controladoraLogica.obtenerPaperPorID(id);
                paper.setDoi(doi);
                paper.setNombre(nombre);
                paper.setAutor(lector);
                paper.setResumen(resumen);
                paper.setGenero(genero);
                paper.setIdioma(idioma);
                controladoraLogica.modificarPaper(paper);
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
