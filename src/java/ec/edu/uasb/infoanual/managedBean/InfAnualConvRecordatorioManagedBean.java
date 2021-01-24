/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.infoanual.entities.InfanualConvrecordatorio;
import ec.edu.uasb.infoanual.session.InfanualConvrecordatorioFacadeLocal;

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
@ManagedBean(name = "convRecordatorioInfAnual")
@ViewScoped
public class InfAnualConvRecordatorioManagedBean extends BaseJSFManagedBean implements Serializable {

    private List<InfanualConvrecordatorio> listInfAnualConvRecordatorio = new ArrayList<InfanualConvrecordatorio>();
    private String snumconvo = null;
    private String smtipcontrato = null;
    private String sdiasconvocatoria = null;
    private String smensaje = null;
    private String sdiasreapertura = null;
    private String smensajereapertura = null;
    private String sestado = null;
    private InfanualConvrecordatorio infAnualConvRecordatorio = new InfanualConvrecordatorio();
    @EJB
    private InfanualConvrecordatorioFacadeLocal infAnualConvRecFacade;
    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">

    public List<InfanualConvrecordatorio> getListInfAnualConvRecordatorio() {
        return listInfAnualConvRecordatorio;
    }

    public void setListInfAnualConvRecordatorio(List<InfanualConvrecordatorio> listInfAnualConvRecordatorio) {
        this.listInfAnualConvRecordatorio = listInfAnualConvRecordatorio;
    }

    public String getSmtipcontrato() {
        return smtipcontrato;
    }

    public void setSmtipcontrato(String smtipcontrato) {
        this.smtipcontrato = smtipcontrato;
    }

    public String getSnumconvo() {
        return snumconvo;
    }

    public void setSnumconvo(String snumconvo) {
        this.snumconvo = snumconvo;
    }

    public String getSdiasconvocatoria() {
        return sdiasconvocatoria;
    }

    public void setSdiasconvocatoria(String sdiasconvocatoria) {
        this.sdiasconvocatoria = sdiasconvocatoria;
    }

    public String getSmensaje() {
        return smensaje;
    }

    public void setSmensaje(String smensaje) {
        this.smensaje = smensaje;
    }

    public String getSdiasreapertura() {
        return sdiasreapertura;
    }

    public void setSdiasreapertura(String sdiasreapertura) {
        this.sdiasreapertura = sdiasreapertura;
    }

    public String getSmensajereapertura() {
        return smensajereapertura;
    }

    public void setSmensajereapertura(String smensajereapertura) {
        this.smensajereapertura = smensajereapertura;
    }

    public String getSestado() {
        return sestado;
    }

    public void setSestado(String sestado) {
        this.sestado = sestado;
    }

    // </editor-fold> 
    /**
     * Creates a new instance of InfAnualConvRecordatorioManagedBean
     */
    public InfAnualConvRecordatorioManagedBean() {
        this.setPaginaMant("/pages/infAnual/parametros/convRecordatorioInfAnual");
    }
    // <editor-fold defaultstate="collapsed" desc="CARGAR LISTADO DE PARAMETROS"> 

    public List<InfanualConvrecordatorio> getlistInfAnualConvRecordatorio() {
        listInfAnualConvRecordatorio.clear();
        if (smtipcontrato != null) {
            listInfAnualConvRecordatorio = infAnualConvRecFacade.findbyTipContrato(smtipcontrato.charAt(0));
        }
        return listInfAnualConvRecordatorio;
    }

