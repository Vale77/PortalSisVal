/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portaldoc.reportes.managedBean;

import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "repdirectorBean")
@ViewScoped
public class RepDirectorJSFManagedBean implements Serializable {

    private Long codProfesor;
    private Long anio;
    private String ls_reporte = null;
    private String url = null;
    private String paramrep = null;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private HtmlSelectOneMenu smreporte = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smciclo = new HtmlSelectOneMenu();

    private String displayReporte = null;
    private String ls_tabvacia = "No tiene Programas Asignados para este período!!!";
    private String ls_cabecera = null;
    private List<String[]> listProgCiclo = new ArrayList<String[]>();
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;

// <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public String getLs_cabecera() {
        return ls_cabecera;
    }

    public void setLs_cabecera(String ls_cabecera) {
        this.ls_cabecera = ls_cabecera;
    }

    public String getLs_tabvacia() {
        return ls_tabvacia;
    }

    public void setLs_tabvacia(String ls_tabvacia) {
        this.ls_tabvacia = ls_tabvacia;
    }

    public List<String[]> getListProgCiclo() {
        return listProgCiclo;
    }

    public void setListProgCiclo(List<String[]> listProgCiclo) {
        this.listProgCiclo = listProgCiclo;
    }

    public String getLs_reporte() {
        return ls_reporte;
    }

    public void setLs_reporte(String ls_reporte) {
        this.ls_reporte = ls_reporte;
    }

    public String getDisplayReporte() {
        return displayReporte;
    }

    public void setDisplayReporte(String displayReporte) {
        this.displayReporte = displayReporte;
    }

    public HtmlSelectOneMenu getSmreporte() {
        return smreporte;
    }

    public void setSmreporte(HtmlSelectOneMenu smreporte) {
        this.smreporte = smreporte;
    }

    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
    }

    public HtmlSelectOneMenu getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(HtmlSelectOneMenu smciclo) {
        this.smciclo = smciclo;
    }

// </editor-fold> 
    /**
     * Creates a new instance of RepDocenteJSFManagedBean
     */
    public RepDirectorJSFManagedBean() {
        //        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
//        codProfesor = usr.getUsuCodigo();
    }

    @PostConstruct
    private void init() {
        ciclos = cicloAcademicoFacade.findAll();
        if (!ciclos.isEmpty()) {
            anio = ciclos.get(0).getAnio();
        }
        codProfesor = Long.parseLong("4755");
    }
