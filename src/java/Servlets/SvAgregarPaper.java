package Servlets;

import Logica.Controlador.ControladoraLogica;
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
            String autor = request.getParameter("autor");
            String resumen = request.getParameter("resumen");

            paper.setDoi(doi);
            paper.setNombre(nombre);
            paper.setAutor(autor);
            paper.setResumen(resumen);

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
