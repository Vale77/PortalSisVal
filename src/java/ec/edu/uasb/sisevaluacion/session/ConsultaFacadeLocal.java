/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.publicacionDGA.entities.Persona;
import ec.edu.uasb.sisevaluacion.entities.Profesor;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface ConsultaFacadeLocal {

    public java.util.Vector ejecutaSqlList(java.lang.String sql);

    public java.util.List<java.lang.String[]> trimestreAnio(java.lang.Long anio);

    public java.util.List<java.lang.String[]> area();

    public List<Profesor> findProfesorInfAnualbySql(String sql);

    public BigDecimal findConAntProfesorbySql(String codProfesor);

    public List<Profesor> findProfesorInfAnualAct(String anio);

    public List<Profesor> findProfesorInfAnualCEbySql(String sql);

    public List<String[]> cargDedicacion(String smtipcontrato);

    public List<Persona> findProfesorbyTipCont(String codTipContrato, String codDedicacion);

    public List<Persona> findEstudiante();

    public List<Persona> findProfesor();

    public String findNomPersona(String cedulaPersona);

    public String getPathDoc();

    public String getSeparadorPath();
}
