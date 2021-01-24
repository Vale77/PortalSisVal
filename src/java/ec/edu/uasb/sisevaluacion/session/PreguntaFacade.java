/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.Pregunta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;
import javax.persistence.Query;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class PreguntaFacade extends AbstractFacade<Pregunta> implements PreguntaFacadeLocal {

    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreguntaFacade() {
        super(Pregunta.class);
    }

    @Override
    public List<Pregunta> findbyEncuesta(int codEncuesta) {
        return em.createNativeQuery("Select * "
                + " from  EVALUACION.PREGUNTA p "
                + " where p.CODIGO_ENCUESTA = " + codEncuesta
                + " order by p.formato", ec.edu.uasb.sisevaluacion.entities.Pregunta.class).getResultList();
    }

    @Override
    public List<Pregunta> findPregPadrebyEncuesta(int codEncuesta) {
        return em.createNativeQuery("Select * "
                + " from  EVALUACION.PREGUNTA p "
                + " where p.pregunta_padre is null and "
                + " p.CODIGO_ENCUESTA = " + codEncuesta
                + " order by p.formato", ec.edu.uasb.sisevaluacion.entities.Pregunta.class).getResultList();
    }

    @Override
    public String removeSafePregunta(Pregunta preg) {
        String ls_resultado = null;

        int li_numrespuesta = 0, li_numhijos = 0;
        /*VERIFICO SI LA PREGUNTA TIENE HIJOS*/
        Query emnumhijos = em.createNamedQuery("Pregunta.countByCodigoPregunta");
        emnumhijos.setParameter("codigoEncuesta", preg.getPreguntaPK().getCodigoEncuesta());
        emnumhijos.setParameter("codigoPregunta", preg.getPreguntaPK().getCodigoPregunta());
        li_numhijos = Integer.valueOf(emnumhijos.getSingleResult().toString());
        //EXTRAIGO EL NUMERO DE RESPUESTAS INGRESADAS PARA ESTA ENCUESTA
        Query emnumrespuesta = em.createNamedQuery("Respuesta.countByCodEncuPreg");
        emnumrespuesta.setParameter("codigoEncuesta", preg.getPreguntaPK().getCodigoEncuesta());
        emnumrespuesta.setParameter("codigoPregunta", preg.getPreguntaPK().getCodigoPregunta());
        li_numrespuesta = Integer.valueOf(emnumrespuesta.getSingleResult().toString());
        if (li_numhijos == 0) {
            if (li_numrespuesta == 0) {
                remove(preg);
            } else {
                ls_resultado = "No se puede borrar la Pregunta/ Existen Respuestas Asociadas";
            }
        } else {
            ls_resultado = "No se puede borrar la Pregunta/ Existen Preguntas Asociadas";
        }
  
        return ls_resultado;
    }
}
