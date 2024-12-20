/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.EnumLeido;
import Logica.Entidades.Grupo;
import Logica.Entidades.Lector;
import Logica.Entidades.MiPaper;
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

@WebServlet(name = "SvGuardarPaper", urlPatterns = {"/SvGuardarPaper"})
public class SvGuardarPaper extends HttpServlet{
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            String id_lector=request.getParameter("lector");
            String id_paper = request.getParameter("paper");
            String paginaOriginal = request.getParameter("paginaOriginal");
            
            ControladoraLogica controladoraLogica = new ControladoraLogica();
            
            Integer idLector = Integer.valueOf(id_lector);
            Integer idPaper = Integer.valueOf(id_paper);
            
            Lector lector = controladoraLogica.obtenerLectorPorID(idLector);
            Paper paper = controladoraLogica.obtenerPaperPorID(idPaper);
            
            MiPaper miPaper = new MiPaper();
            miPaper.setLeido(EnumLeido.NO_LEIDO);
            miPaper.setPaper(paper);
                    
            lector.addPaper(miPaper);
            
            controladoraLogica.modificarLector(lector);
            request.getContextPath();
            response.setHeader("Refresh", "0; URL=" + request.getContextPath()+'/'+ paginaOriginal+"?id="+id_paper);
           
        } catch(Exception e) {
            response.sendRedirect("Error/error.jsp");
        }
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}