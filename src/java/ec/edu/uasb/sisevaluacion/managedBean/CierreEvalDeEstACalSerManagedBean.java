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
@ManagedBean(name = "cierreEvalDeEstACalSer")
@ViewScoped
public class CierreEvalDeEstACalSerManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private List<String[]> listtrimestre = new ArrayList<String[]>();
    private List<String[]> listCalServiCierre = new ArrayList<String[]>();
    private List<String[]> listCalServiFiltro;
    private List<String[]> listCalServiFiltroVac;
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

    public List<String[]> getListCalServiCierre() {
        return listCalServiCierre;
    }

    public void setListCalServiCierre(List<String[]> listCalServiCierre) {
        this.listCalServiCierre = listCalServiCierre;
    }

    public List<String[]> getListCalServiFiltro() {
        return listCalServiFiltro;
    }

    public void setListCalServiFiltro(List<String[]> listCalServiFiltro) {
        this.listCalServiFiltro = listCalServiFiltro;
    }

    public List<String[]> getListCalServiFiltroVac() {
        return listCalServiFiltroVac;
    }

    public void setListCalServiFiltroVac(List<String[]> listCalServiFiltroVac) {
        this.listCalServiFiltroVac = listCalServiFiltroVac;
    }

    // </editor-fold> 
    public CierreEvalDeEstACalSerManagedBean() {
        this.setPaginaMant("/pages/evaluacion/calidadServicios/cierreCalidadServicios");
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
    private void recuperaAsignaturaCierre(Long anio, int trimestre) {
        Vector v = new Vector();
        listCalServiCierre.clear();
        StringBuilder sql = new StringBuilder();
        sql.append("DECLARE @NIVELACADEMICO TABLE (CODIGO_ESP NUMERIC(7,0),TIPO_NIVEACAD NUMERIC(7,0), CODIGO_NIVEL NUMERIC(7,0), ANIO INT, FECHA_INICIAL DATETIME, FECHA_FINAL DATETIME ) "
                +"INSERT  INTO @NIVELACADEMICO(CODIGO_ESP, TIPO_NIVEACAD, CODIGO_NIVEL, ANIO,FECHA_INICIAL,FECHA_FINAL ) "
                +"SELECT DISTINCT NIVESP.CODIGO_ESP, NIVESP.TIPO_NIVEACAD, NIVESP.CODIGO_NIVEL, NIVESP.ANIO, NIVESP.FECHA_INICIAL, NIVESP.FECHA_FINAL "
                +"FROM NIVEL_ESPECIALIZACION NIVESP  "
                +"WHERE NIVESP.ANIO =").append(smciclo).append(" "
                +"AND NIVESP.CODIGO_NIVEL = ").append(strimestre).append(" "
                +"DECLARE @EST TABLE (TOTES NUMERIC(7,0), ANIO INT) "
                +"INSERT  INTO @EST (TOTES, ANIO) "
                +"SELECT COUNT( DISTINCT CODIGO_ESTUDIANTE),ANIO "
                +"FROM VISTA_ESTUDIANTES_MATERIA_PROFESOR "
                +"WHERE ANIO = ").append(smciclo).append(" "
                +"GROUP BY ANIO "
                +"SELECT  DISTINCT (case when enccal.anio is null then CONCAT(NIVESP.ANIO,'-',NIVESP.codigo_nivel) else concat(ENCCAL.ANIO,'-',ENCCAL.CODIGO_ENCUESTA,'-',ENCCAL.CODIGO_MATERIA,'-',ENCCAL.CODIGO_PROFESOR,'-',ENCCAL.codigo_nivel,'-',ENCCAL.codigo_esp) end) cod, "
                +"isnull (ENCCAL.ANIO, NIVESP.ANIO) anio, "
                +"1 as ciclo, "
                +"ISNULL (ENCCAL.CODIGO_MATERIA, -1) codigo_materia, "
                +"ISNULL(ENCCAL.CODIGO_PROFESOR, -1) codigo_profesor, "
                +"ISNULL (ENCCAL.CODIGO_ENCUESTA,23) codigo_encuesta,  "
                +"ISNULL(ENCCAL.cod_estudiante, -1)cod_estudiante, "
                +"ISNULL(ENCCAL.codigo_esp, -1) codigo_esp, "
                +"ISNULL(ENCCAL.codigo_nivel, NIVESP.CODIGO_NIVEL) codigo_nivel, "
                +"ISNULL(ENCCAL.tipo_encuesta, 'S') tipo_encuesta, "
                +"'' programa, "
                +"'' NOMBRE_MATERIA, "
                +"'' TRIMESTRE, "
                +"'ximena.torres@uasb.edu.ec; johana.orozco@uasb.edu.ec' mail, "
                +"-1 CODIGO_COORDINADOR, "
                +"-1 CODIGO_PARALELO, "
                +"' ' NOMBRE_PARALELO, "
                +"'' NOMBRE_PROFESOR,  "
                +"ROUND(( convert(float,isnull((SELECT COUNT(DISTINCT CODIGO_ALUMNO) "
                +"  FROM EVALUACION.ENCUESTA_REALIZADA AS ENCRE "
                +"  WHERE ENCRE.CODIGO_ENCUESTA = ENCCAL.CODIGO_ENCUESTA "
                +"  AND ENCRE.ANIO = ENCCAL.ANIO "
                +"  AND ENCRE.CODIGO_NIVEL= -1 "
                +"  AND ENCRE.CODIGO_MATERIA= ENCCAL.CODIGO_MATERIA  "
                +"  AND ENCRE.CODIGO_PROFESOR= ENCCAL.CODIGO_PROFESOR), 0))/ "
                +"  CONVERT(float,ISNULL((SELECT TOTEST.TOTES "
                +"    FROM @EST TOTEST "
                +"    WHERE TOTEST.ANIO = NIVESP.ANIO), 1)))*100, 2) PORC, "
                +"ISNULL(ENCCAL.ESTADO_ENCUESTA, 'A') estado_encuesta, "
                +"ENCCAL.FECHA_FIN "
                +"FROM @NIVELACADEMICO NIVESP  "
                +"INNER JOIN evaluacion.ENCUESTA_CALENDARIO as ENCCAL ON ENCCAL.ANIO = NIVESP.ANIO "
                +"AND ENCCAL.CODIGO_NIVEL = NIVESP.CODIGO_NIVEL  "
                +"AND ENCCAL.COD_ESTUDIANTE = -1 "
                +"AND ENCCAL.CODIGO_ESP = -1 "
                +"AND ENCCAL.CODIGO_PROFESOR = -1 "
                +"AND ENCCAL.CODIGO_MATERIA = -1 "
                +"AND  (ENCCAL.tipo_encuesta = 'S' OR ENCCAL.tipo_encuesta IS NULL) "
                +"WHERE NIVESP.ANIO =").append(smciclo).append(" "
                +"AND (ENCCAL.eSTADO_ENCUESTA <>'C' OR ENCCAL.ESTADO_ENCUESTA IS NULL) "
                +"AND NIVESP.CODIGO_NIVEL = ").append(strimestre).append(" "
                +"ORDER BY FECHA_FIN");
       /* sql.append(" SELECT  DISTINCT (case when enccal.anio is null then CONCAT(NIVESP.ANIO,'-',NIVESP.codigo_nivel) else concat(ENCCAL.ANIO,'-',ENCCAL.CODIGO_ENCUESTA,'-',ENCCAL.CODIGO_MATERIA,'-',ENCCAL.CODIGO_PROFESOR,'-',ENCCAL.codigo_nivel,'-',ENCCAL.codigo_esp) end) cod, "
                + " isnull (ENCCAL.ANIO, NIVESP.ANIO) anio, "
                + " 1 as ciclo, "
                + " ISNULL (ENCCAL.CODIGO_MATERIA, -1) codigo_materia, "
                + " ISNULL(ENCCAL.CODIGO_PROFESOR, -1) codigo_profesor, "
                + " isnull (ENCCAL.CODIGO_ENCUESTA,23) codigo_encuesta,  "
                + " ISNULL(ENCCAL.cod_estudiante, -1)cod_estudiante, "
                + " ISNULL(ENCCAL.codigo_esp, -1) codigo_esp, "
                + " ISNULL(ENCCAL.codigo_nivel, NIVESP.CODIGO_NIVEL) codigo_nivel, "
                + " ISNULL(ENCCAL.tipo_encuesta, 'S') tipo_encuesta, "
                + " '' programa, "
                + " '' NOMBRE_MATERIA, "
                + " '' TRIMESTRE, "
                + " 'ximena.torres@uasb.edu.ec; johana.orozco@uasb.edu.ec' mail, "
                + " -1 CODIGO_COORDINADOR, "
                + " -1 CODIGO_PARALELO,"
                + " ' ' NOMBRE_PARALELO,"
                + " '' NOMBRE_PROFESOR,  "
                + " round(( convert(float,isnull((select count(distinct CODIGO_ALUMNO) from EVALUACION.ENCUESTA_REALIZADA as ENCRE "
                + "  where ENCRE.CODIGO_ENCUESTA = ENCCAL.CODIGO_ENCUESTA "
                + "  AND ENCRE.ANIO = ENCCAL.ANIO "
                + "  AND ENCRE.CODIGO_NIVEL= -1 "
                + "  AND ENCRE.CODIGO_MATERIA= ENCCAL.CODIGO_MATERIA  "
                + "  AND ENCRE.CODIGO_PROFESOR= ENCCAL.CODIGO_PROFESOR), 0))/ "
                + "  convert(float,isnull((select  count( distinct CODIGO_ESTUDIANTE) "
                + "   from VISTA_ESTUDIANTES_MATERIA_PROFESOR "
                + "   where ANIO = NIVESP.ANIO), 1)))*100, 2) PORC, "
                + " ISNULL(ENCCAL.ESTADO_ENCUESTA, 'A') estado_encuesta,"
                + " ENCCAL.FECHA_FIN"
                + " from NIVEL_ESPECIALIZACION NIVESP INNER JOIN evaluacion.ENCUESTA_CALENDARIO as ENCCAL "
                + " ON ENCCAL.ANIO = NIVESP.ANIO "
                + " AND ENCCAL.CODIGO_NIVEL = NIVESP.CODIGO_NIVEL "
                + " AND ENCCAL.COD_ESTUDIANTE = -1 "
                + " AND ENCCAL.CODIGO_ESP = -1 "
                + " AND ENCCAL.CODIGO_PROFESOR = -1 "
                + " AND ENCCAL.CODIGO_MATERIA = -1 "
                + " AND  (ENCCAL.tipo_encuesta = 'S' or ENCCAL.tipo_encuesta is null) "
                + " WHERE    NIVESP.ANIO =").append(smciclo).append(" "
                        + " AND (ENCCAL.eSTADO_ENCUESTA <>'C' or ENCCAL.eSTADO_ENCUESTA is null) "
                        + " and NIVESP.CODIGO_NIVEL = ").append(strimestre).append(" "
                        + " order bY FECHA_FIN");*/

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
                asign[9] = (object[9] == null ? null : object[9].toString());
                asign[10] = (object[10] == null ? null : object[10].toString());
                asign[11] = (object[11] == null ? null : object[11].toString());
                asign[12] = (object[12] == null ? null : object[12].toString());
                asign[13] = (object[13] == null ? null : object[13].toString());
                asign[14] = (object[14] == null ? null : object[14].toString());
                asign[15] = (object[15] == null ? null : object[15].toString());// CODIGO PARALELO                
                asign[16] = (object[16] == null ? null : object[16].toString()); // NOMBRE PARALELO
                asign[17] = (object[17] == null ? null : object[17].toString());
                asign[18] = (object[18] == null ? null : object[18].toString());
                asign[19] = (object[19] == null ? null : object[19].toString());
                asign[20] = (object[20] == null ? null : formato.format(object[20]));
                listCalServiCierre.add(i, asign);
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
        if (listCalServiFiltroVac != null) {
            listCalServiFiltro = listCalServiFiltroVac;
        }

        if (listCalServiFiltro == null) {
            listCalServiFiltro = listCalServiCierre;
        } else {
            listCalServiFiltroVac = listCalServiFiltro;
        }
        listCalServiFiltroVac = listCalServiFiltro;
        if (sfiltro != null && !sfiltro.isEmpty()) {
            dfiltro = Double.valueOf(sfiltro);
            //  listAsignaturaFiltroVac = listAsignaturaFiltro;
            if (listCalServiFiltro.size() > 0) {
                for (int i = 0; i < listCalServiFiltro.size(); i++) {
                    String[] asign;
                    asign = new String[21];
                    asign = listCalServiFiltro.get(i);
                    if (Double.valueOf(asign[18]) >= dfiltro) {
                        listAsignaturaFiltroAux.add(j, asign);
                        j++;
                    }
                }
            }
            listCalServiFiltro = listAsignaturaFiltroAux;

        } else {
            listCalServiFiltro = listCalServiFiltroVac;
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
                    asign = new String[21];
                    asign = selectedasigcierre.get(i);
                    encuestaCalendarioFacade.cierreEncuesta(asign);
                }

                RequestContext.getCurrentInstance().update("fcierreCalSer:tcierreCalSer");
                RequestContext.getCurrentInstance().execute("mantWidget.hide();");
                JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
                trimestervalueChangeListener();
                CancelCierre();
            }
        }

    }
    // </editor-fold> 

    public void CancelCierre() {
        selectedasigcierre = new ArrayList<String[]>();
        sfiltro = null;
        listCalServiFiltro = listCalServiCierre;
    }

    public void ciclovalueChangeListener() {
        strimestre = null;
        if (smciclo != null) {
            listtrimestre = consultaFacade.trimestreAnio(Long.valueOf(smciclo));
        }
    }
}
