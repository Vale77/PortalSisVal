/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.session;

import ec.edu.uasb.infoanual.entities.InfanualConvrecordatorio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface InfanualConvrecordatorioFacadeLocal {

    void create(InfanualConvrecordatorio infanualConvrecordatorio);

    void edit(InfanualConvrecordatorio infanualConvrecordatorio);

    void remove(InfanualConvrecordatorio infanualConvrecordatorio);

    InfanualConvrecordatorio find(Object id);

    List<InfanualConvrecordatorio> findAll();

    List<InfanualConvrecordatorio> findRange(int[] range);

    int count();

    public List<InfanualConvrecordatorio> findbyTipContrato(char tipContrato);
    
}
