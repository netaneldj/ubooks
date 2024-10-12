package Logica.Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reserva implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @OneToOne
    private Huesped huesped;
    @OneToOne
    private Habitacion habitacion;
    @OneToOne
    private Empleado empleado;
    @Temporal(TemporalType.DATE)
    private Date checkIn;
    @Temporal(TemporalType.DATE)
    private Date checkOut;
    @Basic
    private Integer nroHuespedes;
    private Double montoTotal;

    public Reserva() {
    }

    public Reserva(Integer id, Huesped huesped, Habitacion habitacion, Empleado empleado, Date checkIn, Date checkOut, Integer nroHuespedes, Double montoTotal) {
        this.id = id;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.empleado = empleado;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.nroHuespedes = nroHuespedes;
        this.montoTotal = montoTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getNroHuespedes() {
        return nroHuespedes;
    }

    public void setNroHuespedes(Integer nroHuespedes) {
        this.nroHuespedes = nroHuespedes;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

}
