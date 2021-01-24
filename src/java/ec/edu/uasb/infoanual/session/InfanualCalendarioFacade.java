/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.session;

import ec.edu.uasb.infoanual.entities.InfanualCalendario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class InfanualCalendarioFacade extends AbstractFacade<InfanualCalendario> implements InfanualCalendarioFacadeLocal {
    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InfanualCalendarioFacade() {
        super(InfanualCalendario.class);
    }
    @Override    
    public void aperturaInfAnual(String[] infanual) {
        Query query = em.createNativeQuery("{call GESTIONACADEMICA.sp_reapertura_infanual(?,?,?,?)}");
        query.setParameter(1, infanual[0]); // anio
        query.setParameter(2, infanual[1]);//tipo_docente
        query.setParameter(3, infanual[7]);//tipo_CONTRATO
        query.setParameter(4, infanual[2]); //fecininotas       
        query.executeUpdate();
    }
    @Override
     public void cierreInfAnual(String[] infanual) {
        Query query = em.createNativeQuery("{call GESTIONACADEMICA.sp_cierre_infAnual(?,?,?)}");
        query.setParameter(1, infanual[0]); // anio
        query.setParameter(2, infanual[1]);//tipo_docente            
        query.setParameter(3, infanual[10]);//tipo_CONTRATO
        query.executeUpdate();
    }
}
