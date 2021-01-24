package ec.edu.uasb.infoanual.managedBean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.infoanual.session.InfanualCalendarioFacadeLocal;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "cierreInfAnual")
@ViewScoped
public class InfAnualCierreManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");    
    private List<String[]> listInfAnualCierre = new ArrayList<String[]>();
    private List<String[]> listInfAnualFiltro;
    private List<String[]> listAInfAnualFiltroVac;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private List<String[]> listtipDedicacion = new ArrayList<String[]>();
    private String smtipodocente = null;
    private String sfiltro = null;
    private String smciclo = null;
    private String smtipcontrato = null;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
   @EJB
    private InfanualCalendarioFacadeLocal infanualCalendarioFacade;

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public SimpleDateFormat getFormato() {
        return formato;
    }

    public void setFormato(SimpleDateFormat formato) {
        this.formato = formato;
    }

    public List<String[]> getListtipDedicacion() {
        return listtipDedicacion;
    }

    public void setListtipDedicacion(List<String[]> listtipDedicacion) {
        this.listtipDedicacion = listtipDedicacion;
    }

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }   

    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
    }    

    public String getSfiltro() {
        return sfiltro;
    }

    public void setSfiltro(String sfiltro) {
        this.sfiltro = sfiltro;
    }

    public List<String[]> getListInfAnualCierre() {
        return listInfAnualCierre;
    }

    public void setListInfAnualCierre(List<String[]> listInfAnualCierre) {
        this.listInfAnualCierre = listInfAnualCierre;
    }

    public List<String[]> getListInfAnualFiltro() {
        return listInfAnualFiltro;
    }

    public void setListInfAnualFiltro(List<String[]> listInfAnualFiltro) {
        this.listInfAnualFiltro = listInfAnualFiltro;
    }

    public List<String[]> getListAInfAnualFiltroVac() {
        return listAInfAnualFiltroVac;
    }

    public void setListAInfAnualFiltroVac(List<String[]> listAInfAnualFiltroVac) {
        this.listAInfAnualFiltroVac = listAInfAnualFiltroVac;
    }

    public String getSmtipodocente() {
        return smtipodocente;
    }

    public void setSmtipodocente(String smtipodocente) {
        this.smtipodocente = smtipodocente;
    }

    public String getSmtipcontrato() {
        return smtipcontrato;
    }

    public void setSmtipcontrato(String smtipcontrato) {
        this.smtipcontrato = smtipcontrato;
    }

   

    // </editor-fold> 
    public InfAnualCierreManagedBean() {
        this.setPaginaMant("/pages/infAnual/Procesos/cierreInfAnual");
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();
    }
