/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.sisevaluacion.entities.Encuesta;
import ec.edu.uasb.sisevaluacion.entities.EncuestaConvocatoria;
import ec.edu.uasb.sisevaluacion.session.EncuestaConvocatoriaFacadeLocal;
import ec.edu.uasb.sisevaluacion.session.EncuestaFacadeLocal;
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
@ManagedBean(name = "convoRecordatorioEnc")
@ViewScoped
public class ConvRecordatorioEncuestaManagedBean extends BaseJSFManagedBean implements Serializable {

    private List<Encuesta> listCabEncuesta = new ArrayList<Encuesta>();
    private List<EncuestaConvocatoria> listEncConvocatoria = new ArrayList<EncuestaConvocatoria>();
    private String smencuesta = null;
    private String smtipencuesta = null;
    private String snumconvo = null;
    private String sdiasconvocatoria = null;
    private String smensaje = null;
    private String sdiasreapertura = null;
    private String smensajereapertura = null;
    private String sestado = null;

    private EncuestaConvocatoria encuestaConvocatoria = new EncuestaConvocatoria();
    @EJB
    private EncuestaFacadeLocal encuestaFacade;
    @EJB
    private EncuestaConvocatoriaFacadeLocal encConvFacade;
// <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">

    public String getSmencuesta() {
        return smencuesta;
    }

    public void setSmencuesta(String smencuesta) {
        this.smencuesta = smencuesta;
    }

    public String getSmtipencuesta() {
        return smtipencuesta;
    }

    public void setSmtipencuesta(String smtipencuesta) {
        this.smtipencuesta = smtipencuesta;
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
    public ConvRecordatorioEncuestaManagedBean() {
        this.setPaginaMant("/pages/evaluacion/formulario/convocatoriaRecordatorio");
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

    public List<EncuestaConvocatoria> getListEncConvocatoria() {
        listEncConvocatoria.clear();
        if (smencuesta != null) {
            listEncConvocatoria = encConvFacade.findAllActivo(Integer.valueOf(smencuesta));
        }
        return listEncConvocatoria;
    }

    public void setListEncConvocatoria(List<EncuestaConvocatoria> listEncConvocatoria) {
        this.listEncConvocatoria = listEncConvocatoria;
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private EncuestaConvocatoria selectedConvocatoria;

    public EncuestaConvocatoria getSelectedConvocatoria() {
        return selectedConvocatoria;
    }

    public void setSelectedConvocatoria(EncuestaConvocatoria selectedConvocatoria) {
        this.selectedConvocatoria = selectedConvocatoria;
    }

    // </editor-fold> 


    public void tipencuestavalueChangeListener() {
        listCabEncuesta.clear();
        smencuesta = null;
        if (smtipencuesta != null) {
            getListCabEncuesta();
            getListEncConvocatoria();

        }
    }

    public void encuestavalueChangeListener() {
        if (smencuesta != null) {
            getListEncConvocatoria();
            reset();
        }
    }

    private void reset() {
        selectedConvocatoria = null;
        sdiasconvocatoria = null;
        sdiasreapertura = null;
        sestado = null;
        smensaje = null;
        smensajereapertura = null;
        snumconvo = null;
    }
// <editor-fold defaultstate="collapsed" desc="BOTON EDITAR">  

    @Override
    public void editarButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/evaluacion/formulario/convocatoriaRecordatorioModal");
        super.editarButton_processAction(ae);
        encuestaConvocatoria = selectedConvocatoria;
        sdiasconvocatoria = (encuestaConvocatoria.getDiasConvocatoria() == null ? null : encuestaConvocatoria.getDiasConvocatoria().toString());
        sdiasreapertura = (encuestaConvocatoria.getDiasReapertura() == null ? null : encuestaConvocatoria.getDiasReapertura().toString());
        sestado = (encuestaConvocatoria.getEstadoConvocatoria() == null ? null : encuestaConvocatoria.getEstadoConvocatoria().toString());
        smensaje = (encuestaConvocatoria.getMensaje() == null ? null : encuestaConvocatoria.getMensaje());
        smensajereapertura = (encuestaConvocatoria.getMensajeReapertura() == null ? null : encuestaConvocatoria.getMensajeReapertura());
        snumconvo = (encuestaConvocatoria.getNumConvocatoria() == null ? null : encuestaConvocatoria.getNumConvocatoria().toString());
        RequestContext.getCurrentInstance().update("formMant:panelMant");

    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON CANCELAR">  
    @Override
    public void cancelarButton_processAction(ActionEvent ae) {
        selectedConvocatoria = new EncuestaConvocatoria();
        encuestaConvocatoria = new EncuestaConvocatoria();
        reset();
        this.setPaginaMant("/WEB-INF/templates/includes/sinContenido");

    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON ELIMINAR">  
    @Override
    public void eliminarButton_processAction(ActionEvent ae, String lsmensaje) {
        String ls_mensaje = null;
        encConvFacade.remove(selectedConvocatoria);
        ls_mensaje = "Dato Eliminado";
        getListEncConvocatoria();
        super.eliminarButton_processAction(ae, ls_mensaje);
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON NUEVO">  
    @Override
    public void nuevoButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/evaluacion/formulario/convocatoriaRecordatorioModal");
        super.nuevoButton_processAction(ae);
        reset();

    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  
    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        EncuestaConvocatoria encconvaux = new EncuestaConvocatoria();
        if (encuestaConvocatoria != null) {
            if (encuestaConvocatoria.getCodigoEncrecordatorio() != null) {
                encconvaux = encConvFacade.find(encuestaConvocatoria.getCodigoEncrecordatorio());
            }
        }
        try {
            encuestaConvocatoria.setDiasConvocatoria(sdiasconvocatoria == null ? null : Integer.valueOf(sdiasconvocatoria));
            encuestaConvocatoria.setDiasReapertura(sdiasreapertura == null ? null : Integer.valueOf(sdiasreapertura));
            encuestaConvocatoria.setEstadoConvocatoria(sestado == null ? null : sestado.charAt(0));
            encuestaConvocatoria.setMensaje(smensaje == null ? null : smensaje);
            encuestaConvocatoria.setMensajeReapertura(smensajereapertura == null ? null : smensajereapertura);
            encuestaConvocatoria.setNumConvocatoria(snumconvo == null ? null : Integer.valueOf(snumconvo));
            if (encconvaux.getCodigoEncrecordatorio() == null) {
                Encuesta encu = new Encuesta();
                if (smencuesta != null) {
                    encu = encuestaFacade.find(new Long(smencuesta));
                }
                encuestaConvocatoria.setCodigoEncuesta(encu == null ? null : encu);
                encConvFacade.create(encuestaConvocatoria);
            } else {
                encConvFacade.edit(encuestaConvocatoria);
            }
            JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Guardar Encuesta");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            encuestaConvocatoria = new EncuestaConvocatoria();
        }
        RequestContext.getCurrentInstance().update("frecordatorioEncuesta:dataRecordatorio");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
    }

    // </editor-fold> 
}
