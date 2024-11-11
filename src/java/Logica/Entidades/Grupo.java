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

@Entity
public class Grupo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nombre;
    private String tema;
    @OneToMany
    private List<Lector> miembros;
    @OneToMany
    private List<Paper> papers;
    @OneToMany
    private List<ComentarioGrupo> comentarios;
    
    public Grupo (){
        miembros = new ArrayList<>();
        papers = new ArrayList<>();
        comentarios = new ArrayList<>();
    }
    
    public Grupo(Integer id, String nombre, String tema, List<Lector> miembros, List<Paper> papers, List<ComentarioGrupo> comentarios){
        this.id = id;
        this.nombre = nombre;
        this.tema = tema;
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
    
    public String getTema(){
        return tema;
    }
    
    public void setTema(String tema){
        this.tema = tema;
    }
    
    public List<Lector> getMiembros(){
        return miembros;
    }
    
    public void setMiembros(List<Lector> miembros){
        this.miembros = miembros;
    }
    
    public void addMiembro(Lector miembro){
        miembros.add(miembro);
    }
    
    public List<Paper> getPapers(){
        return papers;
    }
    
    public void setPapers(List<Paper> papers){
        this.papers = papers;
    }
    
    public void addPaper(Paper paper){
        papers.add(paper);
    }
    
    public List<ComentarioGrupo> getComentarios(){
        return comentarios;
    }
    
    public void setComnetarios(List<ComentarioGrupo> comentarios){
        this.comentarios = comentarios;
    }
    
    public void addComentario(ComentarioGrupo comentario){
        comentarios.add(comentario);
    }
    
    public Integer getCantidadMiembros(){
        return miembros.size();
    }
    
    public Integer getCantidadComentarios(){
        return comentarios.size();
    }
    
    public boolean esMiembro(Lector lector){
        for(Lector miembro: miembros){
            if(miembro.getId() == lector.getId()){
                return true;
            }
        }
        return false;
    }
}
