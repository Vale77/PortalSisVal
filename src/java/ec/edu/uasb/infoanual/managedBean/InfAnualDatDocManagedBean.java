/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.managedBean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "datDoc")
@ViewScoped
public class InfAnualDatDocManagedBean extends BaseJSFManagedBean implements Serializable {

    private String smnombre = null;
    private String smapellido = null;
    private String msgtablavacia = "No existen datos Registrados";
    private List<String[]> listDatPro = new ArrayList<String[]>();
    /**
     * Creates a new instance of InfAnualDatDocManagedBean
     */
    @EJB
    private ConsultaFacadeLocal consultaFacade;

// <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public String getSmnombre() {
        return smnombre;
    }

    public void setSmnombre(String smnombre) {
        this.smnombre = smnombre;
    }

    public String getSmapellido() {
        return smapellido;
    }

    public void setSmapellido(String smapellido) {
        this.smapellido = smapellido;
    }

    public List<String[]> getListDatPro() {
        return listDatPro;
    }

    public void setListDatPro(List<String[]> listDatPro) {
        this.listDatPro = listDatPro;
    }

    public String getMsgtablavacia() {
        return msgtablavacia;
    }

    public void setMsgtablavacia(String msgtablavacia) {
        this.msgtablavacia = msgtablavacia;
    }
    // </editor-fold>

    public InfAnualDatDocManagedBean() {
    }

    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA ">    
    private void recuperaInfo() {
        Vector v = new Vector();
        listDatPro.clear();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT CED_PAS_PROFESOR, NOMBRE_PROFESOR,APELLIDO_PROFESOR,"
                + "  EMAIL_PROFESOR, NR_MEMO "
                + " FROM PROFESOR "
                + " WHERE NOMBRE_PROFESOR LIKE '%").append(smnombre).append("%' "
                + " OR APELLIDO_PROFESOR LIKE '%").append(smapellido).append("%'");
        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
       
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[5];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                asign[2] = (object[2] == null ? null : object[2].toString());
                asign[3] = (object[3] == null ? null : object[3].toString());
                asign[4] = (object[4] == null ? null : object[4].toString());
                listDatPro.add(i,asign);
            }            
        }
    }
    public void profesorvalueChangeListener() {
        if (smnombre != null || smapellido!=null) {
            recuperaInfo();
        }
    }
    public void onRowEdit(RowEditEvent event) {
        /*selectedasignatura = (String[]) event.getObject();
        if (sencuesta != null) {
            selectedasignatura[5] = sencuesta;
        }
        selectedasignatura[9] = formato.format(fecinicio);
        encuestaCalendarioFacade.reaperturaEncuesta(selectedasignatura);*/
        RequestContext.getCurrentInstance().update("fsegInfAnual:tdatosDoc");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
        JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");   

    }
}
