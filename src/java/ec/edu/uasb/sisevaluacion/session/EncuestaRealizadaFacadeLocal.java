/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.EncuestaRealizada;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface EncuestaRealizadaFacadeLocal {

    void create(EncuestaRealizada encuestaRealizada);

    void edit(EncuestaRealizada encuestaRealizada);

    void remove(EncuestaRealizada encuestaRealizada);

    EncuestaRealizada find(Object id);

    List<EncuestaRealizada> findAll();

    List<EncuestaRealizada> findRange(int[] range);

    int count();
    
}
