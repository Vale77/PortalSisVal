/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import ec.edu.uasb.sisevaluacion.session.EncuestaCalendarioFacadeLocal;
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
@ManagedBean(name = "cierreEvalDeCordADoc")
@ViewScoped
public class CierreEvalCordADocManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    private List<String[]> listtrimestre = new ArrayList<String[]>();
    private List<String[]> listEvalDeCordADocCierre = new ArrayList<String[]>();
    private List<String[]> listEvalDeCordADocFiltro;
    private List<String[]> listEvalDeCordADocFiltroVac;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private String strimestre = null;
    private String sfiltro = null;
    private String smciclo = null;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private EncuestaCalendarioFacadeLocal encuestaCalendarioFacade;

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public SimpleDateFormat getFormato() {
        return formato;
    }

    public void setFormato(SimpleDateFormat formato) {
        this.formato = formato;
    }

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    public List<String[]> getListtrimestre() {
        return listtrimestre;
    }

    public void setListtrimestre(List<String[]> listtrimestre) {
        this.listtrimestre = listtrimestre;
    }

    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
    }

    public String getStrimestre() {
        return strimestre;
    }

    public void setStrimestre(String strimestre) {
        this.strimestre = strimestre;
    }

    public String getSfiltro() {
        return sfiltro;
    }

    public void setSfiltro(String sfiltro) {
        this.sfiltro = sfiltro;
    }

    public List<String[]> getListEvalDeCordADocCierre() {
        return listEvalDeCordADocCierre;
    }

    public void setListEvalDeCordADocCierre(List<String[]> listEvalDeCordADocCierre) {
        this.listEvalDeCordADocCierre = listEvalDeCordADocCierre;
    }

    public List<String[]> getListEvalDeCordADocFiltro() {
        return listEvalDeCordADocFiltro;
    }

    public void setListEvalDeCordADocFiltro(List<String[]> listEvalDeCordADocFiltro) {
        this.listEvalDeCordADocFiltro = listEvalDeCordADocFiltro;
    }

    public List<String[]> getListEvalDeCordADocFiltroVac() {
        return listEvalDeCordADocFiltroVac;
    }

    public void setListEvalDeCordADocFiltroVac(List<String[]> listEvalDeCordADocFiltroVac) {
        this.listEvalDeCordADocFiltroVac = listEvalDeCordADocFiltroVac;
    }

    // </editor-fold> 
    public CierreEvalCordADocManagedBean() {
        this.setPaginaMant("/pages/evaluacion/coordinador/cierreAsignatura");
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();

    }

