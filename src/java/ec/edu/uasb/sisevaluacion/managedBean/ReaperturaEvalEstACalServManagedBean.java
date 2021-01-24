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
@ManagedBean(name = "reaperturaEvalDeEstACalServ")
@ViewScoped
public class ReaperturaEvalEstACalServManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private List<String[]> listtrimestre = new ArrayList<String[]>();    
    private List<String[]> listCalSerReapertura = new ArrayList<String[]>();
    private List<String[]> listCalSerFiltro;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private List<Encuesta> listEncuesta = new ArrayList<Encuesta>();
    private String strimestre = null;    
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

    public List<String[]> getListCalSerReapertura() {
        return listCalSerReapertura;
    }

    public void setListCalSerReapertura(List<String[]> listCalSerReapertura) {
        this.listCalSerReapertura = listCalSerReapertura;
    }

    public List<String[]> getListCalSerFiltro() {
        return listCalSerFiltro;
    }

    public void setListCalSerFiltro(List<String[]> listCalSerFiltro) {
        this.listCalSerFiltro = listCalSerFiltro;
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

   
    // </editor-fold> 
    public ReaperturaEvalEstACalServManagedBean() {
        this.setPaginaMant("/pages/evaluacion/calidadServicios/reaperturaCalidadServicios");
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();
    }

    
    // <editor-fold defaultstate="collapsed" desc="CARGAR LAS ENCUESTAS HABILITADAS"> 

    public List<Encuesta> getListEncuesta() {
        listEncuesta.clear();
        listEncuesta = encuestaFacade.findAllActivo('S');
        return listEncuesta;
    }

    public void setListEncuesta(List<Encuesta> listEncuesta) {
        this.listEncuesta = listEncuesta;
    }
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private String[] selectedcalservicio = new String[19];

    public String[] getSelectedcalservicio() {
        return selectedcalservicio;
    }

    public void setSelectedcalservicio(String[] selectedcalservicio) {
        this.selectedcalservicio = selectedcalservicio;
    }

    
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA ASIGNATURAS ">   
    private void recuperaCalServicio(Long anio, int trimestre) {
        Vector v = new Vector();
        listCalSerReapertura.clear();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT (case when enccal.ANIO is null then concat(NIVESP.ANIO, '-', NIVESP.CODIGO_NIVEL) else  concat(ENCCAL.ANIO,'-',ENCCAL.ciclo,'-',ENCCAL.CODIGO_MATERIA,'-',ENCCAL.CODIGO_ENCUESTA,'-',ENCCAL.CODIGO_PROFESOR,'-',ENCCAL.codigo_nivel,'-',ENCCAL.codigo_esp) end)cod,"
                +" (case when enccal.ANIO is null then NIVESP.ANIO else enccal.ANIO end) ANIO,   "
                +" (CASE WHEN enccal.CICLO IS NULL THEN 1 ELSE enccal.CICLO END)CICLO, "
                +" (CASE WHEN enccal.CODIGO_MATERIA IS NULL THEN -1 ELSE enccal.CODIGO_MATERIA END ) CODIGO_MATERIA, "
                +" (CASE WHEN enccal.CODIGO_PROFESOR IS NULL THEN -1 ELSE enccal.CODIGO_PROFESOR END) CODIGO_PROFESOR," 
                +" (CASE WHEN enccal.CODIGO_ENCUESTA is null then 23 else enccal.CODIGO_ENCUESTA end ) CODIGO_ENCUESTA, "
                +" (CASE WHEN enccal.cod_estudiante IS NULL  THEN -1 ELSE enccal.cod_estudiante END) cod_estudiante ,"
                +" (CASE WHEN enccal.codigo_esp IS NULL  THEN -1 ELSE enccal.codigo_esp END) CODIGO_ESP," 
                +" (CASE WHEN enccal.codigo_nivel IS NULL  THEN NIVESP.CODIGO_NIVEL ELSE enccal.codigo_nivel END)CODIGO_NIVEL,"
                +" enccal.FECHA_INICIO,   "
                +" enccal.FECHA_FIN,   "
                +" (case enccal.ESTADO_ENCUESTA when 'A' then 'Abierta' when'C' then 'Cerrada' end )ESTADO_ENCUESTA ,"
                +" '' profesor,"
                +"'' NOMBRE_MATERIA,"
                +" ENC.TITULO "
                +" FROM NIVEL_ESPECIALIZACION NIVESP LEFT OUTER JOIN  evaluacion.ENCUESTA_CALENDARIO as ENCCAL "
                +" ON ENCCAL.ANIO = NIVESP.ANIO "
                +" AND ENCCAL.CODIGO_NIVEL = NIVESP.CODIGO_NIVEL"
                +" AND ENCCAL.CODIGO_ESP = -1  "
                + " AND (ENCCAL.tipo_encuesta = 'S' or ENCCAL.tipo_encuesta is null)  LEFT OUTER JOIN EVALUACION.ENCUESTA ENC"
                +" ON ENCCAL.CODIGO_ENCUESTA = ENC.CODIGO_ENCUESTA  "
                +" WHERE NIVESP.ANIO = ").append(smciclo).append(""
                +" AND NIVESP.CODIGO_NIVEL = ").append(strimestre).append(""                
                +" AND (ENCCAL.eSTADO_ENCUESTA <>'C' or ENCCAL.eSTADO_ENCUESTA is null) "
                +" ORDER BY ESTADO_ENCUESTA, enccal.FECHA_FIN desc" );
         
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
                asign[14] = (object[14] == null ? null : object[14].toString());
                /*ASIGNO EL TIPO DE ENCUESTA "A" PARA ASIGNATURA */
                asign[15] = "S";  
                /*ASIGNO EL CODIGO DE PARALELO -1 PARA ESTA ENCUESTA */
                asign[16] = "-1";
                listCalSerReapertura.add(i, asign);
            }
        }
    }

    // </editor-fold> 
    public void trimestrevalueChangeListener() {
        if ( strimestre != null) {
            recuperaCalServicio(Long.valueOf(smciclo), Integer.valueOf(strimestre));
        }
    }

    public void onRowEdit(RowEditEvent event) {
        selectedcalservicio = (String[]) event.getObject();
        if (sencuesta != null) {
            selectedcalservicio[5] = sencuesta;
        }
        selectedcalservicio[9] = formato.format(fecinicio);        
        encuestaCalendarioFacade.reaperturaEncuesta(selectedcalservicio);
         RequestContext.getCurrentInstance().update("freaperturaCalSer:tcalSer");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
        JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
        trimestrevalueChangeListener();
    }

    public void ciclovalueChangeListener() {        
        strimestre = null;
        if (smciclo != null) {
            listtrimestre = consultaFacade.trimestreAnio(Long.valueOf(smciclo));
        }
    }
}
