/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Entidades;

import Logica.Entidades.ComentarioRespuesta;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author bruno
 */
public class ComentarioRespuestaJpaController implements Serializable {
        public ComentarioRespuestaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ComentarioRespuestaJpaController(){
        emf = Persistence.createEntityManagerFactory("UbooksPU");
    }
    
    private EntityManagerFactory emf = null;
     
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    public void create(ComentarioRespuesta respuesta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(respuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
}
