package Servlets;

import Logica.Controlador.ControladoraLogica;
import Logica.Entidades.Empleado;
import Logica.Entidades.Habitacion;
import Logica.Entidades.Huesped;
import Logica.Entidades.Reserva;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvModificarReserva", urlPatterns = {"/SvModificarReserva"})
public class SvModificarReserva extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie reserva = new Cookie("id_reserva", request.getParameter("id_reserva"));
        reserva.setMaxAge(60*60);
        response.addCookie(reserva);
        response.sendRedirect("actualizarReserva.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ControladoraLogica controladoraLogica = new ControladoraLogica();
            String id_reserva = "";
            String id_usuario = "";
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id_reserva")) { 
                    id_reserva = cookie.getValue();
                } else if (cookie.getName().equals("id_usuario")) {
                    id_usuario = cookie.getValue();
                }
            }
            Integer idReserva = Integer.parseInt(id_reserva);
            Integer idEmpleado = controladoraLogica.obtenerEmpleadoPorUsuario(Integer.parseInt(id_usuario)).getId();
            Integer idHuesped = Integer.parseInt(request.getParameter("id_huesped"));
            Integer idHabitacion = Integer.parseInt(request.getParameter("id_habitacion"));
            Integer nroHuespedes = Integer.parseInt(request.getParameter("nro_huespedes"));
            Date checkin = Date.from(LocalDate.parse(request.getParameter("checkin")).atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date checkout = Date.from(LocalDate.parse(request.getParameter("checkout")).atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            Empleado empleado =  controladoraLogica.obtenerEmpleadoPorID(idEmpleado);
            Huesped huesped =  controladoraLogica.obtenerHuespedPorID(idHuesped);
            Habitacion habitacion =  controladoraLogica.obtenerHabitacionPorID(idHabitacion);
            
            if (nroHuespedes.compareTo(habitacion.getTipo().getCantidad()) <= 0) {
                if (controladoraLogica.disponibilidadHabitacion(idHabitacion, checkin, checkout)) {
                    Double montoTotal = controladoraLogica.obtenerHabitacionPorID(idHabitacion).getPrecio()*ControladoraLogica.calcularDiasEntreFechas(checkin, checkout);

                    boolean existeReserva = controladoraLogica.verificarReservaPorID(idReserva);

                    if(!existeReserva){
                        response.sendRedirect("Error/noExisteID.jsp");
                    } else {

                        Reserva reserva = controladoraLogica.obtenerReservaPorID(idReserva);
                        reserva.setEmpleado(empleado);
                        reserva.setHuesped(huesped);
                        reserva.setHabitacion(habitacion);
                        reserva.setCheckIn(checkin);
                        reserva.setCheckOut(checkout);
                        reserva.setNroHuespedes(nroHuespedes);
                        reserva.setMontoTotal(montoTotal);
                        controladoraLogica.modificarReserva(reserva);

                        response.sendRedirect("Exito/exito.jsp");
                    }   
                } else {
                    response.sendRedirect("Error/habitacionNoDisponible.jsp");
                }
            } else {
                response.sendRedirect("Error/numeroHuespedesExcedido.jsp");
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
