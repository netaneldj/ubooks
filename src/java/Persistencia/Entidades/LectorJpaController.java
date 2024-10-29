/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Entidades;

import Logica.Entidades.Lector;
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
import javax.persistence.criteria.Root;

/**
 *
 * @author netan
 */
public class LectorJpaController implements Serializable {

    public LectorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public LectorJpaController(){
        emf = Persistence.createEntityManagerFactory("UbooksPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lector lector) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(lector);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lector lector) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            lector = em.merge(lector);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lector.getId();
                if (findLector(id) == null) {
                    throw new NonexistentEntityException("The lector with id " + id + " no longer exists.");
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
            Lector lector;
            try {
                lector = em.getReference(Lector.class, id);
                lector.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lector with id " + id + " no longer exists.", enfe);
            }
            em.remove(lector);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lector> findLectorEntities() {
        return findLectorEntities(true, -1, -1);
    }

    public List<Lector> findLectorEntities(int maxResults, int firstResult) {
        return findLectorEntities(false, maxResults, firstResult);
    }

    private List<Lector> findLectorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lector.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    /*public List<Lector> findLectoresByName(String nombre) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Lector> cq = cb.createQuery(Lector.class);
            Root<Lector> lector = cq.from(Lector.class);
            cq.select(lector).where(cb.like(cb.lower(lector.get("nombre")), "%" + nombre.toLowerCase() + "%"));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }*/
    
    public List<Lector> findLectoresByName(String nombre, String esAutor) {
    EntityManager em = getEntityManager();
    try {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lector> cq = cb.createQuery(Lector.class);
        Root<Lector> lector = cq.from(Lector.class);
        
        System.out.println("ES AUTOR " + esAutor);
        
        
        
        if( esAutor.equals("No") ){
            cq.select(lector).where(cb.like(cb.lower(lector.get("nombre")), "%" + nombre.toLowerCase() + "%"));    
        }else{
            System.out.println("EN EL ELSE");

            //cq.select(lector).where(cb.and(cb.like(cb.lower(lector.get("nombre")), "%" + nombre.toLowerCase() + "%")), cb.and(lector.get("es_autor"), 1) );
            cq.select(lector)
            .where(cb.and(
                cb.like(cb.lower(lector.get("nombre")), "%" + nombre.toLowerCase() + "%"),
                cb.equal(lector.get("es_autor"), 1)
            ));
        }
        
        Query q = em.createQuery(cq);
        return q.getResultList();
    } finally {
        em.close();
    }
}
    

    public Lector findLector(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lector.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Lector> findAutorEntities() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Lector> cq = cb.createQuery(Lector.class);
            Root<Lector> lector = cq.from(Lector.class);
            cq.select(lector).where(cb.equal(lector.get("esAutor"), true));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getLectorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lector> rt = cq.from(Lector.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
