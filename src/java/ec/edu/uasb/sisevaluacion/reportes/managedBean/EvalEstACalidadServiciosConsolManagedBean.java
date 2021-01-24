/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.reportes.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.Asignatura;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.entities.Profesor;
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
@ManagedBean(name = "EvalEstACalidadServiciosConsol")
@ViewScoped
public class EvalEstACalidadServiciosConsolManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private String ls_reporte = null;
    private String paramrep = null;
    private String smciclo = null;
    private String smtipfiltro = null;
    private String smfiltrodet = null;
    private String smtipodet = null;
    private String smtipo = null;    
    private String smarea = null;      
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();    
    private List<String[]> listarea = new ArrayList<String[]>();
    private Date fecInicio = null;
    private Date fecFin = null;
    
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private VProgramaFacadeLocal programaFacade;
    
    @ManagedProperty(value = "#{modal}")
    private modalManagedBean modal1;

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public modalManagedBean getModal1() {
        return modal1;
    }

    public void setModal1(modalManagedBean modal1) {
        this.modal1 = modal1;
    }

   
    public List<String[]> getListarea() {
        return listarea;
    }

    public void setListarea(List<String[]> listarea) {
        this.listarea = listarea;
    }

    public String getSmtipodet() {
        return smtipodet;
    }

    public void setSmtipodet(String smtipodet) {
        this.smtipodet = smtipodet;
    }

    public SimpleDateFormat getFormato() {
        return formato;
    }

    public void setFormato(SimpleDateFormat formato) {
        this.formato = formato;
    }

    public String getSmtipfiltro() {
        return smtipfiltro;
    }

    public void setSmtipfiltro(String smtipfiltro) {
        this.smtipfiltro = smtipfiltro;
    }

    public String getSmfiltrodet() {
        return smfiltrodet;
    }

    public void setSmfiltrodet(String smfiltrodet) {
        this.smfiltrodet = smfiltrodet;
    }

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

    public String getLs_reporte() {
        return ls_reporte;
    }

    public void setLs_reporte(String ls_reporte) {
        this.ls_reporte = ls_reporte;
    }

    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
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
    public EvalEstACalidadServiciosConsolManagedBean() {
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();
        //Recupero las areas
        listarea = consultaFacade.area();

    }
     public void tipovalueChangeListener() {
        smciclo = null;
        smarea = null;          
    }
 public void areavalueChangeListener() {
//        smprograma = null;
//        lprograma.clear();
//        if (smarea != null && !smarea.equalsIgnoreCase("T")) {
//            if (smtipo != null && (smtipo.equalsIgnoreCase("P") || smtipo.equalsIgnoreCase("T"))) {
//                lprograma = programaFacade.findProgramaAnual(Long.valueOf(smciclo), smarea);
//            }
//        }
    }
// <editor-fold defaultstate="collapsed" desc="VER REPORTE">  
  public void verReporte(String tipo) {
        if (validar() == 0) {
            paramrep = armarparametros();            
            paramrep = paramrep + " &titulo=EvalDeEstACalSerConsol - " + smciclo;
            modal1.verReporte(tipo, paramrep, ls_reporte);
        }
    }
// </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="ARMAR PARAMETROS">  
    private String armarparametros() {
        if (smtipfiltro.equalsIgnoreCase("D")) {
            if (smfiltrodet != null && smfiltrodet.equalsIgnoreCase("A")) {
                if (smtipodet.equalsIgnoreCase("C")) {//Reporte Cuantitativo
                    ls_reporte = "EvalDeEstACalSerConsolGen";
                } else if (smtipodet.equalsIgnoreCase("U")) { //Reporte Cualitativo
                    ls_reporte = "EvalDeEstACalSerCualitConsolGen";
                }
                paramrep = "&anio=" + smciclo;
            } else {
                if (smtipodet.equalsIgnoreCase("C")) {//Reporte Cuantitativo
                    ls_reporte = "EvalDeEstACalSerConsolGenFec";
                } else if (smtipodet.equalsIgnoreCase("U")) {//Reporte Cualitativo
                    ls_reporte = "EvalDeEstACalSerCualitConsolGenFec";
                }
                paramrep = "&fecInicio=" + formato.format(fecInicio)
                        + "&fecFin=" + formato.format(fecFin);
            }
        }
        if (smtipfiltro.equalsIgnoreCase("C") ) {
            if (smtipfiltro.equalsIgnoreCase("C")) {
                ls_reporte = "EvalDeEstACalSerConsol";
            }           
            paramrep = "&anio=" + smciclo;
            if (smarea == null) {
                smarea = "T";
            }
           
            if (!smtipo.isEmpty()) {
                paramrep = paramrep + "&tipReporte=" + smtipo
                        + "&codArea=" + smarea;
            }
        }
        return paramrep;
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
}
