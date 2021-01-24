/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdga.session;

import ec.edu.uasb.portalgesdga.entities.AsignaturaSyllabus;
import ec.edu.uasb.portalgesdga.reportes.bean.SerieSilabo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface AsignaturaSyllabusFacadeLocal {

    void create(AsignaturaSyllabus asignaturaSyllabus);

    void edit(AsignaturaSyllabus asignaturaSyllabus);

    void remove(AsignaturaSyllabus asignaturaSyllabus);

    AsignaturaSyllabus find(Object id);

    List<AsignaturaSyllabus> findAll();

    List<AsignaturaSyllabus> findRange(int[] range);

    int count();

    public List<AsignaturaSyllabus> getAsignatSyllabus(Long anio, Long codArea, Long codProg);

    public List<SerieSilabo> getSyllabusByProg(Long anio, Long codArea, Long codProg);

}
