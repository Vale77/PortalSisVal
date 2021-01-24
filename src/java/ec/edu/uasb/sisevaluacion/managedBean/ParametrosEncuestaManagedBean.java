/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.sisevaluacion.entities.Encuesta;
import ec.edu.uasb.sisevaluacion.entities.Parametros;
import ec.edu.uasb.sisevaluacion.session.EncuestaFacadeLocal;
import ec.edu.uasb.sisevaluacion.session.ParametrosFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "parametrosEncuesta")
@ViewScoped
public class ParametrosEncuestaManagedBean extends BaseJSFManagedBean implements Serializable {

    private List<Encuesta> listCabEncuesta = new ArrayList<Encuesta>();
    private List<Parametros> listParEncuesta = new ArrayList<Parametros>();
    private String smencuesta = null;
    private String smtipencuesta = null;
    private Parametros parametro = new Parametros();
    private boolean bretparam = false;
    private String sdiashabilitado;
    private String sdiasreapertura;
    private String scopiaa;
    private String sasunto;
    private String sasuntoreapertura;
    private String sasuntocierre;
    private String smensajecierre;
    private String sasuntopago;
    private String smensajepago;
    @EJB
    private EncuestaFacadeLocal encuestaFacade;
    @EJB
    private ParametrosFacadeLocal parametrosFacade;

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public String getSmencuesta() {
        return smencuesta;
    }

    public void setSmencuesta(String smencuesta) {
        this.smencuesta = smencuesta;
    }

    public String getSmensajecierre() {
        return smensajecierre;
    }

    public void setSmensajecierre(String smensajecierre) {
        this.smensajecierre = smensajecierre;
    }

    public String getSmtipencuesta() {
        return smtipencuesta;
    }

    public void setSmtipencuesta(String smtipencuesta) {
        this.smtipencuesta = smtipencuesta;
    }

    public boolean isBretparam() {
        return bretparam;
    }

    public void setBretparam(boolean bretparam) {
        this.bretparam = bretparam;
    }

    public String getSdiashabilitado() {
        return sdiashabilitado;
    }

