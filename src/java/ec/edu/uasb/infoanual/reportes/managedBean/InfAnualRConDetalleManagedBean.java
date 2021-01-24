/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.reportes.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.entities.Profesor;
import ec.edu.uasb.sisevaluacion.managedBean.modalManagedBean;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "InfAnualrepConDet")
@ViewScoped
public class InfAnualRConDetalleManagedBean extends BaseJSFManagedBean implements Serializable {

    private String ls_reporte = null;
    private String paramrep = null;
    private String smciclo = null;
    private String smarea = null;
    private String smdedicacion = null;
    private String smtipcontrato = null;
    private String smtipo = null;
    private String smtipAmbito = null;
    private String smtipfiltro = null;
    private String scodProfesor = null;
    private LinkedHashMap<String, String> listsubambito = new LinkedHashMap<String, String>();
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    private List<String[]> listdedicacion = new ArrayList<String[]>();
    private List<String[]> listtipcontrato = new ArrayList<String[]>();
    private List<Profesor> lprofesor = new ArrayList<Profesor>();
    private List<Profesor> listProfesorFiltro;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @ManagedProperty(value = "#{modal}")
    private modalManagedBean modal1;
    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">

    public modalManagedBean getModal1() {
        return modal1;
    }

    public void setModal1(modalManagedBean modal1) {
        this.modal1 = modal1;
    }

    public LinkedHashMap<String, String> getListsubambito() {
        return listsubambito;
    }

    public void setListsubambito(LinkedHashMap<String, String> listsubambito) {
        this.listsubambito = listsubambito;
    }

    public String getSmtipAmbito() {
        return smtipAmbito;
    }

    public void setSmtipAmbito(String smtipAmbito) {
        this.smtipAmbito = smtipAmbito;
    }

    public String getSmtipfiltro() {
        return smtipfiltro;
    }

    public void setSmtipfiltro(String smtipfiltro) {
        this.smtipfiltro = smtipfiltro;
    }

    public List<Profesor> getListProfesorFiltro() {
        return listProfesorFiltro;
    }

    public void setListProfesorFiltro(List<Profesor> listProfesorFiltro) {
        this.listProfesorFiltro = listProfesorFiltro;
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

    public String getScodProfesor() {
        return scodProfesor;
    }

    public void setScodProfesor(String scodProfesor) {
        this.scodProfesor = scodProfesor;
    }

    public String getSmtipo() {
        return smtipo;
    }

    public void setSmtipo(String smtipo) {
        this.smtipo = smtipo;
    }

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    public String getSmarea() {
        return smarea;
    }

    public void setSmarea(String smarea) {
        this.smarea = smarea;
    }

    public String getSmdedicacion() {
        return smdedicacion;
    }

    public void setSmdedicacion(String smdedicacion) {
        this.smdedicacion = smdedicacion;
    }

    public List<String[]> getListdedicacion() {
        return listdedicacion;
    }

    public void setListdedicacion(List<String[]> listdedicacion) {
        this.listdedicacion = listdedicacion;
    }

    public List<String[]> getListtipcontrato() {
        return listtipcontrato;
    }

    public void setListtipcontrato(List<String[]> listtipcontrato) {
        this.listtipcontrato = listtipcontrato;
    }

    public String getSmtipcontrato() {
        return smtipcontrato;
    }

    public void setSmtipcontrato(String smtipcontrato) {
        this.smtipcontrato = smtipcontrato;
    }

    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
    }

    public List<Profesor> getLprofesor() {
        return lprofesor;
    }

    public void setLprofesor(List<Profesor> lprofesor) {
        this.lprofesor = lprofesor;
    }

    // </editor-fold> 
    /**
     * Creates a new instance of InfAnualRConsolidadoMnagedBean
     */
    public InfAnualRConDetalleManagedBean() {
    }

    @Override
    public void init() {
        retrieveDatos();
    }

    private void retrieveDatos() {
        //Recupero las areas
        ciclos = cicloAcademicoFacade.findAllActivos();
    }

