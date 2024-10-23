package Logica.Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Lector extends Persona implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @OneToOne
    private Usuario usuario;
    @ElementCollection(targetClass=IdiomaPaper.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="lector_idioma")
    @Column(name="idioma")
    private List<IdiomaPaper> idiomas;
    @ElementCollection(targetClass=GeneroPaper.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="lector_genero")
    @Column(name="genero")
    private List<GeneroPaper> generos;

    public Lector() {
    }

    public Lector(Integer id, Usuario usuario, String nombre, String apellido, Date nacimiento, List<IdiomaPaper> idiomas, List<GeneroPaper> generos) {
        super(nombre, apellido, nacimiento);
        this.id = id;
        this.usuario = usuario;
        this.idiomas = idiomas;
        this.generos = generos;
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

    
}
