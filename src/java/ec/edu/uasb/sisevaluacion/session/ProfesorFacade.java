/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.Profesor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author johana.orozco
 */
@Stateless
public class ProfesorFacade extends AbstractFacade<Profesor> implements ProfesorFacadeLocal {
    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorFacade() {
        super(Profesor.class);
    }
    @Override
    public List<Profesor>findProfesorAnual( Long anio){
        return em.createNativeQuery("select distinct CODIGO_PROFESOR ,APELLIDO_PROFESOR+' '+ NOMBRE_PROFESOR NOMBRE_PROFESOR"
                + " from materia_dictar "
                + " where anio = "+anio
                +" order by 2,1", Profesor.class).getResultList();
           
    }
    
    @Override
     public List<Profesor>findProfesorEvaluacion(){
        return em.createNativeQuery("  SELECT DISTINCT  profesor.codigo_profesor CODIGO_PROFESOR, profesor.apellido_profesor +' '+ profesor.nombre_profesor NOMBRE_PROFESOR"
                + " FROM profesor inner join evaluacion.RESPUESTA  as respuesta    "
                + " on profesor.codigo_profesor = RESPUESTA.codigo_profesor "
                +" order by 2,1", Profesor.class).getResultList();
           
    }
     
    @Override
      public List<Profesor>findProfesorEvaluacionbyTipandAnio(String tipo, String anio){
        return em.createNativeQuery("  SELECT DISTINCT  profesor.codigo_profesor CODIGO_PROFESOR, profesor.apellido_profesor +' '+ profesor.nombre_profesor NOMBRE_PROFESOR"
                + " FROM profesor inner join evaluacion.RESPUESTA  as respuesta    "
                + " on profesor.codigo_profesor = RESPUESTA.codigo_profesor INNER JOIN EVALUACION.ENCUESTA AS ENCUESTA"
                + " ON RESPUESTA.CODIGO_ENCUESTA = ENCUESTA.CODIGO_ENCUESTA"
                + " AND ENCUESTA.TIPO =  '"+tipo+"' "
                + " where respuesta.anio IN ("+anio +")"
                +" order by 2,1", Profesor.class).getResultList();
           
    }

   
}
