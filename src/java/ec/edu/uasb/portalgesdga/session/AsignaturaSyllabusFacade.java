/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdga.session;

import ec.edu.uasb.portalgesdga.entities.AsignaturaSyllabus;
import ec.edu.uasb.portalgesdga.reportes.bean.SerieSilabo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class AsignaturaSyllabusFacade extends AbstractFacade<AsignaturaSyllabus> implements AsignaturaSyllabusFacadeLocal {

    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignaturaSyllabusFacade() {
        super(AsignaturaSyllabus.class);
    }

    @Override
    public List<AsignaturaSyllabus> getAsignatSyllabus(Long anio, Long codArea, Long codProg) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT DISTINCT VCP.ANIO, VCP.CICLO, VCP.CODIGO_MATERIA, VCP.CODIGO_NIVEL, VCP.VAC_CODNUM,VCP.COD_PARALELO, VCP.CODIGO_PROFESOR,")
                .append(" VCP.NOMBRE_MATERIA, VCP.IDENTIF_MATERIA,VCP.NOMBRE_PARALELO, VCP.TRIMESTRE NOMBRE_NIVEL, VCP.RESPONSABLE_MATERIA, VCP.CODIGO_ESP, ")
                .append(" SY.ESTADO_SYLABUS, SY.APROBADO_POR, SY.ESTADO_ENVIO, SY.OBSERVACION_COORDINADOR, VCP.AREA, VCP.PROGRAMA,VCP.CODIGO_COORDINADOR, ")
                .append(" NCREDITOS*5 HORAS_MATERIA_DICTAR, NCREDITOS,VCP.APELLIDO_PROFESOR,VCP.NOMBRE_PROFESOR,")
                .append(" (SELECT distinct CONCAT(EMAIL,';')  AS [data()] FROM V_SECRETARIA WHERE ARE_CODIGO = VCP.CODIGO_FACULTAD and PRG_CODIGO = VCP.CODIGO_ESP For XML PATH (''))  EMAIL_AREA ")
                .append(" FROM VISTA_COORDINADOR_PROGRAMA VCP LEFT OUTER JOIN SYLABUS_DOCENTE SY ON VCP.CODIGO_PROFESOR = SY.CODIGO_PROFESOR AND ")
                .append(" VCP.CODIGO_NIVEL = SY.CODIGO_NIVEL AND VCP.CODIGO_MATERIA = SY.CODIGO_MATERIA AND VCP.CODIGO_ESP = SY.COD_PROGRAMA AND ")
                .append(" VCP.ANIO = SY.ANIO AND VCP.CICLO = SY.CICLO AND VCP.VAC_CODNUM = SY.VAC_CODNUM AND VCP.COD_PARALELO = SY.COD_PARALELO ")
                .append(" WHERE  VCP.ANIO = ? AND VCP.CICLO = 1 AND VCP.RESPONSABLE_MATERIA = 'S'  ");
         if (codArea != null) {
            sb.append(" AND VCP.CODIGO_FACULTAD = ? ");
        } 
        
        if (codProg != null) {
            sb.append(" AND VCP.CODIGO_ESP =   ? ");
        } 
        sb.append(" ORDER BY  VCP.PROGRAMA,VCP.NOMBRE_MATERIA, VCP.CODIGO_NIVEL,VCP.COD_PARALELO ");

        Query q = em.createNativeQuery(sb.toString(), AsignaturaSyllabus.class);
        q.setParameter(1, anio).setParameter(2, codArea).setParameter(3, codProg);
        List<AsignaturaSyllabus> tmp = q.getResultList();
        return tmp;
    }

    @Override
    public List<SerieSilabo> getSyllabusByProg(Long anio, Long codArea, Long codProg) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT  VCP.CODIGO_ESP, VCP.PROGRAMA,VCP.CODIGO_NIVEL, VCP.TRIMESTRE, ")
                .append(" SUM(IIF(SY.ESTADO_SYLABUS is NULL AND SY.ESTADO_ENVIO is NULL,1,0)) NO_REGISTRADAS, ")
                .append(" SUM(IIF(SY.ESTADO_SYLABUS ='P' AND SY.ESTADO_ENVIO='P',1,0)) REGISTRADAS, ")
                .append(" SUM(IIF(SY.ESTADO_SYLABUS ='P' AND SY.ESTADO_ENVIO='E',1,0)) POR_APROBAR, ")
                .append(" SUM(IIF(SY.ESTADO_SYLABUS ='A' AND SY.ESTADO_ENVIO='E',1,0)) APROBADAS ")
                .append(" FROM VISTA_COORDINADOR_PROGRAMA VCP LEFT OUTER JOIN SYLABUS_DOCENTE SY ON VCP.CODIGO_PROFESOR = SY.CODIGO_PROFESOR AND ")
                .append(" VCP.CODIGO_NIVEL = SY.CODIGO_NIVEL AND VCP.CODIGO_MATERIA = SY.CODIGO_MATERIA AND VCP.CODIGO_ESP = SY.COD_PROGRAMA AND ")
                .append(" VCP.ANIO = SY.ANIO AND VCP.CICLO = SY.CICLO AND VCP.VAC_CODNUM = SY.VAC_CODNUM AND VCP.COD_PARALELO = SY.COD_PARALELO ")
                .append(" WHERE  VCP.ANIO = ? AND VCP.CICLO = 1 AND VCP.RESPONSABLE_MATERIA = 'S'  AND VCP.CODIGO_FACULTAD = ? ")
                .append("  AND VCP.CODIGO_ESP =   ?  ")
                .append(" GROUP BY VCP.CODIGO_ESP, VCP.PROGRAMA,VCP.CODIGO_NIVEL, VCP.TRIMESTRE");
        Query q = em.createNativeQuery(sb.toString(), SerieSilabo.class);
        q.setParameter(1, anio).setParameter(2, codArea).setParameter(3, codProg);
        return q.getResultList();
    }

}
