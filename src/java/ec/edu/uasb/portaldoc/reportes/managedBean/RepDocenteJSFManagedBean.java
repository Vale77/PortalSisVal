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
@ManagedBean(name = "repdocente")
@ViewScoped
public class RepDocenteJSFManagedBean implements Serializable {

    private Long codProfesor;
    private Long anio;
    private String ls_reporte = null;
    private String url = null;
    private String paramrep = null;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private HtmlSelectOneMenu smreporte = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smciclo = new HtmlSelectOneMenu();
    private String displayReporte = null;
    private String ls_tabvacia = "No tiene Evaluaciones Realizadas para este período!!!";
    private List<String[]> listMateriaciclo = new ArrayList<String[]>();
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;

// <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public String getLs_tabvacia() {
        return ls_tabvacia;
    }

    public void setLs_tabvacia(String ls_tabvacia) {
        this.ls_tabvacia = ls_tabvacia;
    }

    public List<String[]> getListMateriaciclo() {
        return listMateriaciclo;
    }

    public void setListMateriaciclo(List<String[]> listMateriaciclo) {
        this.listMateriaciclo = listMateriaciclo;
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
    public RepDocenteJSFManagedBean() {
        //        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
//        codProfesor = usr.getUsuCodigo();
    }

    @PostConstruct
    private void init() {
        ciclos = cicloAcademicoFacade.findAll();
        if (!ciclos.isEmpty()) {
            anio = ciclos.get(0).getAnio();
        }
        codProfesor = Long.parseLong("90");
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
                    + "&titulo=" + selectedMateriaciclo[1] + '-' + selectedMateriaciclo[3]
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
// <editor-fold defaultstate="collapsed" desc="Validar">

    public int validar() {
        int resp = 0;
        if (smreporte.getValue() == null) {
            resp = -1;
        } else {
            if (smreporte.getValue().toString().equalsIgnoreCase("D")) {
                ls_reporte = "EvalDeEstudAlDocente";
                paramrep = "codigoProfesor=" + codProfesor
                        + "&anio=" + smciclo.getValue().toString()
                        + "&codigoMateria=" + selectedMateriaciclo[0]
                        + "&codigoNivel=" + selectedMateriaciclo[2];
            } else if (smreporte.getValue().toString().equalsIgnoreCase("A")) {
                ls_reporte = "EvalDeEstudAAsignatura";
                paramrep = "anio=" + smciclo.getValue().toString()
                        + "&codigoMateria=" + selectedMateriaciclo[0]
                        + "&codigoProfesor=" + codProfesor
                        + "&codigoNivel=" + selectedMateriaciclo[2];
            } else if (smreporte.getValue().toString().equalsIgnoreCase("C")) {
                ls_reporte = "EvalDeCoordAlDocenteVistaDocente";
                paramrep = "codesp=" + selectedMateriaciclo[4]
                        + "&anio=" + smciclo.getValue().toString()
                        + "&codAsignatura=" + selectedMateriaciclo[0]
                        + "&codProfesor=" + codProfesor.toString()
                        + "&codigoNivel=" + selectedMateriaciclo[2];

            }
        }
        if (smciclo.getValue() == null) {
            resp = -1;
        }

        return resp;
    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">    
    private String[] selectedMateriaciclo;

    public String[] getSelectedMateriaciclo() {
        return selectedMateriaciclo;
    }

    public void setSelectedMateriaciclo(String[] selectedMateriaciclo) {
        this.selectedMateriaciclo = selectedMateriaciclo;
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="CARGA DATOS TABLA">
    public void reporteChangeListener() {
        smciclo.resetValue();
        listMateriaciclo.clear();
    }

    public void cicloChangeListener() {
        listMateriaciclo.clear();
        Vector v = new Vector();

        StringBuilder sql = new StringBuilder();
        if (smreporte.getValue().toString().equalsIgnoreCase("C")) {
            sql.append("SELECT DISTINCT MATERIA.CODIGO_MATERIA,"
                    + " MATERIA.NOMBRE_MATERIA,"
                    + " MATERIA.codigo_nivel,"
                    + " MATERIA.TRIMESTRE,"
                    + " MATERIA.codigo_esp,"
                    + " MATERIA.PROGRAMA"
                    + " FROM VISTA_COORDINADOR_PROGRAMA AS MATERIA inner join EVALUACION.ENCUESTA_REALIZADA AS ENCRE"
                    + " on MATERIA.ANIO = ENCRE.ANIO"
                    + " AND MATERIA.CODIGO_ESP = ENCRE.CODIGO_ESP"
                    + " AND MATERIA.CODIGO_NIVEL =ENCRE.CODIGO_NIVEL "
                    + " and MATERIA.CICLO = ENCRE.CICLO"
                    + " and MATERIA.CODIGO_MATERIA = ENCRE.CODIGO_MATERIA "
                    + " and MATERIA.CODIGO_PROFESOR = ENCRE.CODIGO_PROFESOR INNER JOIN EVALUACION.ENCUESTA_CALENDARIO  AS ENCCAL"
                    + " ON ENCRE.ANIO = ENCCAL.ANIO "
                    + " AND ENCRE.CODIGO_ENCUESTA = ENCCAL.CODIGO_ENCUESTA "
                    + " AND ENCRE.CODIGO_ALUMNO = MATERIA.CODIGO_COORDINADOR"
                    + " AND MATERIA.CODIGO_ESP = ENCCAL.CODIGO_ESP "
                    + " AND ENCCAL.TIPO_ENCUESTA = 'C'  "
                    + " WHERE ENCRE.ANIO = ").append(smciclo.getValue().toString()).append(" AND  "
                            + " MATERIA.CODIGO_profesor = ").append(codProfesor).append(""
                            + " ORDER BY  MATERIA.codigo_nivel asc,"
                            + " MATERIA.NOMBRE_MATERIA ASC");

        } else {
            sql.append("SELECT DISTINCT MATERIA.CODIGO_MATERIA,"
                    + " MATERIA.NOMBRE_MATERIA,"
                    + " MATERIA.codigo_nivel,"
                    + " MATERIA.TRIMESTRE,"
                    + " ''codigo_esp,"
                    + " '' programa"
                    + " FROM VISTA_COORDINADOR_PROGRAMA AS MATERIA inner join EVALUACION.ENCUESTA_REALIZADA AS ENCRE"
                    + " on MATERIA.ANIO = ENCRE.ANIO"
                    + " and MATERIA.CICLO = ENCRE.CICLO"
                    + " AND MATERIA.CODIGO_ESP = ENCRE.CODIGO_ESP"
                    + " AND MATERIA.CODIGO_NIVEL = ENCRE.CODIGO_NIVEL"
                    + " and MATERIA.CODIGO_MATERIA = ENCRE.CODIGO_MATERIA "
                    + " and MATERIA.CODIGO_PROFESOR = ENCRE.CODIGO_PROFESOR"
                    + " WHERE MATERIA.ANIO = ").append(smciclo.getValue().toString()).append(" AND  "
                            + " MATERIA.CODIGO_profesor =").append(codProfesor).append(""
                            + " ORDER BY  MATERIA.codigo_nivel asc,"
                            + "  MATERIA.NOMBRE_MATERIA ASC ");
        }

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] materiaciclo;
                materiaciclo = new String[6];
                materiaciclo[0] = object[0].toString();
                materiaciclo[1] = object[1].toString();
                materiaciclo[2] = object[2].toString();
                materiaciclo[3] = object[3].toString();
                materiaciclo[4] = object[4].toString();
                materiaciclo[5] = object[5].toString();
                listMateriaciclo.add(i, materiaciclo);
            }
        }
    }
    // </editor-fold>
}
