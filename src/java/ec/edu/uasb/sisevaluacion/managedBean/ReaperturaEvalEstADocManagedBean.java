/*
 * To change this template, choose Tools | Templates
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
@ManagedBean(name = "reaperturaEvalEstADoc")
@ViewScoped
public class ReaperturaEvalEstADocManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    private List<String[]> listtrimestre = new ArrayList<String[]>();
    private List<String[]> listarea = new ArrayList<String[]>();
    private List<String[]> listAsignaturaReapertura = new ArrayList<String[]>();
    private List<String[]> listAsignaturaFiltro;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private List<Encuesta> listEncuesta = new ArrayList<Encuesta>();
    private String strimestre = null;
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
    public List<String[]> getListAsignaturaReapertura() {
        return listAsignaturaReapertura;
    }

    public void setListAsignaturaReapertura(List<String[]> listAsignaturaReapertura) {
        this.listAsignaturaReapertura = listAsignaturaReapertura;
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

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    public String getStrimestre() {
        return strimestre;
    }

    public void setStrimestre(String strimestre) {
        this.strimestre = strimestre;
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

    public List<String[]> getListtrimestre() {
        return listtrimestre;
    }

    public void setListtrimestre(List<String[]> listtrimestre) {
        this.listtrimestre = listtrimestre;
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

    public List<String[]> getListAsignaturaFiltro() {
        return listAsignaturaFiltro;
    }

    public void setListAsignaturaFiltro(List<String[]> listAsignaturaFiltro) {
        this.listAsignaturaFiltro = listAsignaturaFiltro;
    }

    // </editor-fold> 
    public ReaperturaEvalEstADocManagedBean() {
        this.setPaginaMant("/pages/evaluacion/asignatura/reaperturaAsignatura");
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();
        retrieveDatos();
    }

    private void retrieveDatos() {
        //Recupero las areas
        listarea = consultaFacade.area();

    }
    // <editor-fold defaultstate="collapsed" desc="CARGAR LAS ENCUESTAS HABILITADAS"> 

    public List<Encuesta> getListEncuesta() {
        listEncuesta.clear();
        listEncuesta = encuestaFacade.findAllActivo('A');
        return listEncuesta;
    }

    public void setListEncuesta(List<Encuesta> listEncuesta) {
        this.listEncuesta = listEncuesta;
    }
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private String[] selectedasignatura = new String[19];

    public String[] getSelectedasignatura() {
        return selectedasignatura;
    }

    public void setSelectedasignatura(String[] selectedasignatura) {
        this.selectedasignatura = selectedasignatura;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA ASIGNATURAS ">   
    private void recuperaAsignatura(Long anio, int area, int trimestre) {
        Vector v = new Vector();
        listAsignaturaReapertura.clear();
        StringBuilder sql = new StringBuilder();
        sql.append(" DECLARE @MATERIAFECHA TABLE (CODIGO_ESP NUMERIC(10,0), CODIGO_MATERIA NUMERIC(10,0),CODIGO_NIVEL NUMERIC(10,0), ANIO NUMERIC(4,0), FECHA_INICIAL DATETIME, FECHA_FINAL DATETIME, COD_PARALELO INT, COD_PROFESOR INT, TIP_ASIGNATURA CHAR(1)) "
                + " DECLARE @MATERIA TABLE (CODIGO_PROFESOR NUMERIC(8,0), NOMBRE_PROFESOR VARCHAR(60), APELLIDO_PROFESOR VARCHAR(65), NOMBRE_MATERIA VARCHAR(200), CODIGO_NIVEL INT, PROGRAMA VARCHAR(500), "
                + " CODIGO_ESP VARCHAR(4), CODIGO_FACULTAD NUMERIC(7,0), CODIGO_MATERIA NUMERIC(9,0), ANIO INT, TRIMESTRE VARCHAR(75),CICLO VARCHAR(384), COD_PARALELO INT, NOMBRE_PARALELO VARCHAR(8))"
                + " INSERT INTO @MATERIA  ( CODIGO_PROFESOR , NOMBRE_PROFESOR , APELLIDO_PROFESOR , NOMBRE_MATERIA , CODIGO_NIVEL , PROGRAMA ,"
                + " CODIGO_ESP , CODIGO_FACULTAD , CODIGO_MATERIA , ANIO , TRIMESTRE ,CICLO, COD_PARALELO , NOMBRE_PARALELO )"
                + " SELECT DISTINCT  CODIGO_PROFESOR , NOMBRE_PROFESOR , APELLIDO_PROFESOR , NOMBRE_MATERIA , CODIGO_NIVEL , PROGRAMA , "
                + " CODIGO_ESP , CODIGO_FACULTAD , CODIGO_MATERIA , ANIO , TRIMESTRE ,CICLO, COD_PARALELO, NOMBRE_PARALELO"
                + " FROM dbo.VISTA_COORDINADOR_PROGRAMA "
                + " where anio =  ").append(smciclo).append(""
                        + " INSERT INTO @MATERIAFECHA(CODIGO_ESP, CODIGO_MATERIA,CODIGO_NIVEL , ANIO , FECHA_INICIAL , FECHA_FINAL, COD_PARALELO, COD_PROFESOR,TIP_ASIGNATURA ) "
                        + " SELECT DISTINCT  CODIGO_ESP, CODIGO_MATERIA,CODIGO_NIVEL , ANIO , FECHA_INICIAL , FECHA_FINAL, COD_PARALELO, COD_PROFESOR,TIP_ASIGNATURA  "
                        + " FROM dbo.VISTA_MATERIA_FECHAS_PROFESOR "
                        + " WHERE ANIO = ").append(smciclo).append(""
                        + " SELECT DISTINCT (case when  enccal.anio is null then concat(MAT.ANIO,'-',MAT.ciclo,'-',MAT.CODIGO_MATERIA,'-',MAT.CODIGO_PROFESOR,'-',MAT.codigo_nivel,'-',MAT.codigo_esp, '-', MAT.COD_PARALELO) else concat(ENCCAL.ANIO,'-',ENCCAL.ciclo,'-',ENCCAL.CODIGO_MATERIA,'-',ENCCAL.CODIGO_ENCUESTA,'-',ENCCAL.CODIGO_PROFESOR,'-',ENCCAL.codigo_nivel,'-',ENCCAL.codigo_esp, '-',enccal.CODIGO_PARALELO) end)cod,"
                        + " (case when enccal.ANIO is null then mat.anio else enccal.ANIO end) ANIO, "
                        + " (CASE WHEN enccal.CICLO IS NULL THEN 1 ELSE enccal.CICLO END)CICLO,"
                        + " (CASE WHEN enccal.CODIGO_MATERIA IS NULL THEN mat.CODIGO_MATERIA ELSE enccal.CODIGO_MATERIA END ) CODIGO_MATERIA,"
                        + " (CASE WHEN enccal.CODIGO_PROFESOR IS NULL THEN mat.CODIGO_PROFESOR ELSE enccal.CODIGO_PROFESOR END) CODIGO_PROFESOR,"
                        + " (CASE WHEN enccal.CODIGO_ENCUESTA is null then 17 else enccal.CODIGO_ENCUESTA end ) CODIGO_ENCUESTA,"
                        + " (CASE WHEN enccal.cod_estudiante IS NULL  THEN -1 ELSE enccal.cod_estudiante END) cod_estudiante ,"
                        + " (CASE WHEN enccal.codigo_esp IS NULL  THEN mat.CODIGO_ESP ELSE enccal.codigo_esp END) CODIGO_ESP, "
                        + " (CASE WHEN enccal.codigo_nivel IS NULL  THEN mat.CODIGO_NIVEL ELSE enccal.codigo_nivel END)CODIGO_NIVEL, "
                        + " enccal.FECHA_INICIO, "
                        + " enccal.FECHA_FIN, "
                        + " (case enccal.ESTADO_ENCUESTA when 'A' then 'Abierta' when'C' then 'Cerrada' end )ESTADO_ENCUESTA ,"
                        + " concat(mat.APELLIDO_PROFESOR,' ', mat.nombre_profesor)  profesor, "
                        + " mat.NOMBRE_MATERIA, "
                        + " ENC.TITULO,  "
                        + " MATEFECHA.FECHA_INICIAL, "
                        + " MATEFECHA.FECHA_FINAL, "
                        + " MAT.PROGRAMA, "
                        + " MAT.COD_PARALELO, "
                        + " MAT.NOMBRE_PARALELO"
                        + " FROM @MATERIA as mat LEFT OUTER JOIN evaluacion.ENCUESTA_CALENDARIO as enccal  "
                        + " ON mat.ANIO =  enccal.ANIO  "
                        + " AND mat.CICLO = enccal.CICLO  "
                        + " AND mat.CODIGO_ESP = enccal.CODIGO_ESP "
                        + " AND mat.CODIGO_nivel = enccal.CODIGO_nivel "
                        + " AND mat.CODIGO_MATERIA = enccal.CODIGO_MATERIA "
                        + " AND mat.CODIGO_PROFESOR = enccal.CODIGO_PROFESOR "
                        + " AND mat.COD_PARALELO = enccal.CODIGO_PARALELO "
                        + " AND (ENCCAL.tipo_encuesta = 'A' or ENCCAL.tipo_encuesta is null)  LEFT OUTER JOIN EVALUACION.ENCUESTA ENC "
                        + " ON ENCCAL.CODIGO_ENCUESTA = ENC.CODIGO_ENCUESTA INNER JOIN @MATERIAFECHA AS MATEFECHA  "
                        + " ON mat.ANIO  = MATEFECHA.ANIO "
                        + " AND mat.CODIGO_ESP = MATEFECHA.CODIGO_ESP "
                        + " AND mat.CODIGO_MATERIA = MATEFECHA.CODIGO_MATERIA "
                        + " AND mat.CODIGO_NIVEL = MATEFECHA.CODIGO_NIVEL "
                        + " AND mat.CODIGO_PROFESOR = MATEFECHA.COD_PROFESOR "
                        + " AND mat.COD_PARALELO = MATEFECHA.COD_PARALELO "
                        + " WHERE MATEFECHA.TIP_ASIGNATURA <>'T'"
                        + " AND mat.ANIO = ").append(smciclo).append(""
                        + " AND mat.CICLO = 1   "
                        + " AND mat.CODIGO_NIVEL =").append(trimestre).append(""
                        + " AND mat.CODIGO_FACULTAD =").append(area).append(""
                        + " AND (ENCCAL.eSTADO_ENCUESTA <>'C' or ENCCAL.eSTADO_ENCUESTA is null) "
                        + " ORDER BY ESTADO_ENCUESTA, enccal.FECHA_FIN desc ");

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[21];
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
                asign[14] = (object[14] == null ? null : object[14].toString());
                /*ASIGNO EL TIPO DE ENCUESTA "A" PARA ASIGNATURA */
                asign[15] = "A";
                asign[16] = (object[18] == null ? null : object[18].toString());//CODIGO_PARALELO
                asign[17] = (object[15] == null ? null : formato.format(object[15]));
                asign[18] = (object[16] == null ? null : formato.format(object[16]));
                asign[19] = (object[17] == null ? null : object[17].toString());
                asign[20] = (object[19] == null ? null : object[19].toString());
                listAsignaturaReapertura.add(i, asign);

            }
        }
    }

    // </editor-fold> 
    public void trimestrevalueChangeListener() {
        if (sarea != null && strimestre != null) {
            recuperaAsignatura(Long.valueOf(smciclo), Integer.valueOf(sarea), Integer.valueOf(strimestre));
        }
    }

    public void areavalueChangeListener() {
        strimestre = null;
    }

    public void onRowEdit(RowEditEvent event) {
        selectedasignatura = (String[]) event.getObject();
        if (sencuesta != null) {
            selectedasignatura[5] = sencuesta;
        }
        selectedasignatura[9] = formato.format(fecinicio);
        encuestaCalendarioFacade.reaperturaEncuesta(selectedasignatura);
        RequestContext.getCurrentInstance().update("freapertura:tcalendario");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
        JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
        trimestrevalueChangeListener();

    }

    public void ciclovalueChangeListener() {
        sarea = null;
        strimestre = null;
        if (smciclo != null) {
            listtrimestre = consultaFacade.trimestreAnio(Long.valueOf(smciclo));
        }
    }
}
