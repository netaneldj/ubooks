package Servlets;

import Logica.Controlador.ControladoraLogica;
import java.util.Date;
import Logica.Entidades.Huesped;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvModificarHuesped", urlPatterns = {"/SvModificarHuesped"})
public class SvModificarHuesped extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie huesped = new Cookie("id_huesped", request.getParameter("id_huesped"));
        huesped.setMaxAge(60*60);
        response.addCookie(huesped);
        response.sendRedirect("actualizarHuesped.jsp");
    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
            String id_huesped = "";
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id_huesped")) { 
                    id_huesped = cookie.getValue();
                    break;
                }
            }
            int id = Integer.parseInt(id_huesped);
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            Integer dni = Integer.parseInt(request.getParameter("dni"));
            String direccion = request.getParameter("direccion");
            Date nacimiento = ControladoraLogica.convertirStringADate(request.getParameter("nacimiento"));
            String profesion = request.getParameter("profesion");

            boolean existeHuesped = controladoraLogica.verificarHuespedPorID(id);

            if(!existeHuesped){
                response.sendRedirect("Error/noExisteID.jsp");
            } else {
                Huesped huesped =  controladoraLogica.obtenerHuespedPorID(id);
                huesped.setNombre(nombre);
                huesped.setApellido(apellido);
                huesped.setDni(dni);
                huesped.setDireccion(direccion);
                huesped.setNacimiento(nacimiento);
                huesped.setProfesion(profesion);
                controladoraLogica.modificarHuesped(huesped);

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
