package Persistencia.Controlador;

import Logica.Entidades.Empleado;
import Logica.Entidades.Habitacion;
import Logica.Entidades.Huesped;
import Logica.Entidades.Reserva;
import Logica.Entidades.Usuario;
import Persistencia.Entidades.EmpleadoJpaController;
import Persistencia.Entidades.HabitacionJpaController;
import Persistencia.Entidades.HuespedJpaController;
import Persistencia.Entidades.ReservaJpaController;
import Persistencia.Entidades.UsuarioJpaController;
import Persistencia.Entidades.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.*;

public class ControladoraPersistencia {
    
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    EmpleadoJpaController empleadoJpaController = new EmpleadoJpaController();
    HabitacionJpaController habitacionJpaController = new HabitacionJpaController();
    HuespedJpaController huespedJpaController = new HuespedJpaController();
    ReservaJpaController reservaJpaController = new ReservaJpaController();
    UsuarioJpaController usuarioJpaController = new UsuarioJpaController();

    public ControladoraPersistencia() {}
    
    public boolean crearUsuario(Usuario usuario){
        boolean exito = false;
        try {
            usuarioJpaController.create(usuario);
            logger.log(Level.INFO, "ControladoraPersistencia: Usuario creado con exito!");
            exito = true;
        } catch(Exception e){
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear usuario: %s",e.getMessage());
        } 
        return exito;
    }
    
    public boolean crearEmpleado(Empleado empleado){
        boolean exito = false;
        try {
            empleadoJpaController.create(empleado);
            logger.log(Level.INFO, "ControladoraPersistencia: Empleado creado con exito!");
            exito = true;
        } catch(Exception e){
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear empleado: %s",e.getMessage());
        }
        return exito;
    }
    
    public boolean crearHuesped(Huesped huesped){
        boolean exito = false;
        try {
            huespedJpaController.create(huesped);
            logger.log(Level.INFO, "ControladoraPersistencia: Huesped creado con exito!");
            exito = true;
        } catch(Exception e){
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear huesped: %s",e.getMessage());
        }
        return exito;
    }
    
    public boolean crearHabitacion(Habitacion habitacion){
        boolean exito = false;
        try {
            habitacionJpaController.create(habitacion);
            logger.log(Level.INFO, "ControladoraPersistencia: Habitacion creada con exito!");
            exito = true;
        } catch(Exception e){
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear habitacion: %s",e.getMessage());
        }
        return exito;
    }
    
    public boolean crearReserva(Reserva reserva){
        boolean exito = false;
        try {
            reservaJpaController.create(reserva);
            logger.log(Level.INFO, "ControladoraPersistencia: Reserva creada con exito!");
            exito = true;
        } catch(Exception e){
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear reserva: %s",e.getMessage());
        }
        return exito;
    }
    
    public List<Usuario> obtenerUsuarios() {
        return usuarioJpaController.findUsuarioEntities();
    }
    
    public Usuario obtenerUsuarioPorID(int idUsuario) {
        return usuarioJpaController.findUsuario(idUsuario);
    }
    
    public int obtenerCantidadUsuarios() {
        return usuarioJpaController.getUsuarioCount();
    }
    
    public List<Empleado> obtenerEmpleados() {
        return empleadoJpaController.findEmpleadoEntities();
    }
    
    public Empleado obtenerEmpleadoPorID(int idEmpleado) {
        return empleadoJpaController.findEmpleado(idEmpleado);
    }
    
    public int obtenerCantidadEmpleados() {
        return empleadoJpaController.getEmpleadoCount();
    }
    
    public List<Huesped> obtenerHuespedes() {
        return huespedJpaController.findHuespedEntities();
    }
    
    public Huesped obtenerHuespedPorID(int idHuesped) {
        return huespedJpaController.findHuesped(idHuesped);
    }
    
    public int obtenerCantidadHuespedes() {
        return huespedJpaController.getHuespedCount();
    }
    
