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
public class InfAnualRealizadoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IAE_CODIGO_PROFESOR")
    private int iaeCodigoProfesor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IAE_ANIO")
    private int iaeAnio;
    
    

    public InfAnualRealizadoPK() {
    }

    public InfAnualRealizadoPK(int iaeCodigoProfesor, int iaeAnio) {
        this.iaeCodigoProfesor = iaeCodigoProfesor;
        this.iaeAnio = iaeAnio;
    }

    public int getIaeCodigoProfesor() {
        return iaeCodigoProfesor;
    }

    public void setIaeCodigoProfesor(int iaeCodigoProfesor) {
        this.iaeCodigoProfesor = iaeCodigoProfesor;
    }

    public int getIaeAnio() {
        return iaeAnio;
    }

    public void setIaeAnio(int iaeAnio) {
        this.iaeAnio = iaeAnio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iaeCodigoProfesor;
        hash += (int) iaeAnio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfAnualRealizadoPK)) {
            return false;
        }
        InfAnualRealizadoPK other = (InfAnualRealizadoPK) object;
        if (this.iaeCodigoProfesor != other.iaeCodigoProfesor) {
            return false;
        }
        if (this.iaeAnio != other.iaeAnio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.infoanual.entities.InfAnualRealizadoPK[ iaeCodigoProfesor=" + iaeCodigoProfesor + ", iaeAnio=" + iaeAnio + " ]";
    }

}
