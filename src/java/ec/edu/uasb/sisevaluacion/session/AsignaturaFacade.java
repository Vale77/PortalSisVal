/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.Asignatura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class AsignaturaFacade extends AbstractFacade<Asignatura>implements AsignaturaFacadeLocal {

    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignaturaFacade() {
        super(Asignatura.class);
    }
    
    @Override
     public List<Asignatura>findAsignaturaAnual( Long anio){
        return em.createNativeQuery("select distinct CODIGO_MATERIA as CODIGO_ASIGNATURA ,NOMBRE_MATERIA as NOMBRE_ASIGNATURA"
                + " from materia_dictar "
                + " where anio = "+ anio
                + " order by 2,1", Asignatura.class).getResultList();
           
    }
    
     
    @Override
     public List<Asignatura>findAsignaturaEvaluacion(Long anio){
        return em.createNativeQuery("  SELECT DISTINCT  materia_dictar.CODIGO_MATERIA as CODIGO_ASIGNATURA, NOMBRE_MATERIA as NOMBRE_ASIGNATURA"
                + " FROM materia_dictar inner join evaluacion.RESPUESTA  as respuesta    "
                + " on materia_dictar.codigo_materia = RESPUESTA.codigo_materia "
                +"  and materia_dictar.anio = RESPUESTA.anio"
                +"  where materia_dictar.anio = "+ anio
                +" order by 2,1", Asignatura.class).getResultList();
           
    }

}
