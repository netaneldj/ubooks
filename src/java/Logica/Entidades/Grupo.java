/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Grupo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nombre;
    private List<Integer> miembros;
    private List<Integer> papers;
    private List<Integer> comentarios;
    
    public Grupo (){
    }
    
    public Grupo(Integer id, String nombre, List<Integer> miembros, List<Integer> papers, List<Integer> comentarios){
        this.id = id;
        this.nombre = nombre;
        this.miembros= miembros;
        this.papers = papers;
        this.comentarios = comentarios;   
    }
    
    public Integer getId() {
       return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public List<Integer> getMiembros(){
        return miembros;
    }
    
    public void setMiembros(List<Integer> miembros){
        this.miembros = miembros;
    }
    
    public void addMiembro(Integer miembro){
        miembros.add(miembro);
    }
    
    public List<Integer> getPapers(){
        return papers;
    }
    
    public void setPapers(List<Integer> papers){
        this.papers = papers;
    }
    
    public void addPaper(Integer paper){
        papers.add(paper);
    }
    
    public List<Integer> getComentarios(){
        return comentarios;
    }
    
    public void setComnetarios(List<Integer> comentarios){
        this.comentarios = comentarios;
    }
    
    public void addComentario(Integer comentario){
        comentarios.add(comentario);
    }
}
