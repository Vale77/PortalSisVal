/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.Encuesta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface EncuestaFacadeLocal {

    void create(Encuesta encuesta);

    void edit(Encuesta encuesta);

    void remove(Encuesta encuesta);

    Encuesta find(Object id);

    List<Encuesta> findAll();

    List<Encuesta> findRange(int[] range);

    int count();

    public java.util.List<ec.edu.uasb.sisevaluacion.entities.Encuesta> findAllActivo(char tipEncuesta);

    public java.lang.String removeSafeEncuesta(ec.edu.uasb.sisevaluacion.entities.Encuesta enc);

    public List<Encuesta> findAll(char tipEncuesta);

    


    
}
