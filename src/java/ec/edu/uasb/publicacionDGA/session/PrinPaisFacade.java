/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.publicacionDGA.session;

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
public class PrinPaisFacade extends AbstractFacade<PrinPais> implements PrinPaisFacadeLocal {

    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrinPaisFacade() {
        super(PrinPais.class);
    }
    @Override
   public List<PrinPais> findAllOrdenado() {
        Query q = em.createQuery("Select pai "
                + " FROM  PrinPais pai "
                + " ORDER BY PAI.paiNombre " );        
        return q.getResultList();
    } 
}
