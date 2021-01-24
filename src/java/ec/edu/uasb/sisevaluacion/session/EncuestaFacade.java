/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.Encuesta;
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
public class EncuestaFacade extends AbstractFacade<Encuesta> implements EncuestaFacadeLocal {

    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuestaFacade() {
        super(Encuesta.class);
    }

    @Override
     public List<Encuesta> findAll(char tipEncuesta) {
        return em.createNativeQuery("Select CODIGO_ENCUESTA, TITULO, REFERENCIA, DESCRIPCION, USO, ESTADO, TIPO "
                + " from  EVALUACION.encuesta p "
                + " where p.TIPO =  '" + tipEncuesta + "'", Encuesta.class).getResultList();
    }
    @Override
    public List<Encuesta> findAllActivo(char tipEncuesta) {
        return em.createNativeQuery("Select CODIGO_ENCUESTA, TITULO, REFERENCIA, DESCRIPCION, USO, ESTADO, TIPO "
                + " from  EVALUACION.encuesta p "
                + " where p.ESTADO = 'A'"
                + " AND p.TIPO =  '" + tipEncuesta + "'", Encuesta.class).getResultList();
    }

    
    public String removeSafeEncuesta(Encuesta enc) {
        String ls_resultado = null;

        int li_numrespuesta = 0, li_numpregunta = 0, li_numcalendarizacion = 0, li_numencrealizada = 0;
        //EXTRAIGO EL NUMERO DE RESPUESTAS INGRESADAS PARA ESTA ENCUESTA
        Query emnumrespuesta = em.createNamedQuery("Respuesta.countByCodigoEncuesta");
        emnumrespuesta.setParameter("codigoEncuesta", enc.getCodigoEncuesta());
        //Verifico si tengo encuestas realizadas
        Query emnumencrealizada = em.createNamedQuery("EncuestaRealizada.countByCodigoEncuesta");
        emnumencrealizada.setParameter("codigoEncuesta", enc.getCodigoEncuesta());
        //Verifico si la encuesta fue calendarizada
        Query emnumcalendarizacion = em.createNamedQuery("EncuestaCalendario.countByCodigoEncuesta");
        emnumcalendarizacion.setParameter("codigoEncuesta", enc.getCodigoEncuesta());
        //Verifico si le encuesta tiene preguntas
        Query emnumpregunta = em.createNamedQuery("Pregunta.countByCodigoEncuesta");
        emnumpregunta.setParameter("codigoEncuesta", enc.getCodigoEncuesta());
        li_numrespuesta = Integer.valueOf(emnumrespuesta.getSingleResult().toString());
        li_numcalendarizacion = Integer.valueOf(emnumcalendarizacion.getSingleResult().toString());
        li_numpregunta = Integer.valueOf(emnumpregunta.getSingleResult().toString());
        li_numencrealizada = Integer.valueOf(emnumencrealizada.getSingleResult().toString());

        if (li_numencrealizada == 0) {
            if (li_numrespuesta == 0) {
                if (li_numcalendarizacion == 0) {
                    if (li_numpregunta == 0) {
                        remove(enc);
                    } else {
                        ls_resultado = "No se puede borrar la encuesta/ Existen Preguntas Ingresadas";
                    }
                } else {
                    ls_resultado = "No se puede borrar la encuesta/ Existen Encuestas Calendarizadas";
                }
            } else {
                ls_resultado = "No se puede borrar la encuesta/ Existen Encuestas Realizadas";
            }
        } else {
            ls_resultado = "No se puede borrar la encuesta/ Existen Encuestas Realizadas";
        }

        return ls_resultado;
    }
}
