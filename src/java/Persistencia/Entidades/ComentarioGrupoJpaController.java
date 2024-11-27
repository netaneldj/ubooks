/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Entidades;

import Logica.Entidades.ComentarioGrupo;
import Logica.Entidades.Grupo;
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
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

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

    public ComentarioGrupo findComentarioGrupo(Integer id) {
         EntityManager em = getEntityManager();
        try {
            return em.find(ComentarioGrupo.class, id);
        } finally {
            em.close();
        }
    }

    public void edit(ComentarioGrupo comentario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            comentario = em.merge(comentario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comentario.getId();
                if (findComentarioGrupo(id) == null) {
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

    public List<ComentarioGrupo> findComentariosPorFiltro(String filtro, Grupo grupo) {
        EntityManager em = getEntityManager();
    try {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ComentarioGrupo> cq = cb.createQuery(ComentarioGrupo.class);
        Root<ComentarioGrupo> comentario = cq.from(ComentarioGrupo.class);

         
        Expression<String> nombreCompleto = cb.concat(comentario.get("creador").get("nombre"), " ");
        nombreCompleto = cb.concat(nombreCompleto, comentario.get("creador").get("apellido"));
        
       List<ComentarioGrupo> todosLosComentarios = grupo.getComentarios();
       //comentario.in(todosLosComentarios);
        
        cq.select(comentario)
        .where(cb.and(comentario.in(todosLosComentarios), cb.or(cb.like(cb.lower(nombreCompleto), "%" + filtro.toLowerCase() + "%"),
            cb.like(comentario.get("titulo"), "%" + filtro.toLowerCase() + "%"))))
        ;
        
        Query q = em.createQuery(cq);
        return q.getResultList();
    } finally {
        em.close();
    }
    }
}
