/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.session;

import ec.edu.uasb.infoanual.entities.InfanualRangos;
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
public class InfanualRangosFacade extends AbstractFacade<InfanualRangos> implements InfanualRangosFacadeLocal {

    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InfanualRangosFacade() {
        super(InfanualRangos.class);
    }
     

    @Override
    public List<InfanualRangos> getRangobyTipContrato(Integer codTipContrato) {
        Query q = em.createQuery("SELECT rango FROM InfanualRangos rango WHERE rango.iarTipoContrato =:tipconCodigo "                
                + "ORDER BY rango.iarTipoDocente");
        q.setParameter("tipconCodigo", codTipContrato);
        return q.getResultList();
    }
    
}
