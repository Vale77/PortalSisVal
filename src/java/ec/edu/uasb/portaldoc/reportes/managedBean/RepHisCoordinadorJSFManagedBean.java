
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
@ManagedBean(name = "rephistcoordinador")
@ViewScoped
public class RepHisCoordinadorJSFManagedBean implements Serializable {

    private Long codProfesor;
    private Long anio;
    private String ls_reporte = null;
    private String url = null;
    private String paramrep = null;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private HtmlSelectOneMenu smreporte = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smciclo = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smcicloreali = new HtmlSelectOneMenu();
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

    public HtmlSelectOneMenu getSmcicloreali() {
        return smcicloreali;
    }

    public void setSmcicloreali(HtmlSelectOneMenu smcicloreali) {
        this.smcicloreali = smcicloreali;
    }

// </editor-fold> 
    /**
     * Creates a new instance of RepDocenteJSFManagedBean
     */
    public RepHisCoordinadorJSFManagedBean() {
        //        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
//        codProfesor = usr.getUsuCodigo();
    }

    @PostConstruct
    private void init() {
        ciclos = cicloAcademicoFacade.findAll();
        if (!ciclos.isEmpty()) {
            anio = ciclos.get(0).getAnio();
        }
        codProfesor = Long.parseLong("647");
    }
// <editor-fold defaultstate="collapsed" desc="VER REPORTE">

