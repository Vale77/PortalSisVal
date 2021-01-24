/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.sisevaluacion.entities.Encuesta;
import ec.edu.uasb.sisevaluacion.entities.Pregunta;
import ec.edu.uasb.sisevaluacion.entities.PreguntaPK;
import ec.edu.uasb.sisevaluacion.session.EncuestaFacadeLocal;
import ec.edu.uasb.sisevaluacion.session.PreguntaFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "detformulario")
@ViewScoped
public class DetFormularioManagedBean extends BaseJSFManagedBean implements Serializable {

    private List<Pregunta> listdetEncuesta = new ArrayList<Pregunta>();
    private List<Pregunta> listpregpadreEncuesta = new ArrayList<Pregunta>();
    private List<Encuesta> listCabEncuesta = new ArrayList<Encuesta>();
    //Ingreso información Pregunta
    Pregunta pregunta = new Pregunta();
    private String itxtcodpregunta = null;
    private String itxtdescripcion = null;
    private String itxtreferencia = null;
    private String stipopregunta = "1";
    private Integer peso = 5;
    private String spregpadre = null;
    private HtmlSelectOneMenu smencuesta = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smtipencuesta = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smnivel = new HtmlSelectOneMenu();
    @EJB
    private PreguntaFacadeLocal preguntaFacade;
    @EJB
    private EncuestaFacadeLocal encuestaFacade;

    /**
     * Creates a new instance of CabFormularioManagedBean
     */
    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public HtmlSelectOneMenu getSmencuesta() {
        return smencuesta;
    }

    public void setSmencuesta(HtmlSelectOneMenu smencuesta) {
        this.smencuesta = smencuesta;
    }

    public String getSpregpadre() {
        return spregpadre;
    }

    public void setSpregpadre(String spregpadre) {
        this.spregpadre = spregpadre;
    }

    public HtmlSelectOneMenu getSmnivel() {
        return smnivel;
    }

