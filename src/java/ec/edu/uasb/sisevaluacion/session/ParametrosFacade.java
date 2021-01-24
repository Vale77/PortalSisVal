/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.Parametros;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class ParametrosFacade extends AbstractFacade<Parametros> implements ParametrosFacadeLocal {
    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametrosFacade() {
        super(Parametros.class);
    }
    
    @Override
     public List<Parametros> findAllActivo(int codEncuesta) {
        return em.createNativeQuery("Select * "
                + " from  EVALUACION.PARAMETROS p "
                + " where  p.CODIGO_ENCUESTA =  " + codEncuesta , Parametros.class).getResultList();
    }
    
}
