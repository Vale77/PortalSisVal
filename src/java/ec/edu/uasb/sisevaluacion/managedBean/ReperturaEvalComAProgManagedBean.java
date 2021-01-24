/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.entities.Encuesta;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import ec.edu.uasb.sisevaluacion.session.EncuestaCalendarioFacadeLocal;
import ec.edu.uasb.sisevaluacion.session.EncuestaFacadeLocal;
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
@ManagedBean(name = "reaperturaEvalComAProg")
@ViewScoped
public class ReperturaEvalComAProgManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    private List<String[]> listarea = new ArrayList<String[]>();
    private List<String[]> listProgramaReapertura = new ArrayList<String[]>();
    private List<String[]> listProgramaFiltro;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private List<Encuesta> listEncuesta = new ArrayList<Encuesta>();
    private String sarea = null;
    private String sencuesta = null;
    private String smciclo = null;
    private Date fecinicio = null;
    @EJB
    private EncuestaFacadeLocal encuestaFacade;
    @EJB
    private EncuestaCalendarioFacadeLocal encuestaCalendarioFacade;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public List<String[]> getListProgramaReapertura() {
        return listProgramaReapertura;
    }

    public void setListProgramaReapertura(List<String[]> listProgramaReapertura) {
        this.listProgramaReapertura = listProgramaReapertura;
    }

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    public List<String[]> getListProgramaFiltro() {
        return listProgramaFiltro;
    }

    public void setListProgramaFiltro(List<String[]> listProgramaFiltro) {
        this.listProgramaFiltro = listProgramaFiltro;
    }

    public SimpleDateFormat getFormato() {
        return formato;
    }

    public void setFormato(SimpleDateFormat formato) {
        this.formato = formato;
    }

    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
    }

    public List<String[]> getListarea() {
        return listarea;
    }

    public void setListarea(List<String[]> listarea) {
        this.listarea = listarea;
    }

    public String getSarea() {
        return sarea;
    }

    public void setSarea(String sarea) {
        this.sarea = sarea;
    }

    public String getSencuesta() {
        return sencuesta;
    }

    public void setSencuesta(String sencuesta) {
        this.sencuesta = sencuesta;
    }

    public Date getFecinicio() {
        return fecinicio;
    }

    public void setFecinicio(Date fecinicio) {
        this.fecinicio = fecinicio;
    }

    // </editor-fold> 
    public ReperturaEvalComAProgManagedBean() {
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();
    }

    // <editor-fold defaultstate="collapsed" desc="CARGAR LAS ENCUESTAS HABILITADAS"> 
    public List<Encuesta> getListEncuesta() {
        listEncuesta.clear();
        listEncuesta = encuestaFacade.findAllActivo('O');
        return listEncuesta;
    }

    public void setListEncuesta(List<Encuesta> listEncuesta) {
        this.listEncuesta = listEncuesta;
    }
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private String[] selectedprograma = new String[16];

    public String[] getSelectedprograma() {
        return selectedprograma;
    }

    public void setSelectedprograma(String[] selectedprograma) {
        this.selectedprograma = selectedprograma;
    }

    // </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA PROGRAMAS ">   
    private void recuperaPrograma(Long anio, int area) {
        Vector v = new Vector();
        listProgramaReapertura.clear();
        StringBuilder sql = new StringBuilder();
        sql.append(" DECLARE @MATERIA TABLE (  PROGRAMA VARCHAR(500), CODIGO_ESP VARCHAR(4), CODIGO_FACULTAD NUMERIC(7,0),  ANIO INT,CICLO VARCHAR(384), TIPO_NIVEACAD NUMERIC(2,0))"
                + " INSERT INTO @MATERIA  (  PROGRAMA , CODIGO_ESP , CODIGO_FACULTAD , ANIO , CICLO , TIPO_NIVEACAD )"
                + " SELECT DISTINCT PROGRAMA , CODIGO_ESP , CODIGO_FACULTAD , ANIO , CICLO , TIPO_NIVEACAD "
                + " FROM dbo.VISTA_COORDINADOR_PROGRAMA WHERE anio =  ").append(anio).append(""
                        + " SELECT DISTINCT (case when enccal.anio is null then concat(MAT.ANIO,'-',MAT.ciclo,'-1','-1','-1',MAT.codigo_esp) else concat(ENCCAL.ANIO,'-',ENCCAL.ciclo,'-','-1','-',ENCCAL.CODIGO_ENCUESTA,'-', '-1','-','-1','-',ENCCAL.codigo_esp) end)cod,"
                        + " (case when enccal.ANIO is null then mat.anio else enccal.ANIO end) ANIO, "
                        + " (CASE WHEN enccal.CICLO IS NULL THEN 1 ELSE enccal.CICLO END)CICLO, "
                        + " (CASE WHEN enccal.CODIGO_MATERIA IS NULL THEN -1 ELSE enccal.CODIGO_MATERIA END ) CODIGO_MATERIA,"
                        + " (CASE WHEN enccal.CODIGO_PROFESOR IS NULL THEN -1 ELSE enccal.CODIGO_PROFESOR END) CODIGO_PROFESOR,   "
                        + " (CASE WHEN enccal.CODIGO_ENCUESTA IS NULL THEN (CASE TIPO_NIVEACAD when 1 then 19 when 2 then 19 when 3 then 18  when 4 then 21 end) ELSE enccal.CODIGO_ENCUESTA END )CODIGO_ENCUESTA,   "
                        + " (CASE WHEN enccal.cod_estudiante IS NULL  THEN -1 ELSE enccal.cod_estudiante END) cod_estudiante ,"
                        + " (CASE WHEN enccal.codigo_esp IS NULL  THEN mat.CODIGO_ESP ELSE enccal.codigo_esp END) CODIGO_ESP,"
                        + " (CASE WHEN enccal.codigo_nivel IS NULL  THEN -1 ELSE enccal.codigo_nivel END)CODIGO_NIVEL,"
                        + "  enccal.FECHA_INICIO,   "
                        + "  enccal.FECHA_FIN,   "
                        + " (case enccal.ESTADO_ENCUESTA when 'A' then 'Abierta' when'C' then 'Cerrada' end )ESTADO_ENCUESTA , "
                        + "  mat.PROGRAMA programa,   "
                        + " ENC.TITULO "
                        + " FROM @MATERIA as mat LEFT OUTER JOIN evaluacion.ENCUESTA_CALENDARIO as enccal "
                        + " ON  mat.ANIO =  enccal.ANIO "
                        + " AND  mat.CICLO = enccal.CICLO "
                        + " AND  mat.CODIGO_ESP = enccal.CODIGO_ESP  "
                        + " and  enccal.codigo_materia = -1 "
                        + " and enccal.codigo_profesor = -1 "
                        + " and enccal.codigo_nivel = -1   "
                        + " AND enccal.COD_ESTUDIANTE = -1 "
                        + " AND (ENCCAL.tipo_encuesta = 'O' or ENCCAL.tipo_encuesta is null) LEFT OUTER JOIN EVALUACION.ENCUESTA ENC "
                        + " ON ENCCAL.CODIGO_ENCUESTA = ENC.CODIGO_ENCUESTA"
                        + " wHERE   mat.ANIO = ").append(anio).append("   "
                        + " AND (ENCCAL.eSTADO_ENCUESTA <>'C' or ENCCAL.eSTADO_ENCUESTA is null) "
                        + " AND mat.CICLO = 1   "
                        + " AND mat.CODIGO_FACULTAD = ").append(area).append(" "
                        + " ORDER BY 14, 7 desc");

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[17];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                asign[2] = (object[2] == null ? null : object[2].toString());
                asign[3] = (object[3] == null ? null : object[3].toString());
                asign[4] = (object[4] == null ? null : object[4].toString());
                asign[5] = (object[5] == null ? null : object[5].toString());
                asign[6] = (object[6] == null ? null : object[6].toString());
                asign[7] = (object[7] == null ? null : object[7].toString());
                asign[8] = (object[8] == null ? null : object[8].toString());
                asign[9] = (object[9] == null ? null : formato.format(object[9]));
                asign[10] = (object[10] == null ? null : formato.format(object[10]));
                asign[11] = (object[11] == null ? null : object[11].toString());
                asign[12] = (object[12] == null ? null : object[12].toString());
                asign[13] = (object[13] == null ? null : object[13].toString());
                /*ASIGNO EL TIPO DE ENCUESTA "P" PARA PROGRAMA */
                asign[15] = "O";
                /*ASIGNO EL CODIGO DE PARALELO -1 PARA ESTA ENCUESTA */
                asign[16] = "-1";
                listProgramaReapertura.add(i, asign);
            }
        }
    }
    // </editor-fold> 

    public void areavalueChangeListener() {
        if (sarea != null) {
            recuperaPrograma(Long.valueOf(smciclo), Integer.valueOf(sarea));
        }
    }

    public void onRowEdit(RowEditEvent event) {
        selectedprograma = (String[]) event.getObject();
        if (sencuesta != null) {
            selectedprograma[5] = sencuesta;
        }
        selectedprograma[9] = formato.format(fecinicio);
        encuestaCalendarioFacade.reaperturaEncuesta(selectedprograma);
        RequestContext.getCurrentInstance().update("freaperturaComProg:tComProg");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
        JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
        areavalueChangeListener();
    }

    public void ciclovalueChangeListener() {
        sarea = null;
        listarea = consultaFacade.area();
    }

}
