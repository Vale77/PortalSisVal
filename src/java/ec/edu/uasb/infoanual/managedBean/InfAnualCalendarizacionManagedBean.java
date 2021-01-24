/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.infoanual.session.InfanualCalendarioFacadeLocal;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "InfAnualCalendario")
@ViewScoped
public class InfAnualCalendarizacionManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private List<String[]> listinfAnualReapertura = new ArrayList<String[]>();
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private Date fecinicio = null;
    private String smciclo = null;
    private String smtipdocente = null;
    private String smtipcontrato = null;
    private List<String[]> listtipDedicacion = new ArrayList<String[]>();

    @EJB
    private InfanualCalendarioFacadeLocal infanualCalendarioFacade;
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private ConsultaFacadeLocal consultaFacade;

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
    }

    public List<String[]> getListtipDedicacion() {
        return listtipDedicacion;
    }

    public void setListtipDedicacion(List<String[]> listtipDedicacion) {
        this.listtipDedicacion = listtipDedicacion;
    }

    public SimpleDateFormat getFormato() {
        return formato;
    }

    public void setFormato(SimpleDateFormat formato) {
        this.formato = formato;
    }

    public Date getFecinicio() {
        return fecinicio;
    }

    public void setFecinicio(Date fecinicio) {
        this.fecinicio = fecinicio;
    }

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    public List<String[]> getListinfAnualReapertura() {
        return listinfAnualReapertura;
    }

    public void setListinfAnualReapertura(List<String[]> listinfAnualReapertura) {
        this.listinfAnualReapertura = listinfAnualReapertura;
    }

    public String getSmtipdocente() {
        return smtipdocente;
    }

    public void setSmtipdocente(String smtipdocente) {
        this.smtipdocente = smtipdocente;
    }

    public String getSmtipcontrato() {
        return smtipcontrato;
    }

    public void setSmtipcontrato(String smtipcontrato) {
        this.smtipcontrato = smtipcontrato;
    }

    // </editor-fold> 
    /**
     * Creates a new instance of InfAnualCalendariacionManagedBean
     */
    public InfAnualCalendarizacionManagedBean() {
        this.setPaginaMant("/pages/infAnual/Procesos/reaperturaInfAnual");
    }

    @Override
    public void init() {
        retrieveDatos();
    }

    private void retrieveDatos() {
        //Recupero las areas
        ciclos = cicloAcademicoFacade.findAllActivos();
        recuperaInfAnual();

    }
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private String[] selectedInfAnual = new String[5];

    public String[] getSelectedInfAnual() {
        return selectedInfAnual;
    }

    public void setSelectedInfAnual(String[] selectedInfAnual) {
        this.selectedInfAnual = selectedInfAnual;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA INFORME ANUAL ">   
    private void recuperaInfAnual() {
        Vector v = new Vector();
        listinfAnualReapertura.clear();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT  (CASE WHEN ENCCAL.IAC_ANIO IS NULL THEN ").append(smciclo).append(" ELSE ENCCAL.IAC_ANIO END)IAC_ANIO,"
                + "(CASE WHEN ENCCAL.IAC_TIPO_DOCENTE IS NULL THEN '").append(smtipdocente).append("' ELSE ENCCAL.IAC_TIPO_DOCENTE END)IAC_TIPO_DOCENTE, "
                        + " ENCCAL.IAC_FECINICIO,"
                        + " ENCCAL.IAC_FECFIN,"
                        + " ENCCAL.IAC_ESTADO_INFORME, "
                        + " (CASE (CASE WHEN ENCCAL.IAC_TIPO_DOCENTE IS NULL THEN '").append(smtipdocente).append("' ELSE ENCCAL.IAC_TIPO_DOCENTE END)"
                        + " WHEN 'C' THEN 'Tiempo Completo'"
                        + " WHEN 'M' THEN 'Medio Tiempo' "
                        + " WHEN 'P' THEN 'Tiempo Parcial'"
                        + " WHEN 'I' THEN 'Invitado' END)IAC_TIPO_DOCENTE_nombre,"
                        + " (CASE ENCCAL.IAC_ESTADO_INFORME WHEN 'A' THEN 'Activo' WHEN 'C' THEN 'Cerrado'  ELSE '' END)IAC_ESTADO_INFORME_nombre, "
                        + " (CASE WHEN ENCCAL.IAC_TIPO_CONTRATO IS NULL THEN ").append(smtipcontrato).append(" ELSE ENCCAL.IAC_TIPO_CONTRATO END ) TIPO_CONTRATO,"
                        + " (CASE ISNULL(ENCCAL.IAC_TIPO_CONTRATO, ").append(smtipcontrato).append(")"
                        + "WHEN 1 THEN 'PLANTA ESCALAFONADO'"
                        + "WHEN 2 THEN 'PLANTA CONTRATADO'"
                        + "WHEN 3 THEN 'CONTRATADO'"
                        + "WHEN 4 THEN 'CONTRATADO DESIGNADO'"
                        + "WHEN 5 THEN 'INVITADO' END) NOMBRE_CONTRATO"
                        + " FROM CICLO_ACADEMICO CIC "
                        + "  LEFT OUTER JOIN GESTIONACADEMICA.INFANUAL_CALENDARIO ENCCAL ON ENCCAL.IAC_ANIO = CIC.ANIO "
                        + "  AND  (ENCCAL.IAC_ESTADO_INFORME = 'A' OR ENCCAL.IAC_ESTADO_INFORME='C' OR ENCCAL.IAC_ESTADO_INFORME IS NULL) "
                        + " AND (ENCCAL.IAC_TIPO_CONTRATO= '").append(smtipcontrato).append("' OR ENCCAL.IAC_TIPO_CONTRATO IS NULL)"
                        + " AND (ENCCAL.IAC_TIPO_DOCENTE = '").append(smtipdocente).append("' OR ENCCAL.IAC_TIPO_DOCENTE IS NULL)"
                        + " WHERE CIC.ANIO= ").append(smciclo);

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[9];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                asign[2] = (object[2] == null ? null : formato.format(object[2]));
                asign[3] = (object[3] == null ? null : formato.format(object[3]));
                asign[4] = (object[4] == null ? null : object[4].toString());
                asign[5] = (object[5] == null ? null : object[5].toString());
                asign[6] = (object[6] == null ? null : object[6].toString());
                asign[7] = (object[7] == null ? null : object[7].toString());
                asign[8] = (object[8] == null ? null : object[8].toString());
                listinfAnualReapertura.add(i, asign);
            }
        }
    }

    // </editor-fold> 
    public void onRowEdit(RowEditEvent event) {
        selectedInfAnual = (String[]) event.getObject();
        selectedInfAnual[2] = formato.format(fecinicio);
        infanualCalendarioFacade.aperturaInfAnual(selectedInfAnual);
        RequestContext.getCurrentInstance().update("freapInfAnual:tcalendario");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
        JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
    }

    public void ciclovalueChangeListener() {
        listinfAnualReapertura.clear();
        smtipdocente = null;
        smtipcontrato = null;
    }

    public void tipContratovalueChangeListener() {
        listinfAnualReapertura.clear();
        smtipdocente = null;
        if (smtipcontrato != null) {
            cargDedicacion();
        }
    }

    public void tipDedicacionvalueChangeListener() {
        listinfAnualReapertura.clear();
        if (smciclo != null && smtipdocente != null && smtipcontrato != null) {
            recuperaInfAnual();
        }
    }

    public void cargDedicacion() {
        listtipDedicacion.clear();
        listtipDedicacion=consultaFacade.cargDedicacion(smtipcontrato);       
        
    }
}
