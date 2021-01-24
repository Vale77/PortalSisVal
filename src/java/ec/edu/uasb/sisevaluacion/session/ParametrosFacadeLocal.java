/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.Parametros;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface ParametrosFacadeLocal {

    void create(Parametros parametros);

    void edit(Parametros parametros);

    void remove(Parametros parametros);

    Parametros find(Object id);

    List<Parametros> findAll();

    List<Parametros> findRange(int[] range);

    int count();

    public java.util.List<ec.edu.uasb.sisevaluacion.entities.Parametros> findAllActivo(int codEncuesta);
    
}
