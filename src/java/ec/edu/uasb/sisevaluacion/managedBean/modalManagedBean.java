/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "modal")
@ViewScoped
public class modalManagedBean extends BaseJSFManagedBean implements Serializable {

    private String url = null;
    private String displayReporte = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisplayReporte() {
        return displayReporte;
    }

    public void setDisplayReporte(String displayReporte) {
        this.displayReporte = displayReporte;
    }

    /**
     * Creates a new instance of modalManagedBean
     */
    public modalManagedBean() {
    }

    @PostConstruct
    private void recuperarListados() {

    }

    public void verReporte(String tipo, String paramrep, String ls_reporte) {
         url = paramrep
                + "&tipo=" + tipo
                + "&reporte=" + ls_reporte
                + "&contexto=PortalSisEval";              
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", url);  
        
    }
}
