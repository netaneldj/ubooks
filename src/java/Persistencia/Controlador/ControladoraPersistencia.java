package Persistencia.Controlador;

import Logica.Entidades.ComentarioGrupo;
import Logica.Entidades.ComentarioRespuesta;
import Logica.Entidades.GeneroPaper;
import Logica.Entidades.Grupo;
import Logica.Entidades.Lector;
import Logica.Entidades.MiPaper;
import Logica.Entidades.Paper;
import Logica.Entidades.Usuario;
import Logica.Entidades.Valoracion;
import Persistencia.Entidades.ComentarioGrupoJpaController;
import Persistencia.Entidades.ComentarioRespuestaJpaController;
import Persistencia.Entidades.LectorJpaController;
import Persistencia.Entidades.PaperJpaController;
import Persistencia.Entidades.GrupoJpaController;
import Persistencia.Entidades.MiPaperJpaController;
import Persistencia.Entidades.UsuarioJpaController;
import Persistencia.Entidades.ValoracionJpaController;
import Persistencia.Entidades.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.*;

public class ControladoraPersistencia {
    
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    LectorJpaController lectorJpaController = new LectorJpaController();
    MiPaperJpaController miPaperJpaController = new MiPaperJpaController();

    UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
    PaperJpaController paperJpaController = new PaperJpaController();
    ValoracionJpaController valoracionJpaController = new ValoracionJpaController();
    GrupoJpaController grupoJpaController = new GrupoJpaController();
    ComentarioGrupoJpaController comentarioGrupoJpaController = new ComentarioGrupoJpaController();
    ComentarioRespuestaJpaController comentarioRespuestaJpaController = new ComentarioRespuestaJpaController();

    public ControladoraPersistencia() {}
    
    public boolean crearUsuario(Usuario usuario){
        boolean exito = false;
        try {
            usuarioJpaController.create(usuario);
            logger.log(Level.INFO, "ControladoraPersistencia: Usuario creado con exito!");
            exito = true;
        } catch(Exception e){
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear usuario: {0}", e.getMessage());
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
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear lector: {0}", e.getMessage());
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
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear paper: {0}", e.getMessage());
        }
        return exito;
    }
    
    public boolean crearGrupo(Grupo grupo){
        boolean exito = false;
        try {
            grupoJpaController.create(grupo);
            logger.log(Level.INFO, "ControladoraPersistencia: Grupo creado con exito!");
            exito = true;
        } catch(Exception e){
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear lector: %s",e.getMessage());
        }
        return exito;
    }
    
    public boolean actualizarBiografia(Lector lector, String nuevaBiografia) {
        boolean exito = false;
        
        try {
            lector.setBiografia(nuevaBiografia);
            lectorJpaController.edit(lector);
            logger.log(Level.INFO, "ControladoraPersistencia: Actualización de la biografía exitosa!");
            
            exito = true;
        } catch(Exception e){
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al actualizar la biografía: %s",e.getMessage());
        }
        
        return exito;
    }
    
