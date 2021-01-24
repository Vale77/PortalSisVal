/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.publicacionDGA.session;

import ec.edu.uasb.publicacionDGA.entities.PrinCiudad;
import ec.edu.uasb.publicacionDGA.entities.PrinPais;
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
public class PrinCiudadFacade extends AbstractFacade<PrinCiudad> implements PrinCiudadFacadeLocal {

    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrinCiudadFacade() {
        super(PrinCiudad.class);
    }
    @Override
     public List<PrinCiudad> finbyPais(PrinPais pais) {
        Query q = em.createQuery("Select ciu "
                + " FROM  PrinCiudad ciu "
                + " WHERE  ciu.prinPais =:pais  " );
        q.setParameter("pais", pais);
        return q.getResultList();
    }
}
