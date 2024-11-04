/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.GeneroPaper;
import Logica.Entidades.Lector;
import Logica.Entidades.Paper;
import Logica.Entidades.Grupo;
import static java.io.IO.print;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvCrearGrupo", urlPatterns = {"/SvCrearGrupo"})
public class SvCrearGrupo extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
        
            Grupo grupo = new Grupo();

            String nombre = request.getParameter("nombre");
            String tema = request.getParameter("tema");
           
            String id_usuario = "0";
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
            if(cookie.getName().equals("id_usuario")) { 
                id_usuario = cookie.getValue();
                break;
            }
            }

            grupo.setNombre(nombre);
            grupo.setTema(tema);
            grupo.addMiembro(Integer.valueOf(id_usuario));

            boolean exito = controladoraLogica.crearGrupo(grupo);

            if (exito) {
                response.sendRedirect("Exito/exito.jsp");
            } else {
                response.sendRedirect("Error/error.jsp");
            }
        } catch(Exception e) {
            response.sendRedirect("Error/error.jsp");
        }
    }
}
