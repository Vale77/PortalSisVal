/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.publicacionDGA.session;

import ec.edu.uasb.publicacionDGA.entities.GeacPublicacionDga;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class GeacPublicacionDgaFacade extends AbstractFacade<GeacPublicacionDga> implements GeacPublicacionDgaFacadeLocal {

    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GeacPublicacionDgaFacade() {
        super(GeacPublicacionDga.class);
    }

    @Override
    public List<GeacPublicacionDga> findAllbyPersona(String tipPersona, String cedPersona) {
        Query q = em.createQuery("Select pub "
                + " FROM  GeacPublicacionDga pub "
                + " WHERE  pub.tipoPersona=:tipPersona "
                + "AND pub.cedulaPersona =:cedPersona  "
                + "ORDER BY pub.pubApellidoAutor");
        q.setParameter("tipPersona", tipPersona);
        q.setParameter("cedPersona", cedPersona);
        return q.getResultList();
    }

    @Override
    public List<GeacPublicacionDga> findAllOrde() {
        Query q = em.createQuery("Select pub "
                + " FROM  GeacPublicacionDga pub  "
                + "ORDER BY pub.pubApellidoAutor");
        return q.getResultList();
    }

}
