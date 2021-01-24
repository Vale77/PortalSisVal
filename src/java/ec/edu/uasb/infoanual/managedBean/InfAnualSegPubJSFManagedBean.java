/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.infoanual.session.InfanualRealizadoFacadeLocal;
import ec.edu.uasb.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import ec.edu.uasb.sisevaluacion.managedBean.modalManagedBean;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "segPublica")
@ViewScoped
public class InfAnualSegPubJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private String smciclo = null;
    private String msgtablavacia = "No existen datos Registrados";
    private String scodProfesor = null;
    private String ls_reporte = null;
    private String paramrep = null;
    private List<CicloAcademico> ciclos = new ArrayList<CicloAcademico>();
    //Informacion TESIS UASB
    //private List<String[]> listinfAnualReapertura = new ArrayList<String[]>();
    private List<String[]> listInfAnualRealizado = new ArrayList<String[]>();
    //private List<Profesor> lprofesor = new ArrayList<Profesor>();
    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private InfanualRealizadoFacadeLocal infanualRealizadoFacade;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @ManagedProperty(value = "#{modal}")
    private modalManagedBean modal1;
    //Informacion PUBLICACIONES PROFESOR    
    private List<String[]> listPubProfesor = new ArrayList<String[]>();
    //archivo para descarga
    private StreamedContent file;
    

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    public StreamedContent getFile() {
        return file;
    }

    public List<String[]> getListPubProfesor() {
        return listPubProfesor;
    }

    public void setListPubProfesor(List<String[]> listPubProfesor) {
        this.listPubProfesor = listPubProfesor;
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

    public String getMsgtablavacia() {
        return msgtablavacia;
    }

    public void setMsgtablavacia(String msgtablavacia) {
        this.msgtablavacia = msgtablavacia;
    }

    public List<CicloAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<CicloAcademico> ciclos) {
        this.ciclos = ciclos;
    }

    public String getScodProfesor() {
        return scodProfesor;
    }

    public void setScodProfesor(String scodProfesor) {
        this.scodProfesor = scodProfesor;
    }

    public modalManagedBean getModal1() {
        return modal1;
    }

    public void setModal1(modalManagedBean modal1) {
        this.modal1 = modal1;
    }

    public List<String[]> getListInfAnualRealizado() {
        return listInfAnualRealizado;
    }

    public void setListInfAnualRealizado(List<String[]> listInfAnualRealizado) {
        this.listInfAnualRealizado = listInfAnualRealizado;
    }

    // </editor-fold> 
    /**
     * Creates a new instance of InfAnualSegPubJSFManagedBean
     */
    public InfAnualSegPubJSFManagedBean() {
    }

    @Override
    public void init() {
        retrieveDatos();
    }

    private void retrieveDatos() {
        ciclos = cicloAcademicoFacade.findAllActivos();
    }

    public void ciclovalueChangeListener() {
        StringBuilder sql = new StringBuilder();
        listInfAnualRealizado.clear();
        listPubProfesor.clear();
        if (smciclo != null) {
            recuperaInfAnualRea();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private String[] selectedinfAnualRea;

    public String[] getSelectedinfAnualRea() {
        return selectedinfAnualRea;
    }

    public void setSelectedinfAnualRea(String[] selectedinfAnualRea) {
        this.selectedinfAnualRea = selectedinfAnualRea;
    }
    private String[] selectedpubinfAnual;

    public String[] getSelectedpubinfAnual() {
        return selectedpubinfAnual;
    }

    public void setSelectedpubinfAnual(String[] selectedpubinfAnual) {
        this.selectedpubinfAnual = selectedpubinfAnual;
    }
    private String[] selectedpubinfAnualEdit;

    public String[] getSelectedpubinfAnualEdit() {
        return selectedpubinfAnualEdit;
    }

    public void setSelectedpubinfAnualEdit(String[] selectedpubinfAnualEdit) {
        this.selectedpubinfAnualEdit = selectedpubinfAnualEdit;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="VALIDAR">  
    public int validar() {
        int resp = 0;
        if (smciclo == null) {
            resp = -1;
        }
        return resp;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA INFORME ANUAL ">   
    private void recuperaInfAnualRea() {
        Vector v = new Vector();
        listInfAnualRealizado.clear();
        listPubProfesor.clear();
        StringBuilder sql = new StringBuilder();
        sql.append("DECLARE @PROFESOR TABLE (CODIGO_PROFESOR NUMERIC(7,0), CED_PAS_PROFESOR VARCHAR(15),nombre_profesor VARCHAR(32), apellido_profesor VARCHAR(35), "
                + "	cod_profesor_acadant NUMERIC(10,0), dedicacion VARCHAR(1), cod_tipocontrato INT, ANIO_INICONTRATO INT, ANIO_FINCONTRATO INT ) "
                + "	INSERT  INTO @PROFESOR(CODIGO_PROFESOR,CED_PAS_PROFESOR,nombre_profesor, apellido_profesor, cod_profesor_acadant, dedicacion, cod_tipocontrato,  ANIO_INICONTRATO, ANIO_FINCONTRATO) "
                + "	SELECT CODIGO_PROFESOR,CED_PAS_PROFESOR,nombre_profesor, apellido_profesor, cod_profesor_acadant, dedicacion, "
                + "  cod_tipocontrato, ANIO_INICONTRATO, ANIO_FINCONTRATO "
                + "	FROM interfaseOcu.dbo.PROFESOR "
                + "SELECT PRF.CODIGO_PROFESOR,PRF.cod_profesor_acadant,PRF.APELLIDO_PROFESOR+' '+PRF.NOMBRE_PROFESOR NOMBRE ,"
                + "IARE.IAE_ANIO, IARE.IAE_FECHA, IARE.IAE_ESTADO_IAAD,"
                + "(CASE IARE.IAE_ESTADO_IAAD WHEN 'I' THEN 'INGRESADO' WHEN 'F' THEN 'FINALIZADO' END ) "
                + "FROM GESTIONACADEMICA.INFANUAL_REALIZADO IARE "
                + "INNER JOIN @PROFESOR PRF ON  PRF.CODIGO_PROFESOR = IARE.IAE_CODIGO_PROFESOR "
                + "WHERE IARE.IAE_ANIO= ").append(smciclo).append(" ORDER BY NOMBRE");

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[7];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                asign[2] = (object[2] == null ? null : object[2].toString());
                asign[3] = (object[3] == null ? null : object[3].toString());
                asign[4] = (object[4] == null ? null : formato.format(object[4]));
                asign[5] = (object[5] == null ? null : object[5].toString());
                asign[6] = (object[6] == null ? null : object[6].toString());
                listInfAnualRealizado.add(i, asign);
            }
        }
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA PUBLICACIONES"> 
    private void recuperaPublicacion() {

        Vector v = new Vector();
        listPubProfesor.clear();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT P.CODIGO_PROFESOR, "
                + "P.CODIGO_PUBLICACION, "
                + "(CASE P.TIPO_PUBLICACION WHEN 1 THEN 'Libros de autoría personal'  "
                + "WHEN 2 THEN 'Libros  que actúa como editor, compilador o coordinador' "
                + "WHEN 3 THEN 'Participación como miembro del Comité Editorial' "
                + "WHEN 4 THEN 'Autoría de capítulo dentro de un libro colectivo' "
                + "WHEN 5 THEN 'Publicación de artículos en revistas académicas indexadas' "
                + "WHEN 6 THEN 'Publicación de artículos en revistas académicas no indexadas' "
                + "WHEN 7 THEN 'Ponencia con formato de artículo académico' "
                + "WHEN 11 THEN 'Ponencia con formato de artículo académico' "
                + "WHEN 8 THEN 'Página Web' "
                + "WHEN 9 THEN 'Tesis' "
                + "WHEN 10 THEN 'Otro' "
                + "WHEN 13 THEN 'Libro' "
                + "WHEN 14 THEN 'Revistas' "
                + "WHEN 15 THEN 'Ponencias' "
                + "WHEN 16 THEN 'Productos Audiovisuales' "
                + "WHEN 17 THEN 'Otra Publicación' "
                + "ELSE 'Otro' "
                + "END) tip_publicacion,"
                + "(CASE P.TIPO_PUBLICACION  WHEN 13 THEN "
                + "      (CASE p.PUB_ROL WHEN 1 THEN 'Autor' "
                + "      WHEN 2 THEN 'Coautor' "
                + "      WHEN 3 THEN 'Editor' "
                + "      WHEN 4 THEN 'Compilador' "
                + "      WHEN 5 THEN 'Revisor' "
                + "      WHEN 6 THEN 'Coordinador de Informe Periódico' "
                + "      END ) "
                + "      WHEN 14 THEN "
                + "      (CASE p.PUB_ROL WHEN 1 THEN 'Autor' "
                + "      WHEN 2 THEN 'Coautor' "
                + "      WHEN 3 THEN 'Revisor' "
                + "      WHEN 4 THEN 'Editor' "
                + "      WHEN 5 THEN 'Director' "
                + "      WHEN 6 THEN 'Coordinador Número Monográfico' "
                + "      END ) "
                + "      WHEN 15 THEN "
                + "      (CASE p.PUB_ROL WHEN 1 THEN 'Autor' "
                + "      WHEN 2 THEN 'Coautor' "
                + "      WHEN 3 THEN 'Moderador' "
                + "      WHEN 4 THEN 'Ponente' "
                + "      WHEN 5 THEN 'Expositor' "
                + "      WHEN 6 THEN 'Conferenciante' "
                + "      END ) "
                + "      WHEN 16 THEN "
                + "      (CASE p.PUB_ROL WHEN 1 THEN 'Autor' "
                + "      WHEN 2 THEN 'Coautor'       "
                + "      END ) "
                + "      WHEN 17 THEN "
                + "      (CASE p.PUB_ROL WHEN 1 THEN 'Autor' "
                + "      WHEN 2 THEN 'Coautor' "
                + "      WHEN 3 THEN 'Editor' "
                + "      WHEN 4 THEN 'Otro' "
                + "      WHEN 5 THEN 'Expositor' "
                + "      WHEN 6 THEN 'Conferenciante' "
                + "      END ) "
                + "      ELSE 'Otro' "
                + "  END) DESROL,    "
                + "P.CAMPO10,"
                + "(CASE p.TIPO_PUBLICACION WHEN 13 THEN "
                + "      CONCAT((CASE WHEN p.PUB_APELLIDO_AUTOR IS NOT NULL THEN CONCAT('AUTOR: ',p.PUB_APELLIDO_AUTOR,' ',p.PUB_NOMBRE_AUTOR) ELSE'' END  ), "
                + "      (CASE WHEN p.PUB_TITULO IS NOT NULL THEN CONCAT(' TÍTULO:', p.PUB_TITULO) ELSE'' END), "
                + "      (CASE WHEN p.CAMPO10 IS NOT NULL THEN CONCAT(' AÑO DE PUBLICACIÓN: ',p.CAMPO10) ELSE'' END), "
                + "      (CASE WHEN p.PUB_ALCANCE IS NOT NULL THEN CONCAT(' ALCANCE: ',(CASE p.PUB_ALCANCE WHEN 1 THEN 'NACIONAL' WHEN 2 THEN 'INTERNACIONAL' END)) ELSE'' END), "
                + "      (CASE WHEN p.PUB_NOMBRE_CAPITULO IS NOT NULL THEN CONCAT(' TÍTULO DEL CAPÍTULO: ',p.PUB_NOMBRE_CAPITULO) ELSE'' END), "
                + "      (CASE WHEN p.PUB_ROLANFITRION IS NOT NULL THEN CONCAT(' ROL ANFITRION: ',p.PUB_ROLANFITRION) ELSE'' END), "
                + "      (CASE WHEN p.PUB_RANGPAGINA IS NOT NULL THEN CONCAT(' RANGO PAGINAS: ',p.PUB_RANGPAGINA) ELSE'' END), "
                + "      (CASE WHEN p.PUB_EDITORIAL IS NOT NULL THEN CONCAT(' EDITORIAL: ',p.PUB_EDITORIAL) ELSE'' END), "
                + "      (CASE WHEN p.PUB_NUMPAGINAS IS NOT NULL THEN CONCAT(' N° DE PÁGINAS: ',p.PUB_NUMPAGINAS) ELSE'' END), "
                + "      (CASE WHEN p.PUB_NUMISBN IS NOT NULL THEN CONCAT(' N° ISBN: ',p.PUB_NUMISBN) ELSE'' END),"
                + "      (CASE WHEN p.PUB_FECEDICION IS NOT NULL THEN CONCAT(' FECHA EDICIÓN: ', convert(date, p.PUB_FECEDICION)) ELSE'' END), "
                + "      (CASE WHEN p.AUSPICIO_PUBLICACION IS NOT NULL THEN CONCAT(' AUSPICIO PUBLICACIÓN: ',(CASE p.AUSPICIO_PUBLICACION WHEN 'U' THEN 'UASB' WHEN 'O' THEN 'OTRO' WHEN 'N' THEN 'N/A' ELSE 'N/A' END )) ELSE'' END), "
                + "      (CASE WHEN p.OTRO_AUSPICIO IS NOT NULL THEN CONCAT('  DETALLE: ',p.OTRO_AUSPICIO) ELSE'' END), "
                + "      (CASE WHEN p.FORMA_PUBLICACION IS NOT NULL THEN CONCAT(' FORMA PUBLICACIÓN: ',(CASE P.FORMA_PUBLICACION WHEN 'I' THEN 'Impreso'  WHEN 'D' THEN 'Digital' ELSE 'Otro'END  )) ELSE'' END), "
                + "      (CASE WHEN p.PUB_PARTSERIE IS NOT NULL THEN  CONCAT(' PARTE DE UNA SERIE: ',(CASE p.PUB_PARTSERIE WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END)) ELSE'' END), "
                + "      (CASE WHEN p.PUB_TITSERIE IS NOT NULL THEN CONCAT(' TÍTULO SERIE: ',p.PUB_TITSERIE) ELSE'' END), "
                + "      (CASE WHEN p.PUB_REVPARES IS NOT NULL THEN CONCAT(' REVISIÓN PARES: ',(CASE p.PUB_REVPARES WHEN 'S' THEN 'SI' WHEN 'N' THEN 'NO'END)) ELSE'' END), "
                + "      (CASE WHEN p.PUB_TIPOEDICION IS NOT NULL THEN CONCAT(' EDICIÓN: ',(CASE p.PUB_TIPOEDICION WHEN 1 THEN 'PRIMERA' WHEN 2 THEN 'REEDICIÓN' WHEN 3 THEN 'RECOPILACIÓN'  END)) ELSE'' END), "
                + "      (CASE WHEN p.PUB_TIPOSVARIOS IS NOT NULL THEN CONCAT(' TIPO: ',(CASE p.PUB_TIPOSVARIOS WHEN 1 THEN 'ACADÉMICO' WHEN 2 THEN'NO ACADÉMICO' END)) ELSE'' END)) "
                + "    WHEN 14 THEN "
                + "      CONCAT((CASE WHEN p.PUB_APELLIDO_AUTOR IS NOT NULL THEN CONCAT('AUTOR: ',p.PUB_APELLIDO_AUTOR,' ',p.PUB_NOMBRE_AUTOR) ELSE'' END), "
                + "      (CASE WHEN p.PUB_TITULO IS NOT NULL THEN CONCAT(' TÍTULO:', p.PUB_TITULO) ELSE'' END), "
                + "      (CASE WHEN p.CAMPO10 IS NOT NULL THEN CONCAT(' AÑO DE PUBLICACIÓN: ',p.CAMPO10) ELSE'' END), "
                + "      (CASE WHEN p.PUB_ALCANCE IS NOT NULL THEN CONCAT(' ALCANCE: ',(CASE p.PUB_ALCANCE WHEN 1 THEN 'NACIONAL' WHEN 2 THEN 'INTERNACIONAL' END)) ELSE'' END), "
                + "      (CASE WHEN p.CAMPO8 IS NOT NULL THEN CONCAT(' NOMBRE DE REVISTA: ',p.CAMPO8) ELSE'' END), "
                + "      (CASE WHEN p.CAMPO1 IS NOT NULL THEN CONCAT(' NÚMERO REVISTA: ',p.CAMPO1) ELSE'' END), "
                + "      (CASE WHEN p.CAMPO2 IS NOT NULL THEN CONCAT(' PERIODICIDAD: ',p.CAMPO2) ELSE'' END), "
                + "      (CASE WHEN p.PUB_VOLUMEN IS NOT NULL THEN CONCAT(' VOLUMEN:  ',p.PUB_VOLUMEN) ELSE'' END),"
                + "      (CASE WHEN p.CAMPO7 IS NOT NULL THEN CONCAT(' ÑO DE EDICIÓN: ',p.CAMPO7) ELSE'' END),"
                + "      (CASE WHEN p.PUB_EDITORIAL IS NOT NULL THEN CONCAT(' EDITORIAL: ',p.PUB_EDITORIAL) ELSE'' END), "
                + "      (CASE WHEN p.PUB_NUMPAGINAS IS NOT NULL THEN CONCAT(' N° DE PÁGINAS: ',p.PUB_NUMPAGINAS) ELSE'' END),"
                + "      (CASE WHEN p.PUB_FECEDICION IS NOT NULL THEN CONCAT(' FECHA EDICIÓN: ', convert(date, p.PUB_FECEDICION)) ELSE'' END), "
                + "      (CASE WHEN p.PUB_NUMISSN IS NOT NULL THEN CONCAT(' N° ISSN: ',p.PUB_NUMISSN) ELSE'' END),"
                + "      (CASE WHEN p.FORMA_PUBLICACION IS NOT NULL THEN CONCAT(' FORMA PUBLICACIÓN: ',(CASE P.FORMA_PUBLICACION WHEN 'I' THEN 'Impreso'  WHEN 'D' THEN 'Digital' ELSE 'Otro'END  )) ELSE'' END),"
                + "      (CASE WHEN p.PUB_TIPOSVARIOS IS NOT NULL THEN CONCAT(' BASE INDEXACIÓN:',(CASE p.PUB_TIPOSVARIOS WHEN 1 THEN 'LATINDEX' WHEN 2 THEN'NINGUNO' WHEN 3 THEN 'OTRO' WHEN 4 THEN 'SCOPUS' WHEN 5 THEN 'SCIMAGO' WHEN 6 THEN 'ISI WEB' WHEN 7 THEN 'SCIELO' END)) ELSE'' END), "
                + "      (CASE WHEN p.CAMPO6 IS NOT NULL THEN CONCAT(' DETALLE BASE INDEXACIÓN: ',p.CAMPO6) ELSE'' END)) "
                + "    WHEN 15 THEN "
                + "       CONCAT((CASE WHEN p.PUB_APELLIDO_AUTOR IS NOT NULL THEN CONCAT('AUTOR: ',p.PUB_APELLIDO_AUTOR,' ',p.PUB_NOMBRE_AUTOR) ELSE'' END  ), "
                + "      (CASE WHEN p.PUB_TITULO IS NOT NULL THEN CONCAT(' TÍTULO:', p.PUB_TITULO) ELSE'' END), "
                + "      (CASE WHEN p.CAMPO10 IS NOT NULL THEN CONCAT(' AÑO DE PUBLICACIÓN: ',p.CAMPO10) ELSE'' END), "
                + "      (CASE WHEN p.PUB_ALCANCE IS NOT NULL THEN CONCAT(' ALCANCE: ',(CASE p.PUB_ALCANCE WHEN 1 THEN 'NACIONAL' WHEN 2 THEN 'INTERNACIONAL' END)) ELSE'' END), "
                + "      (CASE WHEN p.CAMPO5 IS NOT NULL THEN CONCAT(' NOMBRE EVENTO: ',p.CAMPO5) ELSE'' END), "
                + "      (CASE WHEN p.PUB_NUMPAGINAS IS NOT NULL THEN CONCAT(' N° DE PÁGINAS: ',p.PUB_NUMPAGINAS) ELSE'' END), "
                + "      (CASE WHEN p.PUB_ENTIDADSEDE IS NOT NULL THEN CONCAT(' ENTIDAD SEDE: ',p.PUB_ENTIDADSEDE) ELSE'' END), "
                + "      (CASE WHEN p.PUB_MEDDIFUSION IS NOT NULL THEN CONCAT(' MECANISMOS DIFUSIÓN: ',(CASE p.PUB_MEDDIFUSION  WHEN 'D' THEN 'DISCUSIÓN EN EL FORO' WHEN 'M' THEN 'MEMORIA'  WHEN 'P' THEN 'PÁGINA WEB' END )) ELSE'' END), "
                + "      (CASE WHEN p.PUB_FECEDICION IS NOT NULL THEN CONCAT(' FECHA EDICIÓN: ', convert(date, p.PUB_FECEDICION)) ELSE'' END), "
                + "      (CASE WHEN p.PUB_EVENTO IS NOT NULL THEN CONCAT(' TIPO ACTIVIDAD: ', (CASE p.PUB_EVENTO WHEN 'C' THEN 'Congreso' WHEN 'S' THEN 'Seminario' WHEN 'I' THEN 'Simposio' WHEN 'O' THEN 'Conferencia' WHEN 'E' THEN 'Encuentro'WHEN 'N' THEN 'Conversatorio' WHEN 'L' THEN 'Coloquio' WHEN 'M' THEN 'Mesa Redonda' WHEN 'F' THEN 'Foro' WHEN 'T' THEN 'Taller de Discusión' WHEN 'G' THEN 'Grupo de Discusión' WHEN 'P' THEN 'Panel' END )) ELSE'' END)) "
                + "     WHEN 16 THEN  "
                + "       CONCAT((CASE WHEN p.PUB_APELLIDO_AUTOR IS NOT NULL THEN CONCAT('AUTOR: ',p.PUB_APELLIDO_AUTOR,' ',p.PUB_NOMBRE_AUTOR) ELSE'' END  ), "
                + "      (CASE WHEN p.PUB_TITULO IS NOT NULL THEN CONCAT(' TÍTULO:', p.PUB_TITULO) ELSE'' END), "
                + "      (CASE WHEN p.CAMPO10 IS NOT NULL THEN CONCAT(' AÑO DE PUBLICACIÓN: ',p.CAMPO10) ELSE'' END), "
                + "      (CASE WHEN p.PUB_ALCANCE IS NOT NULL THEN CONCAT(' ALCANCE: ',(CASE p.PUB_ALCANCE WHEN 1 THEN 'NACIONAL' WHEN 2 THEN 'INTERNACIONAL' END)) ELSE'' END), "
                + "      (CASE WHEN p.PUB_FECEDICION IS NOT NULL THEN CONCAT(' FECHA DE PRESENTACIÓN: ', convert(date, p.PUB_FECEDICION)) ELSE'' END), "
                + "      (CASE WHEN p.PUB_DURACION IS NOT NULL THEN CONCAT(' DURACIÓN: ', (CASE p.PUB_DURACION WHEN 1 THEN 'HASTA 30 MINUTOS' WHEN 2 THEN 'MÁS DE 30 MINUTOS'END )) ELSE'' END)) "
                + "     WHEN 17 THEN  "
                + "      CONCAT((CASE WHEN p.PUB_APELLIDO_AUTOR IS NOT NULL THEN CONCAT('AUTOR: ',p.PUB_APELLIDO_AUTOR,' ',p.PUB_NOMBRE_AUTOR) ELSE'' END  ), "
                + "      (CASE WHEN p.PUB_TITULO IS NOT NULL THEN CONCAT(' TÍTULO:', p.PUB_TITULO) ELSE'' END), "
                + "      (CASE WHEN p.CAMPO10 IS NOT NULL THEN CONCAT(' AÑO DE PUBLICACIÓN: ',p.CAMPO10) ELSE'' END), "
                + "      (CASE WHEN p.PUB_PARTICIPACION IS NOT NULL THEN CONCAT(' TIPO: ',(CASE p.PUB_PARTICIPACION WHEN 'P' THEN 'PAPERS' WHEN 'I' THEN 'INFORMES' WHEN 'O' THEN 'Otros'  END)) ELSE'' END), "
                + "      (CASE WHEN p.PUB_ROL IS NOT NULL THEN CONCAT(' TIPO: ',(CASE p.PUB_ROL WHEN '1' THEN 'Autor' WHEN '2' THEN 'Coautor' WHEN '3' THEN 'Editor' WHEN '4' THEN 'Otro' END)) ELSE'' END), "
                + "      (CASE WHEN p.PUB_FECEDICION IS NOT NULL THEN CONCAT(' FECHA EDICIÓN: ', convert(date, p.PUB_FECEDICION)) ELSE'' END), "
                + "      (CASE WHEN p.PUB_URL IS NOT NULL THEN CONCAT(' DOI/URI/URL: ',p.PUB_URL) ELSE'' END)) "
                + "    ELSE "
                + "      CONCAT((CASE WHEN p.PUB_APELLIDO_AUTOR IS NOT NULL THEN CONCAT('AUTOR: ',p.PUB_APELLIDO_AUTOR,' ',p.PUB_NOMBRE_AUTOR) ELSE'' END), "
                + "      (CASE WHEN p.PUB_APELLIDO_COAUTOR IS NOT NULL THEN CONCAT(' Coautor: ',p.PUB_APELLIDO_COAUTOR,' ',p.PUB_NOMBRE_COAUTOR ) ELSE'' END), "
                + "      (CASE WHEN p.PUB_TITULO IS NOT NULL THEN CONCAT(' TÍTULO:', p.PUB_TITULO) ELSE'' END), "
                + "      (CASE WHEN p.PUB_SUBTITULO IS NOT NULL THEN CONCAT(' Subtítulo:', p.PUB_SUBTITULO) ELSE'' END),"
                + "      (CASE WHEN p.PUB_EDICION IS NOT NULL THEN CONCAT(' Edición:', p.PUB_EDICION) ELSE'' END), "
                + "      (CASE WHEN p.PUB_FECEDICION IS NOT NULL THEN CONCAT(' FECHA EDICIÓN: ', convert(date, p.PUB_FECEDICION)) ELSE'' END), "
                + "      (CASE WHEN p.PUB_NUMPAGINAS IS NOT NULL THEN CONCAT(' N° DE PÁGINAS: ',p.PUB_NUMPAGINAS) ELSE'' END), "
                + "      (CASE WHEN p.PUB_VOLUMEN IS NOT NULL THEN CONCAT(' VOLUMEN:  ',p.PUB_VOLUMEN) ELSE'' END),  "
                + "      (CASE WHEN p.PUB_NUMISSN IS NOT NULL THEN CONCAT(' N° ISSN: ',p.PUB_NUMISSN) ELSE'' END), "
                + "      (CASE WHEN p.PUB_NUMISBN IS NOT NULL THEN CONCAT(' N° ISBN: ',p.PUB_NUMISBN) ELSE'' END), "
                + "      (CASE WHEN p.FORMA_PUBLICACION IS NOT NULL THEN CONCAT(' FORMA PUBLICACIÓN: ',(CASE P.FORMA_PUBLICACION WHEN 'I' THEN 'Impreso'  WHEN 'D' THEN 'Digital' ELSE 'Otro'END  )) ELSE'' END) "
                + "      )END ) DATOS_BIBLIOGRAFICOS,"
                + "(SELECT (CASE WHEN COUNT(1)>0 THEN 'SI' ELSE 'NO' END) FROM academico.dbo.PRIN_DOCUMENTO DOC WHERE DOC.DOC_ENTIDAD_CODIGO = P.CODIGO_PUBLICACION )PDF_SUBIDO,"
                + "doc.DOC_MOD_ORIGEN, "
                + "doc.DOC_DIR_GENERAL, "
                + "doc.DOC_DIR_DESTINO, "
                + "doc.DOC_EXTENSION,"
                + "doc.DOC_NOMBRE "
                + "FROM academico.dbo.PUBLICACION_PROFESOR p "
                + "LEFT OUTER JOIN academico.dbo.PRIN_DOCUMENTO doc ON p.CODIGO_PUBLICACION = doc.DOC_ENTIDAD_CODIGO "
                + "WHERE P.CODIGO_PROFESOR = ").append(selectedinfAnualRea[1]).append(" "
                + "AND P.CAMPO10 >= ").append(smciclo).append("-1").append(""
                + "ORDER BY TIPO_PUBLICACION,  (case WHEN CAMPO10= NULL  THEN PUB_FECEDICION ELSE CAMPO10 END)DESC");
        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[13];
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
                //asign[12] = (object[12] == null ? null : object[12].toString());
                listPubProfesor.add(i, asign);
            }
        }

    }

    // </editor-fold> 
    public void onRowSelect(SelectEvent event) {
        recuperaPublicacion();
    }

    public void onRowUnselect(SelectEvent event) {
        listPubProfesor.clear();
    }

    public void onRowSelectPub(SelectEvent event) {

    }

    public void descArchivoAction(ActionEvent actionEvent) throws AbortProcessingException {
        String carpetaDocumento = null;
        String separadorPath = null;
        InputStream stream = null;
        try {
            separadorPath = consultaFacade.getSeparadorPath();
            carpetaDocumento = consultaFacade.getPathDoc() + separadorPath + selectedpubinfAnual[7]
                    + separadorPath + selectedpubinfAnual[8]
                    + separadorPath + selectedpubinfAnual[9]
                    + separadorPath + selectedpubinfAnual[11];
            stream = new FileInputStream(carpetaDocumento);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("carpetaDocumento"+ carpetaDocumento);
        file = new DefaultStreamedContent(stream, selectedpubinfAnual[10],
                selectedpubinfAnual[11]);

    }

}
