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
@ManagedBean(name = "cierreEvalEstAProg")
@ViewScoped
public class CierreEvalEstAProgManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private List<String[]> listProgramaCierre = new ArrayList<String[]>();
    private List<String[]> listProgramaFiltro;
    private List<String[]> listProgramaFiltroVac;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private String smciclo = null;

    private String sfiltro = null;
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

    public List<String[]> getListProgramaCierre() {
        return listProgramaCierre;
    }

    public void setListProgramaCierre(List<String[]> listProgramaCierre) {
        this.listProgramaCierre = listProgramaCierre;
    }

    public List<String[]> getListProgramaFiltro() {
        return listProgramaFiltro;
    }

    public void setListProgramaFiltro(List<String[]> listProgramaFiltro) {
        this.listProgramaFiltro = listProgramaFiltro;
    }

    public List<String[]> getListProgramaFiltroVac() {
        return listProgramaFiltroVac;
    }

    public void setListProgramaFiltroVac(List<String[]> listProgramaFiltroVac) {
        this.listProgramaFiltroVac = listProgramaFiltroVac;
    }

    // </editor-fold> 
    public CierreEvalEstAProgManagedBean() {
        this.setPaginaMant("/pages/evaluacion/programa/cierrePrograma");
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAllActivos();

    }

// <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private List<String[]> selectedprogcierre = new ArrayList<String[]>();

    public List<String[]> getSelectedprogcierre() {
        return selectedprogcierre;
    }

    public void setSelectedprogcierre(List<String[]> selectedprogcierre) {
        this.selectedprogcierre = selectedprogcierre;
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="CARGAR TABLA PROGRAMAS PARA CIERRE ">   
    private void recuperaProgramaCierre(Long anio) {
        Vector v = new Vector();
        listProgramaCierre.clear();
        StringBuilder sql = new StringBuilder();
        sql.append("DECLARE @MATEST TABLE ( ANIO INT,CODIGO_ESP VARCHAR(4), NUM_EST INT)"
                + " DECLARE @MATERIA TABLE ( AREA VARCHAR(75), PROGRAMA VARCHAR(500),CODIGO_ESP VARCHAR(4), CODIGO_FACULTAD NUMERIC(7,0),  ANIO INT, CODIGO_NIVEACAD VARCHAR(3), CICLO VARCHAR(384), TIPO_NIVEACAD NUMERIC(2,0),EMAIL_COORDINADOR VARCHAR(100), CODIGO_COORDINADOR VARCHAR(384) ) "
                + " INSERT INTO @MATEST  ( ANIO ,CODIGO_ESP, NUM_EST ) "
                + " SELECT DISTINCT  ANIO, CODIGO_ESP, NUMEST "
                + " FROM dbo.VISTA_NUMESTUDIANTE_PROGRAMA"
                + " WHERE ANIO = ").append(anio).append(""
                        + " INSERT INTO @MATERIA(AREA, PROGRAMA ,CODIGO_ESP , CODIGO_FACULTAD ,  ANIO , CODIGO_NIVEACAD , CICLO,TIPO_NIVEACAD,  EMAIL_COORDINADOR, CODIGO_COORDINADOR )"
                        + " SELECT DISTINCT  AREA, PROGRAMA ,CODIGO_ESP , CODIGO_FACULTAD ,  ANIO , CODIGO_NIVEACAD , CICLO, TIPO_NIVEACAD, EMAIL_COORDINADOR, CODIGO_COORDINADOR"
                        + " FROM dbo.VISTA_COORDINADOR_PROGRAMA"
                        + " where anio = ").append(anio).append(""
                        + " SELECT DISTINCT (case when enccal.anio is null then concat(materia.anio,'-',materia.codigo_esp)else concat(ENCCAL.anio,'-',ENCCAL.codigo_esp)end )cod, "
                        + " isnull (ENCCAL.ANIO, MATERIA.ANIO) anio,"
                        + " isnull (ENCCAL.ciclo, MATERIA.ciclo) as ciclo, "
                        + " ISNULL (ENCCAL.CODIGO_MATERIA, -1) codigo_materia,"
                        + " ISNULL(ENCCAL.CODIGO_PROFESOR, -1) codigo_profesor, "
                        + " isnull (ENCCAL.CODIGO_ENCUESTA,(case tipo_niveacad when 1then 19 when 2then 19 when 3 then 18  when 4 then 21 end)) codigo_encuesta, "
                        + " ISNULL(ENCCAL.cod_estudiante, -1)cod_estudiante, "
                        + " ISNULL(ENCCAL.codigo_esp, MATERIA.CODIGO_ESP) codigo_esp, "
                        + " ISNULL(ENCCAL.codigo_nivel, -1) codigo_nivel,"
                        + " ISNULL(ENCCAL.tipo_encuesta, 'P') tipo_encuesta, "
                        + " MATERIA.PROGRAMA programa, "
                        + " '' NOMBRE_MATERIA,  "
                        + " 'ANUAL'TRIMESTRE, "
                        + " EMAIL_COORDINADOR mail, "
                        + " MATERIA.CODIGO_COORDINADOR, "
                        + " -1 CODIGO_PARALELO,"
                        + " ' ' NOMBRE_PARALELO,"
                        + " 'NO APLICA' NOMBRE_PROFESOR, "
                        + " round((convert(float,isnull((select count(*) from EVALUACION.ENCUESTA_REALIZADA as ENCRE "
                        + "                      where ENCRE.CODIGO_ENCUESTA = ENCCAL.CODIGO_ENCUESTA "
                        + "                      AND ENCRE.ANIO = ENCCAL.ANIO "
                        + "                      AND ENCRE.CICLO =ENCCAL.CICLO "
                        + "                      AND ENCRE.CODIGO_ESP= ENCCAL.CODIGO_ESP "
                        + "                	AND ENCRE.CODIGO_NIVEL= ENCCAL.CODIGO_NIVEL "
                        + "                      AND ENCRE.CODIGO_MATERIA= ENCCAL.CODIGO_MATERIA "
                        + "                      AND ENCRE.CODIGO_PROFESOR= ENCCAL.CODIGO_PROFESOR),0))/ convert(float,isnull(MATEST.NUM_EST ,1))*100),2) PORC, "
                        + " ISNULL(ENCCAL.ESTADO_ENCUESTA, 'A') estado_encuesta,"
                        + " ENCCAL.FECHA_FIN"
                        + " from @MATERIA as materia LEFT JOIN EVALUACION.ENCUESTA_CALENDARIO AS ENCCAL "
                        + " ON MATERIA.ANIO =  ENCCAL.ANIO   "
                        + " AND MATERIA.codigo_esp = ENCCAL.codigo_esp    INNER JOIN @MATEST AS MATEST"
                        + " ON ENCCAL.ANIO  = MATEST.ANIO"
                        + " AND ENCCAL.CODIGO_ESP = MATEST.CODIGO_ESP"
                        + " where  MATERIA.ANIO =").append(anio).append("    "
                        + " AND ENCCAL.CODIGO_MATERIA = -1  "
                        + " AND ENCCAL.CODIGO_PROFESOR = -1  "
                        + " AND ENCCAL.CODIGO_NIVEL = -1  "
                        + " AND (ENCCAL.eSTADO_ENCUESTA <>'C' or ENCCAL.eSTADO_ENCUESTA is null) "
                        + " AND (ENCCAL.tipo_encuesta = 'P' or ENCCAL.tipo_encuesta is null) "
                        + " order bY FECHA_FIN");

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
                listProgramaCierre.add(i, asign);
            }
        }

    }
    // </editor-fold> 

