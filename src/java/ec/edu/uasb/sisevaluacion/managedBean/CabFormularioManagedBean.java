/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.managedBean;
import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.sisevaluacion.entities.Encuesta;
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
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "cabformulario")
@ViewScoped
public class CabFormularioManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120420L;
    private List<Encuesta> listCabEncuesta = new ArrayList<Encuesta>();

    //Ingreso información Encuesta
    Encuesta encuesta = new Encuesta();
    private Encuesta selectedEncuesta;
    private Encuesta encuestaEdit;
    private HtmlInputText itxttitencuesta = new HtmlInputText();
    private HtmlInputText itxtdescripcion = new HtmlInputText();
    private HtmlInputText itxtreferencia = new HtmlInputText();
    private HtmlSelectOneMenu smtipuso = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smtipencuesta = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smsubtipencuesta = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smestadencuesta = new HtmlSelectOneMenu();
    @EJB
    private EncuestaFacadeLocal encuestaFacade;

    /**
     * Creates a new instance of CabFormularioManagedBean
     */
    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public HtmlInputText getItxttitencuesta() {
        return itxttitencuesta;
    }

    public void setItxttitencuesta(HtmlInputText itxttitencuesta) {
        this.itxttitencuesta = itxttitencuesta;
    }

    public HtmlInputText getItxtdescripcion() {
        return itxtdescripcion;
    }

    public void setItxtdescripcion(HtmlInputText itxtdescripcion) {
        this.itxtdescripcion = itxtdescripcion;
    }

    public HtmlInputText getItxtreferencia() {
        return itxtreferencia;
    }

    public void setItxtreferencia(HtmlInputText itxtreferencia) {
        this.itxtreferencia = itxtreferencia;
    }

    public HtmlSelectOneMenu getSmtipuso() {
        return smtipuso;
    }

    public void setSmtipuso(HtmlSelectOneMenu smtipuso) {
        this.smtipuso = smtipuso;
    }

    public HtmlSelectOneMenu getSmtipencuesta() {
        return smtipencuesta;
    }

    public void setSmtipencuesta(HtmlSelectOneMenu smtipencuesta) {
        this.smtipencuesta = smtipencuesta;
    }

    public HtmlSelectOneMenu getSmsubtipencuesta() {
        return smsubtipencuesta;
    }

    public void setSmsubtipencuesta(HtmlSelectOneMenu smsubtipencuesta) {
        this.smsubtipencuesta = smsubtipencuesta;
    }

    public HtmlSelectOneMenu getSmestadencuesta() {
        return smestadencuesta;
    }

    public void setSmestadencuesta(HtmlSelectOneMenu smestadencuesta) {
        this.smestadencuesta = smestadencuesta;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public Encuesta getEncuestaEdit() {
        return encuestaEdit;
    }

    public void setEncuestaEdit(Encuesta encuestaEdit) {
        this.encuestaEdit = encuestaEdit;
    }

    // </editor-fold> 
    public CabFormularioManagedBean() {
        this.setPaginaMant("/pages/evaluacion/formulario/CabeceraFormulario");
    }

    // <editor-fold defaultstate="collapsed" desc="CABECERA EVALUACION"> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR LA TABLA"> 
    public List<Encuesta> getListCabEncuesta() {
        listCabEncuesta.clear();
        if (smtipencuesta.getValue() != null) {
            //listCabEncuesta = encuestaFacade.findAllActivo(smtipencuesta.getValue().toString().charAt(0));
            listCabEncuesta = encuestaFacade.findAll(smtipencuesta.getValue().toString().charAt(0));
        }
        return listCabEncuesta;
    }

    public void setListCabEncuesta(List<Encuesta> listCabEncuesta) {
        this.listCabEncuesta = listCabEncuesta;
    }
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  

    public Encuesta getSelectedEncuesta() {
        return selectedEncuesta;
    }

    public void setSelectedEncuesta(Encuesta selectedEncuesta) {
        this.selectedEncuesta = selectedEncuesta;
    }
    // </editor-fold> 
    
   
    // </editor-fold> 
    public void tipencuestavalueChangeListener() {
        listCabEncuesta.clear();
        if (smtipencuesta.getValue() != null) {
            getListCabEncuesta();

        }
    }

    private void reset() {
        selectedEncuesta = null;
    }

// <editor-fold defaultstate="collapsed" desc="BOTON EDITAR">  
    @Override
    public void editarButton_processAction(ActionEvent ae) {

        this.setPaginaMant("/pages/evaluacion/formulario/encuestaModal");
        super.editarButton_processAction(ae);
        encuesta = selectedEncuesta;
        itxttitencuesta.setValue(selectedEncuesta.getTitulo());
        itxtdescripcion.setValue(selectedEncuesta.getDescripcion());
        itxtreferencia.setValue(selectedEncuesta.getReferencia());
        smsubtipencuesta.setValue(selectedEncuesta.getSubtipo());
        smtipuso.setValue(selectedEncuesta.getUso() == null ? "" : selectedEncuesta.getUso().charValue());
        RequestContext.getCurrentInstance().update("formMant:panelMant");

    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON CANCELAR">  

    @Override
    public void cancelarButton_processAction(ActionEvent ae) {
        selectedEncuesta = new Encuesta();
        encuesta = new Encuesta();
        reset();
        this.setPaginaMant("/WEB-INF/templates/includes/sinContenido");

    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON ELIMINAR">  
    @Override
    public void eliminarButton_processAction(ActionEvent ae, String lsmensaje) {
        String ls_mensaje = null;
        ls_mensaje = encuestaFacade.removeSafeEncuesta(selectedEncuesta);
        if (ls_mensaje == null) {
            ls_mensaje = "Dato Eliminado";
        }
        getListCabEncuesta();
        super.eliminarButton_processAction(ae, ls_mensaje);
    }

    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON NUEVO">  
    @Override
    public void nuevoButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/evaluacion/formulario/encuestaModal");
        super.nuevoButton_processAction(ae);
        reset();

    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  
    @Override
    public void guardarButton_processAction(ActionEvent ae) {        
        Encuesta encuestaaux = new Encuesta();
        if (encuesta != null) {
            if (encuesta.getCodigoEncuesta() != null) {
                encuestaaux = encuestaFacade.find(encuesta.getCodigoEncuesta());
            }
        }
        try {
            encuesta.setTipo(smtipencuesta.getValue() == null ? null : smtipencuesta.getValue().toString().charAt(0));
            encuesta.setSubtipo(smsubtipencuesta.getValue() == null ? null : smsubtipencuesta.getValue().toString());
            encuesta.setTitulo(itxttitencuesta.getValue() == null ? null : itxttitencuesta.getValue().toString());
            encuesta.setDescripcion(itxtdescripcion.getValue() == null ? null : itxtdescripcion.getValue().toString());
            encuesta.setReferencia(itxtreferencia.getValue() == null ? null : itxtreferencia.getValue().toString());
            encuesta.setUso(smtipuso.getValue() == null ? null : smtipuso.getValue().toString().charAt(0));
            encuesta.setEstado(smestadencuesta.getValue() == null ? null : smestadencuesta.getValue().toString().charAt(0));
            if (encuestaaux.getCodigoEncuesta() == null) {
                encuestaFacade.create(encuesta);
            } else {
                encuestaFacade.edit(encuesta);
            }
            //Recupero los datos de las encuestas
            JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");            
        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Guardar Encuesta");            
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            encuesta = new Encuesta();
        }      
        RequestContext.getCurrentInstance().update("fcabecera:dataFormulario");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
    }
   
    // </editor-fold> 


   

}
