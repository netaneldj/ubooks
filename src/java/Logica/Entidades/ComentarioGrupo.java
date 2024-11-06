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
public class ComentarioGrupo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String comentario;
    @OneToOne
    private Lector creador;
    @OneToMany
    private List<ComentarioGrupo> respuestas;
    
    public ComentarioGrupo (){
        respuestas = new ArrayList<>();
    }
    
    public ComentarioGrupo(Integer id, String comentario, Lector creador, List<ComentarioGrupo> respuestas){
        this.id = id;
        this.comentario=comentario;
        this.creador = creador;
        this.respuestas = respuestas;
    }
     
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
    
    public List<ComentarioGrupo> getRespuestas(){
        return respuestas;
    }
    
    public void setRespuestas(List<ComentarioGrupo> respuestas){
        this.respuestas = respuestas;
    }
    
    public void addRespuesta(ComentarioGrupo respuesta){
        respuestas.add(respuesta);
    }
}
