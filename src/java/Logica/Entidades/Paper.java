package Logica.Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private GeneroPaper genero;
    @Enumerated(EnumType.STRING)
    private IdiomaPaper idioma;
    @Column(name = "PROMEDIO_VALORACION_NUMERICA")
    private double promedioValoracionNumerica;
    @Column(name = "FOTO_PORTADA")
    private int fotoPortada;

    public Paper() {
    }

    public Paper(Integer id, String doi, String nombre, Lector autor, String resumen, GeneroPaper genero, IdiomaPaper idioma, double promedioValoracionNumerica) {
        this.id = id;
        this.doi = doi;
        this.nombre = nombre;
        this.autor = autor;
        this.resumen = resumen;
        this.genero = genero;
        this.idioma = idioma;
        this.promedioValoracionNumerica = promedioValoracionNumerica;
        this.fotoPortada = 0;
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
    
    public GeneroPaper getGenero() {
        return genero;
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

    public void setGenero(GeneroPaper genero){
        this.genero = genero;
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

    public IdiomaPaper getIdioma() {
        return idioma;
    }

    public void setIdioma(IdiomaPaper idioma) {
        this.idioma = idioma;
    }

    public double getPromedioValoracionNumerica() {
        return promedioValoracionNumerica;
    }

    public void setPromedioValoracionNumerica(double promedioValoracionNumerica) {
        this.promedioValoracionNumerica = promedioValoracionNumerica;
    }
    
    public int getFotoPortada(){
        return this.fotoPortada;
    }
    
    public String getRutaImagenPortada() {
        return getFotoPortada() != 0 
            ? "resources/img/paper" + getFotoPortada() + ".jpg" 
            : "https://via.placeholder.com/150?text=Foto";
}

    public void setFotoPortada(int fotoPortada) {
        this.fotoPortada = fotoPortada;
    }
    
    
}
