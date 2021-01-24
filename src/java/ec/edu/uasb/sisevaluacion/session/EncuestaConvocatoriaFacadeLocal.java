/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.EncuestaConvocatoria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface EncuestaConvocatoriaFacadeLocal {

    void create(EncuestaConvocatoria encuestaConvocatoria);

    void edit(EncuestaConvocatoria encuestaConvocatoria);

    void remove(EncuestaConvocatoria encuestaConvocatoria);

    EncuestaConvocatoria find(Object id);

    List<EncuestaConvocatoria> findAll();

    List<EncuestaConvocatoria> findRange(int[] range);

    int count();

    public java.util.List<ec.edu.uasb.sisevaluacion.entities.EncuestaConvocatoria> findAllActivo(int codEncuesta);
    
}
