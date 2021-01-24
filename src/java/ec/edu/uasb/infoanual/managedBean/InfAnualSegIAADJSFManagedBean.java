/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.infoanual.entities.InfanualRealizado;
import ec.edu.uasb.infoanual.session.InfanualRealizadoFacadeLocal;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.entities.Profesor;
import ec.edu.uasb.sisevaluacion.managedBean.modalManagedBean;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
@ManagedBean(name = "segIAAD")
@ViewScoped
public class InfAnualSegIAADJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private String smciclo = null;
    private String msgtablavacia = "No existen datos Registrados";
    private String scodProfesor = null;
    private String ls_reporte = null;
    private String paramrep = null;
    private String ltotdoc = null;
    private String ltotdocfin = null;
    private String ltotdocing = null;
    private String ltotdocfalt = null;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    //Informacion TESIS UASB
    //private List<String[]> listinfAnualReapertura = new ArrayList<String[]>();
    private List<String[]> listInfAnualRealizado = new ArrayList<String[]>();
    //private List<Profesor> lprofesor = new ArrayList<Profesor>();
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private InfanualRealizadoFacadeLocal infanualRealizadoFacade;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @ManagedProperty(value = "#{modal}")
    private modalManagedBean modal1;
    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    public String getLtotdocfalt() {
        return ltotdocfalt;
    }

    public void setLtotdocfalt(String ltotdocfalt) {
        this.ltotdocfalt = ltotdocfalt;
    }

    public String getLtotdoc() {
        return ltotdoc;
    }

    public void setLtotdoc(String ltotdoc) {
        this.ltotdoc = ltotdoc;
    }

    public String getLtotdocfin() {
        return ltotdocfin;
    }

    public void setLtotdocfin(String ltotdocfin) {
        this.ltotdocfin = ltotdocfin;
    }

    public String getLtotdocing() {
        return ltotdocing;
    }

    public void setLtotdocing(String ltotdocing) {
        this.ltotdocing = ltotdocing;
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

    public String getMsgtablavacia() {
        return msgtablavacia;
    }

    public void setMsgtablavacia(String msgtablavacia) {
        this.msgtablavacia = msgtablavacia;
    }

    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
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

    public List<String[]> getListInfAnualRealizado() {
        return listInfAnualRealizado;
    }

    public void setListInfAnualRealizado(List<String[]> listInfAnualRealizado) {
        this.listInfAnualRealizado = listInfAnualRealizado;
    }

    // </editor-fold> 
    /**
     * Creates a new instance of InfAnualSegIAADJSFManagedBean
     */
    @Override
    public void init() {
        retrieveDatos();
    }

    private void retrieveDatos() {
        
        ciclos = cicloAcademicoFacade.findAllActivos();
    }

    public InfAnualSegIAADJSFManagedBean() {
    }

    public void ciclovalueChangeListener() {
        StringBuilder sql = new StringBuilder();
        listInfAnualRealizado.clear();
        if (smciclo != null) {
            recuperaInfAnualRea();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private String[] selectedinfAnualRea ;

    public String[] getSelectedinfAnualRea() {
        return selectedinfAnualRea;
    }

    public void setSelectedinfAnualRea(String[] selectedinfAnualRea) {
        this.selectedinfAnualRea = selectedinfAnualRea;
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
        StringBuilder sql = new StringBuilder();
        paramrep = "&anio=" + smciclo;
        if (!smciclo.isEmpty()) {
           
            paramrep = paramrep + "&codigo=" + selectedinfAnualRea[1]
                    + "&titulo= " + selectedinfAnualRea[2] + "-" + smciclo;
        }

        return paramrep;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="VER REPORTE">  
    public void verReporte(String tipo) {
        if (validar() == 0) {
            ls_reporte = "informeAnual";
            paramrep = armarparametros();
            modal1.verReporte(tipo, paramrep, ls_reporte);
        }
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA INFORME ANUAL ">   
    private void recuperaInfAnualRea() {
        Vector v = new Vector();
        listInfAnualRealizado.clear();
        StringBuilder sql = new StringBuilder();
        sql.append("DECLARE @PROFESOR TABLE (CODIGO_PROFESOR NUMERIC(7,0), CED_PAS_PROFESOR VARCHAR(15),nombre_profesor VARCHAR(32), apellido_profesor VARCHAR(35), "
                + "	cod_profesor_acadant NUMERIC(10,0), dedicacion VARCHAR(1), cod_tipocontrato INT, ANIO_INICONTRATO INT, ANIO_FINCONTRATO INT ) "
                + "	INSERT  INTO @PROFESOR(CODIGO_PROFESOR,CED_PAS_PROFESOR,nombre_profesor, apellido_profesor, cod_profesor_acadant, dedicacion, cod_tipocontrato,  ANIO_INICONTRATO, ANIO_FINCONTRATO) "
                + "	SELECT CODIGO_PROFESOR,CED_PAS_PROFESOR,nombre_profesor, apellido_profesor, cod_profesor_acadant, dedicacion, "
                + "  cod_tipocontrato, ANIO_INICONTRATO, ANIO_FINCONTRATO "
                + "	FROM interfaseOcu.dbo.PROFESOR "
                + "SELECT PRF.CODIGO_PROFESOR,PRF.cod_profesor_acadant,PRF.APELLIDO_PROFESOR+' '+PRF.NOMBRE_PROFESOR NOMBRE,"
                + "IARE.IAE_ANIO, IARE.IAE_FECHA, IARE.IAE_ESTADO_IAAD,"
                + "(CASE IARE.IAE_ESTADO_IAAD WHEN 'I' THEN 'INGRESADO' WHEN 'F' THEN 'FINALIZADO' END ), "
                + " (SELECT COUNT(1) FROM GESTIONACADEMICA.INFANUAL_CALENDARIO ENCCAL " 
                + "INNER JOIN @PROFESOR PROF ON ENCCAL.IAC_ANIO= IARE.IAE_ANIO " 
                +" AND PROF.dedicacion= ENCCAL.IAC_TIPO_DOCENTE " 
                +" AND PROF.COD_TIPOCONTRATO= ENCCAL.IAC_TIPO_CONTRATO "
                +" AND ENCCAL.IAC_ANIO BETWEEN ANIO_INICONTRATO AND ANIO_FINCONTRATO "
                +" AND CODIGO_PROFESOR NOT IN (4401,4658,44666)) TOTAL_DOCENTES, "
                +" (SELECT COUNT(1) FROM GESTIONACADEMICA.INFANUAL_REALIZADO ENCRE "
                +" WHERE ENCRE.IAE_ANIO = IARE.IAE_ANIO "
                +" AND ENCRE.IAE_ESTADO_IAAD= 'F') TOTAL_FINALIZADO, "
                +" (SELECT COUNT(1) FROM GESTIONACADEMICA.INFANUAL_REALIZADO ENCRE "
                +" WHERE ENCRE.IAE_ANIO = IARE.IAE_ANIO "
                +" AND ENCRE.IAE_ESTADO_IAAD= 'I') TOTAL_INGRESADO "
                + "FROM GESTIONACADEMICA.INFANUAL_REALIZADO IARE "
                + "INNER JOIN @PROFESOR PRF ON  PRF.CODIGO_PROFESOR = IARE.IAE_CODIGO_PROFESOR "
                + "WHERE IARE.IAE_ANIO= ").append(smciclo).append(" ORDER BY NOMBRE") ;        
        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[10];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                asign[2] = (object[2] == null ? null : object[2].toString());
                asign[3] = (object[3] == null ? null : object[3].toString());
                asign[4] = (object[4] == null ? null : formato.format(object[4]));
                asign[5] = (object[5] == null ? null : object[5].toString());
                asign[6] = (object[6] == null ? null : object[6].toString());
                asign[7] = (object[7] == null ? null : object[7].toString());
                ltotdoc=(object[7] == null ? null : object[7].toString());          
                asign[8] = (object[8] == null ? null : object[8].toString());
                ltotdocfin= (object[8] == null ? null : object[8].toString());
                asign[9] = (object[9] == null ? null : object[9].toString());
                ltotdocing= (object[9] == null ? null : object[9].toString());
                listInfAnualRealizado.add(i, asign);
            }
        }
        ltotdocfalt=String.valueOf(Integer.valueOf(ltotdoc)-(Integer.valueOf(ltotdocfin)+Integer.valueOf(ltotdocing)));
    }

    // </editor-fold> 
}