// <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private List<String[]> selectedasigcierre = new ArrayList<String[]>();

    public List<String[]> getSelectedasigcierre() {
        return selectedasigcierre;
    }

    public void setSelectedasigcierre(List<String[]> selectedasigcierre) {
        this.selectedasigcierre = selectedasigcierre;
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="CARGAR TABLA ASIGNATURAS PARA CIERRE ">   
    private void recuperaInfAnualCierre() {
        Vector v = new Vector();
        listInfAnualCierre.clear();
        StringBuilder sql = new StringBuilder();
        sql.append("DECLARE @PROFESOR TABLE (CODIGO_PROFESOR NUMERIC(7,0),dedicacion VARCHAR(1), cod_tipocontrato INT, ANIO_INICONTRATO INT, ANIO_FINCONTRATO INT ) " 
                +" INSERT  INTO @PROFESOR(CODIGO_PROFESOR, dedicacion, cod_tipocontrato, ANIO_INICONTRATO,ANIO_FINCONTRATO ) "
                +" SELECT CODIGO_PROFESOR, dedicacion, cod_tipocontrato, ANIO_INICONTRATO,ANIO_FINCONTRATO "
                +" FROM interfaseOcu.dbo.PROFESOR " 
                +" WHERE PROFESOR.dedicacion =  '").append(smtipodocente).append("'"
                +"  AND PROFESOR.COD_TIPOCONTRATO = '").append(smtipcontrato).append("'"
                +"  AND ") .append(smciclo).append(" BETWEEN ANIO_INICONTRATO AND ANIO_FINCONTRATO "
                + "SELECT DISTINCT  (CASE WHEN ENCCAL.IAC_ANIO IS NULL THEN ") .append(smciclo).append(" ELSE ENCCAL.IAC_ANIO END)IAC_ANIO,"                
                + "(CASE WHEN ENCCAL.IAC_TIPO_DOCENTE IS NULL THEN '") .append(smtipodocente).append("' ELSE ENCCAL.IAC_TIPO_DOCENTE END)IAC_TIPO_DOCENTE, "
                + " ENCCAL.IAC_FECINICIO,"
                + " ENCCAL.IAC_FECFIN,"
                + " ENCCAL.IAC_ESTADO_INFORME, "
                + " (CASE (CASE WHEN ENCCAL.IAC_TIPO_DOCENTE IS NULL THEN '") .append(smtipodocente).append("' ELSE ENCCAL.IAC_TIPO_DOCENTE END)"
                + " WHEN 'C' THEN 'Tiempo Completo'"
                + " WHEN 'M' THEN 'Medio Tiempo' "
                + " WHEN 'P' THEN 'Tiempo Parcial'"
                + " WHEN 'I' THEN 'Invitado' END)IAC_TIPO_DOCENTE_nombre,"
                + " (CASE ENCCAL.IAC_ESTADO_INFORME WHEN 'A' THEN 'Activo' WHEN 'I' THEN 'Inactivo'  ELSE '' END)IAC_ESTADO_INFORME_nombre,"
                + " ISNULL((CONVERT(FLOAT,ISNULL((select count(1) "
                +"                              from GESTIONACADEMICA.INFANUAL_REALIZADO as ENCRE "
                +"                              INNER JOIN  @PROFESOR PROFESOR ON ENCRE.IAE_CODIGO_PROFESOR = PROFESOR.CODIGO_PROFESOR "
                +"                              AND PROFESOR.COD_TIPOCONTRATO = ENCCAL.IAC_TIPO_CONTRATO "
                +"                              AND PROFESOR.dedicacion=ENCCAL.IAC_TIPO_DOCENTE "
                +"                              and ENCRE.IAE_ANIO BETWEEN PROFESOR.ANIO_INICONTRATO AND PROFESOR.ANIO_FINCONTRATO "
                +"                              where ENCRE.IAE_ANIO = ENCCAL.IAC_ANIO),0),2)/"
                +"                      IIF((SELECT DISTINCT  COUNT(PROFESOR.CODIGO_PROFESOR) "
                +"                              FROM @PROFESOR profesor "
                +"                              WHERE PROFESOR.COD_TIPOCONTRATO = ENCCAL.IAC_TIPO_CONTRATO "
                +"                              AND PROFESOR.dedicacion=ENCCAL.IAC_TIPO_DOCENTE "
                +"                              and ENCCAL.IAC_ANIO BETWEEN PROFESOR.ANIO_INICONTRATO AND PROFESOR.ANIO_FINCONTRATO )= 0, 1,ISNULL((SELECT DISTINCT  COUNT(PROFESOR.CODIGO_PROFESOR) "
                +"                              FROM @PROFESOR profesor  "
                +"                              WHERE PROFESOR.COD_TIPOCONTRATO = ENCCAL.IAC_TIPO_CONTRATO "
                +"                              AND PROFESOR.dedicacion=ENCCAL.IAC_TIPO_DOCENTE "
                +"                              and ENCCAL.IAC_ANIO BETWEEN PROFESOR.ANIO_INICONTRATO AND PROFESOR.ANIO_FINCONTRATO),1)))*100, 0) PORC, "
                + " ENCCAL.IAC_FECFIN, "
                + " (CASE ISNULL(ENCCAL.IAC_TIPO_CONTRATO, ").append(smtipcontrato).append(")"
                                        + "WHEN 1 THEN 'PLANTA ESCALAFONADO'"
                                        + "WHEN 2 THEN 'PLANTA CONTRATADO'"
                                        + "WHEN 3 THEN 'CONTRATADO'"
                                        + "WHEN 4 THEN 'CONTRATADO DESIGNADO'"
                                        + "WHEN 5 THEN 'INVITADO' END) NOMBRE_CONTRATO,"
                        + "ISNULL(ENCCAL.IAC_TIPO_CONTRATO, ").append(smtipcontrato).append(")"
                + " FROM CICLO_ACADEMICO CIC " 
                +"  LEFT OUTER JOIN GESTIONACADEMICA.INFANUAL_CALENDARIO ENCCAL ON ENCCAL.IAC_ANIO = CIC.ANIO "     
                + " AND (ENCCAL.IAC_TIPO_CONTRATO= '").append(smtipcontrato).append("' OR ENCCAL.IAC_TIPO_CONTRATO IS NULL)"
                + " AND (ENCCAL.IAC_TIPO_DOCENTE = '").append(smtipodocente).append("' OR ENCCAL.IAC_TIPO_DOCENTE IS NULL)"                
                + " WHERE CIC.ANIO= ").append(smciclo).append(""
                + " AND ENCCAL.IAC_ESTADO_INFORME <>'C' ");
       

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[11];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                asign[2] = (object[2] == null ? null : formato.format(object[2]));
                asign[3] = (object[3] == null ? null : formato.format(object[3]));
                asign[4] = (object[4] == null ? null : object[4].toString());
                asign[5] = (object[5] == null ? null : object[5].toString());
                asign[6] = (object[6] == null ? null : object[6].toString());
                asign[7] = (object[7] == null ? null : object[7].toString());
                asign[8] = (object[8] == null ? null : formato.format(object[8]));
                asign[9] = (object[9] == null ? null : object[9].toString());
                asign[10] = (object[10] == null ? null : object[10].toString());
                listInfAnualCierre.add(i, asign);
            }
        }

    }
    // </editor-fold>    

