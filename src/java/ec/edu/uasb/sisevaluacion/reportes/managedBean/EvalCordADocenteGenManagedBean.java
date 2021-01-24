/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.reportes.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.sisevaluacion.entities.Profesor;
import ec.edu.uasb.sisevaluacion.managedBean.modalManagedBean;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import ec.edu.uasb.sisevaluacion.session.ProfesorFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "EvalCordADocenteGen")
@ViewScoped
public class EvalCordADocenteGenManagedBean extends BaseJSFManagedBean implements Serializable {

    private Long anio;
    private String ls_reporte = null;
    private String paramrep = null;
    private String smciclo = null;
    private String lsanio = "";
    private String lsprograma = "";
    private String lsmateria = "";

    private List<String[]> listprograma = new ArrayList<String[]>();
    private List<Profesor> lprofesor = new ArrayList<Profesor>();
    private List<String[]> listmateria = new ArrayList<String[]>();
    @EJB
    private ProfesorFacadeLocal profesorFacade;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @ManagedProperty(value = "#{modal}")
    private modalManagedBean modal1;

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public modalManagedBean getModal1() {
        return modal1;
    }

    public void setModal1(modalManagedBean modal1) {
        this.modal1 = modal1;
    }

    public List<Profesor> getLprofesor() {
        return lprofesor;
    }

    public void setLprofesor(List<Profesor> lprofesor) {
        this.lprofesor = lprofesor;
    }

    public List<String[]> getListprograma() {
        return listprograma;
    }

    public void setListprograma(List<String[]> listprograma) {
        this.listprograma = listprograma;
    }

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public String getLs_reporte() {
        return ls_reporte;
    }

    public void setLs_reporte(String ls_reporte) {
        this.ls_reporte = ls_reporte;
    }

    public String getParamrep() {
        return paramrep;
    }

    public void setParamrep(String paramrep) {
        this.paramrep = paramrep;
    }

    public List<String[]> getListmateria() {
        return listmateria;
    }

    public void setListmateria(List<String[]> listmateria) {
        this.listmateria = listmateria;
    }

    public String getLsanio() {
        return lsanio;
    }

    public void setLsanio(String lsanio) {
        this.lsanio = lsanio;
    }

    public String getLsprograma() {
        return lsprograma;
    }

    public void setLsprograma(String lsprograma) {
        this.lsprograma = lsprograma;
    }

    public String getLsmateria() {
        return lsmateria;
    }

    public void setLsmateria(String lsmateria) {
        this.lsmateria = lsmateria;
    }

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }
    // </editor-fold> 

    public EvalCordADocenteGenManagedBean() {
    }

    @Override
    public void init() {
        lprofesor = profesorFacade.findProfesorEvaluacion();

    }
