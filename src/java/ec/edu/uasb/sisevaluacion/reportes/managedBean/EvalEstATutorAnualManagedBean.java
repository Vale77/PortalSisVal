/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.reportes.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.entities.Profesor;
import ec.edu.uasb.sisevaluacion.managedBean.modalManagedBean;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import ec.edu.uasb.sisevaluacion.session.ProfesorFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "EvalEstATutorAnual")
@ViewScoped
public class EvalEstATutorAnualManagedBean extends BaseJSFManagedBean implements Serializable {

    private String ls_reporte = null;
    private String paramrep = null;
    private String smciclo = null;
    private String smtipo = null;
    private String sarea = null;
    private String scodProfesor = null;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private List<Profesor> lprofesor = new ArrayList<Profesor>();
    private List<Profesor> lprofesorFiltro;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private ProfesorFacadeLocal profesorFacade;
    @ManagedProperty(value = "#{modal}")
    private modalManagedBean modal1;

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public String getSmtipo() {
        return smtipo;
    }

    public void setSmtipo(String smtipo) {
        this.smtipo = smtipo;
    }

    public String getScodProfesor() {
        return scodProfesor;
    }

    public void setScodProfesor(String scodProfesor) {
        this.scodProfesor = scodProfesor;
    }

    public modalManagedBean getModal1() {
        return modal1;
    }

    public void setModal1(modalManagedBean modal1) {
        this.modal1 = modal1;
    }

    public String getSarea() {
        return sarea;
    }

    public void setSarea(String sarea) {
        this.sarea = sarea;
    }

    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
    }

    public String getLs_reporte() {
        return ls_reporte;
    }

    public void setLs_reporte(String ls_reporte) {
        this.ls_reporte = ls_reporte;
    }

    public String getParamrep() {
        return paramrep;
    }

    public void setParamrep(String paramrep) {
        this.paramrep = paramrep;
    }

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    public List<Profesor> getLprofesor() {
        return lprofesor;
    }

    public void setLprofesor(List<Profesor> lprofesor) {
        this.lprofesor = lprofesor;
    }

    public List<Profesor> getLprofesorFiltro() {
        return lprofesorFiltro;
    }

    public void setLprofesorFiltro(List<Profesor> lprofesorFiltro) {
        this.lprofesorFiltro = lprofesorFiltro;
    }

    // </editor-fold> 
    public EvalEstATutorAnualManagedBean() {
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();

    }

// <editor-fold defaultstate="collapsed" desc="VER REPORTE">  
    public void verReporte(String tipo) {
        if (validar() == 0) {
            ls_reporte = "EvalDeEstudAlTutorAnual";
            paramrep = armarparametros();
            modal1.verReporte(tipo, paramrep, ls_reporte);
        }
    }
// </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="VALIDAR">  

    public int validar() {
        int resp = 0;
        if (smtipo == null) {
            resp = -1;
        }
        if (smciclo == null) {
            resp = -1;
        }
        return resp;
    }

    // </editor-fold> 

// <editor-fold defaultstate="collapsed" desc="ARMAR PARAMETROS">  
    private String armarparametros() {
        scodProfesor = "T"; //TODOS LOS DOCENTES
        paramrep = "&anio=" + smciclo;
        if (sarea == null) {
            sarea = "T";
        }
        
        if (!smtipo.isEmpty()) {
            paramrep = paramrep + "&tipReporte=" + smtipo;
            if (smtipo.equalsIgnoreCase("D")) {
                scodProfesor = Integer.toString(selectedprofesor.getCodigoProfesor());
            }
            paramrep = paramrep + "&codProfesor=" + scodProfesor
                    + "&codArea=" + sarea;
        }
        return paramrep;
    }
    // </editor-fold>  
// <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA DE PROFESOR">  
    private Profesor selectedprofesor = new Profesor();

    public Profesor getSelectedprofesor() {
        return selectedprofesor;
    }

    public void setSelectedprofesor(Profesor selectedprofesor) {
        this.selectedprofesor = selectedprofesor;
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="CAMBIO DE TIPO DE REPORTE">  
    public void tipovalueChangeListener() {
        smciclo = null;
        sarea = null;

    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="CAMBIO DE CICLO">  
    public void ciclovalueChangeListener() {
        Vector v = new Vector();
        String sciclo = null;
        sciclo = "";
        StringBuilder sql = new StringBuilder();
        lprofesor.clear();
        if (smciclo != null) {
            if (smtipo != null && smtipo.equalsIgnoreCase("D")) {
                sql.append("select DISTINCT VEVAL.ANIO , VEVAL.ANIO_EVAL"
                        + " from interfaseOcu.EVALUACION.VISTA_TAB_EVALTESIS_ANIO VEVAL"
                        + " WHERE VEVAL.ANIO_EVAL = ").append(smciclo).append("");
                v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
                if (v.size() > 0) {
                    for (int i = 0; i < v.size(); i++) {
                        Object[] object = (Object[]) v.get(i);
                        String[] asign;
                        asign = new String[1];
                        asign[0] = (object[0] == null ? null : object[0].toString());
                        sciclo = sciclo + asign[0] + ',';
                    }
                    sciclo = sciclo.substring(0, sciclo.length() - 1);
                    lprofesor = profesorFacade.findProfesorEvaluacionbyTipandAnio("T", sciclo);
                }
            }
        }
    }
    // </editor-fold> 

    public void areavalueChangeListener() {
        if (sarea != null && !sarea.equalsIgnoreCase("T")) {
        }
    }

}
