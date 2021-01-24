/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.infoanual.entities.InfanualParametros;
import ec.edu.uasb.infoanual.session.InfanualParametrosFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "parametrosInfAnual")
@ViewScoped
public class InfAnualParCalenManagedBean extends BaseJSFManagedBean implements Serializable {

    private List<InfanualParametros> listParEncuesta = new ArrayList<InfanualParametros>();
    private InfanualParametros parametro = new InfanualParametros();
    private String sdiashabilitado;
    private String sdiasreapertura;
    private String scopiaa;
    private String sasunto;
    private String sasuntoreapertura;
    private String sasuntocierre;
    private String smensajecierre;
    private String sasuntofinalizacion;
    private String smensajefinalizacion;

    @EJB
    private InfanualParametrosFacadeLocal infparametrosFacade;
    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">

    public String getSdiashabilitado() {
        return sdiashabilitado;
    }

    public void setSdiashabilitado(String sdiashabilitado) {
        this.sdiashabilitado = sdiashabilitado;
    }

    public String getSasuntofinalizacion() {
        return sasuntofinalizacion;
    }

    public void setSasuntofinalizacion(String sasuntofinalizacion) {
        this.sasuntofinalizacion = sasuntofinalizacion;
    }

    public String getSmensajefinalizacion() {
        return smensajefinalizacion;
    }

    public void setSmensajefinalizacion(String smensajefinalizacion) {
        this.smensajefinalizacion = smensajefinalizacion;
    }

    public String getSdiasreapertura() {
        return sdiasreapertura;
    }

    public void setSdiasreapertura(String sdiasreapertura) {
        this.sdiasreapertura = sdiasreapertura;
    }

    public String getScopiaa() {
        return scopiaa;
    }

    public void setScopiaa(String scopiaa) {
        this.scopiaa = scopiaa;
    }

    public String getSasunto() {
        return sasunto;
    }

    public void setSasunto(String sasunto) {
        this.sasunto = sasunto;
    }

    public String getSasuntoreapertura() {
        return sasuntoreapertura;
    }

    public void setSasuntoreapertura(String sasuntoreapertura) {
        this.sasuntoreapertura = sasuntoreapertura;
    }

    public String getSasuntocierre() {
        return sasuntocierre;
    }

    public void setSasuntocierre(String sasuntocierre) {
        this.sasuntocierre = sasuntocierre;
    }

    public String getSmensajecierre() {
        return smensajecierre;
    }

    public void setSmensajecierre(String smensajecierre) {
        this.smensajecierre = smensajecierre;
    }

    // </editor-fold> 
    /**
     * Creates a new instance of InfAnualParCalenManagedBean
     */
    public InfAnualParCalenManagedBean() {
        this.setPaginaMant("/pages/infAnual/parametros/parametrosInfAnual");
    }
    // <editor-fold defaultstate="collapsed" desc="CARGAR LISTADO DE PARAMETROS"> 

    public List<InfanualParametros> getListParEncuesta() {
        listParEncuesta.clear();
        listParEncuesta = infparametrosFacade.findAll();
        return listParEncuesta;
    }

    public void setListParEncuesta(List<InfanualParametros> listParEncuesta) {
        this.listParEncuesta = listParEncuesta;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private InfanualParametros selectedParametro;

    public InfanualParametros getSelectedParametro() {
        return selectedParametro;
    }

    public void setSelectedParametro(InfanualParametros selectedParametro) {
        this.selectedParametro = selectedParametro;
    }

    // </editor-fold> 
    private void reset() {
        selectedParametro = null;
        sasunto = null;
        sasuntocierre = null;
        smensajecierre = null;
        sasuntoreapertura = null;
        scopiaa = null;
        sdiashabilitado = null;
        sdiasreapertura = null;
    }

    // <editor-fold defaultstate="collapsed" desc="BOTON NUEVO">  
    @Override
    public void nuevoButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/infAnual/parametros/parametrosInfAnualModal");
        super.nuevoButton_processAction(ae);
        reset();

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON CANCELAR">  
    @Override
    public void cancelarButton_processAction(ActionEvent ae) {
        selectedParametro = new InfanualParametros();
        parametro = new InfanualParametros();
        reset();
        this.setPaginaMant("/WEB-INF/templates/includes/sinContenido");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON EDITAR">  
    @Override
    public void editarButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/infAnual/parametros/parametrosInfAnualModal");
        super.editarButton_processAction(ae);
        parametro = selectedParametro;
        sasunto = (parametro.getIapAsunto() == null ? null : parametro.getIapAsunto());
        sasuntocierre = (parametro.getIapAsuntoCierre() == null ? null : parametro.getIapAsuntoCierre());
        sasuntoreapertura = (parametro.getIapAsuntoReapertura() == null ? null : parametro.getIapAsuntoReapertura());
        scopiaa = (parametro.getIapCopiaA() == null ? null : parametro.getIapCopiaA());
        sdiashabilitado = (parametro.getIapDiasHabilitada() == null ? null : parametro.getIapDiasHabilitada().toString());
        sdiasreapertura = (parametro.getIapDiasReapertura() == null ? null : parametro.getIapDiasReapertura().toString());
        smensajecierre = (parametro.getIapMensajeCierre() == null ? null : parametro.getIapMensajeCierre());
        sasuntofinalizacion = (parametro.getIapAsuntoFinalizacion() == null ? null : parametro.getIapAsuntoFinalizacion());
        smensajefinalizacion = (parametro.getIapMensajeFinalizacion() == null ? null : parametro.getIapMensajeFinalizacion());
        RequestContext.getCurrentInstance().update("formMant:panelMant");

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON ELIMINAR">  
    @Override
    public void eliminarButton_processAction(ActionEvent ae, String lsmensaje) {
        String ls_mensaje = null;
        infparametrosFacade.remove(selectedParametro);
        if (ls_mensaje == null) {
            ls_mensaje = "Dato Eliminado";
        }
        super.eliminarButton_processAction(ae, ls_mensaje);
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  
    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        InfanualParametros parametroaux = new InfanualParametros();
        if (parametro != null) {
            if (parametro.getIapCodigo() != null) {
                parametroaux = infparametrosFacade.find(parametro.getIapCodigo());
            }
        }
        try {
            parametro.setIapDiasHabilitada(sdiashabilitado == null ? null : Integer.valueOf(sdiashabilitado));
            parametro.setIapDiasReapertura(sdiasreapertura == null ? null : Integer.valueOf(sdiasreapertura));
            parametro.setIapAsunto(sasunto == null ? null : sasunto);
            parametro.setIapAsuntoCierre(sasuntocierre == null ? null : sasuntocierre);
            parametro.setIapMensajeCierre(smensajecierre == null ? null : smensajecierre);
            parametro.setIapAsuntoReapertura(sasuntoreapertura == null ? null : sasuntoreapertura);
            parametro.setIapAsuntoFinalizacion(sasuntofinalizacion == null ? null : sasuntofinalizacion);
            parametro.setIapMensajeFinalizacion(smensajefinalizacion == null ? null : smensajefinalizacion);
            parametro.setIapCopiaA(scopiaa == null ? null : scopiaa);
            if (parametroaux.getIapCodigo() == null) {
                infparametrosFacade.create(parametro);
            } else {
                infparametrosFacade.edit(parametro);
            }
            JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Guardar Encuesta");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            parametro = new InfanualParametros();
        }

        RequestContext.getCurrentInstance().update("fparametroInfAnual:dataParametro");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
    }

    // </editor-fold> 
}
