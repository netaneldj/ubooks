/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Entidades;

import Logica.Entidades.Lector;
import Logica.Entidades.MiPaper;
import Persistencia.Entidades.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

/**
 *
 * @author netan
 */
public class MiPaperJpaController implements Serializable {

    public MiPaperJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public MiPaperJpaController(){
        emf = Persistence.createEntityManagerFactory("UbooksPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MiPaper miPaper) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(miPaper);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MiPaper miPaper) throws NonexistentEntityException, Exception {
        EntityManager em = null;
            try {
                em = getEntityManager();
                em.getTransaction().begin();
                miPaper = em.merge(miPaper);
                em.getTransaction().commit();
            } catch (Exception ex) {
                String msg = ex.getLocalizedMessage();
                if (msg == null || msg.length() == 0) {
                    Integer id = miPaper.getId();
                    if (findMiPaper(id) == null) {
                        throw new NonexistentEntityException("The MiPaper with id " + id + " no longer exists.");
                    }
                }
                throw ex;
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MiPaper miPaper;
            try {
                miPaper = em.getReference(MiPaper.class, id);
                miPaper.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The MiPaper with id " + id + " no longer exists.", enfe);
            }
            em.remove(miPaper);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    

    

    public MiPaper findMiPaper(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MiPaper.class, id);
        } finally {
            em.close();
        }
    }
    
   
}
