/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.reportes.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.managedBean.modalManagedBean;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
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
@ManagedBean(name = "EvalEstAProgEstadistico")
@ViewScoped
public class EvalEstAProgramaEstadisticoManagedBean extends BaseJSFManagedBean implements Serializable {

    private String smciclo = null;
    private String ls_reporte = null;
    private String paramrep = null;
    private String sarea = null;
    private String sestado = null;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @ManagedProperty(value = "#{modal}")
    private modalManagedBean modal1;
    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">

    public String getSarea() {
        return sarea;
    }

    public void setSarea(String sarea) {
        this.sarea = sarea;
    }

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    public String getSestado() {
        return sestado;
    }

    public void setSestado(String sestado) {
        this.sestado = sestado;
    }

    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
    }

    public modalManagedBean getModal1() {
        return modal1;
    }

    public void setModal1(modalManagedBean modal1) {
        this.modal1 = modal1;
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

// </editor-fold> 
    public EvalEstAProgramaEstadisticoManagedBean() {
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();

    }

// <editor-fold defaultstate="collapsed" desc="VALIDAR">  
    public int validar() {

        int resp = 0;
        if (sarea == null) {
            resp = -1;
        } else if (sestado == null) {
            resp = -1;
        } 
        return resp;
    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="VER REPORTE">  

    public void verReporte(String tipo) {
        
        if (validar() == 0) {
            ls_reporte = "EvalDeEstudAlProgramaEstadistico";
             paramrep = armarparametros();
            modal1.verReporte(tipo, paramrep, ls_reporte);
        }
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="ARMAR PARAMETROS">  
    private String armarparametros() {
        paramrep = "&anio=" + smciclo;
        if (sarea == null) {
            sarea = "T";
        }
        if (sestado == null) {
            sestado = "T";
        }
        paramrep = paramrep + "&estado=" + sestado
                + "&codArea=" + sarea;
        return paramrep;
    }
    // </editor-fold> 
    public void ciclovalueChangeListener() {
        sarea = null;
    }
}
