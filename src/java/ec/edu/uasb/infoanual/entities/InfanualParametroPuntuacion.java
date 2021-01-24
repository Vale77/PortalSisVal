/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "INFANUAL_PARAMETRO_PUNTUACION", schema = "GESTIONACADEMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfanualParametroPuntuacion.findAll", query = "SELECT i FROM InfanualParametroPuntuacion i")})
public class InfanualParametroPuntuacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IAPP_CODIGO")
    private Long iappCodigo;
    @Size(max = 25)
    @Column(name = "IAAP_AMBITO")
    private String iaapAmbito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "IAAP_NOMBRE")
    private String iaapNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "IAAP_COLUMNA")
    private String iaapColumna;    
    @Basic(optional = false)
    @NotNull
    @Column(name = "IAAP_PUNTOS")
    private BigDecimal iaapPuntos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "IAAP_ESTADO")
    private String iaapEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IAAP_USUARIO_CREA")
    private String iaapUsuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IAAP_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date iaapFechaCrea;
    @Size(max = 30)
    @Column(name = "IAAP_USUARIO_MODIFICA")
    private String iaapUsuarioModifica;
    @Column(name = "IAAP_FECHA_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date iaapFechaModifica;

    public InfanualParametroPuntuacion() {
    }

    public InfanualParametroPuntuacion(Long iappCodigo) {
        this.iappCodigo = iappCodigo;
    }

    public InfanualParametroPuntuacion(Long iappCodigo, String iaapNombre, String iaapColumna, BigDecimal iaapPuntos, String iaapEstado, String iaapUsuarioCrea, Date iaapFechaCrea) {
        this.iappCodigo = iappCodigo;
        this.iaapNombre = iaapNombre;
        this.iaapColumna = iaapColumna;
        this.iaapPuntos = iaapPuntos;
        this.iaapEstado = iaapEstado;
        this.iaapUsuarioCrea = iaapUsuarioCrea;
        this.iaapFechaCrea = iaapFechaCrea;
    }

    public Long getIappCodigo() {
        return iappCodigo;
    }

    public void setIappCodigo(Long iappCodigo) {
        this.iappCodigo = iappCodigo;
    }

    public String getIaapAmbito() {
        return iaapAmbito;
    }

    public void setIaapAmbito(String iaapAmbito) {
        this.iaapAmbito = iaapAmbito;
    }

    public String getIaapNombre() {
        return iaapNombre;
    }

    public void setIaapNombre(String iaapNombre) {
        this.iaapNombre = iaapNombre;
    }

    public String getIaapColumna() {
        return iaapColumna;
    }

    public void setIaapColumna(String iaapColumna) {
        this.iaapColumna = iaapColumna;
    }

    public BigDecimal getIaapPuntos() {
        return iaapPuntos;
    }

    public void setIaapPuntos(BigDecimal iaapPuntos) {
        this.iaapPuntos = iaapPuntos;
    }

    public String getIaapEstado() {
        return iaapEstado;
    }

    public void setIaapEstado(String iaapEstado) {
        this.iaapEstado = iaapEstado;
    }

    public String getIaapUsuarioCrea() {
        return iaapUsuarioCrea;
    }

    public void setIaapUsuarioCrea(String iaapUsuarioCrea) {
        this.iaapUsuarioCrea = iaapUsuarioCrea;
    }

    public Date getIaapFechaCrea() {
        return iaapFechaCrea;
    }

    public void setIaapFechaCrea(Date iaapFechaCrea) {
        this.iaapFechaCrea = iaapFechaCrea;
    }

    public String getIaapUsuarioModifica() {
        return iaapUsuarioModifica;
    }

    public void setIaapUsuarioModifica(String iaapUsuarioModifica) {
        this.iaapUsuarioModifica = iaapUsuarioModifica;
    }

    public Date getIaapFechaModifica() {
        return iaapFechaModifica;
    }

    public void setIaapFechaModifica(Date iaapFechaModifica) {
        this.iaapFechaModifica = iaapFechaModifica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iappCodigo != null ? iappCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfanualParametroPuntuacion)) {
            return false;
        }
        InfanualParametroPuntuacion other = (InfanualParametroPuntuacion) object;
        if ((this.iappCodigo == null && other.iappCodigo != null) || (this.iappCodigo != null && !this.iappCodigo.equals(other.iappCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.infoanual.entities.InfanualParametroPuntuacion[ iappCodigo=" + iappCodigo + " ]";
    }
    
}
