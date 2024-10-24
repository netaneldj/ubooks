package Logica.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Paper implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String doi;
    private String nombre;
    @OneToOne
    private Lector autor;
    private String resumen;

    public Paper() {
    }

    public Paper(Integer id, String doi, String nombre, Lector autor, String resumen) {
        this.id = id;
        this.doi = doi;
        this.nombre = nombre;
        this.autor = autor;
        this.resumen = resumen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lector getAutor() {
        return autor;
    }

    public void setAutor(Lector autor) {
        this.autor = autor;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
    
}
