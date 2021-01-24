/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author johana.orozco
 */
@Embeddable
public class PreguntaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PREGUNTA")
    private Long codigoPregunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENCUESTA")
    private Long codigoEncuesta;

    public PreguntaPK() {
    }

    public PreguntaPK(Long codigoPregunta, Long codigoEncuesta) {
        this.codigoPregunta = codigoPregunta;
        this.codigoEncuesta = codigoEncuesta;
    }

    public Long getCodigoPregunta() {
        return codigoPregunta;
    }

    public void setCodigoPregunta(Long codigoPregunta) {
        this.codigoPregunta = codigoPregunta;
    }

    public Long getCodigoEncuesta() {
        return codigoEncuesta;
    }

    public void setCodigoEncuesta(Long codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.codigoPregunta != null ? this.codigoPregunta.hashCode() : 0);
        hash = 19 * hash + (this.codigoEncuesta != null ? this.codigoEncuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PreguntaPK other = (PreguntaPK) obj;
        if (this.codigoPregunta != other.codigoPregunta && (this.codigoPregunta == null || !this.codigoPregunta.equals(other.codigoPregunta))) {
            return false;
        }
        if (this.codigoEncuesta != other.codigoEncuesta && (this.codigoEncuesta == null || !this.codigoEncuesta.equals(other.codigoEncuesta))) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "ec.edu.uasb.sisevaluacion.entities.PreguntaPK[ codigoPregunta=" + codigoPregunta + ", codigoEncuesta=" + codigoEncuesta + " ]";
    }
    
}
