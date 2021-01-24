/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.publicacionDGA.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.publicacionDGA.entities.GeacPublicacionDga;
import ec.edu.uasb.publicacionDGA.entities.Persona;
import ec.edu.uasb.publicacionDGA.entities.PrinCiudad;
import ec.edu.uasb.publicacionDGA.entities.PrinCiudadPK;
import ec.edu.uasb.publicacionDGA.entities.PrinPais;
import ec.edu.uasb.publicacionDGA.session.GeacPublicacionDgaFacadeLocal;
import ec.edu.uasb.publicacionDGA.session.PrinCiudadFacadeLocal;
import ec.edu.uasb.publicacionDGA.session.PrinPaisFacadeLocal;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.sisevaluacion.entities.Profesor;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.context.RequestContext;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "publicacionDGA")
@ViewScoped
public class publicacionDGAJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private String codProfesor = null;
    private String smtipdocente = null;
    private String smtipcontrato = null;
    private String smtippublicante = null;
    private HtmlInputText smobservacion = new HtmlInputText();
    private HtmlInputText smevidencia = new HtmlInputText();
    private String msgtablavacia = "No existen datos Registrados";
    private List<String[]> listtipDedicacion = new ArrayList<String[]>();
    private List<Persona> lprofesor = new ArrayList<Persona>();
    private List<Persona> lestudiante = new ArrayList<Persona>();

    private List<GeacPublicacionDga> listpublicacion = new ArrayList<GeacPublicacionDga>();
    private List<GeacPublicacionDga> listpublicacionfilter;
    private List<PrinCiudad> listCiudadtit = new ArrayList<PrinCiudad>();
    //Guardado publicaciones
    GeacPublicacionDga publicacionprofesor = new GeacPublicacionDga();
    private Integer indiceTab = 0;
    private HtmlSelectOneMenu smtippublicacion = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smtipparticipacion = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smpaispub = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smciudadpub = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smformpub = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smidiomapub = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smausppub = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smrol = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smalcact = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smduracion = new HtmlSelectOneMenu();
    private HtmlInputText itxtdetotroausp = new HtmlInputText();
    private boolean bhabdetotausp = false;
    private HtmlInputText itxtdbapellautor = new HtmlInputText();
    private HtmlInputText itxtdbnombreautor = new HtmlInputText();
    private boolean bhabdbdatautor = false;
    private HtmlInputText itxtdbapellcoautor = new HtmlInputText();
    private HtmlInputText itxtdbnombrecoautor = new HtmlInputText();
    private InputTextarea itxtdbtitulo = new InputTextarea();
    private HtmlInputText itxtdbsubtitulo = new HtmlInputText();
    private HtmlInputText itxtdbtitulocapi = new HtmlInputText();
    private HtmlInputText itxtdbnomrev = new HtmlInputText();
    private HtmlInputText itxtdbdesedicion = new HtmlInputText();
    private HtmlInputText itxtdbnomeve = new HtmlInputText();
    private boolean bhabdbtitcap = false;
    private HtmlInputText itxtdbedicion = new HtmlInputText();
    private HtmlInputText itxtdbeditorial = new HtmlInputText();
    private String sanioedicion;
    private String saniovisualizacion;
    private Date dbfecedicion;
    private HtmlSelectOneMenu smdbestado = new HtmlSelectOneMenu();
    private HtmlInputText itxtdbnumpag = new HtmlInputText();
    private HtmlInputText itxtdbranpag = new HtmlInputText();
    private HtmlInputText itxtdbtitrevista = new HtmlInputText();
    private boolean bhabdbtitrev = false;
    private HtmlInputText itxtdbvolumen = new HtmlInputText();
    private HtmlInputText itxtdbnumcod = new HtmlInputText();
    private String etcod = "N/A:";
    private HtmlInputText itxtdburl = new HtmlInputText();
    private HtmlInputText itxtdbdetalletipopub = new HtmlInputText();
    private boolean bhabdburl = true;
    private HtmlSelectOneMenu smdbrevpar = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smdbbaserev = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smdbedicion = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smdbtipvarios = new HtmlSelectOneMenu();
    private HtmlInputText itxtdnombase = new HtmlInputText();
    private boolean bhabdnombase = true;
    private LinkedHashMap<String, String> listtippart = new LinkedHashMap<String, String>();
    private LinkedHashMap<String, String> listrol = new LinkedHashMap<String, String>();
    private LinkedHashMap<String, String> listrolanfi = new LinkedHashMap<String, String>();
    private String sompartserie = "0";
    private String somverdetbiblio = "0";
    private HtmlInputText itxtdtitserie = new HtmlInputText();
    private HtmlInputText itxtbaseindex = new HtmlInputText();
    private HtmlInputText itxtdtit = new HtmlInputText();
    private HtmlInputText itxtdbentidadsede = new HtmlInputText();
    private HtmlInputText itxtentidadsede = new HtmlInputText();
    private HtmlSelectOneMenu smevento = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smmeddif = new HtmlSelectOneMenu();
    private String stettitulo = "Título: ";
    private List<PrinPais> listPaistit = new ArrayList<PrinPais>();
    private String labelTipoPub = "Tipo*";
    private String labelAnioPub = "Año*";
    private String labelTitulo = "Título*";
    private String labelPagina = "Número de Páginas*";
    private String labelFecha = "Fecha de Edición*";
    private String labelTitApell = " Apellido*";
    private String labelTitNombre = "Nombre*";
    private String labelRangPag = "Rango Página(-)*";
    private String smnumrevista = null;
    private String smperiodicidad = null;
    private boolean bfecedicion = false;
    private boolean btitcapitulo = false;
    private boolean beditorial = false;
    private boolean bpagina = false;
    private boolean brangpagina = false;
    private boolean bcodigo = false;
    private boolean bauspicio = false;
    private boolean bpublicacion = false;
    private boolean bserie = false;
    private boolean bpares = false;
    private boolean bedicion = false;
    private boolean bnedicion = false;
    private boolean btipo = false;
    private boolean bnombrerev = false;
    private boolean banioedicion = false;
    private boolean bparesrev = false;
    private boolean bbaseindex = false;
    private boolean bnombeve = false;
    private boolean bduracion = false;
    private boolean isRevista = false;
    private boolean bDetalleTipPub = false;
    private boolean bAlcanceAnio = false;
    private boolean bAnioVisualizacion = false;
    private boolean bPais = false;
    private boolean bCiudad = false;
    private boolean brolanfitrion = false;
    private boolean bvolrevista = false;
    private boolean bnumrevista = false;
    private boolean bperiodicidad = false;
    private boolean bingdatbiblio = false;
    private String smrolanfitrion = null;
    private Usuario usr = new Usuario();

    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private GeacPublicacionDgaFacadeLocal publicacionFacade;
    @EJB
    private PrinPaisFacadeLocal paisFacade;
    @EJB
    private PrinCiudadFacadeLocal ciudadFacade;
    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">

    public String getCodProfesor() {
        return codProfesor;
    }

    public void setCodProfesor(String codProfesor) {
        this.codProfesor = codProfesor;
    }

    public List<GeacPublicacionDga> getListpublicacionfilter() {
        return listpublicacionfilter;
    }

    public void setListpublicacionfilter(List<GeacPublicacionDga> listpublicacionfilter) {
        this.listpublicacionfilter = listpublicacionfilter;
    }

    public boolean isBingdatbiblio() {
        return bingdatbiblio;
    }

    public void setBingdatbiblio(boolean bingdatbiblio) {
        this.bingdatbiblio = bingdatbiblio;
    }

    public HtmlInputText getSmobservacion() {
        return smobservacion;
    }

    public void setSmobservacion(HtmlInputText smobservacion) {
        this.smobservacion = smobservacion;
    }

    public HtmlInputText getSmevidencia() {
        return smevidencia;
    }

    public void setSmevidencia(HtmlInputText smevidencia) {
        this.smevidencia = smevidencia;
    }

    public List<Persona> getLestudiante() {
        return lestudiante;
    }

    public void setLestudiante(List<Persona> lestudiante) {
        this.lestudiante = lestudiante;
    }

    public String getSmtippublicante() {
        return smtippublicante;
    }

    public void setSmtippublicante(String smtippublicante) {
        this.smtippublicante = smtippublicante;
    }

    public List<PrinCiudad> getListCiudadtit() {
        return listCiudadtit;
    }

    public void setListCiudadtit(List<PrinCiudad> listCiudadtit) {
        this.listCiudadtit = listCiudadtit;
    }

    public List<PrinPais> getListPaistit() {
        listPaistit = paisFacade.findAllOrdenado();
        return listPaistit;
    }

    public void setListPaistit(List<PrinPais> listPaistit) {
        this.listPaistit = listPaistit;
    }

    public String getSmrolanfitrion() {
        return smrolanfitrion;
    }

    public void setSmrolanfitrion(String smrolanfitrion) {
        this.smrolanfitrion = smrolanfitrion;
    }

    public String getMsgtablavacia() {
        return msgtablavacia;
    }

    public void setMsgtablavacia(String msgtablavacia) {
        this.msgtablavacia = msgtablavacia;
    }

    public String getSmtipdocente() {
        return smtipdocente;
    }

    public void setSmtipdocente(String smtipdocente) {
        this.smtipdocente = smtipdocente;
    }

    public String getSmtipcontrato() {
        return smtipcontrato;
    }

    public void setSmtipcontrato(String smtipcontrato) {
        this.smtipcontrato = smtipcontrato;
    }

    public List<String[]> getListtipDedicacion() {
        return listtipDedicacion;
    }

    public void setListtipDedicacion(List<String[]> listtipDedicacion) {
        this.listtipDedicacion = listtipDedicacion;
    }

    public List<GeacPublicacionDga> getListpublicacion() {
        return listpublicacion;
    }

    public void setListpublicacion(List<GeacPublicacionDga> listpublicacion) {
        this.listpublicacion = listpublicacion;
    }

    public List<Persona> getLprofesor() {
        return lprofesor;
    }

    public void setLprofesor(List<Persona> lprofesor) {
        this.lprofesor = lprofesor;
    }

    public Integer getIndiceTab() {
        return indiceTab;
    }

    public void setIndiceTab(Integer indiceTab) {
        this.indiceTab = indiceTab;
    }

    public HtmlSelectOneMenu getSmtippublicacion() {
        return smtippublicacion;
    }

    public void setSmtippublicacion(HtmlSelectOneMenu smtippublicacion) {
        this.smtippublicacion = smtippublicacion;
    }

    public HtmlSelectOneMenu getSmtipparticipacion() {
        return smtipparticipacion;
    }

    public void setSmtipparticipacion(HtmlSelectOneMenu smtipparticipacion) {
        this.smtipparticipacion = smtipparticipacion;
    }

    public HtmlSelectOneMenu getSmpaispub() {
        return smpaispub;
    }

    public void setSmpaispub(HtmlSelectOneMenu smpaispub) {
        this.smpaispub = smpaispub;
    }

    public HtmlSelectOneMenu getSmciudadpub() {
        return smciudadpub;
    }

    public void setSmciudadpub(HtmlSelectOneMenu smciudadpub) {
        this.smciudadpub = smciudadpub;
    }

    public HtmlSelectOneMenu getSmformpub() {
        return smformpub;
    }

    public void setSmformpub(HtmlSelectOneMenu smformpub) {
        this.smformpub = smformpub;
    }

    public HtmlSelectOneMenu getSmidiomapub() {
        return smidiomapub;
    }

    public void setSmidiomapub(HtmlSelectOneMenu smidiomapub) {
        this.smidiomapub = smidiomapub;
    }

    public HtmlSelectOneMenu getSmausppub() {
        return smausppub;
    }

    public void setSmausppub(HtmlSelectOneMenu smausppub) {
        this.smausppub = smausppub;
    }

    public HtmlSelectOneMenu getSmrol() {
        return smrol;
    }

    public void setSmrol(HtmlSelectOneMenu smrol) {
        this.smrol = smrol;
    }

    public HtmlSelectOneMenu getSmalcact() {
        return smalcact;
    }

    public void setSmalcact(HtmlSelectOneMenu smalcact) {
        this.smalcact = smalcact;
    }

    public HtmlSelectOneMenu getSmduracion() {
        return smduracion;
    }

    public void setSmduracion(HtmlSelectOneMenu smduracion) {
        this.smduracion = smduracion;
    }

    public HtmlInputText getItxtdetotroausp() {
        return itxtdetotroausp;
    }

    public void setItxtdetotroausp(HtmlInputText itxtdetotroausp) {
        this.itxtdetotroausp = itxtdetotroausp;
    }

    public boolean isBhabdetotausp() {
        return bhabdetotausp;
    }

    public void setBhabdetotausp(boolean bhabdetotausp) {
        this.bhabdetotausp = bhabdetotausp;
    }

    public HtmlInputText getItxtdbapellautor() {
        return itxtdbapellautor;
    }

    public void setItxtdbapellautor(HtmlInputText itxtdbapellautor) {
        this.itxtdbapellautor = itxtdbapellautor;
    }

    public HtmlInputText getItxtdbnombreautor() {
        return itxtdbnombreautor;
    }

    public void setItxtdbnombreautor(HtmlInputText itxtdbnombreautor) {
        this.itxtdbnombreautor = itxtdbnombreautor;
    }

    public boolean isBhabdbdatautor() {
        return bhabdbdatautor;
    }

    public void setBhabdbdatautor(boolean bhabdbdatautor) {
        this.bhabdbdatautor = bhabdbdatautor;
    }

    public HtmlInputText getItxtdbapellcoautor() {
        return itxtdbapellcoautor;
    }

    public void setItxtdbapellcoautor(HtmlInputText itxtdbapellcoautor) {
        this.itxtdbapellcoautor = itxtdbapellcoautor;
    }

    public HtmlInputText getItxtdbnombrecoautor() {
        return itxtdbnombrecoautor;
    }

    public void setItxtdbnombrecoautor(HtmlInputText itxtdbnombrecoautor) {
        this.itxtdbnombrecoautor = itxtdbnombrecoautor;
    }

    public InputTextarea getItxtdbtitulo() {
        return itxtdbtitulo;
    }

    public void setItxtdbtitulo(InputTextarea itxtdbtitulo) {
        this.itxtdbtitulo = itxtdbtitulo;
    }

    public HtmlInputText getItxtdbsubtitulo() {
        return itxtdbsubtitulo;
    }

    public void setItxtdbsubtitulo(HtmlInputText itxtdbsubtitulo) {
        this.itxtdbsubtitulo = itxtdbsubtitulo;
    }

    public HtmlInputText getItxtdbtitulocapi() {
        return itxtdbtitulocapi;
    }

    public void setItxtdbtitulocapi(HtmlInputText itxtdbtitulocapi) {
        this.itxtdbtitulocapi = itxtdbtitulocapi;
    }

    public HtmlInputText getItxtdbnomrev() {
        return itxtdbnomrev;
    }

    public void setItxtdbnomrev(HtmlInputText itxtdbnomrev) {
        this.itxtdbnomrev = itxtdbnomrev;
    }

    public HtmlInputText getItxtdbdesedicion() {
        return itxtdbdesedicion;
    }

    public void setItxtdbdesedicion(HtmlInputText itxtdbdesedicion) {
        this.itxtdbdesedicion = itxtdbdesedicion;
    }

    public HtmlInputText getItxtdbnomeve() {
        return itxtdbnomeve;
    }

    public void setItxtdbnomeve(HtmlInputText itxtdbnomeve) {
        this.itxtdbnomeve = itxtdbnomeve;
    }

    public boolean isBhabdbtitcap() {
        return bhabdbtitcap;
    }

    public void setBhabdbtitcap(boolean bhabdbtitcap) {
        this.bhabdbtitcap = bhabdbtitcap;
    }

    public HtmlInputText getItxtdbedicion() {
        return itxtdbedicion;
    }

    public void setItxtdbedicion(HtmlInputText itxtdbedicion) {
        this.itxtdbedicion = itxtdbedicion;
    }

    public HtmlInputText getItxtdbeditorial() {
        return itxtdbeditorial;
    }

    public void setItxtdbeditorial(HtmlInputText itxtdbeditorial) {
        this.itxtdbeditorial = itxtdbeditorial;
    }

    public String getSanioedicion() {
        return sanioedicion;
    }

    public void setSanioedicion(String sanioedicion) {
        this.sanioedicion = sanioedicion;
    }

    public String getSaniovisualizacion() {
        return saniovisualizacion;
    }

    public void setSaniovisualizacion(String saniovisualizacion) {
        this.saniovisualizacion = saniovisualizacion;
    }

    public Date getDbfecedicion() {
        return dbfecedicion;
    }

    public void setDbfecedicion(Date dbfecedicion) {
        this.dbfecedicion = dbfecedicion;
    }

    public HtmlSelectOneMenu getSmdbestado() {
        return smdbestado;
    }

    public void setSmdbestado(HtmlSelectOneMenu smdbestado) {
        this.smdbestado = smdbestado;
    }

    public HtmlInputText getItxtdbnumpag() {
        return itxtdbnumpag;
    }

    public void setItxtdbnumpag(HtmlInputText itxtdbnumpag) {
        this.itxtdbnumpag = itxtdbnumpag;
    }

    public HtmlInputText getItxtdbranpag() {
        return itxtdbranpag;
    }

    public void setItxtdbranpag(HtmlInputText itxtdbranpag) {
        this.itxtdbranpag = itxtdbranpag;
    }

    public HtmlInputText getItxtdbtitrevista() {
        return itxtdbtitrevista;
    }

    public void setItxtdbtitrevista(HtmlInputText itxtdbtitrevista) {
        this.itxtdbtitrevista = itxtdbtitrevista;
    }

    public boolean isBhabdbtitrev() {
        return bhabdbtitrev;
    }

    public void setBhabdbtitrev(boolean bhabdbtitrev) {
        this.bhabdbtitrev = bhabdbtitrev;
    }

    public HtmlInputText getItxtdbvolumen() {
        return itxtdbvolumen;
    }

    public void setItxtdbvolumen(HtmlInputText itxtdbvolumen) {
        this.itxtdbvolumen = itxtdbvolumen;
    }

    public HtmlInputText getItxtdbnumcod() {
        return itxtdbnumcod;
    }

    public void setItxtdbnumcod(HtmlInputText itxtdbnumcod) {
        this.itxtdbnumcod = itxtdbnumcod;
    }

    public String getEtcod() {
        return etcod;
    }

    public void setEtcod(String etcod) {
        this.etcod = etcod;
    }

    public HtmlInputText getItxtdburl() {
        return itxtdburl;
    }

    public void setItxtdburl(HtmlInputText itxtdburl) {
        this.itxtdburl = itxtdburl;
    }

    public HtmlInputText getItxtdbdetalletipopub() {
        return itxtdbdetalletipopub;
    }

    public void setItxtdbdetalletipopub(HtmlInputText itxtdbdetalletipopub) {
        this.itxtdbdetalletipopub = itxtdbdetalletipopub;
    }

    public boolean isBhabdburl() {
        return bhabdburl;
    }

    public void setBhabdburl(boolean bhabdburl) {
        this.bhabdburl = bhabdburl;
    }

    public HtmlSelectOneMenu getSmdbrevpar() {
        return smdbrevpar;
    }

    public void setSmdbrevpar(HtmlSelectOneMenu smdbrevpar) {
        this.smdbrevpar = smdbrevpar;
    }

    public HtmlSelectOneMenu getSmdbbaserev() {
        return smdbbaserev;
    }

    public void setSmdbbaserev(HtmlSelectOneMenu smdbbaserev) {
        this.smdbbaserev = smdbbaserev;
    }

    public HtmlSelectOneMenu getSmdbedicion() {
        return smdbedicion;
    }

    public void setSmdbedicion(HtmlSelectOneMenu smdbedicion) {
        this.smdbedicion = smdbedicion;
    }

    public HtmlSelectOneMenu getSmdbtipvarios() {
        return smdbtipvarios;
    }

    public void setSmdbtipvarios(HtmlSelectOneMenu smdbtipvarios) {
        this.smdbtipvarios = smdbtipvarios;
    }

    public HtmlInputText getItxtdnombase() {
        return itxtdnombase;
    }

    public void setItxtdnombase(HtmlInputText itxtdnombase) {
        this.itxtdnombase = itxtdnombase;
    }

    public boolean isBhabdnombase() {
        return bhabdnombase;
    }

    public void setBhabdnombase(boolean bhabdnombase) {
        this.bhabdnombase = bhabdnombase;
    }

    public LinkedHashMap<String, String> getListtippart() {
        return listtippart;
    }

    public void setListtippart(LinkedHashMap<String, String> listtippart) {
        this.listtippart = listtippart;
    }

    public LinkedHashMap<String, String> getListrol() {
        return listrol;
    }

    public void setListrol(LinkedHashMap<String, String> listrol) {
        this.listrol = listrol;
    }

    public LinkedHashMap<String, String> getListrolanfi() {
        return listrolanfi;
    }

    public void setListrolanfi(LinkedHashMap<String, String> listrolanfi) {
        this.listrolanfi = listrolanfi;
    }

    public String getSompartserie() {
        return sompartserie;
    }

    public void setSompartserie(String sompartserie) {
        this.sompartserie = sompartserie;
    }

    public String getSomverdetbiblio() {
        return somverdetbiblio;
    }

    public void setSomverdetbiblio(String somverdetbiblio) {
        this.somverdetbiblio = somverdetbiblio;
    }

    public HtmlInputText getItxtdtitserie() {
        return itxtdtitserie;
    }

    public void setItxtdtitserie(HtmlInputText itxtdtitserie) {
        this.itxtdtitserie = itxtdtitserie;
    }

    public HtmlInputText getItxtbaseindex() {
        return itxtbaseindex;
    }

    public void setItxtbaseindex(HtmlInputText itxtbaseindex) {
        this.itxtbaseindex = itxtbaseindex;
    }

    public HtmlInputText getItxtdtit() {
        return itxtdtit;
    }

    public void setItxtdtit(HtmlInputText itxtdtit) {
        this.itxtdtit = itxtdtit;
    }

    public HtmlInputText getItxtdbentidadsede() {
        return itxtdbentidadsede;
    }

    public void setItxtdbentidadsede(HtmlInputText itxtdbentidadsede) {
        this.itxtdbentidadsede = itxtdbentidadsede;
    }

    public HtmlInputText getItxtentidadsede() {
        return itxtentidadsede;
    }

    public void setItxtentidadsede(HtmlInputText itxtentidadsede) {
        this.itxtentidadsede = itxtentidadsede;
    }

    public HtmlSelectOneMenu getSmevento() {
        return smevento;
    }

    public void setSmevento(HtmlSelectOneMenu smevento) {
        this.smevento = smevento;
    }

    public HtmlSelectOneMenu getSmmeddif() {
        return smmeddif;
    }

    public void setSmmeddif(HtmlSelectOneMenu smmeddif) {
        this.smmeddif = smmeddif;
    }

    public String getStettitulo() {
        return stettitulo;
    }

    public void setStettitulo(String stettitulo) {
        this.stettitulo = stettitulo;
    }

    public String getLabelTipoPub() {
        return labelTipoPub;
    }

    public void setLabelTipoPub(String labelTipoPub) {
        this.labelTipoPub = labelTipoPub;
    }

    public String getLabelAnioPub() {
        return labelAnioPub;
    }

    public void setLabelAnioPub(String labelAnioPub) {
        this.labelAnioPub = labelAnioPub;
    }

    public String getLabelTitulo() {
        return labelTitulo;
    }

    public void setLabelTitulo(String labelTitulo) {
        this.labelTitulo = labelTitulo;
    }

    public String getLabelPagina() {
        return labelPagina;
    }

    public void setLabelPagina(String labelPagina) {
        this.labelPagina = labelPagina;
    }

    public String getLabelFecha() {
        return labelFecha;
    }

    public void setLabelFecha(String labelFecha) {
        this.labelFecha = labelFecha;
    }

    public String getLabelTitApell() {
        return labelTitApell;
    }

    public void setLabelTitApell(String labelTitApell) {
        this.labelTitApell = labelTitApell;
    }

    public String getLabelTitNombre() {
        return labelTitNombre;
    }

    public void setLabelTitNombre(String labelTitNombre) {
        this.labelTitNombre = labelTitNombre;
    }

    public String getLabelRangPag() {
        return labelRangPag;
    }

    public void setLabelRangPag(String labelRangPag) {
        this.labelRangPag = labelRangPag;
    }

    public String getSmnumrevista() {
        return smnumrevista;
    }

    public void setSmnumrevista(String smnumrevista) {
        this.smnumrevista = smnumrevista;
    }

    public String getSmperiodicidad() {
        return smperiodicidad;
    }

    public void setSmperiodicidad(String smperiodicidad) {
        this.smperiodicidad = smperiodicidad;
    }

    public boolean isBfecedicion() {
        return bfecedicion;
    }

    public void setBfecedicion(boolean bfecedicion) {
        this.bfecedicion = bfecedicion;
    }

    public boolean isBtitcapitulo() {
        return btitcapitulo;
    }

    public void setBtitcapitulo(boolean btitcapitulo) {
        this.btitcapitulo = btitcapitulo;
    }

    public boolean isBeditorial() {
        return beditorial;
    }

    public void setBeditorial(boolean beditorial) {
        this.beditorial = beditorial;
    }

    public boolean isBpagina() {
        return bpagina;
    }

    public void setBpagina(boolean bpagina) {
        this.bpagina = bpagina;
    }

    public boolean isBrangpagina() {
        return brangpagina;
    }

    public void setBrangpagina(boolean brangpagina) {
        this.brangpagina = brangpagina;
    }

    public boolean isBcodigo() {
        return bcodigo;
    }

    public void setBcodigo(boolean bcodigo) {
        this.bcodigo = bcodigo;
    }

    public boolean isBauspicio() {
        return bauspicio;
    }

    public void setBauspicio(boolean bauspicio) {
        this.bauspicio = bauspicio;
    }

    public boolean isBpublicacion() {
        return bpublicacion;
    }

    public void setBpublicacion(boolean bpublicacion) {
        this.bpublicacion = bpublicacion;
    }

    public boolean isBserie() {
        return bserie;
    }

    public void setBserie(boolean bserie) {
        this.bserie = bserie;
    }

    public boolean isBpares() {
        return bpares;
    }

    public void setBpares(boolean bpares) {
        this.bpares = bpares;
    }

    public boolean isBedicion() {
        return bedicion;
    }

    public void setBedicion(boolean bedicion) {
        this.bedicion = bedicion;
    }

    public boolean isBnedicion() {
        return bnedicion;
    }

    public void setBnedicion(boolean bnedicion) {
        this.bnedicion = bnedicion;
    }

    public boolean isBtipo() {
        return btipo;
    }

    public void setBtipo(boolean btipo) {
        this.btipo = btipo;
    }

    public boolean isBnombrerev() {
        return bnombrerev;
    }

    public void setBnombrerev(boolean bnombrerev) {
        this.bnombrerev = bnombrerev;
    }

    public boolean isBanioedicion() {
        return banioedicion;
    }

    public void setBanioedicion(boolean banioedicion) {
        this.banioedicion = banioedicion;
    }

    public boolean isBparesrev() {
        return bparesrev;
    }

    public void setBparesrev(boolean bparesrev) {
        this.bparesrev = bparesrev;
    }

    public boolean isBbaseindex() {
        return bbaseindex;
    }

    public void setBbaseindex(boolean bbaseindex) {
        this.bbaseindex = bbaseindex;
    }

    public boolean isBnombeve() {
        return bnombeve;
    }

    public void setBnombeve(boolean bnombeve) {
        this.bnombeve = bnombeve;
    }

    public boolean isBduracion() {
        return bduracion;
    }

    public void setBduracion(boolean bduracion) {
        this.bduracion = bduracion;
    }

    public boolean isIsRevista() {
        return isRevista;
    }

    public void setIsRevista(boolean isRevista) {
        this.isRevista = isRevista;
    }

    public boolean isbDetalleTipPub() {
        return bDetalleTipPub;
    }

    public void setbDetalleTipPub(boolean bDetalleTipPub) {
        this.bDetalleTipPub = bDetalleTipPub;
    }

    public boolean isbAlcanceAnio() {
        return bAlcanceAnio;
    }

    public void setbAlcanceAnio(boolean bAlcanceAnio) {
        this.bAlcanceAnio = bAlcanceAnio;
    }

    public boolean isbAnioVisualizacion() {
        return bAnioVisualizacion;
    }

    public void setbAnioVisualizacion(boolean bAnioVisualizacion) {
        this.bAnioVisualizacion = bAnioVisualizacion;
    }

    public boolean isbPais() {
        return bPais;
    }

    public void setbPais(boolean bPais) {
        this.bPais = bPais;
    }

    public boolean isbCiudad() {
        return bCiudad;
    }

    public void setbCiudad(boolean bCiudad) {
        this.bCiudad = bCiudad;
    }

    public boolean isBrolanfitrion() {
        return brolanfitrion;
    }

    public void setBrolanfitrion(boolean brolanfitrion) {
        this.brolanfitrion = brolanfitrion;
    }

    public boolean isBvolrevista() {
        return bvolrevista;
    }

    public void setBvolrevista(boolean bvolrevista) {
        this.bvolrevista = bvolrevista;
    }

    public boolean isBnumrevista() {
        return bnumrevista;
    }

    public void setBnumrevista(boolean bnumrevista) {
        this.bnumrevista = bnumrevista;
    }

    public boolean isBperiodicidad() {
        return bperiodicidad;
    }

    public void setBperiodicidad(boolean bperiodicidad) {
        this.bperiodicidad = bperiodicidad;
    }

    public GeacPublicacionDgaFacadeLocal getPublicacionFacade() {
        return publicacionFacade;
    }

    public void setPublicacionFacade(GeacPublicacionDgaFacadeLocal publicacionFacade) {
        this.publicacionFacade = publicacionFacade;
    }

    // </editor-fold> 
    /**
     * Creates a new instance of publicacionDGA1JSFManagedBean
     */
    public publicacionDGAJSFManagedBean() {
        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        this.setPaginaMant("/pages/publicacionesDGA/segPublicacionesGen");
    }

    @Override
    public void init() {
        CancelPub();
        recuperaPublicacion();
        indiceTab = 0;
    }

    public void tipPersonavalueChangeListener() {
        lprofesor.clear();
        lestudiante.clear();
        codProfesor = null;
        smtipcontrato = null;
        smtipdocente = null;
        if (smtippublicante != null) {
            if (smtippublicante.equalsIgnoreCase("E")) {
                lestudiante = consultaFacade.findEstudiante();
            }
            if (smtippublicante.equalsIgnoreCase("D")) {
                lprofesor = consultaFacade.findProfesor();
            }
        }
    }

    public void tipContratovalueChangeListener() {
        lprofesor.clear();
        smtipdocente = null;
        if (smtipcontrato != null) {
            cargDedicacion();
        }
    }

    public void cargDedicacion() {
        listtipDedicacion.clear();
        listtipDedicacion = consultaFacade.cargDedicacion(smtipcontrato);

    }

    public void tipDedicacionvalueChangeListener() {
        lprofesor.clear();
        if (smtipdocente != null && smtipcontrato != null) {
            lprofesor = consultaFacade.findProfesorbyTipCont(smtipcontrato, smtipdocente);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="VALUECHANGELISTENER"> 
    public void paisPublicvalueChangeListener() {
        if (smpaispub.getValue() != null) {
            listCiudadtit = ciudadFacade.finbyPais(new PrinPais(Short.valueOf(smpaispub.getValue().toString())));
        }
    }

    public void auspPublicvalueChangeListener() {
        if (smausppub.getValue() != null) {
            if (smausppub.getValue().toString().equalsIgnoreCase("O")) {
                bhabdetotausp = true;
            } else {
                bhabdetotausp = false;
                itxtdetotroausp.setValue(null);
            }
        } else {
            bhabdetotausp = false;
            itxtdetotroausp.setValue(null);
        }
    }

    public void baseIndexacionValueChangeListener() {
        if (smdbtipvarios.getValue() != null) {
            if (smdbtipvarios.getValue().toString().equalsIgnoreCase("3")) {
                bbaseindex = true;
            } else {
                bbaseindex = false;
                itxtbaseindex.setValue(null);
            }
        } else {
            bbaseindex = false;
            itxtbaseindex.setValue(null);
        }
    }

    public void tipPublicvalueChangeListener() {
        listtippart.clear();
        listrol.clear();
        listrolanfi.clear();
        smtipparticipacion.setValue(null);
        itxtdbapellautor.setValue(null);
        itxtdbnombreautor.setValue(null);
        smausppub.setValue(null);
        itxtdetotroausp.setValue(null);
        smnumrevista = null;
        smperiodicidad = null;
        labelTitApell = "Apellido*";
        labelTitNombre = "Nombre*";
        bhabdbdatautor = false;
        bhabdbtitcap = true;
        bhabdbtitrev = true;
        etcod = "N° ISBN:";
        /*INICIALIZACION DE ETIQUETAS*/
        btitcapitulo = false;
        beditorial = false;
        bpagina = false;
        bcodigo = false;
        bauspicio = false;
        bhabdetotausp = false;
        bpublicacion = false;
        bserie = false;
        bpares = false;
        bedicion = false;
        bnedicion = false;
        btipo = false;
        bnombrerev = false;
        banioedicion = false;
        bparesrev = false;
        bnombeve = false;
        bduracion = false;
        isRevista = false;
        bbaseindex = false;
        bAlcanceAnio = false;
        bAnioVisualizacion = false;
        bPais = false;
        bCiudad = false;
        bfecedicion = false;
        brangpagina = false;
        brolanfitrion = false;
        bvolrevista = false;
        bnumrevista = false;
        bperiodicidad = false;
        bingdatbiblio = false;
//        if (smtippublicacion.getValue()!=null){
//            bingdatbiblio = true;
//        }

        if (smtippublicacion.getValue() != null) {
            bingdatbiblio = true;
            if (smtippublicacion.getValue().toString().equalsIgnoreCase("1")) {
                bhabdbdatautor = true;
            }
            if (smtippublicacion.getValue().toString().equalsIgnoreCase("4") || smtippublicacion.getValue().toString().equalsIgnoreCase("5") || smtippublicacion.getValue().toString().equalsIgnoreCase("6")) {
                bhabdbtitcap = false;
            }
            if (smtippublicacion.getValue().toString().equalsIgnoreCase("5") || smtippublicacion.getValue().toString().equalsIgnoreCase("6")) {
                bhabdbtitrev = false;
            }
            if (!smtippublicacion.getValue().toString().equalsIgnoreCase("5") && !smtippublicacion.getValue().toString().equalsIgnoreCase("6")) {
                etcod = "N° ISBN:";
            } else {
                etcod = "N° ISSN:";
            }

            /*if (smtippublicacion.getValue().toString().equalsIgnoreCase("2")) {
             listtippart.put("Editor", "E");
             listtippart.put("Coordinador", "D");
             listtippart.put("Traductor", "T");
             listtippart.put("Compilador", "M");
             } else {
             if (smtippublicacion.getValue().toString().equalsIgnoreCase("11")) {
             listtippart.put("Autor", "A");
             } else {
             if (smtippublicacion.getValue().toString().equalsIgnoreCase("4")) {
             listtippart.put("Autor Capítulo", "A");
             listtippart.put("Coautor Capítulo", "C");

             } else {
             listtippart.put("Autor", "A");
             listtippart.put("Coautor", "C");
             }
             }

             }*/
            //Cargo las etiquetas para los titulos
            switch (Integer.parseInt(smtippublicacion.getValue().toString())) {
                case 1:
                case 2:
                case 3:
                    stettitulo = "Título ";
                    break;
                case 4:
                    stettitulo = "Título del Libro";
                    break;
                case 5:
                case 6:
                    stettitulo = "Título Revista ";
                    break;
                case 11:
                    stettitulo = "Título Ponencia ";
                    break;
                case 13:
                    labelTipoPub = "Tipo de Libro*";
                    labelAnioPub = "Año de Públicación*";
                    labelTitulo = "Título*";
                    labelPagina = "Número de Páginas*";
                    labelFecha = "Fecha de Edición*";
                    labelTitApell = "Apellido Anfitrión*";
                    labelTitNombre = "Nombre Anfitrión*";
                    etcod = "Número ISBN";
                    bPais = true;
                    bCiudad = true;
                    beditorial = true;
                    bpagina = true;
                    bcodigo = true;
                    bauspicio = true;
                    bpublicacion = true;
                    bserie = true;
                    bedicion = true;
                    bnedicion = true;
                    brolanfitrion = false;
                    btitcapitulo = false;
                    bAlcanceAnio = true;
                    bpares = true;
                    //Lista de tipo de libro
                    listtippart.put("Individual", "I");
                    /*listtippart.put("Coautoria", "C");*/
                    listtippart.put("Capítulo en Libro Colectivo", "P");
                    //Lista de rol
                    listrol.put("Autor", "1");
                    listrol.put("Coautor", "2");
                    listrol.put("Editor", "3");
                    listrol.put("Compilador", "4");
                    listrol.put("Revisor", "5");
                    listrol.put("Coordinador de Informe Periódico", "6");
                    break;
                case 14:
                    labelTipoPub = "Tipo de Revista*";
                    labelAnioPub = "Año de Publicación*";
                    labelTitulo = "Título del Artículo*";
                    labelPagina = "Número de Páginas (Rango)*";
                    labelFecha = "Fecha de Edición*";
                    labelRangPag = "Total Número Páginas *";
                    etcod = "Número ISSN";
                    beditorial = true;
                    bpagina = true;
                    bcodigo = true;
                    bPais = true;
                    bCiudad = true;
                    brangpagina = true;
                    bvolrevista = true;
                    bpublicacion = true;
                    bnumrevista = true;
                    bperiodicidad = true;
                    bnombrerev = true;
                    bparesrev = true;
                    isRevista = true;
                    bAlcanceAnio = true;
                    //Lista de tipo de revistas
                    /*listtippart.put("Artículo Académico", "A");
                     listtippart.put("Artículo no Académico", "N");
                     listtippart.put("Reseña", "R");*/
                    listtippart.put("Indexada", "I");
                    listtippart.put("No Indexada", "N");
                    //Lista de rol
                    listrol.put("Autor", "1");
                    listrol.put("Coautor", "2");
                    listrol.put("Revisor", "3");
                    listrol.put("Editor", "4");
                    listrol.put("Director", "5");
                    listrol.put("Coordinador Número Monográfico", "6");
                    break;
                case 15:
                    labelTipoPub = "Tipo de Ponencia*";
                    labelAnioPub = "Año de Presentación*";
                    labelTitulo = "Nombre Ponencia*";
                    labelPagina = "Número de Páginas";
                    labelFecha = "Fecha de Actividad*";
                    /* btitcapitulo = false;
                     beditorial = false;*/
                    bpagina = true;
                    bPais = true;
                    bCiudad = true;
                    bAlcanceAnio = true;
                    bfecedicion = true;
                    bnombeve = true;
                    //Lista de tipo de ponencias
                    listtippart.put("Publicada", "P");
                    listtippart.put("No Publicada", "N");
                    //Lista de rol
                    listrol.put("Autor", "1");
                    listrol.put("Moderador", "3");
                    listrol.put("Ponente", "4");
                    listrol.put("Expositor", "5");
                    listrol.put("Conferenciante", "6");
                    break;
                case 16:
                    labelTipoPub = "Tipo de Audiovisual*";
                    labelAnioPub = "Año de Presentación*";
                    labelTitulo = "Título del Producto*";
                    labelFecha = "Fecha de Presentación*";
                    bduracion = true;
                    bfecedicion = true;
                    bPais = true;
                    bCiudad = true;
                    //Lista de tipo de productos audiovisuales
                    listtippart.put("Película", "P");
                    listtippart.put("Documental", "D");
                    listtippart.put("Reportaje", "R");
                    listtippart.put("Libros Fotográficos", "L");
                    listtippart.put("Pieza de Radio", "Z");
                    listtippart.put("Sonoro", "S");
                    listtippart.put("Otro (Especifíque)", "O");
                    //Lista de rol
                    listrol.put("Autor", "1");
                    listrol.put("Coautor", "2");
                    break;
                case 17:
                    labelTipoPub = "Tipo*";
                    labelAnioPub = "Año de Públicación*";
                    labelTitulo = "Título Publicación*";
                    labelFecha = "Fecha de Publicación*";
                    bfecedicion = true;
                    bPais = true;
                    bCiudad = true;
                    listtippart.put("Paper", "P");
                    listtippart.put("Informes", "I");
                    listtippart.put("Otro", "O");
                    listrol.put("Autor", "1");
                    listrol.put("Coautor", "2");
                    listrol.put("Editor", "3");
                    listrol.put("Otro (Especifique)", "4");
                    break;
                case 18:
                    labelTipoPub = "Tipo*";
                    labelAnioPub = "Año de Públicación*";
                    labelTitulo = "Título Libro*";
                    labelFecha = "Fecha de Publicación*";
                    etcod = "Número ISBN";
                    bfecedicion = true;
                    bPais = true;
                    bCiudad = true;
                    bcodigo = true;
                    bauspicio = true;
                    bpares = true;
                    bpublicacion = true;
                    beditorial = true;
                    bvolrevista = true;
                    bpagina = true;
                    listrol.put("Autor", "1");
                    listtippart.put("Tesis", "T");

                    break;
                default:
                    stettitulo = "Título ";
                    break;
            }
        } else {
            bingdatbiblio = false;
        }
    }

    public void formPublicvalueChangeListener() {
        bhabdburl = true;
        if (smformpub.getValue() != null) {
            if (smformpub.getValue().toString().equalsIgnoreCase("D")) {
                bhabdburl = false;
            }
        }
    }

    public void basePublicvalueChangeListener() {
        bhabdnombase = true;
        if (smdbbaserev.getValue() != null) {
            if (smdbbaserev.getValue().toString().equalsIgnoreCase("O")) {
                bhabdnombase = false;
            }
        }
    }

    public void detalleTipoPubValueChangeListener() {
        if (smtippublicacion.getValue() != null && smtipparticipacion.getValue() != null) {
            if (Integer.parseInt(smtippublicacion.getValue().toString()) == 16 && smtipparticipacion.getValue().toString().equalsIgnoreCase("O")) {
                bDetalleTipPub = true;
            } else {
                bDetalleTipPub = false;
                itxtdbdetalletipopub.setValue(null);
            }
        } else {
            bDetalleTipPub = false;
            itxtdbdetalletipopub.setValue(null);
        }
        /*Particularidades de la ficha de Libro Capítulo en libro*/
        if (smtippublicacion.getValue() != null && smtippublicacion.getValue().toString().equalsIgnoreCase("13") && smtipparticipacion.getValue().toString().equalsIgnoreCase("P")) {
            listrol.clear();
            listrolanfi.clear();
            listrol.put("Autor", "1");
            listrolanfi.put("Autor", "Autor");
            listrolanfi.put("Coautor", "Coautor");
            listrolanfi.put("Editor", "Editor");
            listrolanfi.put("Compilador", "Compilador");
            listrolanfi.put("Revisor", "Revisor");
            labelTitulo = "Título Libro*";
            labelPagina = "Total número de Páginas*";
            labelRangPag = "Rango Página (desde-hasta)*";
            btitcapitulo = true;
            bserie = false;
            sompartserie = "0";
            bpublicacion = false;
            brangpagina = true;
            brolanfitrion = true;
        }
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA PUBLICACIONES"> 
    private void recuperaPublicacion() {
        List<GeacPublicacionDga> listpubaux = new ArrayList<GeacPublicacionDga>();
        listpublicacion.clear();
        listpubaux.clear();
        listpubaux = publicacionFacade.findAllOrde();
        if (listpubaux.size() > 0) {
            for (int i = 0; i < listpubaux.size(); i++) {
                GeacPublicacionDga publiaux = new GeacPublicacionDga();
                publiaux = listpubaux.get(i);
                publiaux.setNombrePERSONA(consultaFacade.findNomPersona(publiaux.getCedulaPersona()));
                listpublicacion.add(publiaux);

            }
        }

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">    
    private GeacPublicacionDga selectedPublicacion;

    public GeacPublicacionDga getSelectedPublicacion() {
        return selectedPublicacion;
    }

    public void setSelectedPublicacion(GeacPublicacionDga selectedPublicacion) {
        this.selectedPublicacion = selectedPublicacion;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON CANCELAR">  
    public void CancelPub() {
        publicacionprofesor = new GeacPublicacionDga();
        selectedPublicacion = new GeacPublicacionDga();
        smtipdocente = null;
        smtipcontrato = null;
        codProfesor = null;
        smtippublicante = null;
        lprofesor.clear();
        lestudiante.clear();
        dbfecedicion = null;
        smtippublicacion.setValue(null);
        smtipparticipacion.setValue(null);
        smpaispub.setValue(null);
        smciudadpub.setValue(null);
        smformpub.setValue(null);
        smidiomapub.setValue(null);
        smausppub.setValue(null);
        smrolanfitrion = null;
        itxtdetotroausp.resetValue();
        itxtdbapellautor.setValue(null);
        itxtdbnombreautor.setValue(null);
        itxtdbapellcoautor.setValue(null);
        itxtdbnombrecoautor.setValue(null);
        itxtdbtitulo.setValue(null);
        itxtdbsubtitulo.setValue(null);
        itxtdbtitulocapi.setValue(null);
        itxtdbedicion.setValue(null);
        itxtdbeditorial.setValue(null);
        itxtdbranpag.setValue(null);
        sanioedicion = null;
        smnumrevista = null;
        smdbestado.setValue(null);
        itxtdbnumpag.setValue(null);
        itxtdbtitrevista.setValue(null);
        itxtdbvolumen.setValue(null);
        etcod = "N° ISBN:";
        itxtdburl.setValue(null);
        smevidencia.setValue(null);
        smobservacion.setValue(null);
        smdbrevpar.setValue(null);
        smdbbaserev.setValue(null);
        itxtdnombase.setValue(null);
        sompartserie = null;
        itxtdtitserie.setValue(null);
        itxtdbentidadsede.resetValue();
        smmeddif.setValue(null);
        smevento.setValue(null);
        itxtdbnumcod.setValue(null);
        bhabdetotausp = false;
        bhabdbdatautor = false;
        bhabdbtitcap = false;
        bhabdbtitrev = false;
        bhabdburl = true;
        bhabdnombase = true;
        smrol.setValue(null);
        smdbedicion.setValue(null);
        smdbtipvarios.setValue(null);
        itxtdbnomrev.setValue(null);
        itxtdbdesedicion.setValue(null);
        itxtbaseindex.setValue(null);
        itxtdbnomeve.setValue(null);
        smalcact.setValue(null);
        smduracion.setValue(null);
        itxtdbdetalletipopub.setValue(null);
        btitcapitulo = false;
        beditorial = false;
        bpagina = false;
        bcodigo = false;
        bauspicio = false;
        bpublicacion = false;
        bserie = false;
        bpares = false;
        bnombrerev = false;
        bparesrev = false;
        bbaseindex = false;
        bnombeve = false;
        bduracion = false;
        bAlcanceAnio = false;
        bAnioVisualizacion = false;
        saniovisualizacion = null;
        indiceTab = 0;
        bPais = false;
        bCiudad = false;
        bnedicion = false;
        brangpagina = false;
        brolanfitrion = false;
        bvolrevista = false;
        bingdatbiblio = false;
    }
    // </editor-fold> 

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON NUEVO">  
    @Override
    public void nuevoButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/publicacionesDGA/segPublicacionesGenModal");
        super.nuevoButton_processAction(ae);
        CancelPub();

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON GRABAR"> 
    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        GeacPublicacionDga pubprofaux = new GeacPublicacionDga();
        PrinCiudadPK ciudadPK = new PrinCiudadPK();
        PrinPais pais = new PrinPais();
        if (publicacionprofesor != null) {
            if (publicacionprofesor.getCodigoPublicacion() != null) {
                pubprofaux = publicacionFacade.find(publicacionprofesor.getCodigoPublicacion());
            }
        }
        try {
            //DATOS GENERALES PUBLICACION
            publicacionprofesor.setTipoPublicacion(smtippublicacion.getValue() == null ? null : Long.parseLong(smtippublicacion.getValue().toString()));
            publicacionprofesor.setPubParticipacion(smtipparticipacion.getValue() == null ? null : smtipparticipacion.getValue().toString().charAt(0));
            publicacionprofesor.setPubRol(smrol.getValue() == null ? null : Long.parseLong(smrol.getValue().toString()));
            publicacionprofesor.setCampo10(sanioedicion == null ? null : sanioedicion);
            if (smpaispub.getValue() != null) {
                pais.setPaiCodigo(Short.valueOf(smpaispub.getValue().toString()));
            }
            publicacionprofesor.setPrinPais(smpaispub.getValue() == null ? null : pais);
            if (smciudadpub.getValue() != null) {
                ciudadPK.setCiuCodigo(Integer.valueOf(smciudadpub.getValue().toString()));
                ciudadPK.setPaiCodigo(Short.valueOf(smpaispub.getValue().toString()));
            }
            publicacionprofesor.setPrinCiudad(smciudadpub.getValue() == null ? null : new PrinCiudad(ciudadPK));
            publicacionprofesor.setPubUrl(itxtdburl.getValue() == null ? null : itxtdburl.getValue().toString());
            //DATOS BIBLIOGRAFICOS
            publicacionprofesor.setPubApellidoAutor(itxtdbapellautor.getValue() == null ? null : itxtdbapellautor.getValue().toString());
            publicacionprofesor.setPubNombreAutor(itxtdbnombreautor.getValue() == null ? null : itxtdbnombreautor.getValue().toString());
            publicacionprofesor.setPubTitulo(itxtdbtitulo.getValue() == null ? null : itxtdbtitulo.getValue().toString());
            publicacionprofesor.setPubNombreCapitulo(itxtdbtitulocapi.getValue() == null ? null : itxtdbtitulocapi.getValue().toString());
            publicacionprofesor.setPubEditorial(itxtdbeditorial.getValue() == null ? null : itxtdbeditorial.getValue().toString());
            publicacionprofesor.setPubNumpaginas(itxtdbnumpag.getValue() == null ? null : itxtdbnumpag.getValue().toString());
            publicacionprofesor.setPubFecedicion(dbfecedicion == null ? null : dbfecedicion);
            if (isRevista) {
                publicacionprofesor.setPubNumissn(itxtdbnumcod.getValue() == null ? null : itxtdbnumcod.getValue().toString());
            } else {
                publicacionprofesor.setPubNumisbn(itxtdbnumcod.getValue() == null ? null : itxtdbnumcod.getValue().toString());
            }
            publicacionprofesor.setAuspicioPublicacion(smausppub.getValue() == null ? ' ' : smausppub.getValue().toString().charAt(0));
            publicacionprofesor.setFormaPublicacion(smformpub.getValue() == null ? ' ' : smformpub.getValue().toString().charAt(0));
            publicacionprofesor.setPubPartserie(sompartserie == null ? null : sompartserie.charAt(0));
            publicacionprofesor.setPubTitserie(itxtdtitserie.getValue() == null ? null : itxtdtitserie.getValue().toString());
            publicacionprofesor.setPubRevpares(smdbrevpar.getValue() == null ? null : smdbrevpar.getValue().toString().charAt(0));
            publicacionprofesor.setPubTipoedicion(smdbedicion.getValue() == null ? null : Long.parseLong(smdbedicion.getValue().toString()));
            publicacionprofesor.setPubTiposvarios(smdbtipvarios.getValue() == null ? null : Long.parseLong(smdbtipvarios.getValue().toString()));
            //REVISTAS
            publicacionprofesor.setCampo8(itxtdbnomrev.getValue() == null ? null : itxtdbnomrev.getValue().toString());
            publicacionprofesor.setCampo7(itxtdbdesedicion.getValue() == null ? null : itxtdbdesedicion.getValue().toString());
            publicacionprofesor.setCampo6(itxtbaseindex.getValue() == null ? null : itxtbaseindex.getValue().toString());
            publicacionprofesor.setCampo1(smnumrevista == null ? null : smnumrevista);
            publicacionprofesor.setCampo2(smperiodicidad == null ? null : smperiodicidad);
            //PONENCIAS
            publicacionprofesor.setCampo5(itxtdbnomeve.getValue() == null ? null : itxtdbnomeve.getValue().toString());
            publicacionprofesor.setPubEntidadsede(itxtdbentidadsede.getValue() == null ? null : itxtdbentidadsede.getValue().toString());
            publicacionprofesor.setPubAlcance(smalcact.getValue() == null ? null : Long.parseLong(smalcact.getValue().toString()));
            publicacionprofesor.setPubMeddifusion(smmeddif.getValue() == null ? null : smmeddif.getValue().toString().charAt(0));
            publicacionprofesor.setPubEvento(smevento.getValue() == null ? null : smevento.getValue().toString().charAt(0));
            //PRODUCTOS AUDIOVISUALES
            publicacionprofesor.setPubDuracion(smduracion.getValue() == null ? null : Long.parseLong(smduracion.getValue().toString()));
            publicacionprofesor.setCampo4(itxtdbdetalletipopub.getValue() == null ? null : itxtdbdetalletipopub.getValue().toString());
            publicacionprofesor.setPubAniovisualizacion(saniovisualizacion == null ? null : Long.parseLong(saniovisualizacion));
            //OTROS
            publicacionprofesor.setPubIdioma(smidiomapub.getValue() == null ? null : Integer.valueOf(smidiomapub.getValue().toString()));
            publicacionprofesor.setOtroAuspicio(itxtdetotroausp.getValue() == null ? null : itxtdetotroausp.getValue().toString());
            publicacionprofesor.setPubApellidoCoautor(itxtdbapellcoautor.getValue() == null ? null : itxtdbapellcoautor.getValue().toString());
            publicacionprofesor.setPubNombreCoautor(itxtdbnombrecoautor.getValue() == null ? null : itxtdbnombrecoautor.getValue().toString());
            publicacionprofesor.setPubSubtitulo(itxtdbsubtitulo.getValue() == null ? null : itxtdbsubtitulo.getValue().toString());
            publicacionprofesor.setPubEdicion(itxtdbedicion.getValue() == null ? null : itxtdbedicion.getValue().toString());
            publicacionprofesor.setPubEstado(smdbestado.getValue() == null ? null : smdbestado.getValue().toString().charAt(0));
            publicacionprofesor.setPubTitrevista(itxtdbtitrevista.getValue() == null ? null : itxtdbtitrevista.getValue().toString());
            publicacionprofesor.setPubVolumen(itxtdbvolumen.getValue() == null ? null : Integer.valueOf(itxtdbvolumen.getValue().toString()));
            publicacionprofesor.setPubBaserevindexada(smdbbaserev.getValue() == null ? null : smdbbaserev.getValue().toString().charAt(0));
            publicacionprofesor.setPubNomotrabaseindex(itxtdnombase.getValue() == null ? null : itxtdnombase.getValue().toString());
            publicacionprofesor.setPubRangpagina(itxtdbranpag.getValue() == null ? null : itxtdbranpag.getValue().toString());
            publicacionprofesor.setPubRolanfitrion(smrolanfitrion == null ? null : smrolanfitrion);
            publicacionprofesor.setPubObservacion(smobservacion.getValue() == null ? null : smobservacion.getValue().toString());
            publicacionprofesor.setPubEvidencia(smevidencia.getValue() == null ? null : smevidencia.getValue().toString());
            if (pubprofaux.getCodigoPublicacion() == null) {
                publicacionprofesor.setTipoPersona(smtippublicante.charAt(0));
                publicacionprofesor.setCedulaPersona(codProfesor);
                publicacionprofesor.setFechaCrea(new Date());
                publicacionprofesor.setUsuarioCrea(usr.getUsuCedPass());
                publicacionprofesor.setFechaUltmodific(new Date());
                publicacionprofesor.setUsuarioUltmodific(usr.getUsuCedPass());
                publicacionFacade.create(publicacionprofesor);
            } else {
                publicacionprofesor.setFechaUltmodific(new Date());
                publicacionprofesor.setUsuarioUltmodific(usr.getUsuCedPass());
                publicacionFacade.edit(publicacionprofesor);
            }
            //Recuepro los datos de los titulos            
            JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
            recuperaPublicacion();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Guardar Publicación");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            publicacionprofesor = new GeacPublicacionDga();
            CancelPub();
        }
        RequestContext.getCurrentInstance().update("fsegPublicacionDAG:tpublibro");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON CANCELAR">  
    @Override
    public void cancelarButton_processAction(ActionEvent ae) {
        CancelPub();
        this.setPaginaMant("/WEB-INF/templates/includes/sinContenido");
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="BOTON EDITAR">  
    @Override
    public void editarButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/publicacionesDGA/segPublicacionesGenModal");
        super.editarButton_processAction(ae);
        publicacionprofesor = selectedPublicacion;
        smtippublicacion.setValue(publicacionprofesor.getTipoPublicacion() == 0 ? null : publicacionprofesor.getTipoPublicacion());
        if (smtippublicacion.getValue() != null) {
            bingdatbiblio = true;
        }
        tipPublicvalueChangeListener();
        smtipparticipacion.setValue(publicacionprofesor.getPubParticipacion() == null ? null : publicacionprofesor.getPubParticipacion());
        smrol.setValue(publicacionprofesor.getPubRol() == null ? null : publicacionprofesor.getPubRol());
        sanioedicion = (publicacionprofesor.getCampo10() == null ? null : publicacionprofesor.getCampo10());
        smpaispub.setValue(publicacionprofesor.getPrinPais() == null ? null : publicacionprofesor.getPrinPais().getPaiCodigo());
        paisPublicvalueChangeListener();
        smciudadpub.setValue(publicacionprofesor.getPrinCiudad() == null ? null : publicacionprofesor.getPrinCiudad().getPrinCiudadPK().getCiuCodigo());
        smformpub.setValue(publicacionprofesor.getFormaPublicacion());
        smidiomapub.setValue(publicacionprofesor.getPubIdioma() == null ? null : publicacionprofesor.getPubIdioma());
        smausppub.setValue(publicacionprofesor.getAuspicioPublicacion());
        auspPublicvalueChangeListener();
        itxtdetotroausp.setValue(publicacionprofesor.getOtroAuspicio() == null ? null : publicacionprofesor.getOtroAuspicio());
        itxtdbapellautor.setValue(publicacionprofesor.getPubApellidoAutor() == null ? null : publicacionprofesor.getPubApellidoAutor());
        itxtdbnombreautor.setValue(publicacionprofesor.getPubNombreAutor() == null ? null : publicacionprofesor.getPubNombreAutor());
        itxtdbapellcoautor.setValue(publicacionprofesor.getPubApellidoCoautor() == null ? null : publicacionprofesor.getPubApellidoCoautor());
        itxtdbnombrecoautor.setValue(publicacionprofesor.getPubNombreCoautor() == null ? null : publicacionprofesor.getPubNombreCoautor());
        itxtdbtitulo.setValue(publicacionprofesor.getPubTitulo() == null ? null : publicacionprofesor.getPubTitulo());
        itxtdbsubtitulo.setValue(publicacionprofesor.getPubSubtitulo() == null ? null : publicacionprofesor.getPubSubtitulo());
        itxtdbtitulocapi.setValue(publicacionprofesor.getPubNombreCapitulo() == null ? null : publicacionprofesor.getPubNombreCapitulo());
        itxtdbedicion.setValue(publicacionprofesor.getPubEdicion() == null ? null : publicacionprofesor.getPubEdicion());
        itxtdbeditorial.setValue(publicacionprofesor.getPubEditorial() == null ? null : publicacionprofesor.getPubEditorial());
        itxtdbnumpag.setValue(publicacionprofesor.getPubNumpaginas() == null ? null : publicacionprofesor.getPubNumpaginas());
        dbfecedicion = (publicacionprofesor.getPubFecedicion() == null ? null : new java.sql.Date(publicacionprofesor.getPubFecedicion().getTime()));
        itxtdbranpag.setValue(publicacionprofesor.getPubRangpagina() == null ? null : publicacionprofesor.getPubRangpagina().toString());
        smrolanfitrion = (publicacionprofesor.getPubRolanfitrion() == null ? null : publicacionprofesor.getPubRolanfitrion());
        smnumrevista = (publicacionprofesor.getCampo1() == null ? null : publicacionprofesor.getCampo1());
        smperiodicidad = (publicacionprofesor.getCampo2() == null ? null : publicacionprofesor.getCampo2());
        /*if (smtippublicacion.getValue().toString().equalsIgnoreCase("1")
             || smtippublicacion.getValue().toString().equalsIgnoreCase("2")
             || smtippublicacion.getValue().toString().equalsIgnoreCase("3")
             || smtippublicacion.getValue().toString().equalsIgnoreCase("4")) {
             sanioedicion = (dbfecedicion == null ? null : formato.format(dbfecedicion).substring(6));
             }*/
        smdbestado.setValue(publicacionprofesor.getPubEstado() == null ? null : publicacionprofesor.getPubEstado());
        itxtdbtitrevista.setValue(publicacionprofesor.getPubTitrevista() == null ? null : publicacionprofesor.getPubTitrevista());
        itxtdbvolumen.setValue(publicacionprofesor.getPubVolumen() == null ? null : publicacionprofesor.getPubVolumen());
        etcod = (publicacionprofesor.getPubNumisbn() == null ? "N° ISSN:" : "N° ISBN:");
        if (smtippublicacion.getValue() != null) {
            if (smtippublicacion.getValue().toString().equalsIgnoreCase("14")) {
                itxtdbnumcod.setValue(publicacionprofesor.getPubNumissn() == null ? publicacionprofesor.getPubNumissn() : publicacionprofesor.getPubNumissn());
            } else {
                itxtdbnumcod.setValue(publicacionprofesor.getPubNumisbn() == null ? publicacionprofesor.getPubNumisbn() : publicacionprofesor.getPubNumisbn());
            }
        }

        smdbrevpar.setValue(publicacionprofesor.getPubRevpares() == null ? null : publicacionprofesor.getPubRevpares());
        smdbbaserev.setValue(publicacionprofesor.getPubBaserevindexada() == null ? null : publicacionprofesor.getPubBaserevindexada());
        basePublicvalueChangeListener();
        itxtdnombase.setValue(publicacionprofesor.getPubNomotrabaseindex() == null ? null : publicacionprofesor.getPubNomotrabaseindex());
        sompartserie = (publicacionprofesor.getPubPartserie() == null ? null : publicacionprofesor.getPubPartserie().toString());
        itxtdtitserie.setValue(publicacionprofesor.getPubTitserie() == null ? null : publicacionprofesor.getPubTitserie());
        itxtdbentidadsede.setValue(publicacionprofesor.getPubEntidadsede() == null ? null : publicacionprofesor.getPubEntidadsede());
        smevento.setValue(publicacionprofesor.getPubEvento() == null ? null : publicacionprofesor.getPubEvento());
        smmeddif.setValue(publicacionprofesor.getPubMeddifusion() == null ? null : publicacionprofesor.getPubMeddifusion());
        smdbedicion.setValue(publicacionprofesor.getPubTipoedicion() == null ? null : publicacionprofesor.getPubTipoedicion());
        smdbtipvarios.setValue(publicacionprofesor.getPubTiposvarios() == null ? null : publicacionprofesor.getPubTiposvarios());
        itxtdbnomrev.setValue(publicacionprofesor.getCampo8() == null ? null : publicacionprofesor.getCampo8());
        itxtdbdesedicion.setValue(publicacionprofesor.getCampo7() == null ? null : publicacionprofesor.getCampo7());
        itxtbaseindex.setValue(publicacionprofesor.getCampo6() == null ? null : publicacionprofesor.getCampo6());
        baseIndexacionValueChangeListener();
        itxtdbnomeve.setValue(publicacionprofesor.getCampo5() == null ? null : publicacionprofesor.getCampo5());
        smalcact.setValue(publicacionprofesor.getPubAlcance() == null ? null : publicacionprofesor.getPubAlcance());
        smduracion.setValue(publicacionprofesor.getPubDuracion() == null ? null : publicacionprofesor.getPubDuracion());
        detalleTipoPubValueChangeListener();
        itxtdbdetalletipopub.setValue(publicacionprofesor.getCampo4() == null ? null : publicacionprofesor.getCampo4());
        saniovisualizacion = (publicacionprofesor.getPubAniovisualizacion() == null ? null : publicacionprofesor.getPubAniovisualizacion().toString());
        smtippublicante = (publicacionprofesor.getTipoPersona() == null ? null : publicacionprofesor.getTipoPersona().toString());
        tipPersonavalueChangeListener();
        codProfesor = (publicacionprofesor.getCedulaPersona() == null ? null : publicacionprofesor.getCedulaPersona());
        itxtdburl.setValue(publicacionprofesor.getPubUrl() == null ? null : publicacionprofesor.getPubUrl());
        smevidencia.setValue(publicacionprofesor.getPubEvidencia() == null ? null : publicacionprofesor.getPubEvidencia());
        smobservacion.setValue(publicacionprofesor.getPubObservacion() == null ? null : publicacionprofesor.getPubObservacion());
        RequestContext.getCurrentInstance().update("formMant:panelMant");
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="BOTON ELIMINAR">  
    public void eliminarPub_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/publicacionesDGA/confSegPublicacionDGA");
        super.editarButton_processAction(ae);
        RequestContext.getCurrentInstance().update("formMant:panelMant");
    }

    @Override
    public void eliminarButton_processAction(ActionEvent ae, String lsmensaje) {
        String ls_mensaje = null;
        publicacionFacade.remove(selectedPublicacion);
        if (ls_mensaje == null) {
            ls_mensaje = "Dato Eliminado";
        }
        super.eliminarButton_processAction(ae, ls_mensaje);
        recuperaPublicacion();
        RequestContext.getCurrentInstance().update("fsegPublicacionDAG:tpublibro");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
    }

    // </editor-fold> 
}
