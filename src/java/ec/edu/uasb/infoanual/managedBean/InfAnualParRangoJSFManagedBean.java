/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.infoanual.entities.InfanualRangos;
import ec.edu.uasb.infoanual.session.InfanualRangosFacadeLocal;
import ec.edu.uasb.seg.entities.Usuario;

import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "rangoInfAnual")
@ViewScoped
public class InfAnualParRangoJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private List<InfanualRangos> listRangos = new ArrayList<InfanualRangos>();
    private InfanualRangos rangos = new InfanualRangos();
    private String sdedicacion;

    private double rmdesde;
    private double rmhasta;
    private double rpedesde;
    private double rpehasta;
    private String sestado;
    private String smtipcontrato = null;
    private Usuario usr = new Usuario();

    @EJB
    private InfanualRangosFacadeLocal infrangoFacade;
    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">

    public InfanualRangos getRangos() {
        return rangos;
    }

    public void setRangos(InfanualRangos rangos) {
        this.rangos = rangos;
    }

    public String getSmtipcontrato() {
        return smtipcontrato;
    }

    public void setSmtipcontrato(String smtipcontrato) {
        this.smtipcontrato = smtipcontrato;
    }

    public Usuario getUsr() {
        return usr;
    }

    public void setUsr(Usuario usr) {
        this.usr = usr;
    }

    public String getSdedicacion() {
        return sdedicacion;
    }

    public void setSdedicacion(String sdedicacion) {
        this.sdedicacion = sdedicacion;
    }

    public double getRmdesde() {
        return rmdesde;
    }

    public void setRmdesde(double rmdesde) {
        this.rmdesde = rmdesde;
    }

    public double getRmhasta() {
        return rmhasta;
    }

    public void setRmhasta(double rmhasta) {
        this.rmhasta = rmhasta;
    }

    public double getRpedesde() {
        return rpedesde;
    }

    public void setRpedesde(double rpedesde) {
        this.rpedesde = rpedesde;
    }

    public double getRpehasta() {
        return rpehasta;
    }

    public void setRpehasta(double rpehasta) {
        this.rpehasta = rpehasta;
    }

    public String getSestado() {
        return sestado;
    }

    public void setSestado(String sestado) {
        this.sestado = sestado;
    }

    // </editor-fold> 
    /**
     * Creates a new instance of InfAnualParCalenManagedBean
     */
    public InfAnualParRangoJSFManagedBean() {
        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        this.setPaginaMant("/pages/infAnual/parametros/rangosInfAnual");
    }

    // <editor-fold defaultstate="collapsed" desc="CARGAR LISTADO DE PARAMETROS"> 
    public List<InfanualRangos> getListRangos() {
        listRangos.clear();
        if (smtipcontrato != null) {
            listRangos = infrangoFacade.getRangobyTipContrato(Integer.valueOf(smtipcontrato));
        }
        return listRangos;
    }

    public void setListRangos(List<InfanualRangos> listRangos) {
        this.listRangos = listRangos;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private InfanualRangos selectedRangos;

    public InfanualRangos getSelectedRangos() {
        return selectedRangos;
    }

    public void setSelectedRangos(InfanualRangos selectedRangos) {
        this.selectedRangos = selectedRangos;
    }

    // </editor-fold> 
    private void reset() {
        selectedRangos = null;
        sdedicacion = null;
        rmdesde = 0;
        rmhasta = 0;
        rpedesde = 0;
        rpehasta = 0;
        sestado = null;
        //smtipcontrato = null;
    }

    // <editor-fold defaultstate="collapsed" desc="BOTON NUEVO">  
    @Override
    public void nuevoButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/infAnual/parametros/rangosInfAnualModal");
        super.nuevoButton_processAction(ae);
        reset();

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON CANCELAR">  
    @Override
    public void cancelarButton_processAction(ActionEvent ae) {
        selectedRangos = new InfanualRangos();
        rangos = new InfanualRangos();
        reset();
        this.setPaginaMant("/WEB-INF/templates/includes/sinContenido");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON EDITAR">  
    @Override
    public void editarButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/infAnual/parametros/rangosInfAnualModal");
        super.editarButton_processAction(ae);
        rangos = selectedRangos;
        sdedicacion = (rangos.getIarTipoDocente()== null ? null : rangos.getIarTipoDocente().toString());
        rmdesde= (rangos.getIarRmaxDesde()== null ? null : Double.valueOf(rangos.getIarRmaxDesde().toString()));
        rmhasta= (rangos.getIarRminHasta()== null ? null : Double.valueOf(rangos.getIarRminHasta().toString()));
        rpedesde= (rangos.getIarRmaxDesde()== null ? null : Double.valueOf(rangos.getIarRmaxDesde().toString()));
        rpehasta= (rangos.getIarRmaxHasta()== null ? null : Double.valueOf(rangos.getIarRmaxHasta().toString()));
        sestado = (rangos.getIarEstadoRango() == null ? null : rangos.getIarEstadoRango().toString());
        RequestContext.getCurrentInstance().update("formMant:panelMant");

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON ELIMINAR">  
    @Override
    public void eliminarButton_processAction(ActionEvent ae, String lsmensaje) {
        String ls_mensaje = null;
        infrangoFacade.remove(selectedRangos);
        if (ls_mensaje == null) {
            ls_mensaje = "Dato Eliminado";
        }
        super.eliminarButton_processAction(ae, ls_mensaje);
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  
    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        InfanualRangos rangoaux = new InfanualRangos();
        if (rangos != null) {
            if (rangos.getIarCodigo() != null) {
                rangoaux = infrangoFacade.find(rangos.getIarCodigo());
            }
        }
        try {
            
            rangos.setIarTipoContrato(smtipcontrato ==null ? null: Integer.valueOf(smtipcontrato));
            rangos.setIarTipoDocente(sdedicacion==null ? null: sdedicacion.charAt(0));
            rangos.setIarRminDesde(rmdesde== 0 ? null :BigDecimal.valueOf(rmdesde));
            rangos.setIarRminHasta(rmhasta== 0 ? null :BigDecimal.valueOf(rmhasta));
            rangos.setIarRmaxDesde(rpedesde== 0 ? null :BigDecimal.valueOf(rpedesde));
            rangos.setIarRmaxHasta(rpehasta== 0 ? null :BigDecimal.valueOf(rpehasta));
            rangos.setIarEstadoRango(sestado== null ? null :sestado.charAt(0));
            if (rangoaux.getIarCodigo() == null) {
                rangos.setIarUsuarioCrea(usr.getUsuCedPass());
                rangos.setIarFechaCrea(new Date());
                rangos.setIarUsuarioModifica(usr.getUsuCedPass());
                rangos.setIarFechaModifica(new Date());                
                infrangoFacade.create(rangos);
            } else {
                rangos.setIarUsuarioModifica(usr.getUsuCedPass());
                rangos.setIarFechaModifica(new Date());       
                infrangoFacade.edit(rangos);
            }
            JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
            getListRangos();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Guardar los Rangos");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            rangos = new InfanualRangos();
        }

        RequestContext.getCurrentInstance().update("frangoInfAnual:dataRango");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
    }

    // </editor-fold> 
    public void tipcontratovalueChangeListener() {
        listRangos.clear();
        if (smtipcontrato != null) {
            getListRangos();
        }
    }
}