// <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private List<String[]> selectedCordADoc = new ArrayList<String[]>();

    public List<String[]> getSelectedCordADoc() {
        return selectedCordADoc;
    }

    public void setSelectedCordADoc(List<String[]> selectedCordADoc) {
        this.selectedCordADoc = selectedCordADoc;
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="CARGAR TABLA ASIGNATURAS PARA CIERRE ">   
    private void recuperaAsignaturaCierre(Long anio, int trimestre) {
        Vector v = new Vector();
        listEvalDeCordADocCierre.clear();
        StringBuilder sql = new StringBuilder();
        sql.append("DECLARE @MATERIA TABLE ( CODIGO_PROFESOR NUMERIC(8,0), CODIGO_COORDINADOR NUMERIC(8,0),  NOMBRE_COORDINADOR VARCHAR(60), APELLIDO_COORDINADOR VARCHAR(65),  AREA VARCHAR(75), CODIGO_NIVEL INT, PROGRAMA VARCHAR(500),  "
                + " CODIGO_ESP VARCHAR(4), CODIGO_MATERIA NUMERIC(9,0), ANIO INT,   TRIMESTRE VARCHAR(75), EMAIL_COORDINADOR VARCHAR(100), CICLO VARCHAR(384))	 "
                + " INSERT INTO @MATERIA(CODIGO_PROFESOR, CODIGO_COORDINADOR,  NOMBRE_COORDINADOR, APELLIDO_COORDINADOR,  AREA, CODIGO_NIVEL, PROGRAMA,  "
                + "				CODIGO_ESP,  CODIGO_MATERIA, ANIO,   TRIMESTRE,EMAIL_COORDINADOR, CICLO) "
                + " SELECT DISTINCT  CODIGO_PROFESOR, CODIGO_COORDINADOR,  NOMBRE_COORDINADOR, APELLIDO_COORDINADOR,  AREA, CODIGO_NIVEL, PROGRAMA,"
                + "	CODIGO_ESP,  CODIGO_MATERIA, ANIO,   TRIMESTRE,EMAIL_COORDINADOR, CICLO"
                + " FROM dbo.VISTA_COORDINADOR_PROGRAMA "
                + " WHERE ANIO =  ").append(anio).append(" "
                        + " select  DISTINCT MATERIA.codigo_esp cod,"
                        + " isnull (ENCCAL.ANIO, MATERIA.ANIO) anio,"
                        + " 1 as ciclo, "
                        + " '' codigo_materia,  "
                        + " '' codigo_profesor,  "
                        + " isnull (ENCCAL.CODIGO_ENCUESTA,20) codigo_encuesta, "
                        + " ISNULL(ENCCAL.cod_estudiante, -1)cod_estudiante, "
                        + " ISNULL(ENCCAL.codigo_esp, MATERIA.CODIGO_ESP) codigo_esp, "
                        + " ISNULL(ENCCAL.codigo_nivel, MATERIA.CODIGO_NIVEL) codigo_nivel, "
                        + " ISNULL(ENCCAL.tipo_encuesta, 'C') tipo_encuesta, "
                        + " MATERIA.PROGRAMA programa, "
                        + " '' NOMBRE_MATERIA,  "
                        + " MATERIA.TRIMESTRE, "
                        + " isnull( materia.EMAIL_COORDINADOR, 'johana.orozco@uasb.edu.ec') mail, "
                        + " materia.CODIGO_COORDINADOR ,"
                        + " -1 COD_PARALELO,"
                        + " ' ' NOMBRE_PARALELO, "
                        + " concat(APELLIDO_COORDINADOR,' ',NOMBRE_COORDINADOR) NOMBRE_PROFESOR,"
                        + " isnull(round(convert(float,(isnull((select count(*) from EVALUACION.ENCUESTA_REALIZADA as ENCRE "
                        + "   where ENCRE.CODIGO_ENCUESTA = ENCCAL.CODIGO_ENCUESTA "
                        + "   AND ENCRE.ANIO = ENCCAL.ANIO"
                        + "   AND ENCRE.CODIGO_ESP = ENCCAL.CODIGO_ESP"
                        + "   AND ENCRE.CODIGO_NIVEL= ENCCAL.CODIGO_NIVEL "
                        + "   AND ENCRE.CODIGO_ALUMNO = MATERIA.CODIGO_COORDINADOR "
                        + "   AND ENCRE.TIPO_REGISTRO='C'),0)/ convert(float,isnull((select count(1) from @MATERIA mat where "
                        + "   mat.ANIO = MATERIA.anio "
                        + "   AND mat.CODIGO_ESP = MATERIA.codigo_esp "
                        + "   AND mat.CODIGO_NIVEL = MATERIA.codigo_nivel "
                        + "  AND mat.CODIGO_COORDINADOR  = MATERIA.CODIGO_COORDINADOR),1)))*100),2),0) PORC, "
                        + " ISNULL(ENCCAL.ESTADO_ENCUESTA, 'A') estado_encuesta, "
                        + " ENCCAL.FECHA_FIN, "
                        + " '' FECHA_INICIAL, "
                        + " '' FECHA_FINAL "
                        + " from @MATERIA  MATERIA LEFT outer JOIN EVALUACION.ENCUESTA_CALENDARIO  ENCCAL "
                        + " ON MATERIA.ANIO =  ENCCAL.ANIO   "
                        + " AND MATERIA.CICLO = ENCCAL.CICLO    "
                        + " AND MATERIA.codigo_esp = ENCCAL.codigo_esp    "
                        + " AND MATERIA.CODIGO_NIVEL = ENCCAL.CODIGO_NIVEL    "
                        + " AND MATERIA.CODIGO_PROFESOR = ENCCAL.CODIGO_PROFESOR    "
                        + " AND MATERIA.CODIGO_MATERIA = ENCCAL.CODIGO_MATERIA   "
                        + " WHERE    MATERIA.ANIO =").append(anio).append("  "
                        + " AND MATERIA.CICLO =  1   "
                        + " AND MATERIA.CODIGO_NIVEL = ").append(trimestre).append("  "
                        + " AND (ENCCAL.tipo_encuesta = 'C' or ENCCAL.tipo_encuesta is null) "
                        + " AND (ENCCAL.eSTADO_ENCUESTA <>'C' or ENCCAL.eSTADO_ENCUESTA is null) "
                        + " order bY FECHA_FIN");

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[23];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                asign[2] = (object[2] == null ? null : object[2].toString());
                asign[3] = (object[3] == null ? null : object[3].toString());
                asign[4] = (object[4] == null ? null : object[4].toString());
                asign[5] = (object[5] == null ? null : object[5].toString());
                asign[6] = (object[6] == null ? null : object[6].toString());
                asign[7] = (object[7] == null ? null : object[7].toString());
                asign[8] = (object[8] == null ? null : object[8].toString());
                asign[9] = (object[9] == null ? null : object[9].toString());
                asign[10] = (object[10] == null ? null : object[10].toString());
                asign[11] = (object[11] == null ? null : object[11].toString());
                asign[12] = (object[12] == null ? null : object[12].toString());
                asign[13] = (object[13] == null ? null : object[13].toString());
                asign[14] = (object[14] == null ? null : object[14].toString());//CODIGO COORDINADOR
                asign[15] = (object[15] == null ? null : object[15].toString());// CODIGO PARALELO                
                asign[16] = (object[16] == null ? null : object[16].toString()); // NOMBRE PARALELO
                asign[17] = (object[17] == null ? null : object[17].toString());
                asign[18] = (object[18] == null ? null : object[18].toString());
                asign[19] = (object[19] == null ? null : object[19].toString());
                asign[20] = (object[20] == null ? null : formato.format(object[20]));
                asign[21] = (object[21] == null ? null : object[21].toString());
                asign[22] = (object[22] == null ? null : object[22].toString());

                listEvalDeCordADocCierre.add(i, asign);
            }
        }

    }
    // </editor-fold> 

    public void trimestervalueChangeListener() {
        if (strimestre != null) {
            recuperaAsignaturaCierre(Long.valueOf(smciclo), Integer.valueOf(strimestre));
        }
    }

