package Logica.Entidades;

import Logica.Controlador.ControladoraLogica;
import java.io.Serializable;
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

    public Lector() {
    }

    public Lector(Integer id, Usuario usuario, String nombre, String apellido, Date nacimiento, IdiomaPaper idioma, GeneroPaper genero, Boolean esAutor) {
        super(nombre, apellido, nacimiento);
        this.id = id;
        this.usuario = usuario;
        this.idioma = idioma;
        this.genero = genero;
        this.esAutor = esAutor;
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
    
    public Boolean siguePerfil(int id) throws Exception {
        ControladoraLogica controladora = new ControladoraLogica();
        Usuario usuario_ = controladora.obtenerUsuarioPorIdLector(id);
        
        if (usuario_ == null) {
            throw new Exception("El usuario solicitado no existe.");
        } else {
            for (Usuario seguido : this.seguidos) {
                if (Objects.equals(seguido.getId(), usuario_.getId())) {
                    return true;
                }
            }                  
            return false;
        }
    }
    
    public List<Usuario> getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(List<Usuario> seguidos) {
        this.seguidos = seguidos;
    }
}
