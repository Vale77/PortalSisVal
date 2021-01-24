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
@ManagedBean(name = "reaperturaEvalDeCordADoc")
@ViewScoped
public class ReperturaEvalCordADocManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private List<String[]> listtrimestre = new ArrayList<String[]>();
    private List<String[]> listarea = new ArrayList<String[]>();
    private List<String[]> listCorADocReapertura = new ArrayList<String[]>();
    private List<String[]> listCorADocFiltro;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private List<Encuesta> listEncuesta = new ArrayList<Encuesta>();
    private String strimestre = null;
    private String sarea = null;
    private String smciclo = null;
    private String sencuesta = null;
    private Date fecinicio = null;
    @EJB
    private EncuestaCalendarioFacadeLocal encuestaCalendarioFacade;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private EncuestaFacadeLocal encuestaFacade;

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public List<String[]> getListCorADocReapertura() {
        return listCorADocReapertura;
    }

    public void setListCorADocReapertura(List<String[]> listCorADocReapertura) {
        this.listCorADocReapertura = listCorADocReapertura;
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

    public List<String[]> getListCorADocFiltro() {
        return listCorADocFiltro;
    }

    public void setListCorADocFiltro(List<String[]> listCorADocFiltro) {
        this.listCorADocFiltro = listCorADocFiltro;
    }

    // </editor-fold> 
    public ReperturaEvalCordADocManagedBean() {
        this.setPaginaMant("/pages/evaluacion/coordinador/reaperturaEvalDeCordADocente");
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

    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private String[] selectedCordADoc = new String[12];

    public String[] getSelectedCordADoc() {
        return selectedCordADoc;
    }

    public void setSelectedCordADoc(String[] selectedCordADoc) {
        this.selectedCordADoc = selectedCordADoc;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR LAS ENCUESTAS HABILITADAS"> 
    public List<Encuesta> getListEncuesta() {
        listEncuesta.clear();
        listEncuesta = encuestaFacade.findAllActivo('C');
        return listEncuesta;
    }

    public void setListEncuesta(List<Encuesta> listEncuesta) {
        this.listEncuesta = listEncuesta;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA ASIGNATURAS ">   
    private void recuperaAsignatura(Long anio, int area, int trimestre) {
        Vector v = new Vector();
        listCorADocReapertura.clear();
        StringBuilder sql = new StringBuilder();
        sql.append("select  distinct   MATERIA.CODIGO_ESP,"
                + " ENCCAL.FECHA_INICIO, "
                + " ENCCAL.FECHA_FIN,"
                + " MATERIA.PROGRAMA,"
                + " MATERIA.CODIGO_COORDINADOR, "
                + " CONCAT(MATERIA.APELLIDO_COORDINADOR, ' ', MATERIA.NOMBRE_COORDINADOR) AS NOMBRE	,"
                + " (case enccal.ESTADO_ENCUESTA when 'A' then 'Abierta' when'C' then 'Cerrada' end ) estado_encuesta, "
                + " MATERIA.anio, "
                + " MATERIA.codigo_nivel,"
                + " ENC.TITULO,"
                + "(CASE WHEN enccal.CODIGO_ENCUESTA is null then 20 else enccal.CODIGO_ENCUESTA end ) CODIGO_ENCUESTA "                
                + " from dbo.VISTA_COORDINADOR_PROGRAMA MATERIA left outer join EVALUACION.ENCUESTA_CALENDARIO ENCCAL"
                + " on ENCCAL.CICLO = MATERIA.CICLO"
                + " and ENCCAL. ANIO = MATERIA.ANIO"
                + " and ENCCAL.CODIGO_ESP = MATERIA.CODIGO_ESP"
                + " and ENCCAL.CODIGO_MATERIA = MATERIA.CODIGO_MATERIA"
                + " and ENCCAL.CODIGO_PROFESOR = MATERIA.codigo_profesor"
                + " and ENCCAL.CODIGO_NIVEL= MATERIA.CODIGO_NIVEL  "
                + "  and (ENCCAL.tipo_encuesta = 'C' or ENCCAL.tipo_encuesta is null)   LEFT OUTER JOIN EVALUACION.ENCUESTA ENC"
                + "  ON ENCCAL.CODIGO_ENCUESTA = ENC.CODIGO_ENCUESTA "
                + " where MATERIA.anio= ").append(anio).append(""
                        + " AND (ENCCAL.eSTADO_ENCUESTA <>'C' or ENCCAL.eSTADO_ENCUESTA is null)  "
                        + " AND MATERIA.CODIGO_NIVEL = ").append(trimestre).append(" and "
                        + "  MATERIA.CODIGO_FACULTAD = ").append(area).append(" ");

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[12];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : formato.format(object[1]));
                asign[2] = (object[2] == null ? null : formato.format(object[2]));
                asign[3] = (object[3] == null ? null : object[3].toString());
                asign[4] = (object[4] == null ? null : object[4].toString());
                asign[5] = (object[5] == null ? null : object[5].toString());
                asign[6] = (object[6] == null ? null : object[6].toString());
                asign[7] = (object[7] == null ? null : object[7].toString());
                asign[8] = (object[8] == null ? null : object[8].toString());
                asign[9] = (object[9] == null ? null : object[9].toString());
                asign[10] = (object[10] == null ? null : object[10].toString());
                
                
                listCorADocReapertura.add(i, asign);

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
        selectedCordADoc = (String[]) event.getObject();
        if (sencuesta != null) {
            selectedCordADoc[10] = sencuesta;
        }
        selectedCordADoc[11] = formato.format(fecinicio); 
        encuestaCalendarioFacade.reaperturaEvalCoordADocente(selectedCordADoc);
        RequestContext.getCurrentInstance().update("freaperturaCordADoc:tcalendario");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
        JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
        trimestrevalueChangeListener();
    }

    public void ciclovalueChangeListener() {
        strimestre = null;
        sarea = null;
        if (smciclo != null) {
            listtrimestre = consultaFacade.trimestreAnio(Long.valueOf(smciclo));
        }
    }

}
