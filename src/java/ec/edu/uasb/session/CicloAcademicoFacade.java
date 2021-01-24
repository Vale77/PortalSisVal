/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.session;


import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class CicloAcademicoFacade extends AbstractFacade<CicloAcademico> implements CicloAcademicoFacadeLocal {
    @PersistenceContext(unitName = "SisEvaluacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CicloAcademicoFacade() {
        super(CicloAcademico.class);
    }


    @Override
    public List<CicloAcademico> findByEstado(String estado) {
        Query q = em.createNamedQuery("CicloAcademico.findByEstadoCiclo").setParameter("estadoCiclo", estado);
        return q.getResultList();
    }
     


    @Override
    public List<CicloAcademico> findAllActivos() {
        Query q = em.createQuery("SELECT c FROM CicloAcademico c WHERE c.anio >= 2013  order by c.anio DESC");
        return q.getResultList();
    }

   

}