    public void setSdiashabilitado(String sdiashabilitado) {
        this.sdiashabilitado = sdiashabilitado;
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

    public String getSasuntopago() {
        return sasuntopago;
    }

    public void setSasuntopago(String sasuntopago) {
        this.sasuntopago = sasuntopago;
    }

    public String getSmensajepago() {
        return smensajepago;
    }

    public void setSmensajepago(String smensajepago) {
        this.smensajepago = smensajepago;
    }

    // </editor-fold> 
    public ParametrosEncuestaManagedBean() {
        this.setPaginaMant("/pages/evaluacion/formulario/parametrosEncuesta");
    }

// <editor-fold defaultstate="collapsed" desc="CARGAR LISTADO DE ENCUESTAS"> 
    public List<Encuesta> getListCabEncuesta() {
        listCabEncuesta.clear();
        if (smtipencuesta != null) {
            listCabEncuesta = encuestaFacade.findAllActivo(smtipencuesta.toString().charAt(0));
        }
        return listCabEncuesta;
    }

    public void setListCabEncuesta(List<Encuesta> listCabEncuesta) {
        this.listCabEncuesta = listCabEncuesta;
    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="CARGAR LISTADO DE PARAMETROS"> 

    public List<Parametros> getListParEncuesta() {
        listParEncuesta.clear();
        if (smencuesta != null) {
            listParEncuesta = parametrosFacade.findAllActivo(Integer.valueOf(smencuesta));
        }
        return listParEncuesta;
    }

    public void setListParEncuesta(List<Parametros> listParEncuesta) {
        this.listParEncuesta = listParEncuesta;
    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private Parametros selectedParametro;

    public Parametros getSelectedParametro() {
        return selectedParametro;
    }

    public void setSelectedParametro(Parametros selectedParametro) {
        this.selectedParametro = selectedParametro;
    }

    // </editor-fold> 
    public void tipencuestavalueChangeListener() {
        listCabEncuesta.clear();
        smencuesta = null;
        if (smtipencuesta != null) {
            getListCabEncuesta();
            getListParEncuesta();

        }
    }

    public void encuestavalueChangeListener() {
        if (smencuesta != null) {
            reset();
            getListParEncuesta();
            if (listParEncuesta.size() <= 0) {
                bretparam = false;
            } else {
                bretparam = true;
            }
        }
    }

    private void reset() {
        selectedParametro = null;
        sasunto = null;
        sasuntocierre = null;
        smensajecierre = null;
        sasuntoreapertura = null;
        sasuntopago= null;
        smensajepago = null;
        scopiaa = null;
        sdiashabilitado = null;
        sdiasreapertura = null;
    }

// <editor-fold defaultstate="collapsed" desc="BOTON EDITAR">  
    @Override
    public void editarButton_processAction(ActionEvent ae) {

        this.setPaginaMant("/pages/evaluacion/formulario/parametrosEncuestaModal");
        super.editarButton_processAction(ae);
        parametro = selectedParametro;
        sasunto = (parametro.getAsunto() == null ? null : parametro.getAsunto());
        sasuntocierre = (parametro.getAsuntoCierre() == null ? null : parametro.getAsuntoCierre());
        sasuntoreapertura = (parametro.getAsuntoReapertura() == null ? null : parametro.getAsuntoReapertura());
        scopiaa = (parametro.getCopiaA() == null ? null : parametro.getCopiaA());
        sdiashabilitado = (parametro.getDiasHabilitada() == null ? null : parametro.getDiasHabilitada().toString());
        sdiasreapertura = (parametro.getDiasHabilitada() == null ? null : parametro.getDiasReapertura().toString());
        smensajecierre = (parametro.getMensajeCierre() == null ? null : parametro.getMensajeCierre());
        sasuntopago=(parametro.getAsuntoPagestampilla() == null ? null : parametro.getAsuntoPagestampilla());
        smensajepago =(parametro.getMensajePagestampilla()== null ? null : parametro.getMensajePagestampilla());
        RequestContext.getCurrentInstance().update("formMant:panelMant");

    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON CANCELAR">  
    @Override
    public void cancelarButton_processAction(ActionEvent ae) {
        selectedParametro = new Parametros();
        parametro = new Parametros();
        reset();
        this.setPaginaMant("/WEB-INF/templates/includes/sinContenido");

    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON ELIMINAR">  
    @Override
    public void eliminarButton_processAction(ActionEvent ae, String lsmensaje) {
        String ls_mensaje = null;
        parametrosFacade.remove(selectedParametro);
        if (ls_mensaje == null) {
            ls_mensaje = "Dato Eliminado";
        }
        super.eliminarButton_processAction(ae, ls_mensaje);
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON NUEVO">  
    @Override
    public void nuevoButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/evaluacion/formulario/parametrosEncuestaModal");
        super.nuevoButton_processAction(ae);
        reset();

    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  
    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        Parametros parametroaux = new Parametros();
        if (parametro != null) {
            if (parametro.getCodigoPareval() != null) {
                parametroaux = parametrosFacade.find(parametro.getCodigoPareval());
            }
        }
        try {
            parametro.setDiasHabilitada(sdiashabilitado == null ? null : Integer.valueOf(sdiashabilitado));
            parametro.setDiasReapertura(sdiasreapertura == null ? null : Integer.valueOf(sdiasreapertura));
            parametro.setAsunto(sasunto == null ? null : sasunto);
            parametro.setAsuntoCierre(sasuntocierre == null ? null : sasuntocierre);
            parametro.setMensajeCierre(smensajecierre == null ? null : smensajecierre);
            parametro.setAsuntoReapertura(sasuntoreapertura == null ? null : sasuntoreapertura);
            parametro.setAsuntoPagestampilla(sasuntopago== null ? null :sasuntopago);
            parametro.setMensajePagestampilla(smensajepago== null ? null :smensajepago);
            
            parametro.setCopiaA(scopiaa == null ? null : scopiaa);
            if (parametroaux.getCodigoPareval() == null) {
                Encuesta encu = new Encuesta();
                if (smencuesta != null) {
                    encu = encuestaFacade.find(new Long(smencuesta));
                }
                parametro.setCodigoEncuesta(encu == null ? null : encu);
                parametrosFacade.create(parametro);
            } else {
                parametrosFacade.edit(parametro);
            }
            JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Guardar Encuesta");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            parametro = new Parametros();
        }

        RequestContext.getCurrentInstance().update("fparametroEncuesta:dataParametro");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
    }

    // </editor-fold> 
}
