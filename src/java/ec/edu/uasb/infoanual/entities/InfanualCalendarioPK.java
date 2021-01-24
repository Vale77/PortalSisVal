/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.entities;

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
public class InfanualCalendarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IAC_ANIO")
    private long iacAnio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IAC_TIPO_DOCENTE")
    private Character iacTipoDocente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IAC_TIPO_CONTRATO")
    private int iacTipoContrato;

    public InfanualCalendarioPK() {
    }

    public InfanualCalendarioPK(long iacAnio, Character iacTipoDocente, int iacTipoContrato) {
        this.iacAnio = iacAnio;
        this.iacTipoDocente = iacTipoDocente;
        this.iacTipoContrato = iacTipoContrato;
    }

    public long getIacAnio() {
        return iacAnio;
    }

    public void setIacAnio(long iacAnio) {
        this.iacAnio = iacAnio;
    }

    public Character getIacTipoDocente() {
        return iacTipoDocente;
    }

    public void setIacTipoDocente(Character iacTipoDocente) {
        this.iacTipoDocente = iacTipoDocente;
    }

    public int getIacTipoContrato() {
        return iacTipoContrato;
    }

    public void setIacTipoContrato(int iacTipoContrato) {
        this.iacTipoContrato = iacTipoContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iacAnio;
        hash += (iacTipoDocente != null ? iacTipoDocente.hashCode() : 0);
        hash += (int) iacTipoContrato;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfanualCalendarioPK)) {
            return false;
        }
        InfanualCalendarioPK other = (InfanualCalendarioPK) object;
        if (this.iacAnio != other.iacAnio) {
            return false;
        }
        if ((this.iacTipoDocente == null && other.iacTipoDocente != null) || (this.iacTipoDocente != null && !this.iacTipoDocente.equals(other.iacTipoDocente))) {
            return false;
        }
        if (this.iacTipoContrato != other.iacTipoContrato) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.infoanual.entities.InfanualCalendarioPK[ iacAnio=" + iacAnio + ", iacTipoDocente=" + iacTipoDocente + ", iacTipoContrato=" + iacTipoContrato + " ]";
    }
    
}
