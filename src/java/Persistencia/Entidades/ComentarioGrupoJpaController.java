/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Entidades;

import Logica.Entidades.ComentarioGrupo;
import Logica.Entidades.Grupo;
import Persistencia.Entidades.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author bruno
 */
public class ComentarioGrupoJpaController implements Serializable{
    
    public ComentarioGrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ComentarioGrupoJpaController(){
        emf = Persistence.createEntityManagerFactory("UbooksPU");
    }
    
    private EntityManagerFactory emf = null;
     
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    public void create(ComentarioGrupo comentario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(comentario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