    public void setSmnivel(HtmlSelectOneMenu smnivel) {
        this.smnivel = smnivel;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getItxtcodpregunta() {
        return itxtcodpregunta;
    }

    public void setItxtcodpregunta(String itxtcodpregunta) {
        this.itxtcodpregunta = itxtcodpregunta;
    }

    public String getItxtdescripcion() {
        return itxtdescripcion;
    }

    public void setItxtdescripcion(String itxtdescripcion) {
        this.itxtdescripcion = itxtdescripcion;
    }

    public String getItxtreferencia() {
        return itxtreferencia;
    }

    public void setItxtreferencia(String itxtreferencia) {
        this.itxtreferencia = itxtreferencia;
    }

    public String getStipopregunta() {
        return stipopregunta;
    }

    public void setStipopregunta(String stipopregunta) {
        this.stipopregunta = stipopregunta;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public HtmlSelectOneMenu getSmtipencuesta() {
        return smtipencuesta;
    }

    public void setSmtipencuesta(HtmlSelectOneMenu smtipencuesta) {
        this.smtipencuesta = smtipencuesta;
    }

    // </editor-fold> 
    public DetFormularioManagedBean() {
        this.setPaginaMant("/pages/evaluacion/formulario/pregunta");
    }

// <editor-fold defaultstate="collapsed" desc="DETALLE EVALUACION"> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR LA TABLA"> 
    public List<Pregunta> getListdetEncuesta() {
        listdetEncuesta.clear();
        if (smencuesta.getValue() != null) {
            listdetEncuesta = preguntaFacade.findbyEncuesta(Integer.valueOf(smencuesta.getValue().toString()));
        }
        return listdetEncuesta;
    }

    public void setListdetEncuesta(List<Pregunta> listdetEncuesta) {
        this.listdetEncuesta = listdetEncuesta;
    }

    public List<Pregunta> getListpregpadreEncuesta() {
        listpregpadreEncuesta.clear();
        if (smencuesta.getValue() != null) {
            listpregpadreEncuesta = preguntaFacade.findPregPadrebyEncuesta(Integer.valueOf(smencuesta.getValue().toString()));
        }
        return listpregpadreEncuesta;
    }

    public void setListpregpadreEncuesta(List<Pregunta> listpregpadreEncuesta) {
        this.listpregpadreEncuesta = listpregpadreEncuesta;
    }
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private Pregunta selectedPregunta;

    public Pregunta getSelectedPregunta() {
        return selectedPregunta;
    }

    public void setSelectedPregunta(Pregunta selectedPregunta) {
        this.selectedPregunta = selectedPregunta;
    }

    // </editor-fold>     
// </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="CABECERA EVALUACION"> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR LA TABLA"> 
    public List<Encuesta> getListCabEncuesta() {
        listCabEncuesta.clear();
        if (smtipencuesta.getValue() != null) {
            listCabEncuesta = encuestaFacade.findAllActivo(smtipencuesta.getValue().toString().charAt(0));
        }
        return listCabEncuesta;
    }

    public void setListCabEncuesta(List<Encuesta> listCabEncuesta) {
        this.listCabEncuesta = listCabEncuesta;
    }
    // </editor-fold> 
// </editor-fold> 

    public void tipencuestavalueChangeListener() {
        smencuesta.resetValue();
        if (smtipencuesta.getValue() != null) {
            getListCabEncuesta();
            getListdetEncuesta();
        }
    }

    public void encuestavalueChangeListener() {
        System.out.println(" smencuesta ->"+ smencuesta.getValue());
        if (smencuesta.getValue() != null) {
            getListdetEncuesta();
            getListpregpadreEncuesta();
        }
    }

    private void reset() {
        selectedPregunta = null;
    }

    // <editor-fold defaultstate="collapsed" desc="BOTON NUEVO">  
    @Override
    public void nuevoButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/evaluacion/formulario/preguntaModal");
        super.nuevoButton_processAction(ae);
        if (selectedPregunta != null) {
            spregpadre = selectedPregunta.getPreguntaPK().getCodigoPregunta().toString();
        }
        reset();

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  

    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        Pregunta preguntaaux = new Pregunta();
        PreguntaPK pregpk = new PreguntaPK();
        if (pregunta != null) {
            if (pregunta.getPreguntaPK() != null) {
                preguntaaux = preguntaFacade.find(pregunta.getPreguntaPK());
            }
        }
        try {
            pregunta.setDescripcion(itxtdescripcion == null ? null : itxtdescripcion);
            pregunta.setReferencia(itxtreferencia == null ? null : itxtreferencia);
            pregunta.setTipo(stipopregunta == null ? null : stipopregunta.charAt(0));
            pregunta.setFormato(itxtcodpregunta == null ? null : itxtcodpregunta);
            if (stipopregunta.equalsIgnoreCase("1")) {
                pregunta.setPeso(peso == null ? null : peso.longValue());
            } else {
                pregunta.setPeso(null);
            }
            if (preguntaaux.getPreguntaPK() == null) {
                pregpk.setCodigoEncuesta(smencuesta.getValue() == null ? null : new Long(smencuesta.getValue().toString()));
                pregpk.setCodigoPregunta(itxtcodpregunta == null ? null : new Long(itxtcodpregunta.replace(".", "")));
                if (spregpadre != null) {
                    pregunta.setPreguntaPadre(spregpadre == null ? null : new Long(spregpadre));
                } else {
                    pregunta.setPreguntaPadre(null);
                }
                pregunta.setPreguntaPK(pregpk);
                pregunta.setEncuesta(encuestaFacade.find(new Long(smencuesta.getValue().toString())));
                preguntaFacade.create(pregunta);
            } else {
                preguntaFacade.edit(pregunta);
            }
            JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Guardar Pregunta");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            pregunta = new Pregunta();
        }
         RequestContext.getCurrentInstance().update("fpregunta:dataPregunta");
            RequestContext.getCurrentInstance().execute("mantWidget.hide();");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON EDITAR">  
    @Override
    public void editarButton_processAction(ActionEvent ae) {

        this.setPaginaMant("/pages/evaluacion/formulario/preguntaModal");
        super.editarButton_processAction(ae);
        pregunta = selectedPregunta;
        spregpadre = (pregunta.getPreguntaPadre() == null ? null : pregunta.getPreguntaPadre().toString());
        itxtcodpregunta = pregunta.getPreguntaPK().getCodigoPregunta().toString();
        itxtdescripcion = pregunta.getDescripcion();
        itxtreferencia = pregunta.getReferencia();
        stipopregunta = pregunta.getTipo().toString();
        peso = (pregunta.getPeso() == null ? null : pregunta.getPeso().intValue());
        RequestContext.getCurrentInstance().update("formMant:panelMant");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON CANCELAR">  
    @Override
    public void cancelarButton_processAction(ActionEvent ae) {
        selectedPregunta = new Pregunta();
        pregunta = new Pregunta();
        spregpadre = null;
        itxtcodpregunta = null;
        itxtdescripcion = null;
        itxtreferencia = null;
        stipopregunta = "1";
        peso = 5;
        reset();
        this.setPaginaMant("/WEB-INF/templates/includes/sinContenido");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON ELIMINAR">  
    @Override
    public void eliminarButton_processAction(ActionEvent ae, String lsmensaje) {
        String ls_mensaje = null;
        ls_mensaje = preguntaFacade.removeSafePregunta(selectedPregunta);
        if (ls_mensaje == null) {
            ls_mensaje = "Pregunta Borrada";
        }
        getListdetEncuesta();
        super.eliminarButton_processAction(ae, ls_mensaje);
    }

    // </editor-fold> 
}
