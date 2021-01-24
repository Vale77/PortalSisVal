/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.reportes.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.entities.Profesor;
import ec.edu.uasb.sisevaluacion.entities.VPrograma;
import ec.edu.uasb.sisevaluacion.managedBean.modalManagedBean;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import ec.edu.uasb.sisevaluacion.session.ProfesorFacadeLocal;
import ec.edu.uasb.sisevaluacion.session.VProgramaFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "EvalCordADocenteAnual")
@ViewScoped
public class EvalCordADocenteAnualManagedBean extends BaseJSFManagedBean implements Serializable {

    
    private String ls_reporte = null;
    private String smciclo = null;
    private String smtipo = null;
    private String smarea = null;
    private String smprograma = null;
    private String smtrimestre = null;
    private String scodProfesor= null;
    private String paramrep = null;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private List<Profesor> lprofesor = new ArrayList<Profesor>();
    private List<Profesor> lprofesorFiltro;
    private List<VPrograma> lprograma = new ArrayList<VPrograma>();
    private List<String[]> listtrimestre = new ArrayList<String[]>();

    @EJB
    private ProfesorFacadeLocal profesorFacade;
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private VProgramaFacadeLocal programaFacade;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
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

    public List<Profesor> getLprofesorFiltro() {
        return lprofesorFiltro;
    }

    public void setLprofesorFiltro(List<Profesor> lprofesorFiltro) {
        this.lprofesorFiltro = lprofesorFiltro;
    }

    public String getSmarea() {
        return smarea;
    }

    public void setSmarea(String smarea) {
        this.smarea = smarea;
    }

    public String getSmprograma() {
        return smprograma;
    }

    public void setSmprograma(String smprograma) {
        this.smprograma = smprograma;
    }

    public String getSmtrimestre() {
        return smtrimestre;
    }

    public void setSmtrimestre(String smtrimestre) {
        this.smtrimestre = smtrimestre;
    }

    public List<VPrograma> getLprograma() {
        return lprograma;
    }

    public void setLprograma(List<VPrograma> lprograma) {
        this.lprograma = lprograma;
    }

    public List<String[]> getListtrimestre() {
        return listtrimestre;
    }
    
    public void setListtrimestre(List<String[]> listtrimestre) {    
        this.listtrimestre = listtrimestre;
    }

    public modalManagedBean getModal1() {
        return modal1;
    }

    public void setModal1(modalManagedBean modal1) {
        this.modal1 = modal1;
    }

    public List<Profesor> getLprofesor() {
        return lprofesor;
    }

    public void setLprofesor(List<Profesor> lprofesor) {
        this.lprofesor = lprofesor;
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

    // </editor-fold> 
    public EvalCordADocenteAnualManagedBean() {
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();

    }
// <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA DE PROFESOR">  
    private Profesor selectedprofesor = new Profesor();

    public Profesor getSelectedprofesor() {
        return selectedprofesor;
    }

    public void setSelectedprofesor(Profesor selectedprofesor) {
        this.selectedprofesor = selectedprofesor;
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
// <editor-fold defaultstate="collapsed" desc="VER REPORTE">  

    public void verReporte(String tipo) {
        
        if (validar() == 0) {
            ls_reporte = "EvalDeCoordAlDocenteAnual";
            paramrep = armarparametros();    
            modal1.verReporte(tipo, paramrep, ls_reporte);
        }
    }
    // </editor-fold> 

    public void onRowSelect(SelectEvent event) {
        selectedprofesor = (Profesor) event.getObject();
    }

    public void onRowUnselect(UnselectEvent event) {
        selectedprofesor = null;
    }
    
     public void ciclovalueChangeListener() {
        lprofesor.clear();
        lprograma.clear();
        smarea = null;
        smprograma = null;
        smtrimestre = null;
        if (smciclo != null) {
            listtrimestre = consultaFacade.trimestreAnio(Long.valueOf(smciclo));
            if (smtipo != null && smtipo.equalsIgnoreCase("D")) {
                lprofesor = profesorFacade.findProfesorEvaluacionbyTipandAnio("C", smciclo);
            }
        }
    }

    public void tipovalueChangeListener() {
        smciclo = null;
        smarea = null;
        smprograma = null;
        smtrimestre = null;
        lprofesor.clear();
        lprograma.clear();
                
    }

    public void areavalueChangeListener() {
        smprograma = null;
        lprograma.clear();
        if (smarea != null && !smarea.equalsIgnoreCase("T")) {
            if (smtipo!= null && (smtipo.equalsIgnoreCase("P")||smtipo.equalsIgnoreCase("T"))) {
                lprograma = programaFacade.findProgramaAnual(Long.valueOf(smciclo), smarea);
            }
        }
    }

    
// <editor-fold defaultstate="collapsed" desc="ARMAR PARAMETROS">  

    private String armarparametros() {
        scodProfesor = "T"; //TODOS LOS DOCENTES
        paramrep = "&anio=" + smciclo;
        if (smarea == null) {
            smarea = "T";
        }
        if (smprograma == null) {
            smprograma = "T";
        }
        if (smtrimestre == null) {
            smtrimestre = "T";
        }
        if (!smtipo.isEmpty()) {
            paramrep = paramrep + "&tipReporte=" + smtipo;
            if (smtipo.equalsIgnoreCase("D")) {
                scodProfesor = Integer.toString(selectedprofesor.getCodigoProfesor());
            }
            paramrep = paramrep + "&codProfesor=" + scodProfesor
                    + "&codArea=" + smarea
                    + "&codEsp=" + smprograma
                    + "&codTrim=" + smtrimestre;
        }
        return paramrep;
    }
    // </editor-fold> 


}
