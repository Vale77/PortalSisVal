/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.VPrograma;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class VProgramaFacade extends AbstractFacade<VPrograma> implements VProgramaFacadeLocal {

    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VProgramaFacade() {
        super(VPrograma.class);
    }

    @Override
    public List<VPrograma> findProgramaAnual(Long anio, String codArea) {
        return em.createNativeQuery("select DISTINCT PRG_CODIGO CODIGO_PROGRAMA,NOMBRE_PROGRAMA,  ARE_CODIGO CODIGO_AREA,  ANIO"
                + " from dbo.programa "
                + " WHERE ANIO =  " + anio
                + " AND ARE_CODIGO = " + codArea, VPrograma.class).getResultList();

    }

    @Override
    public List<VPrograma> findProgramaAnualByNivAcad(Long anio, String codArea, String coNivAca) {
        return em.createNativeQuery("select DISTINCT PRG_CODIGO CODIGO_PROGRAMA,NOMBRE_PROGRAMA,  ARE_CODIGO CODIGO_AREA,  ANIO"
                + " from dbo.programa "
                + " WHERE ANIO =  " + anio
                + " AND ARE_CODIGO = " + codArea
                + " AND TIPO_NIVEACAD IN (" + coNivAca+")", VPrograma.class).getResultList();

    }
}