     public boolean crearValoracion(Paper paper, Lector lector, int calificacion, String comentario){
        boolean exito = false;
        try {
            Valoracion valoracion = new Valoracion();
            valoracion.setPaper(paper);
            valoracion.setLector(lector);
            valoracion.setValoracionNumerica(calificacion);
            valoracion.setComentario(comentario);
            valoracionJpaController.create(valoracion);
            logger.log(Level.INFO, "ControladoraPersistencia: Valoracion creada con exito!");
            exito = true;
        } catch(Exception e){
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear valoracion: %s",e.getMessage());
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
    
    public List<Grupo> obtenerGrupos(){
        return grupoJpaController.findGrupoEntities();
    }
    
    public List<Grupo> obtenerGruposPorIdLector(Integer id) {
       Lector miembro = obtenerLectorPorID(id);
       return grupoJpaController.findGrupoByLector(miembro);
    }
    
    public List<Lector> obtenerAutores() {
        return lectorJpaController.findAutorEntities();
    }
   
    public Lector obtenerLectorPorID(int idLector) {
        return lectorJpaController.findLector(idLector);
    }
    
    public Lector obtenerLectorPorIdUsuario(int idUsuario) {
        return lectorJpaController.findLectorByIdUsuario(idUsuario);
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
    
    public List<Paper> obtenerPapers(String nombrePaper, String nombreAutor, GeneroPaper genero) {
        return paperJpaController.findPapers(nombrePaper, nombreAutor, genero);
    }
    
    public List<Lector> obtenerLectoresPorNombre(String nombreLector, String esAutor) {
        return lectorJpaController.findLectoresByName(nombreLector, esAutor);
    }
    
    public List<Grupo> obtenerGruposPorNombre(String nombreGrupo){
        return grupoJpaController.findGruposByNombre(nombreGrupo);
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
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar usuario: {0}", e.getMessage());
        }
        return exito;
    }
    
    
    
    public boolean modificarMiPaper(List<MiPaper> misPapers) {
        boolean exito = false;
        for (MiPaper p : misPapers){
            try {
                miPaperJpaController.edit(p);
                logger.log(Level.INFO, "ControladoraPersistencia: MiPaper modificado con exito!");
                exito = true;
            } catch (Exception e) {
                logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar MiPaper: {0}", e.getMessage());
            }
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
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar lector: {0}", e.getMessage());
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
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar paper: {0}", e.getMessage());
        }
        return exito;
    }
    
    public boolean modificarComentarioGrupo(ComentarioGrupo comentario) {
        boolean exito = false;
        try {
            comentarioGrupoJpaController.edit(comentario);
            logger.log(Level.INFO, "ControladoraPersistencia: comentario modificado con exito!");
            exito = true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar comentario: {0}", e.getMessage());
        }
        return exito;
    }
    
    
    public boolean modificarGrupo(Grupo grupo) {
        boolean exito = false;
        try {
            grupoJpaController.edit(grupo);
            logger.log(Level.INFO, "ControladoraPersistencia: Paper modificado con exito!");
            exito = true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al modificar paper: {0}", e.getMessage());
        }
        return exito;
    }
    
    public boolean borrarUsuario(int id) {
        try {
            usuarioJpaController.destroy(id);
            logger.log(Level.INFO, "ControladoraPersistencia: Usuario {0} borrado con exito!", id);
            return true;
        } catch (NonexistentEntityException e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al borrar usuario: {0}", e.getMessage());
        }
        return false;
    }
    
    public boolean borrarLector(int id) {
        try {
            lectorJpaController.destroy(id);
            logger.log(Level.INFO, "ControladoraPersistencia: Lector borrado con exito!");
            return true;
        } catch (NonexistentEntityException e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al borrar lector: {0}", e.getMessage());
        }
        return false;
    }
    
    public boolean borrarPaper(int id) {
        try {
            paperJpaController.destroy(id);
            logger.log(Level.INFO, "ControladoraPersistencia: Paper borrado con exito!");
            return true;
        } catch (NonexistentEntityException e) {
            logger.log(Level.SEVERE, "ControladoraPersistencia: Error al borrar paper: {0}", e.getMessage());
        }
        return false;
    }

    public List<Valoracion> obtenerValoracionesPorPaper(int idPaper) {
        return valoracionJpaController.findValoracionesByPaperId(idPaper);
    }

    public Usuario obtenerUsuarioPorIdLector(int idLector) {
        return usuarioJpaController.findUsuarioByLectorId(idLector);
    }

    public Grupo obtenerGrupoPorId(Integer id) {
        return grupoJpaController.findGrupo(id);
    }

    public boolean crearComentarioGrupo(ComentarioGrupo comentario) {
		boolean exito = false;
		try {
			comentarioGrupoJpaController.create(comentario);
			logger.log(Level.INFO, "ControladoraPersistencia: Grupo creado con exito!");
			exito = true;
		} catch(Exception e){
			logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear lector: %s",e.getMessage());
		}
		return exito;
    }

    public boolean crearComentarioRespuesta(ComentarioRespuesta respuesta) {
        boolean exito = false;
		try {
			comentarioRespuestaJpaController.create(respuesta);
			logger.log(Level.INFO, "ControladoraPersistencia: respuesta creado con exito!");
			exito = true;
		} catch(Exception e){
			logger.log(Level.SEVERE, "ControladoraPersistencia: Error al crear respuesta: %s",e.getMessage());
		}
		return exito;
    }
    
    public ComentarioGrupo obtenerComentarioGrupoPorId(Integer id) {
        return comentarioGrupoJpaController.findComentarioGrupo(id);
    }

    public List<ComentarioGrupo> obtenerCometariosPorFiltro(String filtro, Grupo grupo) {
        return comentarioGrupoJpaController.findComentariosPorFiltro(filtro, grupo);
    }




}
