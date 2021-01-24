/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.publicacionDGA.entities;

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
public class PrinCiudadPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PAI_CODIGO")
    private short paiCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CIU_CODIGO")
    private int ciuCodigo;

    public PrinCiudadPK() {
    }

    public PrinCiudadPK(short paiCodigo, int ciuCodigo) {
        this.paiCodigo = paiCodigo;
        this.ciuCodigo = ciuCodigo;
    }

    public short getPaiCodigo() {
        return paiCodigo;
    }

    public void setPaiCodigo(short paiCodigo) {
        this.paiCodigo = paiCodigo;
    }

    public int getCiuCodigo() {
        return ciuCodigo;
    }

    public void setCiuCodigo(int ciuCodigo) {
        this.ciuCodigo = ciuCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) paiCodigo;
        hash += (int) ciuCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrinCiudadPK)) {
            return false;
        }
        PrinCiudadPK other = (PrinCiudadPK) object;
        if (this.paiCodigo != other.paiCodigo) {
            return false;
        }
        if (this.ciuCodigo != other.ciuCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.publicacionDGA.entities.PrinCiudadPK[ paiCodigo=" + paiCodigo + ", ciuCodigo=" + ciuCodigo + " ]";
    }
    
}
