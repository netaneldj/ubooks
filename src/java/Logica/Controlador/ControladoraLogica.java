package Logica.Controlador;

import Logica.Entidades.ComentarioGrupo;
import Logica.Entidades.ComentarioRespuesta;
import Logica.Entidades.GeneroPaper;
import Logica.Entidades.Grupo;
import Logica.Entidades.IdiomaPaper;
import Logica.Entidades.Lector;
import Logica.Entidades.MiPaper;
import Logica.Entidades.Paper;
import Logica.Entidades.Usuario;
import Logica.Entidades.Valoracion;
import Persistencia.Controlador.ControladoraPersistencia;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControladoraLogica {
    
    ControladoraPersistencia controladoraPersistencia;
    
    public ControladoraLogica(){
        controladoraPersistencia = new ControladoraPersistencia();
    }
      
    public boolean insertarValoracion(Paper paper, Lector lector, int calificacion, String comentario) {
        boolean exito = controladoraPersistencia.crearValoracion(paper, lector, calificacion, comentario);
        return exito;
    }
    
    public boolean actualizarBiografia(Lector lector, String nuevaBiografia) {
        boolean exito = controladoraPersistencia.actualizarBiografia(lector, nuevaBiografia);
        return exito;
    } 
    
    public boolean crearUsuario(Usuario usuario) {
        boolean exito = false;
        if (controladoraPersistencia.crearUsuario(usuario)) exito = true;
        return exito;
    }
    
    public boolean crearLector(Lector lector) {
        boolean exito = false;
        if (controladoraPersistencia.crearLector(lector)) exito = true;
        return exito;
    }
    
    public boolean crearLectorUsuario(Lector lector, Usuario usuario) {
        boolean exito = false;
        if (controladoraPersistencia.crearUsuario(usuario) && 
                controladoraPersistencia.crearLector(lector)) exito = true;
        return exito;
    }
    
    public boolean crearPaper(Paper paper) {
        boolean exito = false;
        if (controladoraPersistencia.crearPaper(paper)) exito = true;
        return exito;
    }
    
     public boolean crearGrupo(Grupo grupo) {
        boolean exito = false;
        if (controladoraPersistencia.crearGrupo(grupo)) exito = true;
        return exito;
    }
    
    public boolean crearComentarioRespuesta(ComentarioRespuesta respuesta) {
        boolean exito = false;
        if (controladoraPersistencia.crearComentarioRespuesta(respuesta)) exito = true;
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
    
    public boolean verificarLectorPorID(int id) {
        boolean existe = false;
        Lector lector = controladoraPersistencia.obtenerLectorPorID(id);
        if (lector != null) existe = true;
        return existe;
    }
    
    public boolean verificarPaperPorId(int id) {
        boolean existe = false;
        Paper paper = controladoraPersistencia.obtenerPaperPorID(id);
        if (paper != null) existe = true;
        return existe;
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
    
    public Lector obtenerLectorPorUsuario(Integer id_usuario) {
       Lector lector = null;
       List<Lector> lectores = controladoraPersistencia.obtenerLectores();

       for (Lector empl : lectores) {
           if (empl.getUsuario().getId().equals(id_usuario)) {
               lector = empl;
               break;
           }
       }
       return lector;
    }
    
    public Usuario obtenerUsuarioPorID(int id) {
        return controladoraPersistencia.obtenerUsuarioPorID(id);
    }
    
    public Lector obtenerLectorPorID(int id) {
        return controladoraPersistencia.obtenerLectorPorID(id);
    }
    
    public Paper obtenerPaperPorID(int id) {
        return controladoraPersistencia.obtenerPaperPorID(id);
    }
    
    public List<Paper> obtenerPapers(String nombre, String nombreAutor, GeneroPaper genero) {
        return controladoraPersistencia.obtenerPapers(nombre, nombreAutor, genero);
    }

    public List<Lector> obtenerLectoresPorNombre(String nombre, String esAutor) {
        return controladoraPersistencia.obtenerLectoresPorNombre(nombre, esAutor);
    }
    
    public List<Grupo> obtenerGruposPorNombre(String nombreGrupo) {
        return controladoraPersistencia.obtenerGruposPorNombre(nombreGrupo);
    }
    
    public Lector obtenerLectorPorIdUsuario(int idUsuario) {
        return controladoraPersistencia.obtenerLectorPorIdUsuario(idUsuario);
    }
    
    public Usuario obtenerUsuarioPorIdLector(int idLector) {
        return controladoraPersistencia.obtenerUsuarioPorIdLector(idLector);
    }
    
    public List<Usuario> obtenerUsuarios() {
        return controladoraPersistencia.obtenerUsuarios();
    }
    
    public List<Lector> obtenerLectores() {
        return controladoraPersistencia.obtenerLectores();
    }
    
    public List<Lector> obtenerAutores() {
        return controladoraPersistencia.obtenerAutores();
    }
    
    public List<Paper> obtenerPapers() {
        return controladoraPersistencia.obtenerPapers();
    }
    
    public List<Paper> obtenerPapersPorIdiomaOrdenadosPorCalificacion(IdiomaPaper idioma) {
        return controladoraPersistencia.obtenerPapers().stream()
                .filter(paper -> paper.getIdioma().toString().equals(idioma.toString()))
                .sorted(Comparator.comparing(Paper::getPromedioValoracionNumerica).reversed())
                .collect(Collectors.toList());
    }
    
    public List<Paper> obtenerPapersPorGenero(GeneroPaper genero, IdiomaPaper idioma){
        return controladoraPersistencia.obtenerPapers().stream()
                .filter(paper -> paper.getGenero().toString().equals(genero.toString()) && paper.getIdioma().toString().equals(idioma.toString()))
                .sorted(Comparator.comparing(Paper::getPromedioValoracionNumerica).reversed())
                .collect(Collectors.toList());
    }
    
    public List<Paper> obtenerPapersNuevos(IdiomaPaper idioma){
        return controladoraPersistencia.obtenerPapers().stream()
                .filter(paper -> paper.getIdioma().toString().equals(idioma.toString()))
                .sorted(Comparator.comparing(Paper::getId).reversed())
                .collect(Collectors.toList());
    }
    
    public List<Grupo> obtenerGrupos(){
        return controladoraPersistencia.obtenerGrupos();
    }
    
    public List<Grupo>obtenerGruposPorIdLector(Integer id){
        return controladoraPersistencia.obtenerGruposPorIdLector(id);
    }
    
    public Grupo obtenerGrupoPorId(Integer id){
        return controladoraPersistencia.obtenerGrupoPorId(id);
    }
    
    public int obtenerCantidadUsuarios() {
        return controladoraPersistencia.obtenerCantidadUsuarios();
    }
    
    public int obtenerCantidadLectores() {
        return controladoraPersistencia.obtenerCantidadLectores();
    }
    
    public int obtenerCantidadPapers() {
        return controladoraPersistencia.obtenerCantidadPapers();
    }
    
    public boolean borrarUsuario(int id) {
        return controladoraPersistencia.borrarUsuario(id);
    }
    
    public boolean borrarLector(int id) {
        return controladoraPersistencia.borrarLector(id);
    }
    
    public boolean borrarPaper(int id) {
        return controladoraPersistencia.borrarPaper(id);
    }
    
    public boolean modificarUsuario(Usuario usuario) {
        return controladoraPersistencia.modificarUsuario(usuario);
    }
    
    public boolean modificarLector(Lector lector) {
        return controladoraPersistencia.modificarLector(lector);
    }
    
    
    public boolean modificarMiPaper(List<MiPaper> misPapers) {
        return controladoraPersistencia.modificarMiPaper(misPapers);
    }

            
            
    public boolean modificarPaper(Paper paper) {
        return controladoraPersistencia.modificarPaper(paper);
    }
    
    public boolean modificarGrupo(Grupo grupo) {
        return controladoraPersistencia.modificarGrupo(grupo); 
    }
    
    public boolean modificarComentarioGrupo(ComentarioGrupo comentario) {
        return controladoraPersistencia.modificarComentarioGrupo(comentario); 
    }
    
    public  List<ComentarioGrupo> obtenerCometariosPorFiltro(String filtro, Grupo grupo) {
        return controladoraPersistencia.obtenerCometariosPorFiltro(filtro,grupo);
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
    
    // Método agregado para obtener valoraciones de un Paper específico
    public List<Valoracion> obtenerValoracionesPorPaper(int idPaper) {
        return controladoraPersistencia.obtenerValoracionesPorPaper(idPaper);
    }
    
    public double obtenerPromedioValoracionPaper(int idPaper) {
        int sumaValoraciones = 0;
        List<Valoracion> valoraciones = controladoraPersistencia.obtenerValoracionesPorPaper(idPaper);
        
        if (valoraciones == null || valoraciones.isEmpty()) {
            return sumaValoraciones;
        }
        
        for (Valoracion valoracion : valoraciones) {
            sumaValoraciones += valoracion.getValoracionNumerica();
        }
        
        return (double) sumaValoraciones/valoraciones.size();
    }
    
    public boolean actualizarPromedioValoracionPaper(int idPaper) {
        Paper paper = controladoraPersistencia.obtenerPaperPorID(idPaper);
        paper.setPromedioValoracionNumerica(obtenerPromedioValoracionPaper(idPaper));
        return controladoraPersistencia.modificarPaper(paper);
    }

    public boolean crearComentarioGrupo(ComentarioGrupo comentario) {
        boolean exito = false;
        if (controladoraPersistencia.crearComentarioGrupo(comentario)) exito = true;
        return exito;
    }
    
    public boolean lectorSiguePefil(int id_lector, int id_lector_seguido) {
        Lector lector = this.obtenerLectorPorID(id_lector);
        Usuario usuario_seguido = this.obtenerUsuarioPorIdLector(id_lector_seguido);
        if (lector == null || usuario_seguido == null) {
            return false;
        }
        for (Usuario seguido : lector.getSeguidos()) {
            if (Objects.equals(seguido.getId(), usuario_seguido.getId())) {
                return true;
            }
        }                  
        return false;
    }
    
    public ComentarioGrupo obtenerComentarioGrupoPorId (Integer id){
        return controladoraPersistencia.obtenerComentarioGrupoPorId(id);
    }






}
