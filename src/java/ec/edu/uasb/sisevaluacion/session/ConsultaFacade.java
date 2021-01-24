/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.publicacionDGA.entities.Persona;
import ec.edu.uasb.sisevaluacion.entities.Profesor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class ConsultaFacade implements ConsultaFacadeLocal {

    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String separadorPath;
     private static String carpetaCompartida = "opt/DATA";
    //private static String carpetaCompartida = "opt";

    @Override
    public Vector ejecutaSqlList(String sql) {
        return (Vector) em.createNativeQuery(sql).setParameter(1, sql).getResultList();
    }

    @Override
    public List<String[]> area() {
        ArrayList<String[]> lresult = new ArrayList<String[]>();
        Vector v = new Vector();
        StringBuilder sql = new StringBuilder();
        sql.append("select ARE_CODIGO, nombre_area "
                + "from area");
        v = (Vector) ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[2];
                asign[0] = object[0].toString();
                asign[1] = object[1].toString();
                lresult.add(asign);
            }

        }
        return lresult;
    }

    @Override
    public List<String[]> trimestreAnio(Long anio) {
        ArrayList<String[]> lresult = new ArrayList<String[]>();
        Vector v = new Vector();
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct codigo_nivel,  (case codigo_nivel when 1 then 'PRIMERO' when 2 then 'SEGUNDO' when 3 then 'TERCERO' when 4 then 'CUARTO' else '' end) trimestre"
                + " from NIVEL_ESPECIALIZACION"
                + " where anio = ").append(anio);
        v = (Vector) ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[2];
                asign[0] = object[0].toString();
                asign[1] = object[1].toString();
                lresult.add(asign);
            }
        }
        return lresult;
    }

    /*RECUPERAR DATOS PARA EL REPORTE DE INFORME ANUAL*/
    @Override
    public List<Profesor> findProfesorInfAnualbySql(String sql) {
        return em.createNativeQuery(" DECLARE @PROFESOR TABLE (CODIGO_PROFESOR NUMERIC(7,0),nombre_profesor VARCHAR(32), apellido_profesor VARCHAR(35), cod_facultad_acadant NUMERIC(10,0), dedicacion VARCHAR(1), cod_tipocontrato INT)"
                + " INSERT  INTO @PROFESOR(CODIGO_PROFESOR,nombre_profesor, apellido_profesor, cod_facultad_acadant, dedicacion, cod_tipocontrato) "
                + " SELECT CODIGO_PROFESOR,nombre_profesor, apellido_profesor, cod_facultad_acadant, dedicacion, cod_tipocontrato "
                + " FROM interfaseOcu.dbo.PROFESOR "
                + " SELECT PROFESOR.CODIGO_PROFESOR CODIGO_PROFESOR, PROFESOR.APELLIDO_PROFESOR+' '+PROFESOR.NOMBRE_PROFESOR NOMBRE_PROFESOR "
                + " FROM @PROFESOR  PROFESOR "
                + " INNER JOIN  interfaseOcu.GESTIONACADEMICA.INFANUAL_REALIZADO ENCRE "
                + " ON PROFESOR.CODIGO_PROFESOR = ENCRE.IAE_CODIGO_PROFESOR "
                + " WHERE 1= 1" + sql
                + " ORDER BY 2,1 ", Profesor.class).getResultList();
    }

    /*RECUPERAR EL CODIGO ANTERIOR DEL DOCENTE*/
    @Override
    public BigDecimal findConAntProfesorbySql(String codProfesor) {
        BigDecimal codProfAnt = null;
        codProfAnt = (BigDecimal) em.createNativeQuery(" SELECT vp.COD_PROFESOR_acadant "
                + "FROM interfaseOcu.dbo.PROFESOR vp "
                + "WHERE vp.CODIGO_PROFESOR =  " + codProfesor).getSingleResult();
        return codProfAnt;
    }

    /*RECUPERAR TODOS LOS DOCENTES QUE PUEDEN INGRESAR AL INFORMA ANUAL*/
    @Override
    public List<Profesor> findProfesorInfAnualAct(String anio) {
        return em.createNativeQuery(" DECLARE @PROFESOR TABLE (CODIGO_PROFESOR NUMERIC(7,0), NOMBRE_PROFESOR VARCHAR(100),"
                + " dedicacion VARCHAR(1), cod_tipocontrato INT, ANIO_INICONTRATO INT, ANIO_FINCONTRATO INT ) "
                + " INSERT  INTO @PROFESOR(CODIGO_PROFESOR,NOMBRE_PROFESOR, dedicacion, cod_tipocontrato, ANIO_INICONTRATO,ANIO_FINCONTRATO ) "
                + " SELECT CODIGO_PROFESOR,APELLIDO_PROFESOR +' '+NOMBRE_PROFESOR, dedicacion, cod_tipocontrato, ANIO_INICONTRATO,ANIO_FINCONTRATO "
                + " FROM interfaseOcu.dbo.PROFESOR "
                + " SELECT DISTINCT  PROFESOR.CODIGO_PROFESOR, "
                + " NOMBRE_PROFESOR "
                + " FROM  @PROFESOR  PROFESOR  "
                + " INNER JOIN GESTIONACADEMICA.INFANUAL_CALENDARIO ENCCAL ON ENCCAL.IAC_ANIO BETWEEN ANIO_INICONTRATO-1 AND ANIO_FINCONTRATO "
                + "  AND PROFESOR.dedicacion = ENCCAL.IAC_TIPO_DOCENTE "
                + "  AND PROFESOR.COD_TIPOCONTRATO  = ENCCAL.IAC_TIPO_CONTRATO "
                + " WHERE ENCCAL.IAC_ANIO =" + anio
                + " ORDER BY NOMBRE_PROFESOR", Profesor.class).getResultList();
    }

    /*PROFESORES QUE YA CUENTAN CON INFORME ANUAL CERRADO*/
    @Override
    public List<Profesor> findProfesorInfAnualCEbySql(String sql) {
        return em.createNativeQuery(" DECLARE @PROFESOR TABLE (CODIGO_PROFESOR NUMERIC(7,0),nombre_profesor VARCHAR(32), apellido_profesor VARCHAR(35), cod_facultad_acadant NUMERIC(10,0), dedicacion VARCHAR(1), cod_tipocontrato INT)"
                + " INSERT  INTO @PROFESOR(CODIGO_PROFESOR,nombre_profesor, apellido_profesor, cod_facultad_acadant, dedicacion, cod_tipocontrato) "
                + " SELECT CODIGO_PROFESOR,nombre_profesor, apellido_profesor, cod_facultad_acadant, dedicacion, cod_tipocontrato "
                + " FROM interfaseOcu.dbo.PROFESOR "
                + " SELECT PROFESOR.CODIGO_PROFESOR CODIGO_PROFESOR, PROFESOR.APELLIDO_PROFESOR+' '+PROFESOR.NOMBRE_PROFESOR NOMBRE_PROFESOR "
                + " FROM @PROFESOR  PROFESOR "
                + " INNER JOIN  interfaseOcu.GESTIONACADEMICA.ESTADISTICAS_INFORME_ANUAL ENCRE "
                + " ON PROFESOR.CODIGO_PROFESOR = ENCRE.CODIGO_PROFESOR "
                + " WHERE 1= 1" + sql
                + " ORDER BY 2,1 ", Profesor.class).getResultList();
    }

    /*LISTADO DE TIPOS DE DEDICACION SEGUN TIPO DE CONTRATO*/
    @Override
    public List<String[]> cargDedicacion(String smtipcontrato) {
        List<String[]> listtipDedicacion = new ArrayList<String[]>();
        listtipDedicacion.clear();
        String[] asign;
        asign = new String[2];
        switch (Integer.valueOf(smtipcontrato)) {
            case 1:
                asign[0] = "C";
                asign[1] = "Tiempo Completo";
                listtipDedicacion.add(0, asign);
                asign = new String[2];
                asign[0] = "M";
                asign[1] = "Medio Tiempo";
                listtipDedicacion.add(1, asign);
                asign = new String[2];
                asign[0] = "P";
                asign[1] = "Tiempo Parcial";
                listtipDedicacion.add(2, asign);
                break;
            case 2:
                asign[0] = "C";
                asign[1] = "Tiempo Completo";
                listtipDedicacion.add(0, asign);
                asign = new String[2];
                asign[0] = "M";
                asign[1] = "Medio Tiempo";
                listtipDedicacion.add(1, asign);
                asign = new String[2];
                asign[0] = "P";
                asign[1] = "Tiempo Parcial";
                listtipDedicacion.add(2, asign);
                break;
            default:
                asign[0] = "P";
                asign[1] = "Tiempo Parcial";
                listtipDedicacion.add(0, asign);
                break;
        }
        return listtipDedicacion;
    }

    /*RECUPERAR DATOS PARA EL REPORTE DE INFORME ANUAL*/
    @Override
    public List<Persona> findProfesorbyTipCont(String codTipContrato, String codDedicacion) {
        return em.createNativeQuery("SELECT PROF.CED_PAS_PROFESOR CEDULA_PERSONA, PROF.APELLIDO_PROFESOR+' '+PROF.NOMBRE_PROFESOR NOMBRE_PERSONA "
                + "FROM  interfaseOcu.dbo.PROFESOR PROF "
                + "WHERE PROF.COD_TIPOCONTRATO =  " + codTipContrato + " "
                + "AND PROF.dedicacion = '" + codDedicacion + "' "
                + "ORDER BY PROF.APELLIDO_PROFESOR", Persona.class).getResultList();
    }

    @Override
    public List<Persona> findEstudiante() {
        return em.createNativeQuery("SELECT PER_ID_DOC CEDULA_PERSONA, PER.PER_PRIMER_APELLIDO+' '+PER.PER_SEGUNDO_APELLIDO+' '+PER.PER_NOMBRES NOMBRE_PERSONA "
                + "FROM interfaseOcu.dbo.PRIN_PERSONA PER "
                + "INNER JOIN interfaseOcu.dbo.ESTUDIANTE EST ON PER.PER_ID_DOC = EST.CED_PAS_ESTUDIANTE "
                + "UNION  "
                + "SELECT PER_ID_DOC, PER.PER_PRIMER_APELLIDO+' '+PER.PER_SEGUNDO_APELLIDO+' '+PER.PER_NOMBRES "
                + "FROM interfaseOcu.dbo.PRIN_PERSONA PER "
                + "INNER JOIN academico.dbo.ESTUDIANTE ESTACAD ON PER.PER_ID_DOC = ESTACAD.CED_PAS_ESTUDIANTE "
                + "  AND ESTACAD.BASE_DIR = 'A'"
                + "ORDER BY NOMBRE_PERSONA", Persona.class).getResultList();
    }

    @Override
    public List<Persona> findProfesor() {
        return em.createNativeQuery("SELECT PER_ID_DOC CEDULA_PERSONA, PER.PER_PRIMER_APELLIDO+' '+PER.PER_SEGUNDO_APELLIDO+' '+PER.PER_NOMBRES NOMBRE_PERSONA "
                + "FROM interfaseOcu.dbo.PRIN_PERSONA PER "
                + "INNER JOIN interfaseOcu.dbo.PROFESOR EST ON PER.PER_ID_DOC = EST.CED_PAS_PROFESOR "
                + "UNION "
                + "SELECT PER_ID_DOC, PER.PER_PRIMER_APELLIDO+' '+PER.PER_SEGUNDO_APELLIDO+' '+PER.PER_NOMBRES NOMBRE_PERSONA "
                + "FROM interfaseOcu.dbo.PRIN_PERSONA PER "
                + "INNER JOIN academico.dbo.PROFESOR EST ON PER.PER_ID_DOC = EST.CED_PAS_PROFESOR "
                + "WHERE EST.ESTADO_PROFESOR = 'A' "
                + "ORDER BY NOMBRE_PERSONA", Persona.class).getResultList();
    }

    @Override
    public String findNomPersona(String cedulaPersona) {
        return em.createNativeQuery("SELECT PER.PER_PRIMER_APELLIDO+' '+PER.PER_SEGUNDO_APELLIDO+' '+PER.PER_NOMBRES NOMBRE_PERSONA "
                + "FROM interfaseOcu.dbo.PRIN_PERSONA PER "
                + "WHERE PER.PER_ID_DOC = '" + cedulaPersona + "' ").getSingleResult().toString();
    }

    private static boolean isWindows() {
        return (OS.contains("win"));
    }

    private static boolean isMac() {
        return (OS.contains("mac"));
    }

    private static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0);
    }

    private static boolean isSolaris() {
        return (OS.contains("sunos"));
    }

    private static String separadorOS() {
        if (isWindows()) {
            return "\\";
        } else if (isUnix() || isSolaris() || isMac()) {
            return "//";
        } else {
            return "";
        }
    }

    @Override
    public String getPathDoc() {
        separadorPath = separadorOS();
        return (isWindows() ? "\\\\JOHANAOROZCO" : "") + separadorPath + carpetaCompartida + separadorPath + "documentos";
    }

    
    @Override
    public String getSeparadorPath() {
        return separadorOS();
    }
}
