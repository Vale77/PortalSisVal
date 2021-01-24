/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.session;

import ec.edu.uasb.infoanual.entities.InfanualParametroPuntuacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface InfanualParametroPuntuacionFacadeLocal {

    void create(InfanualParametroPuntuacion infanualParametroPuntuacion);

    void edit(InfanualParametroPuntuacion infanualParametroPuntuacion);

    void remove(InfanualParametroPuntuacion infanualParametroPuntuacion);

    InfanualParametroPuntuacion find(Object id);

    List<InfanualParametroPuntuacion> findAll();

    List<InfanualParametroPuntuacion> findRange(int[] range);

    int count();
    
}
