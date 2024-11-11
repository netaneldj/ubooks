/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Grupo;
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

@WebServlet(name = "SvUnirmeGrupo", urlPatterns = {"/SvUnirmeGrupo"})
public class SvUnirmeGrupo extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //try {
            
            String id_lector=request.getParameter("lector");
            String id_grupo = request.getParameter("grupo");
            String paginaOriginal = request.getParameter("paginaOriginal");
            
            ControladoraLogica controladoraLogica = new ControladoraLogica();
            
            Integer idLector = Integer.valueOf(id_lector);
            Integer idGrupo = Integer.valueOf(id_grupo);
            
            Lector lector = controladoraLogica.obtenerLectorPorID(idLector);
            Grupo grupo = controladoraLogica.obtenerGrupoPorId(idGrupo);
            
            grupo.addMiembro(lector);
            
            controladoraLogica.modificarGrupo(grupo);
            request.getContextPath();
            response.setHeader("Refresh", "0; URL=" + request.getContextPath()+'/'+ paginaOriginal);
           
        /*} catch(Exception e) {
            response.sendRedirect("Error/error.jsp");
        }*/
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