// <editor-fold defaultstate="collapsed" desc="FILTRO LOS VALORES DEL PORCENTAJ ">   
    public void filtrarValorvalueChangeListener() {
        Double dfiltro;
        int j = 0;
        List<String[]> listProgramaFiltroAux = new ArrayList<String[]>();
        listProgramaFiltroAux.clear();
        if (listProgramaFiltroVac != null) {
            listProgramaFiltro = listProgramaFiltroVac;
        }
        if (listProgramaFiltro == null) {
            listProgramaFiltro = listProgramaCierre;
        } else {
            listProgramaFiltroVac = listProgramaFiltro;
        }
        listProgramaFiltroVac = listProgramaFiltro;
        if (sfiltro != null && !sfiltro.isEmpty()) {
            dfiltro = Double.valueOf(sfiltro);
            //  listAsignaturaFiltroVac = listAsignaturaFiltro;
            if (listProgramaFiltro.size() > 0) {
                for (int i = 0; i < listProgramaFiltro.size(); i++) {
                    String[] asign;
                    asign = new String[21];
                    asign = listProgramaFiltro.get(i);
                    if (Double.valueOf(asign[18]) >= dfiltro) {
                        listProgramaFiltroAux.add(j, asign);
                        j++;
                    }
                }
            }
            listProgramaFiltro = listProgramaFiltroAux;

        } else {
            listProgramaFiltro = listProgramaFiltroVac;
        }
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  
    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        if (selectedprogcierre != null) {
            if (selectedprogcierre.size() > 0) {
                for (int i = 0; i < selectedprogcierre.size(); i++) {
                    String[] asign;
                    asign = new String[21];
                    asign = selectedprogcierre.get(i);
                    encuestaCalendarioFacade.cierreEncuesta(asign);
                }                
                RequestContext.getCurrentInstance().update("fcierreProg:tcierreProg");
                RequestContext.getCurrentInstance().execute("mantWidget.hide();");
                JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
                ciclovalueChangeListener();
                CancelCierre();
            }
        }

    }
    // </editor-fold> 

    public void CancelCierre() {
        selectedprogcierre = new ArrayList<String[]>();
        sfiltro = null;
        listProgramaFiltro = listProgramaCierre;
    }

    public void ciclovalueChangeListener() {
        if (smciclo != null) {
            recuperaProgramaCierre(Long.valueOf(smciclo));
        }
    }
}
