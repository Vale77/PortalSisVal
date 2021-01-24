/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdga.reportes.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.entities.AnioAcademico;
import ec.edu.uasb.entities.Area;
import ec.edu.uasb.entities.Programa;
import ec.edu.uasb.entities.ProgramaPK;
import ec.edu.uasb.portalgesdga.session.AsignaturaSyllabusFacadeLocal;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.session.AreaFacadeLocal;
import ec.edu.uasb.session.ProgramaFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "estadistSilaboBean")
@ViewScoped
public class EstadisticoSilaboJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120420L;

    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private AreaFacadeLocal areaFacade;
    @EJB
    private ProgramaFacadeLocal programaFacade;
    @EJB
    private AsignaturaSyllabusFacadeLocal asignaturaSyllabusFacade;

    private final Usuario usr;
    private final List<Perfil> perfiles;
    private List<AnioAcademico> academicos = new ArrayList<AnioAcademico>();
    private List<Area> areas = new ArrayList<Area>();
    private String filtrarAreas;
    private List<Programa> programas = new ArrayList<Programa>();
    private Long area;
    private Long anio;
    private String smtrimestre = null;
    private Programa progra;
    private final Programa todosProgramas;
    private final Programa sinProgramas;
    private final Area todasAreas;
    private String smtipfiltro = null;

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    public void setProgra(Programa progra) {
        this.progra = progra;
    }

    public Programa getProgra() {
        return progra;
    }

    public String getSmtipfiltro() {
        return smtipfiltro;
    }

    public void setSmtipfiltro(String smtipfiltro) {
        this.smtipfiltro = smtipfiltro;
    }

    public String getSmtrimestre() {
        return smtrimestre;
    }

    public void setSmtrimestre(String smtrimestre) {
        this.smtrimestre = smtrimestre;
    }

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public List<AnioAcademico> getAcademicos() {
        return academicos;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public List<Programa> getProgramas() {
        return programas;
    }

//</editor-fold>
    /**
     * Creates a new instance of SolicContratoJSFManagedBean
     */
    public EstadisticoSilaboJSFManagedBean() {
        this.usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        // seguridad en perfiles y areas
        perfiles = (List<Perfil>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfiles");
        filtrarAreas = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("filtrarAreas");
        for (Perfil pef : perfiles) {
            if (pef.getIdPerfil().equals("ADMEVAL") || pef.getIdPerfil().equals("DGAEVAL")) {
                filtrarAreas = "T";
                break;
            }
        }
        todosProgramas = new Programa(new ProgramaPK(new Long("0"), new Long("0")));
        todosProgramas.setNombrePrograma("TODOS LOS PROGRAMAS");
        
        sinProgramas = new Programa(new ProgramaPK(new Long("0"), new Long("0")));
        sinProgramas.setNombrePrograma("");


        todasAreas = new Area(new Long("0"));
        todasAreas.setNombreArea("TODAS LAS AREAS");
    }

    @Override
    public void init() {
        programas.clear();
        areas.clear();
        area = null;
        progra = null;
        anio = null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("urlRep");
    }

    @PostConstruct
    private void recuperarListados() {
        academicos = anioAcademicoFacade.findAll();
        if (!academicos.isEmpty()) {
            anio = academicos.get(0).getAnio();
        }
        recuperarAreas();
    }

    //<editor-fold defaultstate="collapsed" desc="Anio, Areas y programas">
    private void recuperarAreas() {
        areas.add(todasAreas);
        if (filtrarAreas.equals("T")) {
            areas.addAll(1, areaFacade.findAll());
        } else if (filtrarAreas.equals("A")) {
            areas.addAll(1, areaFacade.getAreasBySecre(usr.getUsuCodigo()));
        }
        area = areas.size() >= 1 ? areas.get(0).getAreCodigo() : null;
        if (area != null) {
            recuperarProgramas();
        }
    }

    private void recuperarProgramas() {
        programas.clear();
        if (area != null && anio != null) {
            programas.add(todosProgramas);
            if (area != 0) {
                programas.addAll(1, programaFacade.getProgramasByArea(area, anio));
            } else {
                programas.add(1, sinProgramas);
            }

        }
    }

    //</editor-fold>
    public void handleAreaAnioProgChange(String select) {
        if (!select.equals("silabos")) {
            progra = null;
            recuperarProgramas();
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("urlRep");
    }

    public void recupSeries() {
        String nomReporte=null;
        if (anio != null && area != null && progra != null) {
            if (smtipfiltro.equalsIgnoreCase("G")){
                nomReporte= "grafEstadSilabo";
            }if (smtipfiltro.equalsIgnoreCase("C")){
            nomReporte= "EstadSilabo";
            }if (smtipfiltro.equalsIgnoreCase("R")){
            nomReporte= "DetSilaboNoReg";
            }
            String urlReporte = "reporte="+nomReporte
                    + "&tipo=pdf"
                    + "&contexto=PortalSisEval"
                    + "&anAnio=" + anio                    
                    + "&anTrim=" + smtrimestre;
            if (!progra.getProgramaPK().getPrgCodigo().equals(new Long("0"))) {
                urlReporte = urlReporte + "&anProg=" + progra.getProgramaPK().getPrgCodigo().toString();
            } else {
                urlReporte = urlReporte + "&anProg=T";
            }
            if (!area.equals(new Long("0"))) {
                urlReporte = urlReporte + "&anArea=" + area.toString();
            } else {
                urlReporte = urlReporte + "&anArea=T";
            }            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte);
        }
    }
}