    // <editor-fold defaultstate="collapsed" desc="RECUPERAR TIPO CONTRATO ">   
    private void recTipContrato() {
        Vector v = new Vector();
        listtipcontrato.clear();
        StringBuilder sql = new StringBuilder();
        sql.append("select DISTINCT IAC_TIPO_CONTRATO, "
                + " (CASE IAC_TIPO_CONTRATO  "
                + "	WHEN 1 THEN 'Planta Escalafonado' "
                + "	WHEN 2 THEN 'Planta Contratado' "
                + "	WHEN 3 THEN 'Contratado' "
                + "	WHEN 4 THEN 'Contratado Designado' "
                + "	WHEN 5 THEN 'Invitado' ELSE 'SIN CONTRATO'END )Nom_tipContrato "
                + " FROM interfaseOcu.GESTIONACADEMICA.INFANUAL_CALENDARIO ENCCAL "
                + " WHERE ENCCAL.IAC_ANIO = ").append(smciclo);

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[2];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                listtipcontrato.add(i, asign);
            }
        }
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="RECUPERAR DEDICACIONES ">   
    private void recDedicacion() {
        Vector v = new Vector();
        listdedicacion.clear();
        StringBuilder sql = new StringBuilder();
        sql.append("select DISTINCT IAC_TIPO_DOCENTE, "
                + " (CASE IAC_TIPO_DOCENTE  "
                + "	WHEN 'C' THEN 'Tiempo Completo' "
                + "	WHEN 'M' THEN 'Medio Tiempo' "
                + "	WHEN 'P' THEN 'Tiempo Parcial' "
                + "	WHEN 'I' THEN 'Invitado' ELSE 'SIN CONTRATO'END )Nom_tipDocente "
                + " FROM interfaseOcu.GESTIONACADEMICA.INFANUAL_CALENDARIO ENCCAL "
                + " WHERE ENCCAL.IAC_ANIO = ").append(smciclo).append("");
        if (smtipcontrato != null && !smtipcontrato.equalsIgnoreCase("T")) {
            sql.append(" AND ENCCAL.IAC_TIPO_CONTRATO = ").append(smtipcontrato);
        }

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[2];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                listdedicacion.add(i, asign);
            }
        }
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private Profesor selectedprofesor = new Profesor();

    public Profesor getSelectedprofesor() {
        return selectedprofesor;
    }

    public void setSelectedprofesor(Profesor selectedprofesor) {
        this.selectedprofesor = selectedprofesor;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="VALIDAR">  
    public int validar() {
        int resp = 0;
        if (smtipo == null) {
            resp = -1;
        } else if (smciclo == null) {
            resp = -1;
        }
        return resp;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="ARMAR PARAMETROS">  
    private String armarparametros() {
        scodProfesor = "T"; //TODOS LOS DOCENTES
        paramrep = "&anio=" + smciclo;
        if (smarea == null) {
            smarea = "T";
        }
        if (smtipcontrato == null) {
            smtipcontrato = "T";
        }
        if (smdedicacion == null) {
            smdedicacion = "T";
        }
        if (!smtipo.isEmpty()) {
            paramrep = paramrep + "&tipReporte=" + smtipo;
            if (smtipo.equalsIgnoreCase("D")) {
                scodProfesor = Integer.toString(selectedprofesor.getCodigoProfesor());
            }
            paramrep = paramrep + "&codProfesor=" + scodProfesor
                    + "&codArea=" + smarea
                    + "&codTContrato=" + smtipcontrato
                    + "&codDedicacion=" + smdedicacion;
        }
        if (!smtipfiltro.isEmpty()) {
            if (smtipfiltro.equalsIgnoreCase("1")) {

                ls_reporte = "InfAnualConAsigUDet";
                paramrep = paramrep + "&titulo= Detalle Asignatura-UASB- " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("2")) {
                ls_reporte = "InfAnualConAsigODet";
                paramrep = paramrep + "&titulo=  Detalle Asignatura-Otra Universidad - " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("3")) {
                ls_reporte = "InfAnualConTitDocDet";
                paramrep = paramrep + "&titulo=  Detalle Titulacion-Doctorado - " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("4")) {
                ls_reporte = "InfAnualConTitMaeDet";
                paramrep = paramrep + "&titulo=  Detalle Titulacion-Maestría - " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("5")) {
                ls_reporte = "InfAnualConTitEspDet";
                paramrep = paramrep + "&titulo=  Detalle Titulacion-Especializacion - " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("6")) {
                ls_reporte = "InfAnualConTitExEspDet";
                paramrep = paramrep + "&titulo=  Detalle Titulacion-Examen Complexivo - " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("7")) {
                ls_reporte = "InfAnualConTitExDocDet";
                paramrep = paramrep + "&titulo=  Detalle Titulacion-Trabajos Doctorado - " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("8")) {
                ls_reporte = "InfAnualConInvFUasbDet";
                paramrep = paramrep + "&titulo=  Detalle Investigacion-Proyectos Fondo UASB - " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("9")) {
                ls_reporte = "InfAnualConInvInterDet";
                paramrep = paramrep + "&titulo=  Detalle Investigacion-Interinstitucional - " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("10")) {
                ls_reporte = "InfAnualConInvAsoDet";
                paramrep = paramrep + "&titulo=  Detalle Investigacion-Acompañamiento Investigadores - " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("11")) {
                ls_reporte = "InfAnualConPubLibDet";
                paramrep = paramrep + "&titulo=  Detalle Publicaciones-Libros- " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("12")) {
                ls_reporte = "InfAnualConPubCapLibDet";
                paramrep = paramrep + "&titulo=  Detalle Publicaciones-Capitulo en Libros- " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("13")) {
                ls_reporte = "InfAnualConPubRevDet";
                paramrep = paramrep + "&titulo=  Detalle Publicaciones-Revistas- " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("14")) {
                ls_reporte = "InfAnualConPubPonDet";
                paramrep = paramrep + "&titulo=  Detalle Publicaciones-Ponencias- " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("15")) {
                ls_reporte = "InfAnualConPubAudDet";
                paramrep = paramrep + "&titulo=  Detalle Publicaciones-Audiovisuales- " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("16")) {
                ls_reporte = "InfAnualConPubOPubDet";
                paramrep = paramrep + "&titulo=  Detalle Publicaciones-Otras Publicaciones- " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("17")) {
                ls_reporte = "InfAnualConVinCurTalDet";
                paramrep = paramrep + "&titulo=  Detalle Vinculacion-Cursos Talleres Pemanentes- " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("18")) {
                ls_reporte = "InfAnualConVinCurAbiDet";
                paramrep = paramrep + "&titulo=  Detalle Vinculacion-Cursos Abiertos- " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("19")) {
                ls_reporte = "InfAnualConVinAcEvUasbDet";
                paramrep = paramrep + "&titulo=  Detalle Vinculacion-Actividades y Eventos Academicos UASB- " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("20")) {
                ls_reporte = "InfAnualConVinAcEvODet";
                paramrep = paramrep + "&titulo=  Detalle Vinculacion-Actividades y Eventos Academicos Otras Instituciones- " + smciclo;
            }
             if (smtipfiltro.equalsIgnoreCase("21")) {
                ls_reporte = "InfAnualConGestFunDirDet";
                paramrep = paramrep + "&titulo=  Detalle Gestion Academica-Funciones Academicas- " + smciclo;
            }
             if (smtipfiltro.equalsIgnoreCase("22")) {
                ls_reporte = "InfAnualConGestCordAcaDet";
                paramrep = paramrep + "&titulo=  Detalle Gestion Academica-Coordinación Academica- " + smciclo;
            }
             if (smtipfiltro.equalsIgnoreCase("23")) {
                ls_reporte = "InfAnualConGestMiComInsDet";
                paramrep = paramrep + "&titulo=  Detalle Gestion Academica-Comite Institucional " + smciclo;
            }
             if (smtipfiltro.equalsIgnoreCase("24")) {
                ls_reporte = "InfAnualConGestMiComTriDet";
                paramrep = paramrep + "&titulo=  Detalle Gestion Academica-Comite Tribunal " + smciclo;
            }
             if (smtipfiltro.equalsIgnoreCase("25")) {
                ls_reporte = "InfAnualConGestDisProgDet";
                paramrep = paramrep + "&titulo=  Detalle Gestion Academica-Diseño Programa Posgrado- " + smciclo;
            }
             if (smtipfiltro.equalsIgnoreCase("26")) {
                ls_reporte = "InfAnualConGestDisEduConDet";
                paramrep = paramrep + "&titulo=  Detalle Gestion Academica-Diseño Educacion Continua " + smciclo;
            }
             if (smtipfiltro.equalsIgnoreCase("27")) {
                ls_reporte = "InfAnualConGestOrgActEvVinDet";
                paramrep = paramrep + "&titulo=  Detalle Gestion Academica-Organizacion Actividades y Eventos Vinculacion- " + smciclo;
            }
             if (smtipfiltro.equalsIgnoreCase("28")) {
                ls_reporte = "InfAnualConGestOFunDet";
                paramrep = paramrep + "&titulo=  Detalle Gestion Academica-Otra Funcion- " + smciclo;
            }             
            if (smtipfiltro.equalsIgnoreCase("29")) {
                ls_reporte = "InfAnualConGestPertOrgDet";
                paramrep = paramrep + "&titulo=  Detalle Gestion Academica-Pertenencia Organizaciones - " + smciclo;
            }
            if (smtipfiltro.equalsIgnoreCase("30")) {
                ls_reporte = "InfAnualConPerfPerDocDet";
                paramrep = paramrep + "&titulo=  Detalle Perfeccionamiento Docente-Perfeccionamiento DOcente - " + smciclo;
            }
        }

        return paramrep;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="VER REPORTE">  
    public void verReporte(String tipo) {
        if (validar() == 0) {
            paramrep = armarparametros();
            modal1.verReporte(tipo, paramrep, ls_reporte);
        }
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="ValueChangeListener">  
    public void tipAmbitovalueChangeListener() {
        smtipfiltro = null;
        smtipo = null;
        smciclo = null;
        smarea = null;
        smtipcontrato = null;
        smdedicacion = null;
        lprofesor.clear();
        listtipcontrato.clear();
        listdedicacion.clear();
        listsubambito.clear();
        if (smtipAmbito != null) {
            switch (smtipAmbito.charAt(0)) {
                case 'D':
                    listsubambito.put("Asignatura UASB", "1");
                    listsubambito.put("Asignatura Otras Universidades", "2");
                    break;
                case 'T':
                    listsubambito.put("Doctorado", "3");
                    listsubambito.put("Maestría", "4");
                    listsubambito.put("Especialización Superior", "5");
                    listsubambito.put("Examen Complexivo", "6");
                    listsubambito.put("Trabajos de Doctorado", "7");

                    break;
                case 'I':
                    listsubambito.put("Proyectos Fondo UASB", "8");
                    listsubambito.put("Investigaciones Interinstitucional", "9");
                    listsubambito.put("Acompañamiento Investigadores", "10");
                    break;
                case 'P':
                    listsubambito.put("Libros", "11");
                    listsubambito.put("Capitulo en Libros", "12");
                    listsubambito.put("Revistas", "13");
                    listsubambito.put("Ponencias", "14");
                    listsubambito.put("Audiovisuales", "15");
                    listsubambito.put("Otras Publicaciones", "16");
                    break;
                case 'V':
                    listsubambito.put("Cursos y Talleres Permanentes", "17");
                    listsubambito.put("Cursos Abiertos UASB", "18");
                    listsubambito.put("Actividades y Eventos Académicos UASB", "19");
                    listsubambito.put("Actividades y Eventos Académicos Otras Instituciones", "20");
                    break;
                case 'G':
                    listsubambito.put("Funciones de Dirección", "21");
                    listsubambito.put("Coordinación Académica", "22");
                    listsubambito.put("Miembro Comité Institucional", "23");
                    listsubambito.put("Miembro Comité/ Tribunal", "24");
                    listsubambito.put("Diseño Programas Posgrado", "25");
                    listsubambito.put("Diseño Educación Continua", "26");
                    listsubambito.put("Organización Eventos y Actividades Vinculación", "27");
                    listsubambito.put("Otra Función", "28");                    
                    listsubambito.put("Pertencia a Organizaciones", "29");
                    break;
                case 'E':
                    listsubambito.put("Perfeccionamiento Docente", "30");
                    break;
                default:
                    break;
            }
        }
    }

    public void tipFiltrovalueChangeListener() {
        smtipo = null;
        smciclo = null;
        smarea = null;
        smtipcontrato = null;
        smdedicacion = null;
        lprofesor.clear();
        listtipcontrato.clear();
        listdedicacion.clear();
    }

    public void tipovalueChangeListener() {
        smciclo = null;
        smarea = null;
        smtipcontrato = null;
        smdedicacion = null;
        lprofesor.clear();
        listtipcontrato.clear();
        listdedicacion.clear();
    }

    public void ciclovalueChangeListener() {
        StringBuilder sql = new StringBuilder();
        smarea = null;
        smtipcontrato = null;
        smdedicacion = null;
        lprofesor.clear();
        if (smciclo != null) {
            recTipContrato();
            if (smtipo != null && smtipo.equalsIgnoreCase("D")) {
                sql.append("AND ANIO = ").append(smciclo).append("");
                lprofesor = consultaFacade.findProfesorInfAnualCEbySql(sql.toString());
            }
        }
    }

    public void areavalueChangeListener() {
        smtipcontrato = null;
        smdedicacion = null;
        lprofesor.clear();
        listtipcontrato.clear();
        recTipContrato();
    }

    public void tipContvalueChangeListener() {
        smdedicacion = null;
        lprofesor.clear();
        if (smtipo != null) {
            if (smtipo.equalsIgnoreCase("E")) {
                recDedicacion();
            }
        }

    }

     // </editor-fold> 
}