    public List<Habitacion> obtenerHabitaciones() {
        return habitacionJpaController.findHabitacionEntities();
    }
    
    public Habitacion obtenerHabitacionPorID(int idHabitacion) {
        return habitacionJpaController.findHabitacion(idHabitacion);
    }
    
    public int obtenerCantidadHabitaciones() {
        return habitacionJpaController.getHabitacionCount();
    }
    
    public List<Reserva> obtenerReservas() {
        return reservaJpaController.findReservaEntities();
    }
    
    public Reserva obtenerReservaPorID(int idReserva) {
        return reservaJpaController.findReserva(idReserva);
    }
    
    public int obtenerCantidadReservas() {
        return reservaJpaController.getReservaCount();
    }
    
    public boolean modificarUsuario(Usuario usuario) {
        boolean exito = false;
        try {
            usuarioJpaController.edit(usuario);
            logger.log(Level.INFO, "ControladoraPersistencia: Usuario modificado con exito!");
            exito = true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar usuario: %s",e.getMessage());
        }
        return exito;
    }
    
    public boolean modificarEmpleado(Empleado empleado) {
        boolean exito = false;
        try {
            empleadoJpaController.edit(empleado);
            logger.log(Level.INFO, "ControladoraPersistencia: Empleado modificado con exito!");
            exito = true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar empleado: %s",e.getMessage());
        }
        return exito;
    }
    
    public boolean modificarHuesped(Huesped huesped) {
        boolean exito = false;
        try {
            huespedJpaController.edit(huesped);
            logger.log(Level.INFO, "ControladoraPersistencia: Huesped modificado con exito!");
            exito = true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar huesped: %s",e.getMessage());
        }
        return exito;
    }
    
    public boolean modificarHabitacion(Habitacion habitacion) {
        boolean exito = false;
        try {
            habitacionJpaController.edit(habitacion);
            logger.log(Level.INFO, "ControladoraPersistencia: Habitacion modificada con exito!");
            exito = true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar habitacion: %s",e.getMessage());
        }
        return exito;
    }
    
    public boolean modificarReserva(Reserva reserva) {
        boolean exito = false;        
        try {
            reservaJpaController.edit(reserva);
            logger.log(Level.INFO, "ControladoraPersistencia: Reserva modificada con exito!");
            exito = true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar reserva: %s",e.getMessage());
        }
        return exito;
    }
    
    public boolean borrarUsuario(int id) {
        try {
            usuarioJpaController.destroy(id);
            logger.log(Level.INFO, "ControladoraPersistencia: Usuario %d borrado con exito!", id);
            return true;
        } catch (NonexistentEntityException e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al borrar usuario: %s",e.getMessage());
        }

        return false;
    }
    
    public boolean borrarEmpleado(int id) {
        try {
            empleadoJpaController.destroy(id);
            logger.log(Level.INFO, "ControladoraPersistencia: Empleado borrado con exito!");
            return true;
        } catch (NonexistentEntityException e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al borrar empleado: %s",e.getMessage());
        }

        return false;
    }
    
    public boolean borrarHuesped(int id) {
        try {
            huespedJpaController.destroy(id);
            logger.log(Level.INFO, "ControladoraPersistencia: Huesped borrado con exito!");
            return true;
        } catch (NonexistentEntityException e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al borrar huesped: %s",e.getMessage());
        }

        return false;
    }
    
    public boolean borrarHabitacion(int id) {
        try {
            habitacionJpaController.destroy(id);
            logger.log(Level.INFO, "ControladoraPersistencia: Habitacion borrada con exito!");
            return true;
        } catch (NonexistentEntityException e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al borrar habitacion: %s",e.getMessage());
        }

        return false;
    }
    
    public boolean borrarReserva(int id) {
        try {
            reservaJpaController.destroy(id);
            logger.log(Level.INFO, "ControladoraPersistencia: Reserva borrada con exito!");
            return true;
        } catch (NonexistentEntityException e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al borrar reserva: %s",e.getMessage());
        }

        return false;
    }
}
