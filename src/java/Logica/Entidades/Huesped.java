package Logica.Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Huesped extends Persona implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Basic
    private String profesion;

    public Huesped() {
    }

    public Huesped(Integer id, String profesion, Integer dni, String nombre, String apellido, Date nacimiento, String direccion) {
        super(dni, nombre, apellido, nacimiento, direccion);
        this.id = id;
        this.profesion = profesion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    
}
