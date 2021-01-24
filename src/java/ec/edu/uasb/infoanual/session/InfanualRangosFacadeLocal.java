/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.session;

import ec.edu.uasb.infoanual.entities.InfanualRangos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface InfanualRangosFacadeLocal {

    void create(InfanualRangos infanualRangos);

    void edit(InfanualRangos infanualRangos);

    void remove(InfanualRangos infanualRangos);

    InfanualRangos find(Object id);

    List<InfanualRangos> findAll();

    List<InfanualRangos> findRange(int[] range);

    int count();



    public List<InfanualRangos> getRangobyTipContrato(Integer codTipContrato);
    
}
