package Logica.Entidades;

public enum TipoHabitacion {
    SINGLE(1),
    DOBLE(2),
    TRIPLE(3),
    MULTIPLE(4);
    
    private Integer cantidad;
    
    TipoHabitacion(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }
}