// <editor-fold defaultstate="collapsed" desc="FILTRO LOS VALORES DEL PORCENTAJ ">   
    public void filtrarValorvalueChangeListener() {
        Double dfiltro;
        int j = 0;
        List<String[]> listAsignaturaFiltroAux = new ArrayList<String[]>();
        listAsignaturaFiltroAux.clear();
        if (listEvalDeCordADocFiltroVac != null) {
            listEvalDeCordADocFiltro = listEvalDeCordADocFiltroVac;
        }
        if (listEvalDeCordADocFiltro == null) {
            listEvalDeCordADocFiltro = listEvalDeCordADocCierre;
        } else {
            listEvalDeCordADocFiltroVac = listEvalDeCordADocFiltro;
        }
        listEvalDeCordADocFiltroVac = listEvalDeCordADocFiltro;
        if (sfiltro != null && !sfiltro.isEmpty()) {
            dfiltro = Double.valueOf(sfiltro);
            //  listAsignaturaFiltroVac = listAsignaturaFiltro;
            if (listEvalDeCordADocFiltro.size() > 0) {
                for (int i = 0; i < listEvalDeCordADocFiltro.size(); i++) {
                    String[] asign;
                    asign = new String[23];
                    asign = listEvalDeCordADocFiltro.get(i);
                    if (Double.valueOf(asign[18]) >= dfiltro) {
                        listAsignaturaFiltroAux.add(j, asign);
                        j++;
                    }
                }
            }
            listEvalDeCordADocFiltro = listAsignaturaFiltroAux;

        } else {
            listEvalDeCordADocFiltro = listEvalDeCordADocFiltroVac;
        }
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  
    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        StringBuilder sql = new StringBuilder();
        Vector v = new Vector();
        if (selectedCordADoc != null) {
            if (selectedCordADoc.size() > 0) {
                for (int i = 0; i < selectedCordADoc.size(); i++) {

                    String[] asign;
                    asign = new String[21];
                    asign = selectedCordADoc.get(i);
                    sql = new StringBuilder();
                    sql.append("SELECT DISTINCT CODIGO_MATERIA, CODIGO_PROFESOR"
                            + " from vista_coordinador_programa"
                            + " where anio = ").append(smciclo).append(""
                                    + " and codigo_esp = ").append(asign[0]).append(""
                                    + " and codigo_coordinador = ").append(asign[14]).append(""
                                    + " and codigo_nivel =  ").append(Integer.valueOf(strimestre)).append("");

                    v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
                    if (v.size() > 0) {
                        for (int j = 0; j < v.size(); j++) {
                            Object[] object = (Object[]) v.get(j);
                            asign[3] = (object[0] == null ? null : object[0].toString());
                            asign[4] = (object[1] == null ? null : object[1].toString());
                            encuestaCalendarioFacade.cierreEncuesta(asign);
                        }
                    }
                }
                RequestContext.getCurrentInstance().update("fcierreEvalCoordDoc:tcierreEvalCoordDoc");
                RequestContext.getCurrentInstance().execute("mantWidget.hide();");
                JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
                trimestervalueChangeListener();
                CancelCierre();
            }
        }

    }
    // </editor-fold> 

    public void CancelCierre() {
        selectedCordADoc = new ArrayList<String[]>();
        sfiltro = null;
        listEvalDeCordADocFiltro = listEvalDeCordADocCierre;
    }

    public void ciclovalueChangeListener() {
        strimestre = null;
        if (smciclo != null) {
            listtrimestre = consultaFacade.trimestreAnio(Long.valueOf(smciclo));
        }
    }
}
