/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 *
 * @author bruno
 */
@MappedSuperclass
abstract class ComentarioAbstracto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Integer id;
    protected String comentario;
    @OneToOne
    protected Lector creador;
    
    public Integer getId() {
       return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getComentario(){
        return comentario;
    }
    
    public void setComentario(String comentario){
        this.comentario=comentario;
    }
    
    public Lector getCreador(){
        return creador;
    }
    
    public void setCreador(Lector creador){
        this.creador = creador;
    }
    
}
