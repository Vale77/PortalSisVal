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
public class EncuestaRealizadaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ALUMNO")
    private long codigoAlumno;
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
    @Column(name = "CODIGO_ENCUESTA")
    private long codigoEncuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PROFESOR")
    private long codigoProfesor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_REGISTRO")
    private char tipoRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESP")
    private long codigoEsp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_NIVEL")
    private long codigoNivel;

    public EncuestaRealizadaPK() {
    }

    public EncuestaRealizadaPK(long codigoAlumno, long anio, long ciclo, long codigoMateria, long codigoEncuesta, long codigoProfesor, char tipoRegistro, long codigoEsp, long codigoNivel) {
        this.codigoAlumno = codigoAlumno;
        this.anio = anio;
        this.ciclo = ciclo;
        this.codigoMateria = codigoMateria;
        this.codigoEncuesta = codigoEncuesta;
        this.codigoProfesor = codigoProfesor;
        this.tipoRegistro = tipoRegistro;
        this.codigoEsp = codigoEsp;
        this.codigoNivel = codigoNivel;
    }

    public long getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(long codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
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

    public char getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(char tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public long getCodigoEsp() {
        return codigoEsp;
    }

    public void setCodigoEsp(long codigoEsp) {
        this.codigoEsp = codigoEsp;
    }

    public long getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(long codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoAlumno;
        hash += (int) anio;
        hash += (int) ciclo;
        hash += (int) codigoMateria;
        hash += (int) codigoEncuesta;
        hash += (int) codigoProfesor;
        hash += (int) tipoRegistro;
        hash += (int) codigoEsp;
        hash += (int) codigoNivel;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncuestaRealizadaPK)) {
            return false;
        }
        EncuestaRealizadaPK other = (EncuestaRealizadaPK) object;
        if (this.codigoAlumno != other.codigoAlumno) {
            return false;
        }
        if (this.anio != other.anio) {
            return false;
        }
        if (this.ciclo != other.ciclo) {
            return false;
        }
        if (this.codigoMateria != other.codigoMateria) {
            return false;
        }
        if (this.codigoEncuesta != other.codigoEncuesta) {
            return false;
        }
        if (this.codigoProfesor != other.codigoProfesor) {
            return false;
        }
        if (this.tipoRegistro != other.tipoRegistro) {
            return false;
        }
        if (this.codigoEsp != other.codigoEsp) {
            return false;
        }
        if (this.codigoNivel != other.codigoNivel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.sisevaluacion.entities.EncuestaRealizadaPK[ codigoAlumno=" + codigoAlumno + ", anio=" + anio + ", ciclo=" + ciclo + ", codigoMateria=" + codigoMateria + ", codigoEncuesta=" + codigoEncuesta + ", codigoProfesor=" + codigoProfesor + ", tipoRegistro=" + tipoRegistro + ", codigoEsp=" + codigoEsp + ", codigoNivel=" + codigoNivel + " ]";
    }
    
}
