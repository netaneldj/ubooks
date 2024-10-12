package Logica.Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Habitacion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TematicaHabitacion tematica;
    @Enumerated(EnumType.STRING)
    private TipoHabitacion tipo;
    @Basic
    private Integer piso;
    private Double precio;

    public Habitacion() {
    }

    public Habitacion(Integer id, Integer piso, TematicaHabitacion tematica, TipoHabitacion tipo, Double precio) {
        this.id = id;
        this.piso = piso;
        this.tematica = tematica;
        this.tipo = tipo;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TematicaHabitacion getTematica() {
        return tematica;
    }

    public void setTematica(TematicaHabitacion tematica) {
        this.tematica = tematica;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
}
