/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.Controlador.ControladoraLogica;
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

@WebServlet(name = "SvBuscarGrupos", urlPatterns = {"/SvBuscarGrupos"})
public class SvBuscarGrupos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie nombre_grupo = new Cookie("nombre_grupo", request.getParameter("nombre_grupo"));
                 
        nombre_grupo.setMaxAge(60*60);
        response.addCookie(nombre_grupo);
       
        response.sendRedirect("mostrarGrupos.jsp");
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
