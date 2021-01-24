/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.session;

import ec.edu.uasb.infoanual.entities.InfanualParametros;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface InfanualParametrosFacadeLocal {

    void create(InfanualParametros infanualParametros);

    void edit(InfanualParametros infanualParametros);

    void remove(InfanualParametros infanualParametros);

    InfanualParametros find(Object id);

    List<InfanualParametros> findAll();

    List<InfanualParametros> findRange(int[] range);

    int count();
    
}
