/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.infoanual.entities.InfanualParametroPuntuacion;
import ec.edu.uasb.infoanual.session.InfanualParametroPuntuacionFacadeLocal;
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
@ManagedBean(name = "parPuntuacionInfAnual")
@ViewScoped
public class InfAnualParPuntuacionJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private List<InfanualParametroPuntuacion> listParPuntuacion = new ArrayList<InfanualParametroPuntuacion>();
    private InfanualParametroPuntuacion parPuntuacion = new InfanualParametroPuntuacion();
    private String sambito;
    private String sactividad;
    private String scolumna;
    private double spunto;
    private String sestado;
    private Usuario usr = new Usuario();


    @EJB
    private InfanualParametroPuntuacionFacadeLocal infparpuntFacade;
    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">

    public InfanualParametroPuntuacion getParPuntuacion() {
        return parPuntuacion;
    }

    public void setParPuntuacion(InfanualParametroPuntuacion parPuntuacion) {
        this.parPuntuacion = parPuntuacion;
    }

    public String getSambito() {
        return sambito;
    }

    public void setSambito(String sambito) {
        this.sambito = sambito;
    }

    public String getSactividad() {
        return sactividad;
    }

    public void setSactividad(String sactividad) {
        this.sactividad = sactividad;
    }

    public String getScolumna() {
        return scolumna;
    }

    public void setScolumna(String scolumna) {
        this.scolumna = scolumna;
    }

    public double getSpunto() {
        return spunto;
    }

    public void setSpunto(double spunto) {
        this.spunto = spunto;
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
    public InfAnualParPuntuacionJSFManagedBean() {
        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");        
        this.setPaginaMant("/pages/infAnual/parametros/parPuntuacionInfAnual");
    }
    // <editor-fold defaultstate="collapsed" desc="CARGAR LISTADO DE PARAMETROS"> 

    
    public List<InfanualParametroPuntuacion> getListParPuntuacion() {
        listParPuntuacion.clear();
        listParPuntuacion = infparpuntFacade.findAll();
        return listParPuntuacion;
    }

    public void setListParPuntuacion(List<InfanualParametroPuntuacion> listParPuntuacion) {
        this.listParPuntuacion = listParPuntuacion;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private InfanualParametroPuntuacion selectedParPuntuacion;

    public InfanualParametroPuntuacion getSelectedParPuntuacion() {
        return selectedParPuntuacion;
    }

    public void setSelectedParPuntuacion(InfanualParametroPuntuacion selectedParPuntuacion) {
        this.selectedParPuntuacion = selectedParPuntuacion;
    }   

    // </editor-fold> 
    private void reset() {
        selectedParPuntuacion = null;
        sambito = null;
        sactividad = null;
        scolumna = null;
        spunto= 0;
        sestado = null;

    }

    // <editor-fold defaultstate="collapsed" desc="BOTON NUEVO">  
    @Override
    public void nuevoButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/infAnual/parametros/parPuntuacionInfAnualModal");
        super.nuevoButton_processAction(ae);
        reset();

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON CANCELAR">  
    @Override
    public void cancelarButton_processAction(ActionEvent ae) {
        selectedParPuntuacion = new InfanualParametroPuntuacion();
        parPuntuacion = new InfanualParametroPuntuacion();
        reset();
        this.setPaginaMant("/WEB-INF/templates/includes/sinContenido");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON EDITAR">  
    @Override
    public void editarButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/infAnual/parametros/parPuntuacionInfAnualModal");
        super.editarButton_processAction(ae);
        parPuntuacion = selectedParPuntuacion;
        sambito = (parPuntuacion.getIaapAmbito() == null ? null : parPuntuacion.getIaapAmbito());
        sactividad = (parPuntuacion.getIaapNombre() == null ? null : parPuntuacion.getIaapNombre());
        scolumna = (parPuntuacion.getIaapColumna() == null ? null : parPuntuacion.getIaapColumna());
        spunto =(parPuntuacion.getIaapPuntos() == null ? null : Double.valueOf(parPuntuacion.getIaapPuntos().toString()));
        sestado = (parPuntuacion.getIaapEstado() == null ? null : parPuntuacion.getIaapEstado());
        RequestContext.getCurrentInstance().update("formMant:panelMant");

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON ELIMINAR">  
    @Override
    public void eliminarButton_processAction(ActionEvent ae, String lsmensaje) {
        String ls_mensaje = null;
        infparpuntFacade.remove(selectedParPuntuacion);
        if (ls_mensaje == null) {
            ls_mensaje = "Dato Eliminado";
        }
        super.eliminarButton_processAction(ae, ls_mensaje);
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  
    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        InfanualParametroPuntuacion parPuntuacionaux = new InfanualParametroPuntuacion();
        if (parPuntuacion != null) {
            if (parPuntuacion.getIappCodigo() != null) {
                parPuntuacionaux = infparpuntFacade.find(parPuntuacion.getIappCodigo());
            }
        }
        try {
            parPuntuacion.setIaapAmbito(sambito == null ? null : sambito);
            parPuntuacion.setIaapColumna(scolumna== null ? null :scolumna);
            parPuntuacion.setIaapNombre(sactividad== null ? null :sactividad);
            parPuntuacion.setIaapPuntos(spunto== 0 ? null :BigDecimal.valueOf(spunto));
            parPuntuacion.setIaapEstado(sestado== null ? null :sestado);
            if (parPuntuacionaux.getIappCodigo() == null) {
                parPuntuacion.setIaapUsuarioCrea(usr.getUsuCedPass());
                parPuntuacion.setIaapFechaCrea(new Date());
                parPuntuacion.setIaapUsuarioModifica(usr.getUsuCedPass());
                parPuntuacion.setIaapFechaModifica(new Date());                
                infparpuntFacade.create(parPuntuacion);
            } else {
                parPuntuacion.setIaapUsuarioModifica(usr.getUsuCedPass());
                parPuntuacion.setIaapFechaModifica(new Date());
                infparpuntFacade.edit(parPuntuacion);
            }
            JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
            getListParPuntuacion();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Guardar Encuesta");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            parPuntuacion = new InfanualParametroPuntuacion();
        }

        RequestContext.getCurrentInstance().update("fpuntuacionInfAnual:dataParametro");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
    }

    // </editor-fold> 
}
