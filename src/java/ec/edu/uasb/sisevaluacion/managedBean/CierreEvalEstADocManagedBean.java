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
@ManagedBean(name = "cierreEvalEstADoc")
@ViewScoped
public class CierreEvalEstADocManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    private List<String[]> listtrimestre = new ArrayList<String[]>();
    private List<String[]> listAsignaturaCierre = new ArrayList<String[]>();
    private List<String[]> listAsignaturaFiltro;
    private List<String[]> listAsignaturaFiltroVac;
    private List<String[]> listarea = new ArrayList<String[]>();
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private String strimestre = null;
    private String sfiltro = null;
    private String smciclo = null;
    private String smarea = null;
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

    public String getSmarea() {
        return smarea;
    }

    public void setSmarea(String smarea) {
        this.smarea = smarea;
    }

    public List<String[]> getListarea() {
        return listarea;
    }

    public void setListarea(List<String[]> listarea) {
        this.listarea = listarea;
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

    public List<String[]> getListAsignaturaCierre() {
        return listAsignaturaCierre;
    }

    public void setListAsignaturaCierre(List<String[]> listAsignaturaCierre) {
        this.listAsignaturaCierre = listAsignaturaCierre;
    }

    public List<String[]> getListAsignaturaFiltro() {
        return listAsignaturaFiltro;
    }

    public void setListAsignaturaFiltro(List<String[]> listAsignaturaFiltro) {
        this.listAsignaturaFiltro = listAsignaturaFiltro;
    }

    public List<String[]> getListAsignaturaFiltroVac() {
        return listAsignaturaFiltroVac;
    }

    public void setListAsignaturaFiltroVac(List<String[]> listAsignaturaFiltroVac) {
        this.listAsignaturaFiltroVac = listAsignaturaFiltroVac;
    }

    // </editor-fold> 
    public CierreEvalEstADocManagedBean() {
        this.setPaginaMant("/pages/evaluacion/asignatura/cierreAsignatura");
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();
        //Recupero las areas
        listarea = consultaFacade.area();
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
        listAsignaturaCierre.clear();
        StringBuilder sql = new StringBuilder();
        sql.append(" DECLARE @MATEST TABLE  (ANIO NUMERIC(4,0), CODIGO_ESP NUMERIC(10,0), CODIGO_MATERIA NUMERIC(10,0), CODIGO_PROFESOR NUMERIC(10,0),CODIGO_NIVEL NUMERIC(10,0), NUMEST NUMERIC(10,0), CODIGO_PARALELO INT)"
                + " DECLARE @MATERIA TABLE (CODIGO_PROFESOR NUMERIC(8,0), CED_PAS_PROFESOR VARCHAR(15), NOMBRE_PROFESOR VARCHAR(60), APELLIDO_PROFESOR VARCHAR(65), NOMBRE_MATERIA VARCHAR(200), AREA VARCHAR(75), CODIGO_NIVEL INT, PROGRAMA VARCHAR(500), "
                + " CODIGO_ESP VARCHAR(4), CODIGO_MATERIA NUMERIC(9,0), ANIO INT,   TRIMESTRE VARCHAR(75), EMAIL_PROFESOR VARCHAR(100), CICLO VARCHAR(384), COD_PARALELO INT, NOMBRE_PARALELO VARCHAR(8), COD_AREA NUMERIC(7,0))"
                + " DECLARE @MATERIAFECHA TABLE (CODIGO_ESP NUMERIC(10,0), CODIGO_MATERIA NUMERIC(10,0),CODIGO_NIVEL NUMERIC(10,0), ANIO NUMERIC(4,0), FECHA_INICIAL DATETIME, FECHA_FINAL DATETIME, COD_PARALELO INT, COD_PROFESOR INT,TIP_ASIGNATURA CHAR(1) ) "
                + " INSERT INTO @MATEST(ANIO, CODIGO_ESP , CODIGO_MATERIA, CODIGO_PROFESOR,CODIGO_NIVEL, NUMEST, CODIGO_PARALELO ) "
                + " SELECT DISTINCT ANIO, CODIGO_ESP , CODIGO_MATERIA, CODIGO_PROFESOR,CODIGO_NIVEL, NUMEST, CODIGO_PARALELO "
                + " FROM dbo.VISTA_NUMESTUDIANTE_MATERIA "
                + " where ANIO = ").append(smciclo).append(""
                + " INSERT INTO @MATERIA(CODIGO_PROFESOR, CED_PAS_PROFESOR, NOMBRE_PROFESOR, APELLIDO_PROFESOR, NOMBRE_MATERIA, AREA, CODIGO_NIVEL, PROGRAMA,  "
                + " CODIGO_ESP,  CODIGO_MATERIA, ANIO,   TRIMESTRE,EMAIL_PROFESOR, CICLO, COD_PARALELO , NOMBRE_PARALELO,COD_AREA )"
                + " SELECT DISTINCT CODIGO_PROFESOR, CED_PAS_PROFESOR, NOMBRE_PROFESOR, APELLIDO_PROFESOR, NOMBRE_MATERIA, AREA, CODIGO_NIVEL, PROGRAMA, "
                + " CODIGO_ESP,  CODIGO_MATERIA, ANIO,   TRIMESTRE,EMAIL_PROFESOR, CICLO, COD_PARALELO , NOMBRE_PARALELO,CODIGO_FACULTAD "
                + " FROM dbo.VISTA_COORDINADOR_PROGRAMA "
                + " WHERE ANIO = ").append(smciclo).append(""
                + " INSERT INTO @MATERIAFECHA(CODIGO_ESP, CODIGO_MATERIA,CODIGO_NIVEL , ANIO , FECHA_INICIAL , FECHA_FINAL, COD_PARALELO , COD_PROFESOR,TIP_ASIGNATURA ) "
                + " SELECT CODIGO_ESP, CODIGO_MATERIA,CODIGO_NIVEL , ANIO , FECHA_INICIAL , FECHA_FINAL, COD_PARALELO , COD_PROFESOR,TIP_ASIGNATURA "
                + " FROM VISTA_MATERIA_FECHAS_PROFESOR "
                + " WHERE ANIO = ").append(smciclo).append(""
                + " SELECT  DISTINCT (case when enccal.anio is null then CONCAT(MATERIA.ANIO,'-',MATERIA.CODIGO_MATERIA,'-', MATERIA.CODIGO_PROFESOR,'-',MATERIA.codigo_nivel,'-',MATERIA.codigo_esp, '-', MATERIA.COD_PARALELO) else concat(ENCCAL.ANIO,'-',ENCCAL.CODIGO_MATERIA,'-',ENCCAL.CODIGO_ENCUESTA,'-',ENCCAL.CODIGO_PROFESOR,'-',ENCCAL.codigo_nivel,'-',ENCCAL.codigo_esp,'-', ENCCAL.CODIGO_PARALELO) end) cod, "
                + " isnull (ENCCAL.ANIO, MATERIA.ANIO) anio, "
                + " 1 as ciclo, "
                + " ISNULL (ENCCAL.CODIGO_MATERIA, MATERIA.CODIGO_MATERIA) codigo_materia, "
                + " ISNULL(ENCCAL.CODIGO_PROFESOR, MATERIA.CODIGO_PROFESOR) codigo_profesor, "
                + " isnull (ENCCAL.CODIGO_ENCUESTA,17) codigo_encuesta,  "
                + " ISNULL(ENCCAL.cod_estudiante, -1)cod_estudiante,  "
                + " ISNULL(ENCCAL.codigo_esp, MATERIA.CODIGO_ESP) codigo_esp,  "
                + " ISNULL(ENCCAL.codigo_nivel, MATERIA.CODIGO_NIVEL) codigo_nivel, "
                + " ISNULL(ENCCAL.tipo_encuesta, 'A') tipo_encuesta,  "
                + " MATERIA.PROGRAMA programa, "
                + " MATERIA.NOMBRE_MATERIA,  "
                + " MATERIA.TRIMESTRE, "
                + " isnull( materia.EMAIL_PROFESOR, 'johana.orozco@uasb.edu.ec') mail, "
                + " '' CODIGO_COORDINADOR,"
                + " MATERIA.COD_PARALELO,"
                + " MATERIA.NOMBRE_PARALELO, "
                + " concat(MATERIA.APELLIDO_PROFESOR,' ',MATERIA.nombre_profesor) NOMBRE_PROFESOR,  "
                + " isnull(round(convert(float,(isnull((select count(*) from EVALUACION.ENCUESTA_REALIZADA as ENCRE "
                + " where ENCRE.CODIGO_ENCUESTA = ENCCAL.CODIGO_ENCUESTA "
                + " AND ENCRE.ANIO = ENCCAL.ANIO "
                + " AND ENCRE.CICLO =ENCCAL.CICLO  "
                + " AND ENCRE.CODIGO_ESP= ENCCAL.CODIGO_ESP "
                + " AND ENCRE.CODIGO_NIVEL= ENCCAL.CODIGO_NIVEL "
                + " AND ENCRE.CODIGO_MATERIA= ENCCAL.CODIGO_MATERIA "
                + " AND ENCRE.CODIGO_PROFESOR= ENCCAL.CODIGO_PROFESOR "
                + " AND ENCRE.CODIGO_PARALELO = ENCCAL.CODIGO_PARALELO),0)/ isnull(MATEST.NUMEST,1))*100),2),0) PORC, "
                + " ISNULL(ENCCAL.ESTADO_ENCUESTA, 'A') estado_encuesta, "
                + " ENCCAL.FECHA_FIN, "
                + " MATERIAFECHA.FECHA_INICIAL, "
                + " MATERIAFECHA.FECHA_FINAL"
                + " FROM @MATERIA AS MATERIA LEFT JOIN EVALUACION.ENCUESTA_CALENDARIO AS ENCCAL "
                + " ON MATERIA.ANIO =  ENCCAL.ANIO   "
                + " AND  MATERIA.CICLO = ENCCAL.CICLO  "
                + " AND MATERIA.codigo_esp = ENCCAL.codigo_esp "
                + " AND MATERIA.CODIGO_NIVEL = ENCCAL.CODIGO_NIVEL "
                + " AND MATERIA.CODIGO_PROFESOR = ENCCAL.CODIGO_PROFESOR "
                + " AND MATERIA.CODIGO_MATERIA = ENCCAL.CODIGO_MATERIA "
                + " AND MATERIA.COD_PARALELO = ENCCAL.CODIGO_PARALELO"
                + " AND  (ENCCAL.tipo_encuesta = 'A' or ENCCAL.tipo_encuesta is null) INNER JOIN @MATERIAFECHA AS MATERIAFECHA "
                + " ON MATERIAFECHA.ANIO =  MATERIA.ANIO  "
                + " AND MATERIAFECHA.codigo_esp = MATERIA.codigo_esp  "
                + " AND MATERIAFECHA.CODIGO_NIVEL = MATERIA.CODIGO_NIVEL "
                + " AND MATERIAFECHA.CODIGO_MATERIA = MATERIA.CODIGO_MATERIA "
                + " AND MATERIAFECHA.COD_PROFESOR= MATERIA.CODIGO_PROFESOR "
                + " AND MATERIAFECHA.COD_PARALELO = MATERIA.COD_PARALELO   INNER JOIN @MATEST AS MATEST "
                + " ON MATERIA.ANIO =  MATEST.ANIO "
                + " AND MATERIA.codigo_esp = MATEST.codigo_esp "
                + " AND MATERIA.CODIGO_NIVEL = MATEST.CODIGO_NIVEL "
                + " AND MATERIA.CODIGO_MATERIA = MATEST.CODIGO_MATERIA "
                + " AND MATERIA.CODIGO_PROFESOR = MATEST.CODIGO_PROFESOR "
                + " AND MATERIA.COD_PARALELO = MATEST.CODIGO_PARALELO "
                + " WHERE MATERIAFECHA.TIP_ASIGNATURA<> 'T' "
                + " AND MATERIA.ANIO =").append(smciclo).append(""
                + " AND (ENCCAL.eSTADO_ENCUESTA <>'C' or ENCCAL.eSTADO_ENCUESTA is null) "
                + " AND MATERIA.CICLO =  1 "
                + " AND MATERIA.CODIGO_NIVEL = ").append(strimestre);
        if (!smarea.equalsIgnoreCase("T")) {
            sql.append(" AND MATERIA.COD_AREA = ").append(smarea);
        }
        sql.append("  order bY FECHA_FIN");

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
                asign[21] = (object[21] == null ? null : formato.format(object[21]));
                asign[22] = (object[22] == null ? null : formato.format(object[22]));

                listAsignaturaCierre.add(i, asign);
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
        if (listAsignaturaFiltroVac != null) {
            listAsignaturaFiltro = listAsignaturaFiltroVac;
        }

        if (listAsignaturaFiltro == null) {
            listAsignaturaFiltro = listAsignaturaCierre;
        } else {
            listAsignaturaFiltroVac = listAsignaturaFiltro;
        }
        listAsignaturaFiltroVac = listAsignaturaFiltro;
        if (sfiltro != null && !sfiltro.isEmpty()) {
            dfiltro = Double.valueOf(sfiltro);
            //  listAsignaturaFiltroVac = listAsignaturaFiltro;
            if (listAsignaturaFiltro.size() > 0) {
                for (int i = 0; i < listAsignaturaFiltro.size(); i++) {
                    String[] asign;
                    asign = new String[23];
                    asign = listAsignaturaFiltro.get(i);
                    if (Double.valueOf(asign[18]) >= dfiltro) {
                        listAsignaturaFiltroAux.add(j, asign);
                        j++;
                    }
                }
            }
            listAsignaturaFiltro = listAsignaturaFiltroAux;

        } else {
            listAsignaturaFiltro = listAsignaturaFiltroVac;
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
                    asign = new String[23];
                    asign = selectedasigcierre.get(i);
                    encuestaCalendarioFacade.cierreEncuesta(asign);
                }

                RequestContext.getCurrentInstance().update("fcierreAsig:tcierreAsig");
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
        listAsignaturaFiltro = listAsignaturaCierre;
    }

    public void ciclovalueChangeListener() {
        strimestre = null;
        smarea = null;
        if (smciclo != null) {
            listtrimestre = consultaFacade.trimestreAnio(Long.valueOf(smciclo));
        }
    }
     public void areavalueChangeListener() {
        strimestre = null;        
        
    }
}
