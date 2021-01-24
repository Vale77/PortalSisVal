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
@Table(name = "INFANUAL_RANGOS", schema = "GESTIONACADEMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfanualRangos.findAll", query = "SELECT i FROM InfanualRangos i")})
public class InfanualRangos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IAR_CODIGO")
    private Long iarCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IAR_TIPO_DOCENTE")
    private Character iarTipoDocente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IAR_TIPO_CONTRATO")
    private int iarTipoContrato;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "IAR_RMIN_DESDE")
    private BigDecimal iarRminDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IAR_RMIN_HASTA")
    private BigDecimal iarRminHasta;
    @Column(name = "IAR_RMAX_DESDE")
    private BigDecimal iarRmaxDesde;
    @Column(name = "IAR_RMAX_HASTA")
    private BigDecimal iarRmaxHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IAR_ESTADO_RANGO")
    private Character iarEstadoRango;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IAR_USUARIO_CREA")
    private String iarUsuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IAR_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date iarFechaCrea;
    @Size(max = 30)
    @Column(name = "IAR_USUARIO_MODIFICA")
    private String iarUsuarioModifica;
    @Column(name = "IAR_FECHA_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date iarFechaModifica;

    public InfanualRangos() {
    }

    public InfanualRangos(Long iarCodigo) {
        this.iarCodigo = iarCodigo;
    }

    public InfanualRangos(Long iarCodigo, Character iarTipoDocente, int iarTipoContrato, BigDecimal iarRminDesde, BigDecimal iarRminHasta, Character iarEstadoRango, String iarUsuarioCrea, Date iarFechaCrea) {
        this.iarCodigo = iarCodigo;
        this.iarTipoDocente = iarTipoDocente;
        this.iarTipoContrato = iarTipoContrato;
        this.iarRminDesde = iarRminDesde;
        this.iarRminHasta = iarRminHasta;
        this.iarEstadoRango = iarEstadoRango;
        this.iarUsuarioCrea = iarUsuarioCrea;
        this.iarFechaCrea = iarFechaCrea;
    }

    public Long getIarCodigo() {
        return iarCodigo;
    }

    public void setIarCodigo(Long iarCodigo) {
        this.iarCodigo = iarCodigo;
    }

    public Character getIarTipoDocente() {
        return iarTipoDocente;
    }

    public void setIarTipoDocente(Character iarTipoDocente) {
        this.iarTipoDocente = iarTipoDocente;
    }

    public int getIarTipoContrato() {
        return iarTipoContrato;
    }

    public void setIarTipoContrato(int iarTipoContrato) {
        this.iarTipoContrato = iarTipoContrato;
    }

    public BigDecimal getIarRminDesde() {
        return iarRminDesde;
    }

    public void setIarRminDesde(BigDecimal iarRminDesde) {
        this.iarRminDesde = iarRminDesde;
    }

    public BigDecimal getIarRminHasta() {
        return iarRminHasta;
    }

    public void setIarRminHasta(BigDecimal iarRminHasta) {
        this.iarRminHasta = iarRminHasta;
    }

    public BigDecimal getIarRmaxDesde() {
        return iarRmaxDesde;
    }

    public void setIarRmaxDesde(BigDecimal iarRmaxDesde) {
        this.iarRmaxDesde = iarRmaxDesde;
    }

    public BigDecimal getIarRmaxHasta() {
        return iarRmaxHasta;
    }

    public void setIarRmaxHasta(BigDecimal iarRmaxHasta) {
        this.iarRmaxHasta = iarRmaxHasta;
    }

    public Character getIarEstadoRango() {
        return iarEstadoRango;
    }

    public void setIarEstadoRango(Character iarEstadoRango) {
        this.iarEstadoRango = iarEstadoRango;
    }

    public String getIarUsuarioCrea() {
        return iarUsuarioCrea;
    }

    public void setIarUsuarioCrea(String iarUsuarioCrea) {
        this.iarUsuarioCrea = iarUsuarioCrea;
    }

    public Date getIarFechaCrea() {
        return iarFechaCrea;
    }

    public void setIarFechaCrea(Date iarFechaCrea) {
        this.iarFechaCrea = iarFechaCrea;
    }

    public String getIarUsuarioModifica() {
        return iarUsuarioModifica;
    }

    public void setIarUsuarioModifica(String iarUsuarioModifica) {
        this.iarUsuarioModifica = iarUsuarioModifica;
    }

    public Date getIarFechaModifica() {
        return iarFechaModifica;
    }

    public void setIarFechaModifica(Date iarFechaModifica) {
        this.iarFechaModifica = iarFechaModifica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iarCodigo != null ? iarCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfanualRangos)) {
            return false;
        }
        InfanualRangos other = (InfanualRangos) object;
        if ((this.iarCodigo == null && other.iarCodigo != null) || (this.iarCodigo != null && !this.iarCodigo.equals(other.iarCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.infoanual.entities.InfanualRangos[ iarCodigo=" + iarCodigo + " ]";
    }
    
}
