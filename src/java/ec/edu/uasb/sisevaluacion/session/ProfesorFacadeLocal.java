/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.Profesor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface ProfesorFacadeLocal {

    void create(Profesor profesor);

    void edit(Profesor profesor);

    void remove(Profesor profesor);

    Profesor find(Object id);

    List<Profesor> findAll();

    List<Profesor> findRange(int[] range);

    int count();

    public java.util.List<ec.edu.uasb.sisevaluacion.entities.Profesor> findProfesorAnual(java.lang.Long anio);

    public java.util.List<ec.edu.uasb.sisevaluacion.entities.Profesor> findProfesorEvaluacion();

    public List<Profesor> findProfesorEvaluacionbyTipandAnio(String tipo, String anio);




    
}
