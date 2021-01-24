/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "PARAMETROS", schema = "EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p"),
    @NamedQuery(name = "Parametros.findByCodigoPareval", query = "SELECT p FROM Parametros p WHERE p.codigoPareval = :codigoPareval"),
    @NamedQuery(name = "Parametros.findByDiasHabilitada", query = "SELECT p FROM Parametros p WHERE p.diasHabilitada = :diasHabilitada"),
    @NamedQuery(name = "Parametros.findByDiasReapertura", query = "SELECT p FROM Parametros p WHERE p.diasReapertura = :diasReapertura"),
    @NamedQuery(name = "Parametros.findByCopiaA", query = "SELECT p FROM Parametros p WHERE p.copiaA = :copiaA"),
    @NamedQuery(name = "Parametros.findByAsunto", query = "SELECT p FROM Parametros p WHERE p.asunto = :asunto"),
    @NamedQuery(name = "Parametros.findByAsuntoReapertura", query = "SELECT p FROM Parametros p WHERE p.asuntoReapertura = :asuntoReapertura"),
    @NamedQuery(name = "Parametros.findByAsuntoCierre", query = "SELECT p FROM Parametros p WHERE p.asuntoCierre = :asuntoCierre")})
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_PAREVAL")
    private Long codigoPareval;
    @Column(name = "DIAS_HABILITADA")
    private Integer diasHabilitada;
    @Column(name = "DIAS_REAPERTURA")
    private Integer diasReapertura;    
    @Column(name = "COPIA_A")
    private String copiaA;    
    @Column(name = "ASUNTO")
    private String asunto;    
    @Column(name = "ASUNTO_REAPERTURA")
    private String asuntoReapertura;    
    @Column(name = "ASUNTO_CIERRE")
    private String asuntoCierre;
    @Column(name = "MENSAJE_CIERRE")
    private String mensajeCierre;
     @Column(name = "ASUNTO_PAGESTAMPILLA")
    private String asuntoPagestampilla;
    @Column(name = "MENSAJE_PAGESTAMPILLA")
    private String mensajePagestampilla;
    @JoinColumn(name = "CODIGO_ENCUESTA", referencedColumnName = "CODIGO_ENCUESTA")
    @ManyToOne
    private Encuesta codigoEncuesta;

    public Parametros() {
    }

    public Parametros(Long codigoPareval) {
        this.codigoPareval = codigoPareval;
    }

    public Long getCodigoPareval() {
        return codigoPareval;
    }

    public void setCodigoPareval(Long codigoPareval) {
        this.codigoPareval = codigoPareval;
    }

    public Integer getDiasHabilitada() {
        return diasHabilitada;
    }

    public void setDiasHabilitada(Integer diasHabilitada) {
        this.diasHabilitada = diasHabilitada;
    }

    public Integer getDiasReapertura() {
        return diasReapertura;
    }

    public void setDiasReapertura(Integer diasReapertura) {
        this.diasReapertura = diasReapertura;
    }

    public String getCopiaA() {
        return copiaA;
    }

    public void setCopiaA(String copiaA) {
        this.copiaA = copiaA;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getAsuntoReapertura() {
        return asuntoReapertura;
    }

    public void setAsuntoReapertura(String asuntoReapertura) {
        this.asuntoReapertura = asuntoReapertura;
    }

    public String getAsuntoCierre() {
        return asuntoCierre;
    }

    public void setAsuntoCierre(String asuntoCierre) {
        this.asuntoCierre = asuntoCierre;
    }

    public String getMensajeCierre() {
        return mensajeCierre;
    }

    public void setMensajeCierre(String mensajeCierre) {
        this.mensajeCierre = mensajeCierre;
    }

    public Encuesta getCodigoEncuesta() {
        return codigoEncuesta;
    }

    public void setCodigoEncuesta(Encuesta codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    public String getAsuntoPagestampilla() {
        return asuntoPagestampilla;
    }

    public void setAsuntoPagestampilla(String asuntoPagestampilla) {
        this.asuntoPagestampilla = asuntoPagestampilla;
    }

    public String getMensajePagestampilla() {
        return mensajePagestampilla;
    }

    public void setMensajePagestampilla(String mensajePagestampilla) {
        this.mensajePagestampilla = mensajePagestampilla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPareval != null ? codigoPareval.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.codigoPareval == null && other.codigoPareval != null) || (this.codigoPareval != null && !this.codigoPareval.equals(other.codigoPareval))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.sisevaluacion.entities.Parametros[ codigoPareval=" + codigoPareval + " ]";
    }
}
