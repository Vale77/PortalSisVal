/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.session;


import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface CicloAcademicoFacadeLocal {

    void create(CicloAcademico cicloAcademico);

    void edit(CicloAcademico cicloAcademico);

    void remove(CicloAcademico cicloAcademico);

    CicloAcademico find(Object id);

    List<CicloAcademico> findAll();

    List<CicloAcademico> findRange(int[] range);

    int count();

    public java.util.List<ec.edu.uasb.sisevaluacion.entities.CicloAcademico> findAllActivos();

    public java.util.List<ec.edu.uasb.sisevaluacion.entities.CicloAcademico> findByEstado(java.lang.String estado);
    
    
}
