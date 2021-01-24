/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.reportes.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
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
@ManagedBean(name = "EvalEstAProgramaAnual")
@ViewScoped
public class EvalEstAProgramaAnualManagedBean extends BaseJSFManagedBean implements Serializable {

    private String ls_reporte = null;
    private String paramrep = null;
    private String smciclo = null;
    private String smtipo = null;
    private String smprograma = null;
    private String sarea = null;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private List<VPrograma> lprograma = new ArrayList<VPrograma>();
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private VProgramaFacadeLocal programaFacade;
    @ManagedProperty(value = "#{modal}")
    private modalManagedBean modal1;

    public List<VPrograma> getLprograma() {
        return lprograma;
    }

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public void setLprograma(List<VPrograma> lprograma) {
        this.lprograma = lprograma;
    }

    public String getSmtipo() {
        return smtipo;
    }

    public void setSmtipo(String smtipo) {
        this.smtipo = smtipo;
    }

    public String getSmprograma() {
        return smprograma;
    }

    public void setSmprograma(String smprograma) {
        this.smprograma = smprograma;
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

    // </editor-fold> 
    public EvalEstAProgramaAnualManagedBean() {
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();

    }

// <editor-fold defaultstate="collapsed" desc="VER REPORTE">  
    public void verReporte(String tipo) {
        if (validar() == 0) {
            ls_reporte = "EvalDeEstudAlProgramaAnual";
            paramrep = armarparametros();
            modal1.verReporte(tipo, paramrep, ls_reporte);
        }
    }
// </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="VALIDAR">  

    public int validar() {
        int resp = 0;
        if (smciclo == null) {
            resp = -1;
        }
        return resp;
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="ARMAR PARAMETROS">  

    private String armarparametros() {
      
        paramrep = "&anio=" + smciclo;
        if (sarea == null) {
            sarea = "T";
        }
        if (smprograma == null) {
            smprograma = "T";
        }        
        if (!smtipo.isEmpty()) {
            paramrep = paramrep + "&tipReporte=" + smtipo
                    + "&codArea=" + sarea
                    + "&codEsp=" + smprograma;
        }
        return paramrep;
    }
    // </editor-fold> 
    public void tipovalueChangeListener() {
        smciclo = null;
        sarea = null;
        smprograma = null;
    }

    public void areavalueChangeListener() {
        smprograma = null;
        lprograma.clear();
        if (sarea != null && !sarea.equalsIgnoreCase("T")) {
            if (smtipo != null && smtipo.equalsIgnoreCase("P")) {
                lprograma = programaFacade.findProgramaAnual(Long.valueOf(smciclo), sarea);
            }
        }
    }

}