    public void verReporte(String tipo) {        
       
        FacesContext context = FacesContext.getCurrentInstance();
        if (validar() == 0) {
            url = context.getExternalContext().getRequestScheme()
                    + "://" + context.getExternalContext().getRequestServerName()
                    + ":" + context.getExternalContext().getRequestServerPort()
                    + "/Syllabus/servlet?" + paramrep
                    + "&tipo=" + tipo
                    + "&reporte=" + ls_reporte
                    + "&contexto=PortalD" ;
                    //+ "&contexto=" + context.getExternalContext().getRealPath("/reportes");
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
        if (smreporte.getValue() == null) {
            resp = -1;
        } else {
            if (smreporte.getValue().toString().equalsIgnoreCase("D")) {
                ls_reporte = "revalestdocentecoordinador";
                paramrep = "codprofesor=" + selectedProgDocMateria[0]
                        + "&anio=" + smciclo.getValue().toString()
                        + "&titulo= Docente- "+selectedProgDocMateria[1]+ "- "+smciclo.getValue().toString() ;                
            } else if (smreporte.getValue().toString().equalsIgnoreCase("CD")) {
                ls_reporte = "revaldocenteconsolidado";
                 paramrep = "codesp=" + selectedProgDocMateria[0]
                        + "&anio=" + smciclo.getValue().toString()
                         + "&titulo= Consoldocente- "+selectedProgDocMateria[1]+ "- "+smciclo.getValue().toString() ;
            } else if (smreporte.getValue().toString().equalsIgnoreCase("A")) {
                ls_reporte = "EvalDeEstuAAsignatura";
                 paramrep = "codigoMateria=" + selectedProgDocMateria[0]
                        + "&anio=" + smciclo.getValue().toString();
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
                paramrep = "codesp=" + selectedProgDocMateria[0]
                        + "&anio=" + smcicloreali.getValue().toString();                
            }else if (smreporte.getValue().toString().equalsIgnoreCase("CT")) {
                ls_reporte = "EvalDeEstudAlTutorConsol";
                paramrep = "codesp=" + selectedProgDocMateria[0]
                        + "&anio=" + smcicloreali.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("P")) {
                ls_reporte = "revalprograma";
                paramrep = "codesp=" + selectedProgDocMateria[0]
                        + "&anio=" + smciclo.getValue().toString();
            } else if (smreporte.getValue().toString().equalsIgnoreCase("CP")) {
                ls_reporte = "revalprogramaconsol";
                paramrep = "codcordinador=" + codProfesor
                        + "&anio=" + smciclo.getValue().toString();
            }else if (smreporte.getValue().toString().equalsIgnoreCase("C")) {
                ls_reporte = "EvalDeCoordAlDocenteXTrim";
                paramrep = "codesp=" + selectedProgDocMateria[0]
                        + "&anio=" + smciclo.getValue().toString();
            }else if (smreporte.getValue().toString().equalsIgnoreCase("CC")) {
                ls_reporte = "EvalDeCoordAlDocente";
                paramrep = "codesp=" + selectedProgDocMateria[0]
                        + "&anio=" + smciclo.getValue().toString();
            }else if (smreporte.getValue().toString().equalsIgnoreCase("COP")) {
                ls_reporte = "EvalDeCoordAlDocente";
                paramrep = "codesp=" + selectedProgDocMateria[0]
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
     //   listProgCiclo.clear();
        Vector v = new Vector();
        StringBuilder sql = new StringBuilder();
        
       if (smreporte.getValue().toString().equalsIgnoreCase("CD")||smreporte.getValue().toString().equalsIgnoreCase("CA") ||smreporte.getValue().toString().equalsIgnoreCase("P"))  {
            sql.append(" SELECT DISTINCT VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP, "
                    + "VISTA_COORDINADOR_PROGRAMA.PROGRAMA+' MENCION: '+VISTA_COORDINADOR_PROGRAMA.MENCION  "                  
                    + "FROM VISTA_COORDINADOR_PROGRAMA inner join 	ENCUESTA_REALIZADA "
                    + "on VISTA_COORDINADOR_PROGRAMA.ANIO = ENCUESTA_REALIZADA.ANIO "
                    + "and VISTA_COORDINADOR_PROGRAMA.CICLO = ENCUESTA_REALIZADA.CICLO "
                    + "and VISTA_COORDINADOR_PROGRAMA.CODIGO_MATERIA = ENCUESTA_REALIZADA.CODIGO_MATERIA "
                    + "and VISTA_COORDINADOR_PROGRAMA.CODIGO_PROFESOR = ENCUESTA_REALIZADA.CODIGO_PROFESOR "
                    + "WHERE "
                    + "ENCUESTA_REALIZADA.ANIO = ").append(anio.toString()).append(" AND "
                    + "VISTA_COORDINADOR_PROGRAMA.CODIGO_COORDINADOR =").append(codProfesor).append(" "
                    + "ORDER BY VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP DESC");
            ls_cabecera= "PROGRAMA";
        }else if (smreporte.getValue().toString().equalsIgnoreCase("T") ||smreporte.getValue().toString().equalsIgnoreCase("CT")){
            sql.append("SELECT DISTINCT VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP, "
                    + "VISTA_COORDINADOR_PROGRAMA.PROGRAMA+' MENCION: '+VISTA_COORDINADOR_PROGRAMA.MENCION "
                    + " FROM VISTA_COORDINADOR_PROGRAMA inner join 	ENCUESTA_REALIZADA "
                    + " on VISTA_COORDINADOR_PROGRAMA.ANIO = ENCUESTA_REALIZADA.ANIO "
                    + " and VISTA_COORDINADOR_PROGRAMA.CICLO = ENCUESTA_REALIZADA.CICLO  INNER JOIN ENCUESTA_CALENDARIO "
                    + "	ON ENCUESTA_REALIZADA.ANIO = ENCUESTA_CALENDARIO.ANIO "
                    + "	AND ENCUESTA_REALIZADA.CODIGO_ENCUESTA = ENCUESTA_CALENDARIO.CODIGO_ENCUESTA "
                    + " AND ENCUESTA_REALIZADA.CODIGO_ALUMNO = ENCUESTA_CALENDARIO.COD_ESTUDIANTE "
                    + " AND VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP = ENCUESTA_CALENDARIO.CODIGO_ESP "
                    + "	AND ENCUESTA_CALENDARIO.TIPO_ENCUESTA = 'T' "
                    + " WHERE "
                    + " ENCUESTA_REALIZADA.ANIO =").append(smciclo.getValue().toString()).append( " AND "
                    + " VISTA_COORDINADOR_PROGRAMA.CODIGO_COORDINADOR =").append(codProfesor).append(" "
                    + "ORDER BY VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP DESC");
                    ls_cabecera= "PROGAMA";
        }else if (smreporte.getValue().toString().equalsIgnoreCase("C") ||smreporte.getValue().toString().equalsIgnoreCase("CC")){
            sql.append("SELECT DISTINCT VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP, "
                    + "VISTA_COORDINADOR_PROGRAMA.PROGRAMA+' MENCION: '+VISTA_COORDINADOR_PROGRAMA.MENCION "
                    + " FROM VISTA_COORDINADOR_PROGRAMA inner join 	ENCUESTA_REALIZADA "
                    + " on VISTA_COORDINADOR_PROGRAMA.ANIO = ENCUESTA_REALIZADA.ANIO "
                    + " and VISTA_COORDINADOR_PROGRAMA.CICLO = ENCUESTA_REALIZADA.CICLO  INNER JOIN ENCUESTA_CALENDARIO "
                    + "	ON ENCUESTA_REALIZADA.ANIO = ENCUESTA_CALENDARIO.ANIO "
                    + "	AND ENCUESTA_REALIZADA.CODIGO_ENCUESTA = ENCUESTA_CALENDARIO.CODIGO_ENCUESTA "
                    + " AND ENCUESTA_REALIZADA.CODIGO_ALUMNO = VISTA_COORDINADOR_PROGRAMA.CODIGO_COORDINADOR"
                    + " AND VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP = ENCUESTA_CALENDARIO.CODIGO_ESP "
                    + "	AND ENCUESTA_CALENDARIO.TIPO_ENCUESTA = 'C' "
                    + " WHERE "
                    + " ENCUESTA_REALIZADA.ANIO =").append(smciclo.getValue().toString()).append( " AND "
                    + " VISTA_COORDINADOR_PROGRAMA.CODIGO_COORDINADOR =").append(codProfesor).append(" "
                    + "ORDER BY VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP DESC");
                    ls_cabecera= "PROGAMA";
        }else if (smreporte.getValue().toString().equalsIgnoreCase("COP")){
            sql.append("SELECT DISTINCT VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP, "
                    + "VISTA_COORDINADOR_PROGRAMA.PROGRAMA+' MENCION: '+VISTA_COORDINADOR_PROGRAMA.MENCION "
                    + " FROM VISTA_COORDINADOR_PROGRAMA inner join 	ENCUESTA_REALIZADA "
                    + " on VISTA_COORDINADOR_PROGRAMA.ANIO = ENCUESTA_REALIZADA.ANIO "
                    + " and VISTA_COORDINADOR_PROGRAMA.CICLO = ENCUESTA_REALIZADA.CICLO  INNER JOIN ENCUESTA_CALENDARIO "
                    + "	ON ENCUESTA_REALIZADA.ANIO = ENCUESTA_CALENDARIO.ANIO "
                    + "	AND ENCUESTA_REALIZADA.CODIGO_ENCUESTA = ENCUESTA_CALENDARIO.CODIGO_ENCUESTA "
                    + " AND ENCUESTA_REALIZADA.CODIGO_ALUMNO = VISTA_COORDINADOR_PROGRAMA.CODIGO_COORDINADOR"
                    + " AND VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP = ENCUESTA_CALENDARIO.CODIGO_ESP "
                    + "	AND ENCUESTA_CALENDARIO.TIPO_ENCUESTA = 'O' "
                    + " WHERE "
                    + " ENCUESTA_REALIZADA.ANIO =").append(smciclo.getValue().toString()).append( " AND "
                    + " VISTA_COORDINADOR_PROGRAMA.CODIGO_COORDINADOR =").append(codProfesor).append(" "
                    + "ORDER BY VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP DESC");
                    ls_cabecera= "PROGAMA";
        }
        else{
             sql.append(" SELECT DISTINCT VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP, "
                    + "VISTA_COORDINADOR_PROGRAMA.PROGRAMA, "
                    + "VISTA_COORDINADOR_PROGRAMA.codigo_MATERIA, "
                    + "VISTA_COORDINADOR_PROGRAMA.NOMBRE_MATERIA, "
                    + "VISTA_COORDINADOR_PROGRAMA.CODIGO_PROFESOR, "
                    + "VISTA_COORDINADOR_PROGRAMA.APELLIDO_PROFESOR +' '+ VISTA_COORDINADOR_PROGRAMA.NOMBRE_PROFESOR nombre, "
                    + "VISTA_COORDINADOR_PROGRAMA.codigo_nivel "
                    + "FROM VISTA_COORDINADOR_PROGRAMA inner join 	ENCUESTA_REALIZADA "
                    + "on VISTA_COORDINADOR_PROGRAMA.ANIO = ENCUESTA_REALIZADA.ANIO "
                    + "and VISTA_COORDINADOR_PROGRAMA.CICLO = ENCUESTA_REALIZADA.CICLO "
                    + "and VISTA_COORDINADOR_PROGRAMA.CODIGO_MATERIA = ENCUESTA_REALIZADA.CODIGO_MATERIA "
                    + "and VISTA_COORDINADOR_PROGRAMA.CODIGO_PROFESOR = ENCUESTA_REALIZADA.CODIGO_PROFESOR "
                    + "WHERE "
                    + "ENCUESTA_REALIZADA.ANIO = ").append(smciclo.getValue().toString()).append(" AND "
                    + "VISTA_COORDINADOR_PROGRAMA.CODIGO_COORDINADOR =").append(codProfesor).append(" "
                    + "ORDER BY VISTA_COORDINADOR_PROGRAMA.CODIGO_ESP DESC, "
                    + "VISTA_COORDINADOR_PROGRAMA.codigo_nivel asc, "
                    + "VISTA_COORDINADOR_PROGRAMA.codigo_MATERIA,  "
                    + "VISTA_COORDINADOR_PROGRAMA.NOMBRE_MATERIA ASC,   "
                    + "nombre ASC");
        }

        
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
    // </editor-fold>
}
