/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.session;

import ec.edu.uasb.infoanual.entities.InfanualRealizado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class InfanualRealizadoFacade extends AbstractFacade<InfanualRealizado> implements InfanualRealizadoFacadeLocal {
    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InfanualRealizadoFacade() {
        super(InfanualRealizado.class);
    }
     
    @Override
    public List<InfanualRealizado> findbyAnio(String anio) {
        return em.createNativeQuery("Select * "
                + " from  GESTIONACADEMICA.INFANUAL_REALIZADO p "
                + " where P.IAE_ANIO =  '" + anio + "'", InfanualRealizado.class).getResultList();
    }
    
    
}
