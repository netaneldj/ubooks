package Logica.Controlador;

import Logica.Entidades.Empleado;
import Logica.Entidades.Habitacion;
import Logica.Entidades.Huesped;
import Logica.Entidades.Reserva;
import Logica.Entidades.Usuario;
import Persistencia.Controlador.ControladoraPersistencia;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ControladoraLogica {
    
    ControladoraPersistencia controladoraPersistencia;
    
    public ControladoraLogica(){
        controladoraPersistencia = new ControladoraPersistencia();
    }
    
    public boolean crearUsuario(Usuario usuario) {
        boolean exito = false;
        if (controladoraPersistencia.crearUsuario(usuario)) exito = true;
        return exito;
    }
    
    public boolean crearEmpleado(Empleado empleado) {
        boolean exito = false;
        if (controladoraPersistencia.crearEmpleado(empleado)) exito = true;
        return exito;
    }
    
    public boolean crearHuesped(Huesped huesped) {
        boolean exito = false;
        if (controladoraPersistencia.crearHuesped(huesped)) exito = true;
        return exito;
    }
    
    public boolean crearHabitacion(Habitacion habitacion) {
        boolean exito = false;
        if (controladoraPersistencia.crearHabitacion(habitacion)) exito = true;
        return exito;
    }
    
    public boolean crearReserva(Reserva reserva) {
        boolean exito = false;
        if (controladoraPersistencia.crearReserva(reserva)) exito = true;
        return exito;
    }
    
    public boolean crearEmpleadoUsuario(Empleado empleado, Usuario usuario) {
        boolean exito = false;
        if (controladoraPersistencia.crearUsuario(usuario) && 
                controladoraPersistencia.crearEmpleado(empleado)) exito = true;
        return exito;
    }
    
    public boolean esUsuarioValido(String nombreUsuario, String contrasenia) {
       boolean valido = false;
       List<Usuario> usuarios= controladoraPersistencia.obtenerUsuarios();

       for (Usuario usuario : usuarios) {
           if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasenia().equals(contrasenia)) {
               valido = true;
               break;
           }
       }
       return valido;
    }
    
    public boolean existeNombreUsuario(String nombreUsuario) {
        boolean existe = false;
        //List<User> allUsers = new ArrayList<User>();
        List<Usuario> usuarios = controladoraPersistencia.obtenerUsuarios();

        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                existe = true;
                break;
            }
        }
        return existe;
    }
    
    public boolean verificarUsuarioPorID(int id) {
        boolean existe = false;
        Usuario usuario = controladoraPersistencia.obtenerUsuarioPorID(id);
        if (usuario != null) existe = true;
        return existe;
    }
    
    public boolean verificarEmpleadoPorID(int id) {
        boolean existe = false;
        Empleado empleado = controladoraPersistencia.obtenerEmpleadoPorID(id);
        if (empleado != null) existe = true;
        return existe;
    }
    
    public boolean verificarHuespedPorID(int id) {
        boolean existe = false;
        Huesped huesped = controladoraPersistencia.obtenerHuespedPorID(id);
        if (huesped != null) existe = true;
        return existe;
    }
    
    public boolean verificarHabitacionPorID(int id) {
        boolean existe = false;
        Habitacion habitacion = controladoraPersistencia.obtenerHabitacionPorID(id);
        if (habitacion != null) existe = true;
        return existe;
    }
    
    public boolean verificarReservaPorID(int id) {
        boolean existe = false;
        Reserva reserva = controladoraPersistencia.obtenerReservaPorID(id);
        if (reserva != null) existe = true;
        return existe;
    }
    
    public boolean disponibilidadHabitacion(Integer id_habitacion, Date checkin, Date checkout) {
        boolean disponible = true;
        List<Reserva> reservas = controladoraPersistencia.obtenerReservas();
        for (Reserva reserva: reservas) {
            if (id_habitacion.equals(reserva.getHabitacion().getId()) && 
                    (estaOcupadaEntreFechas(checkin, reserva.getCheckIn(), reserva.getCheckOut()) || 
                    estaOcupadaEntreFechas(checkout, reserva.getCheckIn(), reserva.getCheckOut())))
                disponible = false;
        }
        return disponible;
    }
    
    public Usuario obtenerUsuarioPorCredenciales(String nombreUsuario, String contrasenia) {
       Usuario usuario = null;
       List<Usuario> usuarios= controladoraPersistencia.obtenerUsuarios();

       for (Usuario usr : usuarios) {
           if (usr.getNombreUsuario().equals(nombreUsuario) && usr.getContrasenia().equals(contrasenia)) {
               usuario = usr;
               break;
           }
       }
       return usuario;
    }
    
    public Empleado obtenerEmpleadoPorUsuario(Integer id_usuario) {
       Empleado empleado = null;
       List<Empleado> empleados = controladoraPersistencia.obtenerEmpleados();

       for (Empleado empl : empleados) {
           if (empl.getUsuario().getId().equals(id_usuario)) {
               empleado = empl;
               break;
           }
       }
       return empleado;
    }
    
    public Usuario obtenerUsuarioPorID(int id) {
        return controladoraPersistencia.obtenerUsuarioPorID(id);
    }
    
    public Empleado obtenerEmpleadoPorID(int id) {
        return controladoraPersistencia.obtenerEmpleadoPorID(id);
    }
    
    public Huesped obtenerHuespedPorID(int id) {
        return controladoraPersistencia.obtenerHuespedPorID(id);
    }
    
    public Habitacion obtenerHabitacionPorID(int id) {
        return controladoraPersistencia.obtenerHabitacionPorID(id);
    }
    
    public Reserva obtenerReservaPorID(int id) {
        return controladoraPersistencia.obtenerReservaPorID(id);
    }
    
    public List<Usuario> obtenerUsuarios() {
        return controladoraPersistencia.obtenerUsuarios();
    }
    
    public List<Empleado> obtenerEmpleados() {
        return controladoraPersistencia.obtenerEmpleados();
    }
    
    public List<Huesped> obtenerHuespedes() {
        return controladoraPersistencia.obtenerHuespedes();
    }
    
    public List<Habitacion> obtenerHabitaciones() {
        return controladoraPersistencia.obtenerHabitaciones();
    }
    
    public List<Reserva> obtenerReservas() {
        return controladoraPersistencia.obtenerReservas();
    }
    
    public int obtenerCantidadUsuarios() {
        return controladoraPersistencia.obtenerCantidadUsuarios();
    }
    
    public int obtenerCantidadEmpleados() {
        return controladoraPersistencia.obtenerCantidadEmpleados();
    }
    
    public int obtenerCantidadHuespedes() {
        return controladoraPersistencia.obtenerCantidadHuespedes();
    }
    
    public int obtenerCantidadHabitaciones() {
        return controladoraPersistencia.obtenerCantidadHabitaciones();
    }
    
    public int obtenerCantidadReservas() {
        return controladoraPersistencia.obtenerCantidadReservas();
    }
    
    public boolean borrarUsuario(int id) {
        return controladoraPersistencia.borrarUsuario(id);
    }
    
    public boolean borrarEmpleado(int id) {
        return controladoraPersistencia.borrarEmpleado(id);
    }
    
    public boolean borrarHuesped(int id) {
        return controladoraPersistencia.borrarHuesped(id);
    }
    
    public boolean borrarHabitacion(int id) {
        return controladoraPersistencia.borrarHabitacion(id);
    }
    
    public boolean borrarReserva(int id) {
        return controladoraPersistencia.borrarReserva(id);
    }
    
    public boolean modificarUsuario(Usuario usuario) {
        return controladoraPersistencia.modificarUsuario(usuario);
    }
    
    public boolean modificarEmpleado(Empleado empleado) {
        return controladoraPersistencia.modificarEmpleado(empleado);
    }
    
    public boolean modificarHuesped(Huesped huesped) {
        return controladoraPersistencia.modificarHuesped(huesped);
    }
    
    public boolean modificarHabitacion(Habitacion habitacion) {
        return controladoraPersistencia.modificarHabitacion(habitacion);
    }
    
    public boolean modificarReserva(Reserva reserva) {
        return controladoraPersistencia.modificarReserva(reserva);
    }
    
    public static synchronized Date convertirStringADate(String fecha) {
        return Date.from(LocalDate.parse(fecha).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public static String convertirDateAString(Date fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(fecha);
    }
    
     public static String convertirDateAString2(Date fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(fecha);
    }
    
    public static long calcularDiasEntreFechas(Date fecha1, Date fecha2) {
        return Duration.between(fecha1.toInstant().atZone(ZoneId.systemDefault()), fecha2.toInstant().atZone(ZoneId.systemDefault())).toDays();
    }
    
    public static boolean estaOcupadaEntreFechas(Date fecha, Date checkin, Date checkout) {
        return (checkin.compareTo(fecha)*fecha.compareTo(checkout)>0);
    }
    
    public static String obtenerNombreDeNumeroMes(int mes) {

		String nombreMes = null;
		switch (mes) {
		case 0:
			nombreMes = "Enero";
			break;
		case 1:
			nombreMes = "Febrero";
			break;
		case 2:
			nombreMes = "Marzo";
			break;
		case 3:
			nombreMes = "Abril";
			break;
		case 4:
			nombreMes = "Mayo";
			break;
		case 5:
			nombreMes = "Junio";
			break;
		case 6:
			nombreMes = "Julio";
			break;
		case 7:
			nombreMes = "Agosto";
			break;
		case 8:
			nombreMes = "Septiembre";
			break;
		case 9:
			nombreMes = "Octubre";
			break;
		case 10:
			nombreMes = "Noviembre";
			break;
		case 11:
			nombreMes = "Diciembre";
			break;
		}

		return nombreMes;
	}
}
