/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.entities.Profesor;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "segtesis")
@ViewScoped
public class InfAnualSegTesisManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private BigDecimal codProfesor = null;
    private String smciclo = null;
    private String smprofesor = null;
    private String smtipo = null;
    private String scedula = null;
    private String msgtablavacia = "No existen datos Registrados";
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    //Informacion TESIS UASB
    private List<String[]> listTesisuasb = new ArrayList<String[]>();
    private List<String[]> listTesdocuasb = new ArrayList<String[]>();
    private List<String[]> listTesmonuasb = new ArrayList<String[]>();
    private List<Profesor> lprofesor = new ArrayList<Profesor>();
    private List<Profesor> listProfesorFiltro;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
// <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">

    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
    }

    public String getScedula() {
        return scedula;
    }

    public void setScedula(String scedula) {
        this.scedula = scedula;
    }

    public String getSmtipo() {
        return smtipo;
    }

    public void setSmtipo(String smtipo) {
        this.smtipo = smtipo;
    }

    public String getMsgtablavacia() {
        return msgtablavacia;
    }

    public void setMsgtablavacia(String msgtablavacia) {
        this.msgtablavacia = msgtablavacia;
    }

    public String getSmprofesor() {
        return smprofesor;
    }

    public void setSmprofesor(String smprofesor) {
        this.smprofesor = smprofesor;
    }

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    public List<Profesor> getLprofesor() {
        return lprofesor;
    }

    public void setLprofesor(List<Profesor> lprofesor) {
        this.lprofesor = lprofesor;
    }

    public List<Profesor> getListProfesorFiltro() {
        return listProfesorFiltro;
    }

    public void setListProfesorFiltro(List<Profesor> listProfesorFiltro) {
        this.listProfesorFiltro = listProfesorFiltro;
    }

    public List<String[]> getListTesisuasb() {
        return listTesisuasb;
    }

    public void setListTesisuasb(List<String[]> listTesisuasb) {
        this.listTesisuasb = listTesisuasb;
    }

    public List<String[]> getListTesdocuasb() {
        return listTesdocuasb;
    }

    public void setListTesdocuasb(List<String[]> listTesdocuasb) {
        this.listTesdocuasb = listTesdocuasb;
    }

    public List<String[]> getListTesmonuasb() {
        return listTesmonuasb;
    }

    public void setListTesmonuasb(List<String[]> listTesmonuasb) {
        this.listTesmonuasb = listTesmonuasb;
    }

    // </editor-fold> 
    @Override
    public void init() {
        retrieveDatos();
    }

    private void retrieveDatos() {
        //Recupero las areas
        ciclos = cicloAcademicoFacade.findAllActivos();
    }

    /**
     * Creates a new instance of InfAnualSegTesisManagedBean
     */
    public InfAnualSegTesisManagedBean() {
    }

    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA ">    
    private void recuperaTesisUasb() {
        Vector v = new Vector();
        listTesisuasb.clear();
        int li_andoc = 8;
        int li_anmae = 3;
        int li_anesp = 2;
        StringBuilder sql = new StringBuilder();
        /*EXTRAIGO EL CODIGO DEL ACADEMICO ANTERIOR*/
        if (smtipo.equalsIgnoreCase("D")) {
            codProfesor = consultaFacade.findConAntProfesorbySql(smprofesor);
        }
        /*TUTORIAS CON LOS DIFERENTES ROLES EN EL AÑO DEL INFORME ACADEMICO ANTERIOR*/
        /* sql.append("SELECT TM.nivel + ' EN ' + TM.programa PROGRAMA, "
         + " (CASE TM.TIPO WHEN 'TP' THEN 'TRIBUNAL PLAN' "
         + " WHEN 'TU' THEN 'TUTOR DE TESIS' WHEN 'SM' THEN 'SUPERVISIÓN DE MONOGRAFÍAS' "
         + " WHEN 'LT' THEN 'LECTOR DE TESIS' WHEN 'SL' THEN 'SEGUNDO LECTOR' "
         + " WHEN 'TR' THEN 'TRIBUNAL DE TESIS' "
         + " WHEN 'DI' THEN 'DIRECTOR DE TESIS' ELSE 'N/I' END) ROLDOCEN, "
         + " TM.TITULO_TES_MON TEMA, "
         + " TM.FECHA  FECHA, "
         + " TM.F_ENTREGA_DEFINITIVA, "
         + " TM.Apellidos + ' '+ TM.Nombres NOMBRES, "
         + " (CASE  TM.TESIS_MONOGRAFIA WHEN 'D' THEN 'TESIS DOCTORAL'WHEN 'M' THEN 'MONOGRAFIA' WHEN 'T' THEN 'TESIS' END) TIPTRABAJO, "
         + " TM.NUM_CONSULTAS, "
         + " TM.NUM_HORAS, "
         + " TM.anio_estudiante, "
         + " TM.ciclo, "
         + " TM.matricula, "
         + " TM.codigo_esp, "
         + " TM.Código CODIGO_ESTUDIANTE, "
         + " TM.TIPO, "
         + " TM.FECHA_INI,  "
         + " TM.FECHA_FIN,  "
         + " TM.TIP_HORAS,  "
         + " TM.ANIO_ESTUDIANTE, "
         + " TM.NOMBRES_PROFESOR, "
         + " (CASE WHEN TM.CODIGO_NIVEACAD = 3 THEN 3  "
         + " WHEN TM.CODIGO_NIVEACAD IN(1,10,13) THEN 2 "
         + " WHEN TM.CODIGO_NIVEACAD = 2 THEN 1 ELSE 0 END) CODIGO_NIVEACAD , "
         + " (CASE TM.ESTADO_TES_MON WHEN 'A' THEN 'EN PROCESO' ELSE 'N/I' END) ESTADO_TES_MON, "
         + " (CASE TM.ESTADO_PROCESO WHEN 'P' THEN 'EN PROCESO' WHEN 'C' THEN 'CONCLUIDA' END) ESTADO_PROCESO, "
         + " TM.ANIO_INICIO, TM.ANIO_FIN, "
         + " NULL AS TIPPROGRAMA, "
         + " 'ACADEMICO ANTERIOR' sistema"
         + " FROM academico.dbo.VISTA_TESIS_MONOGRAFIA TM "
         + " WHERE TM.CODIGO_NIVEACAD IN(3,1,10,13,2)"
         + " AND  TM.TIPO NOT IN('DI','TU', 'SM') ");
         if (smtipo.equalsIgnoreCase("D")) {
         sql.append(" AND TM.codigo_profesor= ").append(codProfesor.toString()).append(""
         + " AND ").append(smciclo).append(" between TM.ANIO_INICIO and TM.ANIO_FIN ");
         } else if (smtipo.equalsIgnoreCase("E")) {
         sql.append(" AND [Cédula] = '").append(scedula).append("'");
         }*/
        /*TUTORIAS CON EL ROL DE TUTOR EN EL AÑO DEL INFORME  ACADEMICO ANTERIOR*/
        /*sql.append(" UNION "
         + "SELECT TM.nivel + ' EN ' + TM.programa PROGRAMA, "
         + " (CASE TM.TIPO WHEN 'TP' THEN 'TRIBUNAL PLAN' "
         + " WHEN 'TU' THEN 'TUTOR DE TESIS' WHEN 'SM' THEN 'SUPERVISIÓN DE MONOGRAFÍAS' "
         + " WHEN 'LT' THEN 'LECTOR DE TESIS' WHEN 'SL' THEN 'SEGUNDO LECTOR' "
         + " WHEN 'TR' THEN 'TRIBUNAL DE TESIS' "
         + " WHEN 'DI' THEN 'DIRECTOR DE TESIS' ELSE 'N/I' END) ROLDOCEN, "
         + " TM.TITULO_TES_MON TEMA, "
         + "  TM.FECHA  FECHA, "
         + " TM.F_ENTREGA_DEFINITIVA, "
         + " TM.Apellidos + ' '+ TM.Nombres NOMBRES, "
         + " (CASE  TM.TESIS_MONOGRAFIA WHEN 'D' THEN 'TESIS DOCTORAL'WHEN 'M' THEN 'MONOGRAFIA' WHEN 'T' THEN 'TESIS' END) TIPTRABAJO, "
         + " TM.NUM_CONSULTAS, "
         + " TM.NUM_HORAS, "
         + " TM.anio_estudiante, "
         + " TM.ciclo, "
         + " TM.matricula, "
         + " TM.codigo_esp, "
         + " TM.Código CODIGO_ESTUDIANTE, "
         + " TM.TIPO, "
         + " TM.FECHA_INI,  "
         + " TM.FECHA_FIN,  "
         + " TM.TIP_HORAS,  "
         + " TM.ANIO_ESTUDIANTE, "
         + " TM.NOMBRES_PROFESOR, "
         + " (CASE WHEN TM.CODIGO_NIVEACAD = 3 THEN 3  "
         + " WHEN TM.CODIGO_NIVEACAD IN(1,10,13) THEN 2 "
         + " WHEN TM.CODIGO_NIVEACAD = 2 THEN 1 ELSE 0 END) CODIGO_NIVEACAD , "
         + " (CASE TM.ESTADO_TES_MON WHEN 'A' THEN 'EN PROCESO' ELSE 'N/I' END) ESTADO_TES_MON, "
         + " (CASE TM.ESTADO_PROCESO WHEN 'P' THEN 'EN PROCESO' WHEN 'C' THEN 'CONCLUIDA' END) ESTADO_PROCESO, "
         + " TM.ANIO_INICIO, TM.ANIO_FIN, "
         + " NULL AS TIPPROGRAMA, "
         + " 'ACADEMICO ANTERIOR' sistema"
         + " FROM academico.dbo.VISTA_TESIS_MONOGRAFIA TM "
         + " WHERE  TM.CODIGO_NIVEACAD IN(3,1,10,13,2)"
         + " AND TM.TIPO IN('DI','TU', 'SM') ");
         if (smtipo.equalsIgnoreCase("D")) {
         sql.append(" AND TM.codigo_profesor= ").append(codProfesor.toString()).append(""
         + " AND ").append(smciclo).append(" between TM.ANIO_INICIO and TM.ANIO_FIN ");
         } else if (smtipo.equalsIgnoreCase("E")) {
         sql.append(" AND [Cédula] = '").append(scedula).append("'");
         }*/
        /*TUTORIAS CON EL ROL DE TUTOR AÑOS HACIA ATRAS DEL INFORME  ACADEMICO ANTERIOR*/
        /*sql.append(" UNION "
         + " SELECT TM.nivel  +' EN '+  TM.programa, "
         + " (CASE TM.TIPO WHEN 'TP' THEN 'TRIBUNAL PLAN' "
         + " WHEN 'TU' THEN 'TUTOR DE TESIS' WHEN 'SM' THEN 'SUPERVISIÓN DE MONOGRAFÍAS'  "
         + " WHEN 'LT' THEN 'LECTOR DE TESIS' WHEN 'SL' THEN 'SEGUNDO LECTOR' "
         + " WHEN 'TR' THEN 'TRIBUNAL DE TESIS' "
         + " WHEN 'DI' THEN 'DIRECTOR DE TESIS' ELSE 'N/I' END) roldocen, "
         + " TM.TITULO_TES_MON TEMA, "
         + " TM.FECHA  FECHA, "
         + " TM.F_ENTREGA_DEFINITIVA, "
         + " TM.Apellidos + ' '+ TM.Nombres NOMBRES, "
         + " (CASE  TM.TESIS_MONOGRAFIA WHEN 'D' THEN 'TESIS DOCTORAL' WHEN 'M' THEN 'MONOGRAFIA' WHEN 'T' THEN 'TESIS' END) TIPTRABAJO, "
         + " TM.NUM_CONSULTAS, "
         + " TM.NUM_HORAS, "
         + " TM.anio_estudiante, "
         + " TM.ciclo, "
         + " TM.matricula, "
         + " TM.codigo_esp, "
         + " TM.Código CODIGO_ESTUDIANTE, "
         + " TM.TIPO, "
         + " TM.FECHA_INI, "
         + " TM.FECHA_FIN, "
         + " TM.TIP_HORAS, "
         + " TM.ANIO_ESTUDIANTE, "
         + " TM.NOMBRES_PROFESOR,"
         + " (CASE WHEN TM.CODIGO_NIVEACAD = 3 THEN 3 "
         + " WHEN TM.CODIGO_NIVEACAD IN(1,10,13) THEN 2  "
         + " WHEN TM.CODIGO_NIVEACAD = 2 THEN 1 ELSE 0 END) CODIGO_NIVEACAD , "
         + " (CASE TM.ESTADO_TES_MON WHEN 'A' THEN 'EN PROCESO' ELSE 'N/I' END) ESTADO_TES_MON, "
         + " (CASE TM.ESTADO_PROCESO WHEN 'P' THEN 'EN PROCESO' WHEN 'C' THEN 'CONCLUIDA' END) ESTADO_PROCESO, "
         + " TM.ANIO_INICIO, "
         + " TM.ANIO_FIN, "
         + " NULL AS TIPPROGRAMA, "
         + " 'ACADEMICO ANTERIOR' sistema"
         + " FROM academico.dbo.VISTA_TESIS_MONOGRAFIA TM "
         + " WHERE  TM.CODIGO_NIVEACAD IN(3,1,10,13,2)"
         + " AND TM.TIPO IN('DI','TU', 'SM') ");
         if (smtipo.equalsIgnoreCase("D")) {
         sql.append(" AND TM.codigo_profesor= ").append(codProfesor.toString()).append(""
         + " AND CONVERT(DATE, (CASE WHEN TM.FECHA_FIN IS NULL THEN TM.FECHA ELSE TM.FECHA_FIN END), 120) BETWEEN "
         + "	( CASE TM.TIPO WHEN 'DI' THEN(SELECT DateAdd(Year, - ").append(li_andoc).append(", ca.F_INICIO) "
         + "   				      FROM academico.dbo.CICLO_ACADEMICO ca WHERE ca.ANIO = ").append(smciclo).append(")"
         + "			WHEN 'TU' THEN(SELECT DateAdd(Year, - ").append(li_anmae).append(", ca.F_INICIO) "
         + "		     		       FROM academico.dbo.CICLO_ACADEMICO ca WHERE ca.ANIO = ").append(smciclo).append(")"
         + " 		        WHEN 'SM' THEN(SELECT DateAdd(Year, - ").append(li_anesp).append(", ca.F_INICIO) "
         + "                                      FROM academico.dbo.CICLO_ACADEMICO ca WHERE ca.ANIO = ").append(smciclo).append(")"
         + "	 END ) "
         + "AND "
         + "(SELECT ca.F_FINAL " +
         "                           				      FROM academico.dbo.CICLO_ACADEMICO ca WHERE ca.ANIO = ").append(smciclo).append(")");
         } else if (smtipo.equalsIgnoreCase("E")) {
         sql.append(" AND [Cédula] = '").append(scedula).append("'");
         }*/
        /*TUTORIAS CON OTROS ROL EN EL  AÑO DEL INFORME UXXI*/
        /*sql.append(" UNION  "
         + " SELECT vtm.PROGRAMA, "
         + " (CASE vtm.ROL_DOCENTE WHEN 'TP' THEN 'TRIBUNAL PLAN'  "
         + " WHEN 'TU' THEN 'TUTOR DE TESIS' WHEN 'SM' THEN 'SUPERVISIÓN DE MONOGRAFÍAS' "
         + " WHEN 'LT' THEN 'LECTOR DE TESIS' WHEN 'SL' THEN 'SEGUNDO LECTOR'  "
         + " WHEN 'TR' THEN 'TRIBUNAL DE TESIS'  "
         + " WHEN 'DI' THEN 'DIRECTOR DE TESIS' ELSE 'N/I' END) ROL_DOCENTE, "
         + " vtm.TEMA, "
         + " CONVERT(DATE, vtm.FECHA ,120) FECHA, "
         + " CONVERT(DATE,vtm.F_ENTREGA_DEFINITIVA, 120)F_ENTREGA_DEFINITIVA, "
         + " vtm.NOMBRES_ESTUDIANTES,"
         + " (CASE vtm.COD_TIPPROGRAMA WHEN 1 THEN 'MONOGRAFÍA' WHEN 2 THEN 'TESIS' WHEN 3 THEN 'TESIS' WHEN 4 THEN 'TESIS DOCTORAL' ELSE 'N/I' END) COD_TIPPROGRAMA, "
         + " vtm.NUM_CONSULTAS, "
         + " vtm.NUM_HORAS, "
         + " vtm.ANIO_ESTUDIANTE, "
         + " 1 CICLO, "
         + " vtm.NUM_MATRICULA, "
         + " vtm.CODIGO_ESP, "
         + " vtm.CODIGO_ESTUDIANTE, "
         + " vtm.ROL_DOCENTE, "
         + " CONVERT(DATE,vtm.FECHA_INI,120), "
         + " CONVERT(DATE,vtm.FECHA_FIN,120), "
         + " vtm.TIP_HORAS, "
         + " vtm.ANIO_ESTUDIANTE, "
         + " vtm.NOMBRES_PROFESOR, "
         + " vtm.CODIGO_NIVEACAD, "
         + " vtm.ESTADOTESIS, "
         + " (CASE vtm.ESTADO_PROCESO WHEN 'P' THEN 'EN PROCESO' WHEN 'C' THEN 'CONCLUIDA' END) ESTADO_PROCESO, "
         + " vtm.ANIO_INICIO, vtm.ANIO_FIN, "
         + " vtm.TIPPROGRAMA, "
         + " 'UXXI' sistema"
         + " FROM interfaseOcu.dbo.VISTA_TESIS_MONOGRAFIA vtm "
         + " WHERE  vtm.CODIGO_NIVEACAD IN(1,2,3)"
         + " AND vtm.ROL_DOCENTE NOT IN('DI','TU', 'SM') ");
         if (smtipo.equalsIgnoreCase("D")) {
         sql.append("AND vtm.CODIGO_PROFESOR = ").append(smprofesor).append(""
         + " AND ").append(smciclo).append(" BETWEEN vtm.ANIO_INICIO AND vtm.ANIO_FIN ");
         } else if (smtipo.equalsIgnoreCase("E")) {
         sql.append(" AND VTM.CED_PAS_ESTUDIANTE='").append(scedula).append("'");
         }*/
        /*TUTORIAS CON ROL DE TUTOR EN EL  AÑO DEL INFORME UXXI*/
        /*sql.append(" UNION  "
         + " SELECT vtm.PROGRAMA, "
         + " (CASE vtm.ROL_DOCENTE WHEN 'TP' THEN 'TRIBUNAL PLAN'  "
         + " WHEN 'TU' THEN 'TUTOR DE TESIS' WHEN 'SM' THEN 'SUPERVISIÓN DE MONOGRAFÍAS' "
         + " WHEN 'LT' THEN 'LECTOR DE TESIS' WHEN 'SL' THEN 'SEGUNDO LECTOR'  "
         + " WHEN 'TR' THEN 'TRIBUNAL DE TESIS'  "
         + " WHEN 'DI' THEN 'DIRECTOR DE TESIS' ELSE 'N/I' END) ROL_DOCENTE, "
         + " vtm.TEMA, "
         + " CONVERT(DATE, vtm.FECHA ,120) FECHA, "
         + " CONVERT(DATE,vtm.F_ENTREGA_DEFINITIVA, 120)F_ENTREGA_DEFINITIVA, "
         + " vtm.NOMBRES_ESTUDIANTES,"
         + " (CASE vtm.COD_TIPPROGRAMA WHEN 1 THEN 'MONOGRAFÍA' WHEN 2 THEN 'TESIS' WHEN 3 THEN 'TESIS' WHEN 4 THEN 'TESIS DOCTORAL' ELSE 'N/I' END) COD_TIPPROGRAMA, "
         + " vtm.NUM_CONSULTAS, "
         + " vtm.NUM_HORAS, "
         + " vtm.ANIO_ESTUDIANTE, "
         + " 1 CICLO, "
         + " vtm.NUM_MATRICULA, "
         + " vtm.CODIGO_ESP, "
         + " vtm.CODIGO_ESTUDIANTE, "
         + " vtm.ROL_DOCENTE, "
         + " CONVERT(DATE,vtm.FECHA_INI,120), "
         + " CONVERT(DATE,vtm.FECHA_FIN,120), "
         + " vtm.TIP_HORAS, "
         + " vtm.ANIO_ESTUDIANTE, "
         + " vtm.NOMBRES_PROFESOR, "
         + " vtm.CODIGO_NIVEACAD, "
         + " vtm.ESTADOTESIS, "
         + " (CASE vtm.ESTADO_PROCESO WHEN 'P' THEN 'EN PROCESO' WHEN 'C' THEN 'CONCLUIDA' END) ESTADO_PROCESO, "
         + " vtm.ANIO_INICIO, vtm.ANIO_FIN, "
         + " vtm.TIPPROGRAMA, "
         + " 'UXXI' sistema"
         + " FROM interfaseOcu.dbo.VISTA_TESIS_MONOGRAFIA vtm "
         + " WHERE vtm.CODIGO_NIVEACAD IN(1,2,3)"
         + " AND  vtm.ROL_DOCENTE IN('DI','TU', 'SM') ");
         if (smtipo.equalsIgnoreCase("D")) {
         sql.append("AND vtm.CODIGO_PROFESOR = ").append(smprofesor).append(""
         + " AND ").append(smciclo).append(" BETWEEN vtm.ANIO_INICIO AND vtm.ANIO_FIN ");
         } else if (smtipo.equalsIgnoreCase("E")) {
         sql.append(" AND VTM.CED_PAS_ESTUDIANTE='").append(scedula).append("'");
         }*/
        /*TUTORIAS CON ROL DE TUTOR EN AÑOS ANTERIORES DEL INFORME UXXI*/
        /*sql.append(" UNION "
         + " SELECT vtm.PROGRAMA, "
         + " (CASE vtm.ROL_DOCENTE WHEN 'TP' THEN 'TRIBUNAL PLAN'  "
         + " WHEN 'TU' THEN 'TUTOR DE TESIS' WHEN 'SM' THEN 'SUPERVISIÓN DE MONOGRAFÍAS'  "
         + " WHEN 'LT' THEN 'LECTOR DE TESIS' WHEN 'SL' THEN 'SEGUNDO LECTOR'  "
         + " WHEN 'TR' THEN 'TRIBUNAL DE TESIS'  "
         + " WHEN 'DI' THEN 'DIRECTOR DE TESIS' ELSE 'N/I' END) ROL_DOCENTE, "
         + " vtm.TEMA, "
         + " CONVERT(DATE, vtm.FECHA ,120) FECHA, "
         + " CONVERT(DATE,vtm.F_ENTREGA_DEFINITIVA,120)F_ENTREGA_DEFINITIVA, "
         + " vtm.NOMBRES_ESTUDIANTES, "
         + " (CASE vtm.COD_TIPPROGRAMA WHEN 1 THEN 'MONOGRAFÍA' WHEN 2 THEN 'TESIS' "
         + " WHEN 3 THEN 'TESIS' WHEN 4 THEN 'TESIS DOCTORAL' ELSE 'N/I' END) COD_TIPPROGRAMA, "
         + " vtm.NUM_CONSULTAS, "
         + " vtm.NUM_HORAS, "
         + " vtm.ANIO_ESTUDIANTE, "
         + " 1 CICLO, "
         + " vtm.NUM_MATRICULA, "
         + " vtm.CODIGO_ESP, "
         + " vtm.CODIGO_ESTUDIANTE, "
         + " vtm.ROL_DOCENTE, "
         + " CONVERT(DATE,vtm.FECHA_INI,120), "
         + " CONVERT(DATE,vtm.FECHA_FIN,120), "
         + " vtm.TIP_HORAS, "
         + " vtm.ANIO_ESTUDIANTE, "
         + " vtm.NOMBRES_PROFESOR, "
         + " vtm.CODIGO_NIVEACAD, "
         + " vtm.ESTADOTESIS, "
         + " (CASE vtm.ESTADO_PROCESO WHEN 'P' THEN 'EN PROCESO' WHEN 'C' THEN 'CONCLUIDA' END) ESTADO_PROCESO, "
         + " vtm.ANIO_INICIO, vtm.ANIO_FIN, "
         + " vtm.TIPPROGRAMA, "
         + " 'UXXI' sistema"
         + " FROM interfaseOcu.dbo.VISTA_TESIS_MONOGRAFIA vtm "
         + " WHERE vtm.CODIGO_NIVEACAD IN(1,2,3)"
         + " AND  vtm.ROL_DOCENTE IN('DI','TU', 'SM') ");
         if (smtipo.equalsIgnoreCase("D")) {
         sql.append(" AND vtm.CODIGO_PROFESOR = ").append(smprofesor).append(""
         + " AND CONVERT(DATE, (CASE WHEN vtm.FECHA_FIN IS NULL THEN vtm.FECHA ELSE vtm.FECHA_FIN END), 120) BETWEEN  "
         + "			(CASE vtm.ROL_DOCENTE WHEN 'DI' THEN(SELECT DateAdd(Year, - ").append(li_andoc).append(", ca.F_INICIO) "
         + "                                                          FROM interfaseOcu.dbo.CICLO_ACADEMICO ca WHERE ca.ANIO = ").append(smciclo).append(") "
         + "  				      WHEN 'TU' THEN(SELECT DateAdd(Year, - ").append(li_anmae).append(", ca.F_INICIO) "
         + "						     FROM interfaseOcu.dbo.CICLO_ACADEMICO ca WHERE ca.ANIO = ").append(smciclo).append(") "
         + "                                   WHEN 'SM' THEN(SELECT DateAdd(Year, - ").append(li_anesp).append(", ca.F_INICIO) "
         + "					     FROM interfaseOcu.dbo.CICLO_ACADEMICO ca WHERE ca.ANIO = ").append(smciclo).append(")"
         + "				END)"
         + "AND "
         + "(SELECT ca.F_FINAL " 
         + "   FROM interfaseOcu.dbo.CICLO_ACADEMICO ca WHERE ca.ANIO = ").append(smciclo).append(")");
         } else if (smtipo.equalsIgnoreCase("E")) {
         sql.append(" AND VTM.CED_PAS_ESTUDIANTE ='").append(scedula).append("'");
         }       
         sql.append("ORDER BY CODIGO_NIVEACAD DESC, FECHA DESC");*/
        //INFORMACION DE LOS DIFERENTES ROLES EN LAS TESIS Y MAESTRIAS SEGUN LOS REQUERIMIENTOS DE LA DGA
        sql.append("SELECT TES.CODIGO_ESP ,TES.CODIGO_NIVEACAD ,TES.PROGRAMA ,  TES.TEMA , TES.COD_ESTUDIANTE ,TES.CED_PAS_ESTUDIANTE ,TES.NOMBRE_ESTUDIANTE, " 
                +"TES.TIPTRABAJO , TES.NUM_CONSULTAS , TES.NUM_HORAS , TES.MATRICULA ,TES.TIPO ,  TES.ROLDOCEN , TES.TIP_HORAS, TES.ANIO_ESTUDIANTE , " 
                +"TES.NOMBRES_PROFESOR , TES.ESTADO_TES_MON ,TES.ESTADO_PROCESO , TES.FECHA ,TES.FECHA_INI , TES.FECHA_FIN, TES.ANIO_INICIO ,TES.ANIO_FIN ,TES.TIPPROGRAMA, TES.SISTEMA ");
                
        if (smtipo.equalsIgnoreCase("D")) {
            sql.append(" FROM interfaseOcu.GESTIONACADEMICA.f_genInfoTesis ('D',NULL,").append(smciclo).append(",").append(codProfesor).append(",") .append(smprofesor).append(") TES ");
                    //+ "EXECUTE interfaseOcu.GESTIONACADEMICA.sp_genInfoTESIS @tiprep='D', @cedEstud=NULL,  @anioacademico =").append(smciclo).append(",@codProfesorAnt= ").append(codProfesor).append(", @codProfesor= ").append(smprofesor);
        } else if (smtipo.equalsIgnoreCase("E")) {
            sql.append(" FROM interfaseOcu.GESTIONACADEMICA.f_genInfoTesis ('E',").append(scedula).append(",").append(smciclo).append(",NULL,NULL) TES ");
            //sql.append("EXECUTE interfaseOcu.GESTIONACADEMICA.sp_genInfoTESIS @tiprep='E', @cedEstud='").append(scedula).append("',@anioacademico =NULL,@codProfesorAnt= NULL, @codProfesor= NULL");
        }
      
        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        int itestm = 0, itestd = 0, imon = 0;
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[26];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] =(object[1] == null ? null : object[1].toString());
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
                asign[15] = (object[15] == null ? null : object[15].toString());
                asign[16] = (object[16] == null ? null : ("C".equals(object[16].toString())?"CONCLUIDA":"PROCESO"));                
                asign[17] = (object[17] == null ? null : ("C".equals(object[17].toString())?"CONCLUIDA":"PROCESO")); 
                asign[18] = (object[18] == null ? null : formato.format(object[18]));
                asign[19] = ("D".equals(smtipo)?smprofesor.toString():null);
                asign[20] = (object[19] == null ? null : formato.format(object[19]));
                asign[21] = (object[20] == null ? null : formato.format(object[20]));
                asign[22] = (object[21] == null ? null : object[21].toString());
                asign[23] = (object[22] == null ? null : object[22].toString());
                asign[24] = (object[23] == null ? null : object[23].toString());
                asign[25] = (object[24] == null ? null : object[24].toString());
                
                
               /* asign = new String[27];
                asign[0] = object[0].toString();
                asign[1] = (String) object[1];
                asign[2] = (String) object[2];
                asign[3] = (object[3] == null ? null : formato.format(object[3]));
                asign[4] = (object[4] == null ? null : formato.format(object[4]));
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
                asign[15] = (object[15] == null ? null : formato.format(object[15]));
                asign[16] = (object[16] == null ? null : formato.format(object[16]));
                asign[17] = (object[17] == null ? null : object[17].toString());
                asign[18] = (object[18] == null ? null : object[18].toString());
                asign[19] = smprofesor;
                asign[20] = (object[19] == null ? null : object[19].toString());
                asign[21] = (object[20] == null ? null : object[20].toString());
                asign[22] = (object[22] == null ? null : object[22].toString());
                asign[23] = (object[23] == null ? null : object[23].toString());
                asign[24] = (object[24] == null ? null : object[24].toString());
                asign[25] = (object[25] == null ? null : object[25].toString());
                asign[26] = (object[26] == null ? null : object[26].toString());*/
                if (object[1].toString().equalsIgnoreCase("1")) {
                    listTesmonuasb.add(imon, asign);
                    imon++;
                }
                if (object[1].toString().equalsIgnoreCase("2")) {
                    listTesisuasb.add(itestm, asign);
                    itestm++;
                }
                if (object[1].toString().equalsIgnoreCase("3")) {
                    listTesdocuasb.add(itestd, asign);
                    itestd++;
                }

            }
        }
    }

    // </editor-fold> 
    public void ciclovalueChangeListener() {
        StringBuilder sql = new StringBuilder();
        lprofesor.clear();
        if (smciclo != null) {
            lprofesor = consultaFacade.findProfesorInfAnualAct(smciclo);
        }
    }

    public void profesorvalueChangeListener() {
        listTesmonuasb.clear();
        listTesisuasb.clear();
        listTesdocuasb.clear();
        if (smprofesor != null) {
            recuperaTesisUasb();
        }
    }

    public void estudvalueChangeListener() {
        listTesmonuasb.clear();
        listTesisuasb.clear();
        listTesdocuasb.clear();
        if (scedula != null) {
            recuperaTesisUasb();
        }
    }

}
