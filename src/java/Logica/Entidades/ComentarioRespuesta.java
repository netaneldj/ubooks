/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Entidades;


import javax.persistence.Entity;

/**
 *
 * @author bruno
 */
@Entity
public class ComentarioRespuesta extends ComentarioAbstracto{
    public ComentarioRespuesta (){
    }
    
    public ComentarioRespuesta(Integer id, String comentario, Lector creador){
        this.id = id;
        this.comentario=comentario;
        this.creador = creador;
    }
    
}
