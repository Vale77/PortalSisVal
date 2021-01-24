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
public class RespuestaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANIO")
    private long anio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CICLO")
    private long ciclo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MATERIA")
    private long codigoMateria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_ENCUESTA")
    private long numeroEncuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PREGUNTA")
    private long codigoPregunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENCUESTA")
    private long codigoEncuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PROFESOR")
    private long codigoProfesor;

    public RespuestaPK() {
    }

    public RespuestaPK(long anio, long ciclo, long codigoMateria, long numeroEncuesta, long codigoPregunta, long codigoEncuesta, long codigoProfesor) {
        this.anio = anio;
        this.ciclo = ciclo;
        this.codigoMateria = codigoMateria;
        this.numeroEncuesta = numeroEncuesta;
        this.codigoPregunta = codigoPregunta;
        this.codigoEncuesta = codigoEncuesta;
        this.codigoProfesor = codigoProfesor;
    }

    public long getAnio() {
        return anio;
    }

    public void setAnio(long anio) {
        this.anio = anio;
    }

    public long getCiclo() {
        return ciclo;
    }

    public void setCiclo(long ciclo) {
        this.ciclo = ciclo;
    }

    public long getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(long codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public long getNumeroEncuesta() {
        return numeroEncuesta;
    }

    public void setNumeroEncuesta(long numeroEncuesta) {
        this.numeroEncuesta = numeroEncuesta;
    }

    public long getCodigoPregunta() {
        return codigoPregunta;
    }

    public void setCodigoPregunta(long codigoPregunta) {
        this.codigoPregunta = codigoPregunta;
    }

    public long getCodigoEncuesta() {
        return codigoEncuesta;
    }

    public void setCodigoEncuesta(long codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    public long getCodigoProfesor() {
        return codigoProfesor;
    }

    public void setCodigoProfesor(long codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) anio;
        hash += (int) ciclo;
        hash += (int) codigoMateria;
        hash += (int) numeroEncuesta;
        hash += (int) codigoPregunta;
        hash += (int) codigoEncuesta;
        hash += (int) codigoProfesor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaPK)) {
            return false;
        }
        RespuestaPK other = (RespuestaPK) object;
        if (this.anio != other.anio) {
            return false;
        }
        if (this.ciclo != other.ciclo) {
            return false;
        }
        if (this.codigoMateria != other.codigoMateria) {
            return false;
        }
        if (this.numeroEncuesta != other.numeroEncuesta) {
            return false;
        }
        if (this.codigoPregunta != other.codigoPregunta) {
            return false;
        }
        if (this.codigoEncuesta != other.codigoEncuesta) {
            return false;
        }
        if (this.codigoProfesor != other.codigoProfesor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.sisevaluacion.entities.RespuestaPK[ anio=" + anio + ", ciclo=" + ciclo + ", codigoMateria=" + codigoMateria + ", numeroEncuesta=" + numeroEncuesta + ", codigoPregunta=" + codigoPregunta + ", codigoEncuesta=" + codigoEncuesta + ", codigoProfesor=" + codigoProfesor + " ]";
    }
    
}
