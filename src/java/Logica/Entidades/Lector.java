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
    private List<Paper> misPapers;
    

    public Lector() {
        misPapers = new ArrayList<>();
    }

    public Lector(Integer id, Usuario usuario, String nombre, String apellido, Date nacimiento, IdiomaPaper idioma, GeneroPaper genero, Boolean esAutor, List<Paper> misPapers) {
        super(nombre, apellido, nacimiento);
        this.id = id;
        this.usuario = usuario;
        this.idioma = idioma;
        this.genero = genero;
        this.esAutor = esAutor;
        this.misPapers = misPapers;
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
    
    public void setMisPapers(List<Paper> misPapers){
        this.misPapers = misPapers;
    }
    
    public List<Paper> getMisPapers(){
        return misPapers;
    }
    
    public void addPaper(Paper paper){
        misPapers.add(paper);
    }
    
    public Boolean siguePaper(Paper paper){
        for(Paper miPaper: misPapers){
            if(paper.getId()==(miPaper.getId())){
                return true;
            }
        }
        return false;
    }
    
}