    public void setListParEncuesta(List<InfanualConvrecordatorio> listInfAnualConvRecordatorio) {
        this.listInfAnualConvRecordatorio = listInfAnualConvRecordatorio;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private InfanualConvrecordatorio selectedInfAnualConvRecordatorio;

    public InfanualConvrecordatorio getSelectedInfAnualConvRecordatorio() {
        return selectedInfAnualConvRecordatorio;
    }

    public void setSelectedInfAnualConvRecordatorio(InfanualConvrecordatorio selectedInfAnualConvRecordatorio) {
        this.selectedInfAnualConvRecordatorio = selectedInfAnualConvRecordatorio;
    }

    // </editor-fold> 
    private void reset() {
        selectedInfAnualConvRecordatorio = null;
        sdiasconvocatoria = null;
        sdiasreapertura = null;
        sestado = null;
        smensaje = null;
        smensajereapertura = null;
        snumconvo = null;
    }

    // <editor-fold defaultstate="collapsed" desc="BOTON NUEVO">  
    @Override
    public void nuevoButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/infAnual/parametros/convRecordatorioInfAnualModal");
        super.nuevoButton_processAction(ae);
        reset();

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON EDITAR">  
    @Override
    public void editarButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/infAnual/parametros/convRecordatorioInfAnualModal");
        super.editarButton_processAction(ae);
        infAnualConvRecordatorio = selectedInfAnualConvRecordatorio;
        sdiasconvocatoria = (infAnualConvRecordatorio.getIarDiasRecordatorio() == null ? null : infAnualConvRecordatorio.getIarDiasRecordatorio().toString());
        sdiasreapertura = (infAnualConvRecordatorio.getIarDiasReapertura() == null ? null : infAnualConvRecordatorio.getIarDiasReapertura().toString());
        sestado = (infAnualConvRecordatorio.getIarEstadoRecordatorio() == null ? null : infAnualConvRecordatorio.getIarEstadoRecordatorio().toString());
        smensaje = (infAnualConvRecordatorio.getIarMensaje() == null ? null : infAnualConvRecordatorio.getIarMensaje());
        smensajereapertura = (infAnualConvRecordatorio.getIarMensajeReapertura() == null ? null : infAnualConvRecordatorio.getIarMensajeReapertura());
        snumconvo = (infAnualConvRecordatorio.getIarNumRecordatorio() == null ? null : infAnualConvRecordatorio.getIarNumRecordatorio().toString());
        RequestContext.getCurrentInstance().update("formMant:panelMant");

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON CANCELAR">  
    @Override
    public void cancelarButton_processAction(ActionEvent ae) {
        selectedInfAnualConvRecordatorio = new InfanualConvrecordatorio();
        infAnualConvRecordatorio = new InfanualConvrecordatorio();
        reset();
        this.setPaginaMant("/WEB-INF/templates/includes/sinContenido");

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON ELIMINAR">  
    @Override
    public void eliminarButton_processAction(ActionEvent ae, String lsmensaje) {
        String ls_mensaje = null;
        infAnualConvRecFacade.remove(selectedInfAnualConvRecordatorio);
        ls_mensaje = "Dato Eliminado";
        getlistInfAnualConvRecordatorio();
        super.eliminarButton_processAction(ae, ls_mensaje);
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  
    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        InfanualConvrecordatorio encconvaux = new InfanualConvrecordatorio();
        if (infAnualConvRecordatorio != null) {
            if (infAnualConvRecordatorio.getIarCodigo() != null) {
                encconvaux = infAnualConvRecFacade.find(infAnualConvRecordatorio.getIarCodigo());
            }
        }
        try {
            infAnualConvRecordatorio.setIarDiasRecordatorio(sdiasconvocatoria == null ? null : Integer.valueOf(sdiasconvocatoria));
            infAnualConvRecordatorio.setIarDiasReapertura(sdiasreapertura == null ? null : Integer.valueOf(sdiasreapertura));
            infAnualConvRecordatorio.setIarEstadoRecordatorio(sestado == null ? null : sestado.charAt(0));
            infAnualConvRecordatorio.setIarMensaje(smensaje == null ? null : smensaje);
            infAnualConvRecordatorio.setIarMensajeReapertura(smensajereapertura == null ? null : smensajereapertura);
            infAnualConvRecordatorio.setIarNumRecordatorio(snumconvo == null ? null : Integer.valueOf(snumconvo));
            infAnualConvRecordatorio.setIarTipoContrato(smtipcontrato == null ? null : Integer.valueOf(smtipcontrato));
            if (encconvaux.getIarCodigo() == null) {
                infAnualConvRecFacade.create(infAnualConvRecordatorio);
            } else {
                infAnualConvRecFacade.edit(infAnualConvRecordatorio);
            }
            JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Guardar Encuesta");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            infAnualConvRecordatorio = new InfanualConvrecordatorio();
        }
        RequestContext.getCurrentInstance().update("fconvrecordatorioInfAnual:dataRecordatorio");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
    }

    // </editor-fold> 
    public void tipcontratovalueChangeListener() {
        listInfAnualConvRecordatorio.clear();
        if (smtipcontrato != null) {
            getlistInfAnualConvRecordatorio();
        }
    }
}
