/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.session;

import ec.edu.uasb.infoanual.entities.InfanualCalendario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface InfanualCalendarioFacadeLocal {

    void create(InfanualCalendario infanualCalendario);

    void edit(InfanualCalendario infanualCalendario);

    void remove(InfanualCalendario infanualCalendario);

    InfanualCalendario find(Object id);

    List<InfanualCalendario> findAll();

    List<InfanualCalendario> findRange(int[] range);

    int count();

    public void aperturaInfAnual(String[] infanual);

    public void cierreInfAnual(String[] infanual);
    
}
