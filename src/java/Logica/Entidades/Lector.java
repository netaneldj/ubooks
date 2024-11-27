package Logica.Entidades;

import Logica.Controlador.ControladoraLogica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

@Entity
public class Lector extends Persona implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    private int puntos;
    
    @OneToOne
    private Usuario usuario;
    
    @Enumerated(EnumType.STRING)
    private IdiomaPaper idioma;
    
    @Enumerated(EnumType.STRING)
    private GeneroPaper genero;
    
    @Column(name = "ES_AUTOR")
    private Boolean esAutor;

    // Relación Many-to-Many para representar los usuarios que sigue el lector
    @ManyToMany
    @JoinTable(
        name = "seguidores",
        joinColumns = @JoinColumn(name = "id_lector"),
        inverseJoinColumns = @JoinColumn(name = "id_usuario_seguido")
    )
    private List<Usuario> seguidos;

    @OneToMany
    private List<MiPaper> misPapers;
    
    public Lector() {
        misPapers = new ArrayList<>();
    }

    public Lector(Integer id, Usuario usuario, String nombre, String apellido, Date nacimiento, IdiomaPaper idioma, GeneroPaper genero, Boolean esAutor, List<MiPaper> misPapers) {
        super(nombre, apellido, nacimiento);
        this.id = id;
        this.usuario = usuario;
        this.idioma = idioma;
        this.genero = genero;
        this.esAutor = esAutor;
        this.misPapers = misPapers;
        this.puntos = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public IdiomaPaper getIdioma() {
        return idioma;
    }

    public void setIdioma(IdiomaPaper idioma) {
        this.idioma = idioma;
    }

    public GeneroPaper getGenero() {
        return genero;
    }

    public String getEmail() {
        return this.usuario.getEmail();
    } 
    
    public void setGenero(GeneroPaper genero) {
        this.genero = genero;
    }

    public Boolean getEsAutor() {
        return esAutor;
    }

    public void setEsAutor(Boolean esAutor) {
        this.esAutor = esAutor;
    }
    
    public List<Usuario> getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(List<Usuario> seguidos) {
        this.seguidos = seguidos;
    }
	
    public void setMisPapers(List<MiPaper> misPapers){
        this.misPapers = misPapers;
    }
    
    public List<MiPaper> getMisPapers(){
        return misPapers;
    }
    
    public void addPaper(MiPaper paper){
        misPapers.add(paper);
    }
    
    public Boolean siguePaper(Paper paper){
        for(MiPaper miPaper: misPapers){
            if(paper.getId()==(miPaper.getIdPaper())){
                return true;
            }
        }
        return false;
    }
 
    public Boolean paperLeido(Paper paper){
        for(MiPaper miPaper: misPapers){
            if( (paper.getId().equals(miPaper.getIdPaper())) && (miPaper.getLeido().equals(EnumLeido.LEIDO))){
                return true;
            }
        }
        return false;
    }
    
    public void addPuntos(){
        this.puntos += 100;
    }
    
    public void setPuntos(int puntos){
        this.puntos = puntos;
    }
    
    public int getPuntos(){
        return this.puntos;
    }
    
    
}