// <editor-fold defaultstate="collapsed" desc="VER REPORTE">

    public void verReporte(String tipo) {

        FacesContext context = FacesContext.getCurrentInstance();
        if (validar() == 0) {
            url = context.getExternalContext().getRequestScheme()
                    + "://" + context.getExternalContext().getRequestServerName()
                    + ":" + context.getExternalContext().getRequestServerPort()
                    + "/ServletImpresion/servlet?" + paramrep
                    + "&tipo=" + tipo
                    + "&reporte=" + ls_reporte
                    + "&contexto=PortalSisEval";

            if (tipo.equalsIgnoreCase("pdf")) {
                this.setDisplayReporte(url);
            } else {
                try {
                    context.getExternalContext().redirect(url);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    context.responseComplete();
                }
            }
        }
    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="VALIDAR">

    public int validar() {
        int resp = 0;
        ls_reporte = "";
        if (smreporte.getValue() == null) {
            resp = -1;
        } else {
            if (smreporte.getValue().toString().equalsIgnoreCase("D")) {
                ls_reporte = "EvalDeEstudAldocenteVistaCoord"; //revalestdocentecoordinador
                paramrep = "codigoProfesor=" + selectedProgDocMateria[0].substring(0, selectedProgDocMateria[0].indexOf('-'))
                        + "&anio=" + smciclo.getValue().toString()
                        + "&codigoEsp=" + selectedProgDocMateria[0].substring(selectedProgDocMateria[0].indexOf('-') + 1, selectedProgDocMateria[0].length())
                        + "&titulo= Docente- " + selectedProgDocMateria[1] + "- " + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("CD")) {
                //Cambio de formularios 2020
                 if (Long.valueOf(smciclo.getValue().toString()) >= 2020) {
                    ls_reporte = "EvalDeEstudAlDocenteConsol2020";//revaldocenteconsolidado
                } else {
                    ls_reporte = "EvalDeEstudAlDocenteConsol";//revaldocenteconsolidado
                }
                paramrep = "codesp=" + selectedProgDocMateria[0]
                        + "&anio=" + smciclo.getValue().toString()
                        + "&titulo= Consoldocente- " + selectedProgDocMateria[1] + "- " + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("A")) {
                ls_reporte = "EvalDeEstudAAsignaturaVCoord";
                paramrep = "&anio=" + smciclo.getValue().toString()
                        + "&codigoMateria=" + selectedProgDocMateria[0]
                        + "&titulo=" + selectedProgDocMateria[1] + "- " + smciclo.getValue().toString();;
            } else if (smreporte.getValue().toString().equalsIgnoreCase("CA")) {
                //Cambio de formularios 2020
                 if (Long.valueOf(smciclo.getValue().toString()) >= 2020) {
                    ls_reporte = "EvalDeEstudAAsignaturaConsol2020";//revaldocenteconsolidado
                } else {
                    ls_reporte = "EvalDeEstudAAsignaturaConsol";
                }  
                paramrep = "codesp=" + selectedProgDocMateria[0]
                        + "&anio=" + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("T")) {
                ls_reporte = "EvalDeEstudAlTutor";
                paramrep = "codCoordinador=" + codProfesor
                        + "&anio=" + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("CT")) {
                ls_reporte = "EvalDeEstudAlTutorConsol";
                paramrep = "codCoordinador=" + codProfesor
                        + "&anio=" + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("P")) {
                ls_reporte = "EvalDeEstudAlPrograma"; //revalprograma
                paramrep = "codigoEsp=" + selectedProgDocMateria[0]
                        + "&anio=" + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("CP")) {
                ls_reporte = "EvalDeEstudAlProgramaConsol"; //revalprogramaconsol
                paramrep = "codcordinador=" + codProfesor
                        + "&anio=" + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("CPM")) {
                ls_reporte = "EvalDeEstudAlProgramaMInvestigacionConsol"; //revalprogramaconsolMInvestigacion
                paramrep = "codcordinador=" + codProfesor
                        + "&anio=" + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("CPD")) {
                ls_reporte = "EvalDeEstudAlProgramaDoctoConsol"; //revalprogramaconsol
                paramrep = "codcordinador=" + codProfesor
                        + "&anio=" + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("C")) {
                ls_reporte = "EvalDeCoordAlDocente";
                paramrep = "codesp=" + selectedProgDocMateria[0]
                        + "&anio=" + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("CC")) {
                ls_reporte = "EvalDeCoordAlDocenteConsol";
                paramrep = "codesp=" + selectedProgDocMateria[0]
                        + "&anio=" + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("COP")) {
                ls_reporte = "EvalDeCoordAlPrograma";
                paramrep = "codesp=" + selectedProgDocMateria[0]
                        + "&anio=" + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("CCOP")) {
                ls_reporte = "EvalDeCoordAlProgramaConsol";
                paramrep = "codigoProfesor=" + codProfesor
                        + "&anio=" + smciclo.getValue().toString();
            }
        }
        if (smciclo.getValue() == null) {
            resp = -1;
        }

        return resp;
    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">    
    private String[] selectedProgDocMateria;

    public String[] getSelectedProgDocMateria() {
        return selectedProgDocMateria;
    }

    public void setSelectedProgDocMateria(String[] selectedProgDocMateria) {
        this.selectedProgDocMateria = selectedProgDocMateria;
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="CARGA DATOS TABLA">
    public void reporteChangeListener() {
        listProgCiclo.clear();
        smciclo.resetValue();
    }

    public void cicloChangeListener() {
        listProgCiclo.clear();
        Vector v = new Vector();
        StringBuilder sql = new StringBuilder();

        if (smreporte.getValue().toString().equalsIgnoreCase("D")) {
            sql.append("SELECT DISTINCT  CONCAT (MATERIA.CODIGO_PROFESOR , '-', MATERIA.CODIGO_ESP), "
                    + "CONCAT(MATERIA.APELLIDO_PROFESOR ,' ',MATERIA.NOMBRE_PROFESOR,' / ',materia.PROGRAMA) nombre, "
                    + "MATERIA.CODIGO_NIVEACAD "
                    + "FROM VISTA_COORDINADOR_PROGRAMA as MATERIA WITH (NOLOCK) "
                    + "INNER JOIN EVALUACION.ENCUESTA_REALIZADA  AS ENCRE WITH (NOLOCK) ON MATERIA.ANIO = ENCRE.ANIO "
                    + "and MATERIA.CICLO = ENCRE.CICLO  "
                    + "AND MATERIA.CODIGO_ESP = ENCRE.CODIGO_ESP "
                    + "AND MATERIA.CODIGO_NIVEL = ENCRE.CODIGO_NIVEL "
                    + "and MATERIA.CODIGO_MATERIA = ENCRE.CODIGO_MATERIA "
                    + "and MATERIA.CODIGO_PROFESOR = ENCRE.CODIGO_PROFESOR  "
                    + "INNER JOIN interfaseOcu.dbo.AREA are ON MATERIA.CODIGO_FACULTAD = are.ARE_CODIGO "
                    + "INNER JOIN EVALUACION.ENCUESTA enc ON  ENCRE.CODIGO_ENCUESTA = enc.CODIGO_ENCUESTA "
                    + "WHERE enc.TIPO = 'A' "
                    + "AND ENCRE.ANIO =").append(smciclo.getValue().toString()).append(""
                            + "AND  are.cod_director =").append(codProfesor).append(""
                            + "ORDER BY MATERIA.CODIGO_NIVEACAD desc,nombre ASC");
            ls_cabecera = "DOCENTE";

        } else if (smreporte.getValue().toString().equalsIgnoreCase("CD") || smreporte.getValue().toString().equalsIgnoreCase("CA") || smreporte.getValue().toString().equalsIgnoreCase("P")) {
            sql.append("SELECT DISTINCT PROG.PRG_CODIGO, PROG.NOMBRE_PROGRAMA,"
                    + "PROG.COD_NIVEL_ACAD "
                    + "FROM PROGRAMA PROG "
                    + "INNER JOIN evaluacion.ENCUESTA_REALIZADA  AS ENCRE ON PROG.ANIO = ENCRE.ANIO "
                    + "AND  PROG.PRG_CODIGO = ENCRE.CODIGO_ESP "
                    + "INNER JOIN interfaseOcu.dbo.AREA are ON PROG.ARE_CODIGO = are.ARE_CODIGO "
                    + "INNER JOIN EVALUACION.ENCUESTA enc ON  ENCRE.CODIGO_ENCUESTA = enc.CODIGO_ENCUESTA "
                    + "WHERE  enc.TIPO = 'P' "
                    + "AND ENCRE.ANIO =  ").append(smciclo.getValue().toString()).append(""
                            + "  AND  are.cod_director =").append(codProfesor).append(""
                            + "ORDER BY PROG.COD_NIVEL_ACAD DESC");
            ls_cabecera = "PROGRAMA";
        } else if (smreporte.getValue().toString().equalsIgnoreCase("A")) {
            sql.append("SELECT DISTINCT  MATERIA.CODIGO_MATERIA, "
                    + "CONCAT( MATERIA.NOMBRE_MATERIA, ' / ',materia.PROGRAMA) NOMBRE, "
                    + "MATERIA.CODIGO_NIVEACAD "
                    + "FROM VISTA_COORDINADOR_PROGRAMA as MATERIA WITH (NOLOCK) "
                    + "INNER JOIN EVALUACION.ENCUESTA_REALIZADA  AS ENCRE WITH (NOLOCK) ON MATERIA.ANIO = ENCRE.ANIO "
                    + "and MATERIA.CICLO = ENCRE.CICLO  "
                    + "AND MATERIA.CODIGO_ESP = ENCRE.CODIGO_ESP "
                    + "AND MATERIA.CODIGO_NIVEL = ENCRE.CODIGO_NIVEL "
                    + "and MATERIA.CODIGO_MATERIA = ENCRE.CODIGO_MATERIA "
                    + "and MATERIA.CODIGO_PROFESOR = ENCRE.CODIGO_PROFESOR  "
                    + "INNER JOIN interfaseOcu.dbo.AREA are ON MATERIA.CODIGO_FACULTAD = are.ARE_CODIGO "
                    + "INNER JOIN EVALUACION.ENCUESTA enc ON  ENCRE.CODIGO_ENCUESTA = enc.CODIGO_ENCUESTA "
                    + "WHERE enc.TIPO = 'A' "
                    + "AND ENCRE.ANIO =").append(smciclo.getValue().toString()).append(""
                            + "AND  are.cod_director =").append(codProfesor).append(""
                            + "ORDER BY MATERIA.CODIGO_NIVEACAD desc,nombre ASC");

            ls_cabecera = "MATERIA";

        } else if (smreporte.getValue().toString().equalsIgnoreCase("C") || smreporte.getValue().toString().equalsIgnoreCase("CC")) {
            sql.append("SELECT DISTINCT PROG.PRG_CODIGO, PROG.NOMBRE_PROGRAMA,"
                    + "PROG.COD_NIVEL_ACAD "
                    + "FROM PROGRAMA PROG "
                    + "INNER JOIN evaluacion.ENCUESTA_REALIZADA  AS ENCRE ON PROG.ANIO = ENCRE.ANIO "
                    + "AND  PROG.PRG_CODIGO = ENCRE.CODIGO_ESP "
                    + "INNER JOIN interfaseOcu.dbo.AREA are ON PROG.ARE_CODIGO = are.ARE_CODIGO "
                    + "INNER JOIN EVALUACION.ENCUESTA enc ON  ENCRE.CODIGO_ENCUESTA = enc.CODIGO_ENCUESTA "
                    + "WHERE  enc.TIPO = 'C' "
                    + "AND ENCRE.ANIO =  ").append(smciclo.getValue().toString()).append(""
                            + "  AND  are.cod_director =").append(codProfesor).append(""
                            + "ORDER BY PROG.COD_NIVEL_ACAD DESC");
            ls_cabecera = "PROGAMA";
        } else if (smreporte.getValue().toString().equalsIgnoreCase("COP")) {
            sql.append("SELECT DISTINCT PROG.PRG_CODIGO, PROG.NOMBRE_PROGRAMA,"
                    + "PROG.COD_NIVEL_ACAD "
                    + "FROM PROGRAMA PROG "
                    + "INNER JOIN evaluacion.ENCUESTA_REALIZADA  AS ENCRE ON PROG.ANIO = ENCRE.ANIO "
                    + "AND  PROG.PRG_CODIGO = ENCRE.CODIGO_ESP "
                    + "INNER JOIN interfaseOcu.dbo.AREA are ON PROG.ARE_CODIGO = are.ARE_CODIGO "
                    + "INNER JOIN EVALUACION.ENCUESTA enc ON  ENCRE.CODIGO_ENCUESTA = enc.CODIGO_ENCUESTA "
                    + "WHERE  enc.TIPO = 'O' "
                    + "AND ENCRE.ANIO =  ").append(smciclo.getValue().toString()).append(""
                            + "  AND  are.cod_director =").append(codProfesor).append(""
                            + "ORDER BY PROG.COD_NIVEL_ACAD DESC");
            ls_cabecera = "PROGAMA";
        }

        if (sql.length() > 0) {
            v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
            if (v.size() > 0) {
                for (int i = 0; i < v.size(); i++) {
                    Object[] object = (Object[]) v.get(i);
                    String[] programaciclo;
                    programaciclo = new String[2];
                    programaciclo[0] = object[0].toString();
                    programaciclo[1] = object[1].toString();
                    listProgCiclo.add(i, programaciclo);
                }
            }
        }

    }
    // </editor-fold>
}
