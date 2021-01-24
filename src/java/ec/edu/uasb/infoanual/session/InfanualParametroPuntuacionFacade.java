/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.session;

import ec.edu.uasb.infoanual.entities.InfanualParametroPuntuacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class InfanualParametroPuntuacionFacade extends AbstractFacade<InfanualParametroPuntuacion> implements InfanualParametroPuntuacionFacadeLocal {

    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InfanualParametroPuntuacionFacade() {
        super(InfanualParametroPuntuacion.class);
    }
    
}
