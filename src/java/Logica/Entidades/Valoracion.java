package Logica.Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Valoracion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Basic
    private Integer idPaper; // ID del paper asociado
    @Basic
    private Integer idUsuario; // ID del usuario que hace la valoración
    @Basic
    private int valoracionNumerica; // Cantidad de estrellas
    @Basic
    private String comentario; // Comentario adicional

    public Valoracion() {
    }

    public Valoracion(Integer id, Integer idPaper, Integer idUsuario, int valoracionNumerica, String comentario) {
        this.id = id;
        this.idPaper = idPaper;
        this.idUsuario = idUsuario;
        this.valoracionNumerica = valoracionNumerica;
        this.comentario = comentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(Integer idPaper) {
        this.idPaper = idPaper;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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
