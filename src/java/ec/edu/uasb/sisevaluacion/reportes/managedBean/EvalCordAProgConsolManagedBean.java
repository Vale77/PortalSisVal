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
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import ec.edu.uasb.sisevaluacion.session.VProgramaFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "EvalCordAProgConsol")
@ViewScoped
public class EvalCordAProgConsolManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  // Solo para temas del reporte uso 

    private String ls_reporte = null;
    private String paramrep = null;
    private String smciclo = null;
    private String smtipfiltro = null;
    private String smfiltrodet = null;
    private String smtipo = null;
    private String smtipoprog = null;
    private String smarea = null;
    private String smprograma = null;
    private String smtipodet = null;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private List<VPrograma> lprograma = new ArrayList<VPrograma>();
    private Date fecInicio = null;
    private Date fecFin = null;
    @EJB
    private VProgramaFacadeLocal programaFacade;
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @ManagedProperty(value = "#{modal}")
    private modalManagedBean modal1;

// <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public String getLs_reporte() {
        return ls_reporte;
    }

    public void setLs_reporte(String ls_reporte) {
        this.ls_reporte = ls_reporte;
    }

    public String getSmfiltrodet() {
        return smfiltrodet;
    }

    public void setSmfiltrodet(String smfiltrodet) {
        this.smfiltrodet = smfiltrodet;
    }

    public String getSmtipoprog() {
        return smtipoprog;
    }

    public void setSmtipoprog(String smtipoprog) {
        this.smtipoprog = smtipoprog;
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

    public String getSmtipodet() {
        return smtipodet;
    }

    public void setSmtipodet(String smtipodet) {
        this.smtipodet = smtipodet;
    }

    public String getSmtipo() {
        return smtipo;
    }

    public void setSmtipo(String smtipo) {
        this.smtipo = smtipo;
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

    public String getSmtipfiltro() {
        return smtipfiltro;
    }

    public void setSmtipfiltro(String smtipfiltro) {
        this.smtipfiltro = smtipfiltro;
    }

    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
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

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFecFin() {
        return fecFin;
    }

    public void setFecFin(Date fecFin) {
        this.fecFin = fecFin;
    }

    // </editor-fold> 
    public EvalCordAProgConsolManagedBean() {
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();
    }

// <editor-fold defaultstate="collapsed" desc="VER REPORTE">  
    public void verReporte(String tipo) {

        if (validar() == 0) {
            paramrep = armarparametros();
            paramrep = paramrep + " &titulo=EvalDeCoordAlProgramaConsol - " + smciclo;
            modal1.verReporte(tipo, paramrep, ls_reporte);
        }
    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="VALIDAR">  

    public int validar() {
        int resp = 0;
        if (smtipfiltro.equalsIgnoreCase("D")) {
            if (smfiltrodet.equalsIgnoreCase("A") && smciclo == null) {
                resp = -1;
            }
            if (smfiltrodet.equalsIgnoreCase("F") && fecInicio == null && fecFin == null) {
                resp = -1;
            }
        }
        if (smtipfiltro.equalsIgnoreCase("C")) {
            if (smtipo == null) {
                resp = -1;
            } else if (smciclo == null) {
                resp = -1;
            }
        }
        return resp;
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="ARMAR PARAMETROS">  
    private String armarparametros() {
        if (smtipfiltro.equalsIgnoreCase("D")) {
            if (smfiltrodet != null && smfiltrodet.equalsIgnoreCase("A")) {
                if (smtipodet.equalsIgnoreCase("C")) {
                    ls_reporte = "EvalDeCoordAlProgramaConsolGen";
                } else if (smtipodet.equalsIgnoreCase("U")) {
                    ls_reporte = "EvalDeCoordAlProgramaCualitConsolGen";
                }
                paramrep = "&anio=" + smciclo;
            } else {
                if (smtipodet.equalsIgnoreCase("C")) {
                    ls_reporte = "EvalDeCoordAlProgramaConsolGenFec";
                } else if (smtipodet.equalsIgnoreCase("U")) {
                    ls_reporte = "EvalDeCoordAlProgramaCualitConsolGenFec";
                }
                paramrep = "&fecInicio=" + formato.format(fecInicio)
                        + "&fecFin=" + formato.format(fecFin);
            }
        }
        if (smtipfiltro.equalsIgnoreCase("C") || smtipfiltro.equalsIgnoreCase("I")) {
            if (smtipfiltro.equalsIgnoreCase("C")) {
                ls_reporte = "EvalDeCoordAlProgramaConsol";
            }
            if (smtipfiltro.equalsIgnoreCase("I")) {
                ls_reporte = "EvalDeCoordAlProgConsolInfor";
            }
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
        }
        return paramrep;
    }

    // </editor-fold> 
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
}
