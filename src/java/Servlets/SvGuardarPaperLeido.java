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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvGuardarPaperLeido", urlPatterns = {"/SvGuardarPaperLeido"})
public class SvGuardarPaperLeido extends HttpServlet{
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
            
            List<MiPaper> misPapers = lector.getMisPapers();
            
            Boolean coincidencia = false;
            int posicionPaper = 0;
            
            for(int i = 0; i < misPapers.size(); i++) {
                MiPaper p = misPapers.get(i);
                if( p.getIdPaper().equals(paper.getId()) && !(p.getLeido().equals(EnumLeido.LEIDO) ) ){
                    posicionPaper = i;
                    lector.addPuntos();
                    coincidencia = true;
                    break;
                }
            }
            
            if (coincidencia){
                //System.out.print("ENTRE en coincidencia");
                misPapers.get(posicionPaper).setLeido(EnumLeido.LEIDO);
                lector.setMisPapers(misPapers);
            }
            
            controladoraLogica.modificarMiPaper(misPapers);
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
