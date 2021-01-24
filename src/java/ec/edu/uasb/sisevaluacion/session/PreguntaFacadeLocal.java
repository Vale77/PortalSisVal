/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.Pregunta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface PreguntaFacadeLocal {

    void create(Pregunta pregunta);

    void edit(Pregunta pregunta);

    void remove(Pregunta pregunta);

    Pregunta find(Object id);

    List<Pregunta> findAll();

    List<Pregunta> findRange(int[] range);

    int count();


    public java.util.List<ec.edu.uasb.sisevaluacion.entities.Pregunta> findbyEncuesta(int codEncuesta);

    public java.util.List<ec.edu.uasb.sisevaluacion.entities.Pregunta> findPregPadrebyEncuesta(int codEncuesta);

    public java.lang.String removeSafePregunta(ec.edu.uasb.sisevaluacion.entities.Pregunta preg);
   
    
}