// <editor-fold defaultstate="collapsed" desc="FILTRO LOS VALORES DEL PORCENTAJ ">   
    public void filtrarValorvalueChangeListener() {
        Double dfiltro;
        int j = 0;
        List<String[]> listInfAnualFiltroAux = new ArrayList<String[]>();
        listInfAnualFiltroAux.clear();
        if (listAInfAnualFiltroVac != null) {
            listInfAnualFiltro = listAInfAnualFiltroVac;
        }

        if (listInfAnualFiltro == null) {
            listInfAnualFiltro = listInfAnualCierre;
        } else {
            listAInfAnualFiltroVac = listInfAnualFiltro;
        }
        listAInfAnualFiltroVac = listInfAnualFiltro;
        if (sfiltro != null && !sfiltro.isEmpty()) {
            dfiltro = Double.valueOf(sfiltro);
            //  listAInfAnualFiltroVac = listInfAnualFiltro;
            if (listInfAnualFiltro.size() > 0) {
                for (int i = 0; i < listInfAnualFiltro.size(); i++) {
                    String[] asign;
                    asign = new String[9];
                    asign = listInfAnualFiltro.get(i);
                    if (Double.valueOf(asign[7]) >= dfiltro) {
                        listInfAnualFiltroAux.add(j, asign);
                        j++;
                    }
                }
            }
            listInfAnualFiltro = listInfAnualFiltroAux;

        } else {
            listInfAnualFiltro = listAInfAnualFiltroVac;
        }
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  
    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        if (selectedasigcierre != null) {
            if (selectedasigcierre.size() > 0) {
                for (int i = 0; i < selectedasigcierre.size(); i++) {
                    String[] asign;
                    asign = new String[1];
                    asign = selectedasigcierre.get(i);
                    infanualCalendarioFacade.cierreInfAnual(asign);
                }

                RequestContext.getCurrentInstance().update("fcierreInfAnual:tcierreInfAnual");
                RequestContext.getCurrentInstance().execute("mantWidget.hide();");
                JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
                tipodocentevalueChangeListener();
                CancelCierre();
            }
        }
    }
    // </editor-fold> 

    public void CancelCierre() {
        selectedasigcierre = new ArrayList<String[]>();
        sfiltro = null;
        listInfAnualFiltro = listInfAnualCierre;
    }   
    
     public void ciclovalueChangeListener() {
        listInfAnualCierre.clear();
        smtipodocente = null;
        smtipcontrato= null;
           
    }
     public void tipContratovalueChangeListener() {
         listInfAnualCierre.clear();      
        smtipodocente= null; 
        if (smtipcontrato != null) {
            cargDedicacion();
        }
    }
     public void tipodocentevalueChangeListener() {
        listInfAnualCierre.clear();
        if (smtipodocente != null && smtipcontrato!=null &&smtipodocente !=null) {
            recuperaInfAnualCierre();
        }
    }
      public void cargDedicacion() {
        listtipDedicacion.clear();
        listtipDedicacion=consultaFacade.cargDedicacion(smtipcontrato);        
    }
}
