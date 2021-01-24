/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.session;

import ec.edu.uasb.infoanual.entities.InfanualRealizado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface InfanualRealizadoFacadeLocal {

    void create(InfanualRealizado infanualRealizado);

    void edit(InfanualRealizado infanualRealizado);

    void remove(InfanualRealizado infanualRealizado);

    InfanualRealizado find(Object id);

    List<InfanualRealizado> findAll();

    List<InfanualRealizado> findRange(int[] range);

    int count();

    public List<InfanualRealizado> findbyAnio(String anio);
    
}
