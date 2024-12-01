package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.GeneroPaper;
import Logica.Entidades.IdiomaPaper;
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


@WebServlet(name = "SvEnviarValoracion", urlPatterns = {"/SvEnviarValoracion"})
public class SvEnviarValoracion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
            String id_lector = "";
            String id_paper = "";
            Lector lector = null;
            Paper paper = null;
            Boolean exito = false;
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id_paper")) { 
                    id_paper = cookie.getValue();
                } else if (cookie.getName().equals("id_lector")) {
                    id_lector = cookie.getValue();
                }
            }

            if (id_paper.equals("") || id_lector.equals("")) {
                response.sendRedirect("index.jsp");
            } 
            
            try {
                lector = controladoraLogica.obtenerLectorPorID(Integer.parseInt(id_lector));
                paper = controladoraLogica.obtenerPaperPorID(Integer.parseInt(id_paper));
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("Error/error.jsp"); // Redirige a una página de error específica si ocurre una excepción
            }

            // Manejo la valoración enviada
            String comentario = request.getParameter("comentario");
            String valoracionNumericaStr = request.getParameter("valoracion");
            int valoracionNumerica = valoracionNumericaStr != null ? Integer.parseInt(valoracionNumericaStr) : 0;

            if (valoracionNumerica > 0) {
                if (Objects.isNull(comentario)){
                    comentario = "";
                }
                exito = controladoraLogica.insertarValoracion(paper, lector, valoracionNumerica, comentario) && controladoraLogica.actualizarPromedioValoracionPaper(Integer.valueOf(id_paper));
            }
        

            if (exito) {
                response.sendRedirect("verPaper.jsp?id="+id_paper);
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
