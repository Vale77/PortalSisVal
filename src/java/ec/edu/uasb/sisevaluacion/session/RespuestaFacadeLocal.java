/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.Respuesta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface RespuestaFacadeLocal {

    void create(Respuesta respuesta);

    void edit(Respuesta respuesta);

    void remove(Respuesta respuesta);

    Respuesta find(Object id);

    List<Respuesta> findAll();

    List<Respuesta> findRange(int[] range);

    int count();


    
}
