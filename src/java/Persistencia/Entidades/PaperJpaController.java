/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Entidades;

import Logica.Entidades.Paper;
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
public class PaperJpaController implements Serializable {

    public PaperJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public PaperJpaController(){
        emf = Persistence.createEntityManagerFactory("UbooksPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paper paper) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(paper);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paper paper) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            paper = em.merge(paper);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paper.getId();
                if (findPaper(id) == null) {
                    throw new NonexistentEntityException("The paper with id " + id + " no longer exists.");
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
            Paper paper;
            try {
                paper = em.getReference(Paper.class, id);
                paper.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paper with id " + id + " no longer exists.", enfe);
            }
            em.remove(paper);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paper> findPaperEntities() {
        return findPaperEntities(true, -1, -1);
    }

    public List<Paper> findPaperEntities(int maxResults, int firstResult) {
        return findPaperEntities(false, maxResults, firstResult);
    }

    private List<Paper> findPaperEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paper.class));
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

    public Paper findPaper(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paper.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Paper> findPapersByName(String nombre) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Paper> cq = cb.createQuery(Paper.class);
            Root<Paper> paper = cq.from(Paper.class);
            cq.select(paper).where(cb.like(cb.lower(paper.get("nombre")), "%" + nombre.toLowerCase() + "%"));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }


    public int getPaperCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paper> rt = cq.from(Paper.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
