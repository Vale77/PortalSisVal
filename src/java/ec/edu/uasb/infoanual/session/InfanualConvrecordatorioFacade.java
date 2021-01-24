/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.session;

import ec.edu.uasb.infoanual.entities.InfanualConvrecordatorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class InfanualConvrecordatorioFacade extends AbstractFacade<InfanualConvrecordatorio> implements InfanualConvrecordatorioFacadeLocal {
    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InfanualConvrecordatorioFacade() {
        super(InfanualConvrecordatorio.class);
    }
    

    @Override
    public List<InfanualConvrecordatorio> findbyTipContrato(char tipContrato) {
        return em.createNativeQuery("Select * "
                + " from  GESTIONACADEMICA.INFANUAL_CONVRECORDATORIO p "
                + " where p.IAR_ESTADO_RECORDATORIO = 'A'"
                + " AND p.IAR_TIPO_CONTRATO =  '" + tipContrato + "'", InfanualConvrecordatorio.class).getResultList();
    }
    
}
