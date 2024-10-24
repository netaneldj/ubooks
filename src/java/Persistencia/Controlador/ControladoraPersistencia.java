package Persistencia.Controlador;

import Logica.Entidades.Lector;
import Logica.Entidades.Paper;
import Logica.Entidades.Usuario;
import Persistencia.Entidades.LectorJpaController;
import Persistencia.Entidades.PaperJpaController;
import Persistencia.Entidades.UsuarioJpaController;
import Persistencia.Entidades.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.*;

public class ControladoraPersistencia {
    
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    LectorJpaController lectorJpaController = new LectorJpaController();
    UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
    PaperJpaController paperJpaController = new PaperJpaController();

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
    
    public boolean crearLector(Lector lector){
        boolean exito = false;
        try {
            lectorJpaController.create(lector);
            logger.log(Level.INFO, "ControladoraPersistencia: Lector creado con exito!");
            exito = true;
        } catch(Exception e){
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear lector: %s",e.getMessage());
        }
        return exito;
    }
    
    public boolean crearPaper(Paper paper){
        boolean exito = false;
        try {
            paperJpaController.create(paper);
            logger.log(Level.INFO, "ControladoraPersistencia: Paper creado con exito!");
            exito = true;
        } catch(Exception e){
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear lector: %s",e.getMessage());
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
    
    public List<Lector> obtenerLectores() {
        return lectorJpaController.findLectorEntities();
    }
    
    public List<Lector> obtenerAutores() {
        return lectorJpaController.findAutorEntities();
    }
    
    public Lector obtenerLectorPorID(int idLector) {
        return lectorJpaController.findLector(idLector);
    }
    
    public int obtenerCantidadLectores() {
        return lectorJpaController.getLectorCount();
    }
    
     public List<Paper> obtenerPapers() {
        return paperJpaController.findPaperEntities();
    }
    
    public Paper obtenerPaperPorID(int idPaper) {
        return paperJpaController.findPaper(idPaper);
    }
    
    public List<Paper> obtenerPapersPorNombre(String nombrePaper) {
        return paperJpaController.findPapersByName(nombrePaper);
    }
    
    public int obtenerCantidadPapers() {
        return paperJpaController.getPaperCount();
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
    
    public boolean modificarLector(Lector lector) {
        boolean exito = false;
        try {
            lectorJpaController.edit(lector);
            logger.log(Level.INFO, "ControladoraPersistencia: Lector modificado con exito!");
            exito = true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar lector: %s",e.getMessage());
        }
        return exito;
    }
    
    public boolean modificarPaper(Paper paper) {
        boolean exito = false;
        try {
            paperJpaController.edit(paper);
            logger.log(Level.INFO, "ControladoraPersistencia: Paper modificado con exito!");
            exito = true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar lector: %s",e.getMessage());
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
    
    public boolean borrarLector(int id) {
        try {
            lectorJpaController.destroy(id);
            logger.log(Level.INFO, "ControladoraPersistencia: Lector borrado con exito!");
            return true;
        } catch (NonexistentEntityException e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al borrar lector: %s",e.getMessage());
        }

        return false;
    }
    
    public boolean borrarPaper(int id) {
        try {
            paperJpaController.destroy(id);
            logger.log(Level.INFO, "ControladoraPersistencia: Paper borrado con exito!");
            return true;
        } catch (NonexistentEntityException e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al borrar lector: %s",e.getMessage());
        }

        return false;
    }
}
