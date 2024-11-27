package Logica.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class MiPaper implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @OneToOne
    private Paper paper;
    @Enumerated(EnumType.STRING)
    private EnumLeido leido;
    
    public MiPaper() {
    }

    public MiPaper(Integer id, EnumLeido leido, Paper paper) {
        this.id = id;
        this.leido = leido;
        this.paper = paper;
    }

    public Integer getId() { //Este te da el id del MiPaper
        return id;
    }
    
    public Integer getIdPaper() { // Este te da el id del Paper
        return this.paper.getId();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public EnumLeido getLeido() {
        return leido;
    }

    public void setLeido(EnumLeido leido) {
        this.leido = leido;
    }
    
    public String getNombre(){
        return this.paper.getNombre();
    }
    
    public Lector getAutor(){
        return this.paper.getAutor();
    }
    
}
