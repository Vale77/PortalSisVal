/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.entities;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "INFANUAL_REALIZADO", schema = "GESTIONACADEMICA")
@XmlRootElement

public class InfanualRealizado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InfAnualRealizadoPK infanualRealizadoPK;
    @Column(name = "IAE_FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date iaeFecha;
    @Column(name = "IAE_ESTADO_IAAD")
    private Character iaeEstadoIAad;

    public String getnomestado() {
        String variable = "";
        if (this.iaeEstadoIAad != null) {
            switch (this.iaeEstadoIAad) {
                case 'I':
                    variable = "Ingresado";
                    break;
                case 'F':
                    variable = "Finalizado";
                    break;

                default:
                    break;
            }
        } else {
            variable = "S/V";
        }

        return variable;
    }
   
    public InfanualRealizado() {
    }

    public InfanualRealizado(InfAnualRealizadoPK InfAnualRealizadoPK) {
        this.infanualRealizadoPK = InfAnualRealizadoPK;
    }

    public InfanualRealizado(int iaeCodigoProfesor, int iaeAnio) {
        this.infanualRealizadoPK = new InfAnualRealizadoPK(iaeCodigoProfesor, iaeAnio);
    }

    public InfAnualRealizadoPK getInfanualRealizadoPK() {
        return infanualRealizadoPK;
    }

    public void setInfanualRealizadoPK(InfAnualRealizadoPK infanualRealizadoPK) {
        this.infanualRealizadoPK = infanualRealizadoPK;
    }

    public Date getIaeFecha() {
        return iaeFecha;
    }

    public void setIaeFecha(Date iaeFecha) {
        this.iaeFecha = iaeFecha;
    }

    public Character getIaeEstadoIAad() {
        return iaeEstadoIAad;
    }

    public void setIaeEstadoIAad(Character iaeEstadoIAad) {
        this.iaeEstadoIAad = iaeEstadoIAad;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (infanualRealizadoPK != null ? infanualRealizadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfanualRealizado)) {
            return false;
        }
        InfanualRealizado other = (InfanualRealizado) object;
        if ((this.infanualRealizadoPK == null && other.infanualRealizadoPK != null) || (this.infanualRealizadoPK != null && !this.infanualRealizadoPK.equals(other.infanualRealizadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.infoanual.entities.InfanualRealizado[ infanualCalendarioPK=" + infanualRealizadoPK + " ]";
    }

}
