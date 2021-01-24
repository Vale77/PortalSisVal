/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.EncuestaConvocatoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class EncuestaConvocatoriaFacade extends AbstractFacade<EncuestaConvocatoria> implements EncuestaConvocatoriaFacadeLocal {
    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuestaConvocatoriaFacade() {
        super(EncuestaConvocatoria.class);
    }
       
    @Override
     public List<EncuestaConvocatoria> findAllActivo(int codEncuesta) {
        return em.createNativeQuery("Select * "
                + " from  EVALUACION.ENCUESTA_CONVOCATORIA p "
                + " where  p.CODIGO_ENCUESTA =  " + codEncuesta , EncuestaConvocatoria.class).getResultList();
    }
}
