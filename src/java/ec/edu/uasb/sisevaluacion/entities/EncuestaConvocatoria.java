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
@Table(name = "ENCUESTA_CONVOCATORIA", schema = "EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncuestaConvocatoria.findAll", query = "SELECT e FROM EncuestaConvocatoria e"),
    @NamedQuery(name = "EncuestaConvocatoria.findByCodigoEncrecordatorio", query = "SELECT e FROM EncuestaConvocatoria e WHERE e.codigoEncrecordatorio = :codigoEncrecordatorio"),
    @NamedQuery(name = "EncuestaConvocatoria.findByNumConvocatoria", query = "SELECT e FROM EncuestaConvocatoria e WHERE e.numConvocatoria = :numConvocatoria"),
    @NamedQuery(name = "EncuestaConvocatoria.findByDiasConvocatoria", query = "SELECT e FROM EncuestaConvocatoria e WHERE e.diasConvocatoria = :diasConvocatoria"),
    @NamedQuery(name = "EncuestaConvocatoria.findByMensaje", query = "SELECT e FROM EncuestaConvocatoria e WHERE e.mensaje = :mensaje"),
    @NamedQuery(name = "EncuestaConvocatoria.findByDiasReapertura", query = "SELECT e FROM EncuestaConvocatoria e WHERE e.diasReapertura = :diasReapertura"),
    @NamedQuery(name = "EncuestaConvocatoria.findByMensajeReapertura", query = "SELECT e FROM EncuestaConvocatoria e WHERE e.mensajeReapertura = :mensajeReapertura"),
    @NamedQuery(name = "EncuestaConvocatoria.findByEstadoConvocatoria", query = "SELECT e FROM EncuestaConvocatoria e WHERE e.estadoConvocatoria = :estadoConvocatoria")})
public class EncuestaConvocatoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_ENCRECORDATORIO")
    private Integer codigoEncrecordatorio;
    @Column(name = "NUM_CONVOCATORIA")
    private Integer numConvocatoria;
    @Column(name = "DIAS_CONVOCATORIA")
    private Integer diasConvocatoria; 
    @Column(name = "MENSAJE")
    private String mensaje;
    @Column(name = "DIAS_REAPERTURA")
    private Integer diasReapertura; 
    @Column(name = "MENSAJE_REAPERTURA")
    private String mensajeReapertura;
    @Column(name = "ESTADO_CONVOCATORIA")
    private Character estadoConvocatoria;
    @JoinColumn(name = "CODIGO_ENCUESTA", referencedColumnName = "CODIGO_ENCUESTA")
    @ManyToOne
    private Encuesta codigoEncuesta;
    
     public String getnomestado() {
        String variable = "";
        if (this.estadoConvocatoria != null) {
            switch (this.estadoConvocatoria) {
                case 'A':
                    variable = "Activo";
                    break;
                case 'I':
                    variable = "Inactivo";
                    break;
                default:
                    break;
            }
        } else {
            variable = "S/V";
        }


        return variable;
    }


    public EncuestaConvocatoria() {
    }

    public EncuestaConvocatoria(Integer codigoEncrecordatorio) {
        this.codigoEncrecordatorio = codigoEncrecordatorio;
    }

    public Integer getCodigoEncrecordatorio() {
        return codigoEncrecordatorio;
    }

    public void setCodigoEncrecordatorio(Integer codigoEncrecordatorio) {
        this.codigoEncrecordatorio = codigoEncrecordatorio;
    }

    public Integer getNumConvocatoria() {
        return numConvocatoria;
    }

    public void setNumConvocatoria(Integer numConvocatoria) {
        this.numConvocatoria = numConvocatoria;
    }

    public Integer getDiasConvocatoria() {
        return diasConvocatoria;
    }

    public void setDiasConvocatoria(Integer diasConvocatoria) {
        this.diasConvocatoria = diasConvocatoria;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getDiasReapertura() {
        return diasReapertura;
    }

    public void setDiasReapertura(Integer diasReapertura) {
        this.diasReapertura = diasReapertura;
    }

    public String getMensajeReapertura() {
        return mensajeReapertura;
    }

    public void setMensajeReapertura(String mensajeReapertura) {
        this.mensajeReapertura = mensajeReapertura;
    }

    public Character getEstadoConvocatoria() {
        return estadoConvocatoria;
    }

    public void setEstadoConvocatoria(Character estadoConvocatoria) {
        this.estadoConvocatoria = estadoConvocatoria;
    }

    public Encuesta getCodigoEncuesta() {
        return codigoEncuesta;
    }

    public void setCodigoEncuesta(Encuesta codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEncrecordatorio != null ? codigoEncrecordatorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncuestaConvocatoria)) {
            return false;
        }
        EncuestaConvocatoria other = (EncuestaConvocatoria) object;
        if ((this.codigoEncrecordatorio == null && other.codigoEncrecordatorio != null) || (this.codigoEncrecordatorio != null && !this.codigoEncrecordatorio.equals(other.codigoEncrecordatorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.sisevaluacion.entities.EncuestaConvocatoria[ codigoEncrecordatorio=" + codigoEncrecordatorio + " ]";
    }
}
