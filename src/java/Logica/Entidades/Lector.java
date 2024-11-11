package Logica.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Lector extends Persona implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @OneToOne
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private IdiomaPaper idioma;
    @Enumerated(EnumType.STRING)
    private GeneroPaper genero;
    @Column(name = "ES_AUTOR")
    private Boolean esAutor;
    
    @OneToMany
    private List<Usuario> misLectores;
    
    @OneToMany
    private List<Paper> misPapers;
    
    public Lector() {
    }

    public Lector(Integer id, Usuario usuario, String nombre, String apellido, Date nacimiento, IdiomaPaper idioma, GeneroPaper genero, Boolean esAutor) {
        super(nombre, apellido, nacimiento);
        this.id = id;
        this.usuario = usuario;
        this.idioma = idioma;
        this.genero = genero;
        this.esAutor = esAutor;
        this.misLectores = new ArrayList<Usuario>();
        this.misPapers = new ArrayList<Paper>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public List<Usuario> getMisLectores() {
        return misLectores;
    }

    public void addMisLectores(Usuario lector) {
        misLectores.add(lector);
    }
    
    public List<Paper> getMisPapers() {
        return misPapers;
    }

    public void addMisPapers(Paper paper) {
        misPapers.add(paper);
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
    
}