// <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA DE PROFESOR">  
    private Profesor selectedprofesor = new Profesor();

    public Profesor getSelectedprofesor() {
        return selectedprofesor;
    }

    public void setSelectedprofesor(Profesor selectedprofesor) {
        this.selectedprofesor = selectedprofesor;
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="CARGAR TABLA PROGRAMAS ">   
    private void recuperaPrograma(int codigoProfesor) {
        Vector v = new Vector();
        listprograma.clear();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT VCORD.CODIGO_ESP,VCORD.ANIO, programa + ' CICLO ACADÉMICO '+NOMBRE_CICLO AS PROGRAMA "
                + " FROM EVALUACION.ENCUESTA ENCU  "
                + " INNER JOIN EVALUACION.RESPUESTA AS RESPUESTA ON ENCU.CODIGO_ENCUESTA = RESPUESTA.CODIGO_ENCUESTA "
                + " INNER JOIN dbo.VISTA_COORDINADOR_PROGRAMA AS VCORD ON VCORD.ANIO = RESPUESTA.ANIO "
                + " AND VCORD.CODIGO_ESP = RESPUESTA.CODIGO_ESP "
                + " AND VCORD.CODIGO_NIVEL = RESPUESTA.CODIGO_NIVEL "
                + " AND VCORD.CODIGO_MATERIA = RESPUESTA.CODIGO_MATERIA "
                + " AND VCORD.CODIGO_PROFESOR = RESPUESTA.CODIGO_PROFESOR "
                + " INNER JOIN dbo.CICLO_ACADEMICO "
                + " ON VCORD.anio = CICLO_ACADEMICO.ANIO "
                + " WHERE  ENCU.TIPO= 'C' "
                + " AND RESPUESTA.CODIGO_PROFESOR =").append(codigoProfesor).append(
                "  ORDER BY VCORD.ANIO DESC ");

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[3];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                asign[2] = (object[2] == null ? null : object[2].toString());

                listprograma.add(i, asign);
            }
        }

    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA DE PROGRAMA">  
    private List<String[]> selectedprograma = new ArrayList<String[]>();

    public List<String[]> getSelectedprograma() {
        return selectedprograma;
    }

    public void setSelectedprograma(List<String[]> selectedprograma) {
        this.selectedprograma = selectedprograma;
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="VALIDAR">  
    public int validar() {
        int resp = 0;
        if (selectedprofesor == null) {
            resp = -1;
        } else if (selectedprograma == null) {
            resp = -1;
        } else if (selectedmateria == null) {
            resp = -1;
        } else {
            if (selectedmateria.size() > 0) {
                lsmateria = "";
                lsprograma = "";
                lsanio = "";
                for (int i = 0; i < selectedmateria.size(); i++) {
                    String[] asign;
                    asign = new String[5];
                    asign = selectedmateria.get(i);
                    lsmateria = lsmateria + "'" + asign[4] + '-' + asign[3] + '-' + asign[1] + "'" + ',';

                    lsanio = lsanio + asign[4] + ',';
                }
                lsmateria = lsmateria.substring(0, lsmateria.length() - 1);

                lsanio = lsanio.substring(0, lsanio.length() - 1);
            }
        }
        return resp;
    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="VER REPORTE">  

    public void verReporte(String tipo) {

        if (validar() == 0) {
            ls_reporte = "EvalDeCoordAlDocenteHis";
            paramrep = armarparametros();
            modal1.verReporte(tipo, paramrep, ls_reporte);
        }
    }
    // </editor-fold> 

    public void onRowSelect(SelectEvent event) {
        selectedprofesor = (Profesor) event.getObject();
        recuperaPrograma(selectedprofesor.getCodigoProfesor());

    }

    public void onRowUnselect(UnselectEvent event) {
        selectedprofesor = null;
        listprograma.clear();
    }
// <editor-fold defaultstate="collapsed" desc="CARGAR TABLA MATERIAS ">   

    private void recuperaMateria(int codigoProfesor, String anio, String codigoEsp) {
        Vector v = new Vector();

        StringBuilder sql = new StringBuilder();
        sql.append(" DECLARE @MATERIA TABLE (CODIGO_PROFESOR NUMERIC(8,0),NOMBRE_PROFESOR VARCHAR(100), APELLIDO_PROFESOR VARCHAR(100), NOMBRE_MATERIA VARCHAR(512),CODIGO_NIVEL INT, PROGRAMA VARCHAR(1024),");
        sql.append(" CODIGO_COORDINADOR VARCHAR(384), CODIGO_ESP VARCHAR(4),  CODIGO_MATERIA NUMERIC(9,0), ANIO INT,   TRIMESTRE VARCHAR(75), IDENTIF_MATERIA VARCHAR(15),CICLO VARCHAR(384))");
        sql.append(" INSERT INTO @MATERIA( CODIGO_PROFESOR,NOMBRE_PROFESOR , APELLIDO_PROFESOR , NOMBRE_MATERIA,CODIGO_NIVEL, PROGRAMA,CODIGO_COORDINADOR, ");
        sql.append(" CODIGO_ESP,  CODIGO_MATERIA, ANIO, TRIMESTRE, IDENTIF_MATERIA ,CICLO )");
        sql.append(" SELECT DISTINCT CODIGO_PROFESOR,NOMBRE_PROFESOR , APELLIDO_PROFESOR , NOMBRE_MATERIA,CODIGO_NIVEL, PROGRAMA,CODIGO_COORDINADOR, CODIGO_ESP,  CODIGO_MATERIA, ANIO, TRIMESTRE, IDENTIF_MATERIA ,CICLO "
                + " FROM VISTA_COORDINADOR_PROGRAMA "
                + " WHERE ANIO in ( ").append(anio).append(") "
                + "AND CODIGO_ESP in ( ").append(codigoEsp).append(") "
                + "AND CODIGO_PROFESOR= ").append(codigoProfesor).append("");

        sql.append(" SELECT DISTINCT VCORD.CODIGO_nivel,VCORD.CODIGO_MATERIA,VCORD.NOMBRE_MATERIA+ ' - '+VCORD.TRIMESTRE as MATERIA, "
                + " VCORD.CODIGO_ESP, VCORD.anio"
                + " FROM @MATERIA AS VCORD INNER JOIN EVALUACION.RESPUESTA AS RESPUESTA"
                + " ON VCORD.ANIO = RESPUESTA.ANIO"
                + " AND VCORD.CODIGO_ESP = RESPUESTA.CODIGO_ESP"
                + " AND VCORD.CODIGO_NIVEL = RESPUESTA.CODIGO_NIVEL"
                + " AND VCORD.CODIGO_MATERIA = RESPUESTA.CODIGO_MATERIA "
                + " AND VCORD.CODIGO_PROFESOR = RESPUESTA.CODIGO_PROFESOR inner join CICLO_ACADEMICO"
                + " on VCORD.anio = CICLO_ACADEMICO.ANIO  INNER JOIN EVALUACION.ENCUESTA ENCUESTA"
                + " ON RESPUESTA.CODIGO_ENCUESTA =  ENCUESTA.CODIGO_ENCUESTA"
                + " AND ENCUESTA.TIPO = 'C' "
                + " where RESPUESTA.CODIGO_PROFESOR = ").append(codigoProfesor).append(""
                + " and RESPUESTA.ANIO in ( ").append(anio).append(") "
                + " and RESPUESTA.CODIGO_ESP in ( ").append(codigoEsp).append(") "
                + " ORDER BY VCORD.ANIO DESC,VCORD.CODIGO_ESP , VCORD.CODIGO_nivel ");

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[5];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                asign[2] = (object[2] == null ? null : object[2].toString());
                asign[3] = (object[3] == null ? null : object[3].toString());
                asign[4] = (object[4] == null ? null : object[4].toString());
                listmateria.add(i, asign);

            }
        }

    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA DE MATERIA">  
    private List<String[]> selectedmateria = new ArrayList<String[]>();

    public List<String[]> getSelectedmateria() {
        return selectedmateria;
    }

    public void setSelectedmateria(List<String[]> selectedmateria) {
        this.selectedmateria = selectedmateria;
    }

    // </editor-fold> 
    public void programavalueChange() {
        if (selectedprograma != null) {
            if (selectedprograma.size() > 0) {
                listmateria.clear();
                lsprograma = "";
                lsanio = "";
                for (int i = 0; i < selectedprograma.size(); i++) {
                    String[] asign;
                    asign = new String[3];
                    asign = selectedprograma.get(i);
                    lsprograma = lsprograma + asign[0] + ',';
                    lsanio = lsanio + asign[1] + ',';
                }
                lsanio = lsanio.substring(0, lsanio.length() - 1);
                lsprograma = lsprograma.substring(0, lsprograma.length() - 1);

                recuperaMateria(selectedprofesor.getCodigoProfesor(), lsanio, lsprograma);
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="ARMAR PARAMETROS">  
    private String armarparametros() {
        paramrep = "&lsAnio=" + lsanio
                + "&lsMateria=" + lsmateria
                + "&codigoProfesor=" + Integer.toString(selectedprofesor.getCodigoProfesor());

        return paramrep;
    }
    // </editor-fold> 

}
