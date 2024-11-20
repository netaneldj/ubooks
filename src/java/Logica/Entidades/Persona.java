package Logica.Entidades;

import java.util.Date;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Persona {
    
    private String nombre;
    private String apellido;
    @Temporal(TemporalType.DATE)
    private Date nacimiento;
    private String biografia;

    public Persona() {
    }
    
    public String getBiografia() {
        return this.biografia;
    }
    
    public String setBiografia(String nuevaBiografia) {
        return this.biografia = nuevaBiografia;
    }

    public Persona(String nombre, String apellido, Date nacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacimiento = nacimiento;
        this.biografia = "";
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
    
    
}
