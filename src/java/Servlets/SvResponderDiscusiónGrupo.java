/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.ComentarioGrupo;
import Logica.Entidades.ComentarioRespuesta;
import Logica.Entidades.GeneroPaper;
import Logica.Entidades.Lector;
import Logica.Entidades.Paper;
import Logica.Entidades.Grupo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvResponderDiscusiónGrupo", urlPatterns = {"/SvResponderDiscusiónGrupo"})
public class SvResponderDiscusiónGrupo extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
            String paginaOriginal = request.getParameter("paginaOriginal");
        
            ComentarioRespuesta respuesta = new ComentarioRespuesta();

            String descripción = request.getParameter("resp");
           
            String id_usuario = "0";
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
            if(cookie.getName().equals("id_usuario")) { 
                id_usuario = cookie.getValue();
                break;
            }
            }
            Lector lectorCreador = controladoraLogica.obtenerLectorPorUsuario(Integer.valueOf(id_usuario));
            ComentarioGrupo comentarioPrincipal = controladoraLogica.obtenerComentarioGrupoPorId(Integer.valueOf(request.getParameter("comentario")));
            respuesta.setCreador(lectorCreador);
            respuesta.setComentario(descripción);

            boolean exito = controladoraLogica.crearComentarioRespuesta(respuesta);

            if (exito) {
                comentarioPrincipal.addRespuesta(respuesta);
                controladoraLogica.modificarComentarioGrupo(comentarioPrincipal);
                request.getContextPath();
                response.setHeader("Refresh", "0; URL=" + request.getContextPath()+'/'+ "inicio.jsp");
            } else {
                response.sendRedirect("Error/error.jsp");
            }
        } catch(Exception e) {
            response.sendRedirect("Error/error.jsp");
        }
    }
}
