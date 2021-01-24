/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.VPrograma;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface VProgramaFacadeLocal {

    void create(VPrograma vPrograma);

    void edit(VPrograma vPrograma);

    void remove(VPrograma vPrograma);

    VPrograma find(Object id);

    List<VPrograma> findAll();

    List<VPrograma> findRange(int[] range);

    int count();


    public List<VPrograma> findProgramaAnual(Long anio, String codArea);

    public List<VPrograma> findProgramaAnualByNivAcad(Long anio, String codArea, String coNivAca);
    
}
