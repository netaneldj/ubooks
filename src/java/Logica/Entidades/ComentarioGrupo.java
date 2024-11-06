/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author bruno
 */
@Entity
public class ComentarioGrupo extends ComentarioAbstracto {
    @OneToMany
    private List<ComentarioRespuesta> respuestas;
    
    public ComentarioGrupo (){
        respuestas = new ArrayList<>();
    }
    
    public ComentarioGrupo(Integer id, String comentario, Lector creador, List<ComentarioRespuesta> respuestas){
        this.id = id;
        this.comentario=comentario;
        this.creador = creador;
        this.respuestas = respuestas;
    }
         
    public List<ComentarioRespuesta> getRespuestas(){
        return respuestas;
    }
    
    public void setRespuestas(List<ComentarioRespuesta> respuestas){
        this.respuestas = respuestas;
    }
    
    public void addRespuesta(ComentarioRespuesta respuesta){
        respuestas.add(respuesta);
    }
}
