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
import ec.edu.uasb.sisevaluacion.session.VProgramaFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "EvalCordAProgAnual")
@ViewScoped
public class EvalCordAProgAnualManagedBean extends BaseJSFManagedBean implements Serializable {

    private String ls_reporte = null;
    private String smciclo = null;
    private String smtipo = null;
    private String smarea = null;
    private String smprograma = null;
    private String paramrep = null;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private List<VPrograma> lprograma = new ArrayList<VPrograma>();
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private VProgramaFacadeLocal programaFacade;

    @ManagedProperty(value = "#{modal}")
    private modalManagedBean modal1;

// <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public String getSmtipo() {
        return smtipo;
    }

    public void setSmtipo(String smtipo) {
        this.smtipo = smtipo;
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

    public List<VPrograma> getLprograma() {
        return lprograma;
    }

    public void setLprograma(List<VPrograma> lprograma) {
        this.lprograma = lprograma;
    }

    public modalManagedBean getModal1() {
        return modal1;
    }

    public void setModal1(modalManagedBean modal1) {
        this.modal1 = modal1;
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
    public EvalCordAProgAnualManagedBean() {
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();

    }

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
            ls_reporte = "EvalDeCoordAlProgramaAnual";
            paramrep = armarparametros();
            modal1.verReporte(tipo, paramrep, ls_reporte);
        }
    }
    // </editor-fold> 
   
    public void ciclovalueChangeListener() {
        if (smciclo != null) {
        }
    }

    public void tipovalueChangeListener() {
        smciclo = null;
        smarea = null;
        smprograma = null;
    }

    public void areavalueChangeListener() {
        smprograma = null;
        lprograma.clear();
        if (smarea != null && !smarea.equalsIgnoreCase("T")) {
            if (smtipo != null && (smtipo.equalsIgnoreCase("P") || smtipo.equalsIgnoreCase("T"))) {
                lprograma = programaFacade.findProgramaAnual(Long.valueOf(smciclo), smarea);
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="ARMAR PARAMETROS">  
    private String armarparametros() {

        paramrep = "&anio=" + smciclo;
        if (smarea == null) {
            smarea = "T";
        }
        if (smprograma == null) {
            smprograma = "T";
        }

        if (!smtipo.isEmpty()) {
            paramrep = paramrep + "&tipReporte=" + smtipo
                    + "&codArea=" + smarea
                    + "&codEsp=" + smprograma;
        }
        return paramrep;
    }
    // </editor-fold> 

}
