package Logica.Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Valoracion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToOne
    private Paper paper; // ID del paper asociado
    @OneToOne
    private Lector lector; // ID del usuario que hace la valoración
    @Column(name = "valoracion_numerica")
    private int valoracionNumerica; // Cantidad de estrellas
    private String comentario; // Comentario adicional

    public Valoracion() {
    }

    public Valoracion(Integer id, Paper paper, Lector lector, int valoracionNumerica, String comentario) {
        this.id = id;
        this.paper = paper;
        this.lector = lector;
        this.valoracionNumerica = valoracionNumerica;
        this.comentario = comentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public int getValoracionNumerica() {
        return valoracionNumerica;
    }

    public void setValoracionNumerica(int valoracionNumerica) {
        this.valoracionNumerica = valoracionNumerica;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
