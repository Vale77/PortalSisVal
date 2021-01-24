/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface AsignaturaFacadeLocal {

    public java.util.List<ec.edu.uasb.sisevaluacion.entities.Asignatura> findAsignaturaAnual(java.lang.Long anio);

    public java.util.List<ec.edu.uasb.sisevaluacion.entities.Asignatura> findAsignaturaEvaluacion(java.lang.Long anio);
    
}
