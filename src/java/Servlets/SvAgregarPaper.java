package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.GeneroPaper;
import Logica.Entidades.Lector;
import Logica.Entidades.Paper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvAgregarPaper", urlPatterns = {"/SvAgregarPaper"})
public class SvAgregarPaper extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
        
            Paper paper = new Paper();

            String doi = request.getParameter("doi");
            String nombre = request.getParameter("nombre");
            String resumen = request.getParameter("resumen");
            GeneroPaper genero = GeneroPaper.valueOf(request.getParameter("genero"));
            
            Integer idLector = Integer.parseInt(request.getParameter("id_lector"));
            Lector lector = controladoraLogica.obtenerLectorPorID(idLector);

            paper.setDoi(doi);
            paper.setNombre(nombre);
            paper.setAutor(lector);
            paper.setResumen(resumen);
            paper.setGenero(genero);

            boolean exito = controladoraLogica.crearPaper(paper);

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
