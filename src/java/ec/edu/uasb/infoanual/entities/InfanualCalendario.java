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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "INFANUAL_CALENDARIO", schema = "GESTIONACADEMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfanualCalendario.findAll", query = "SELECT i FROM InfanualCalendario i"),
    @NamedQuery(name = "InfanualCalendario.findByIacAnio", query = "SELECT i FROM InfanualCalendario i WHERE i.infanualCalendarioPK.iacAnio = :iacAnio"),
    @NamedQuery(name = "InfanualCalendario.findByIacTipoDocente", query = "SELECT i FROM InfanualCalendario i WHERE i.infanualCalendarioPK.iacTipoDocente = :iacTipoDocente"),
    @NamedQuery(name = "InfanualCalendario.findByIacTipoContrato", query = "SELECT i FROM InfanualCalendario i WHERE i.infanualCalendarioPK.iacTipoContrato = :iacTipoContrato"),
    @NamedQuery(name = "InfanualCalendario.findByIacFecinicio", query = "SELECT i FROM InfanualCalendario i WHERE i.iacFecinicio = :iacFecinicio"),
    @NamedQuery(name = "InfanualCalendario.findByIacFecfin", query = "SELECT i FROM InfanualCalendario i WHERE i.iacFecfin = :iacFecfin"),
    @NamedQuery(name = "InfanualCalendario.findByIacEstadoInforme", query = "SELECT i FROM InfanualCalendario i WHERE i.iacEstadoInforme = :iacEstadoInforme"),
    @NamedQuery(name = "InfanualCalendario.findByIacFreapertura", query = "SELECT i FROM InfanualCalendario i WHERE i.iacFreapertura = :iacFreapertura"),
    @NamedQuery(name = "InfanualCalendario.findByIacNumRecordatorio", query = "SELECT i FROM InfanualCalendario i WHERE i.iacNumRecordatorio = :iacNumRecordatorio"),
    @NamedQuery(name = "InfanualCalendario.findByIacNumRecordatorioReapertura", query = "SELECT i FROM InfanualCalendario i WHERE i.iacNumRecordatorioReapertura = :iacNumRecordatorioReapertura")})
public class InfanualCalendario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InfanualCalendarioPK infanualCalendarioPK;
    @Column(name = "IAC_FECINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date iacFecinicio;
    @Column(name = "IAC_FECFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date iacFecfin;
    @Column(name = "IAC_ESTADO_INFORME")
    private Character iacEstadoInforme;
    @Column(name = "IAC_FREAPERTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date iacFreapertura;
    @Column(name = "IAC_NUM_RECORDATORIO")
    private Integer iacNumRecordatorio;
    @Column(name = "IAC_NUM_RECORDATORIO_REAPERTURA")
    private Integer iacNumRecordatorioReapertura;
    public String getnomtipodocente() {
        String variable = "";
        if (this.infanualCalendarioPK.getIacTipoDocente() != null) {
            switch (this.infanualCalendarioPK.getIacTipoDocente()) {
                case 'C':
                    variable = "Tiempo Completo";
                    break;
                case 'M':
                    variable = "Medio Tiempo";
                    break;
                case 'P':
                    variable = "Tiempo Parcial";
                    break;
                 case 'I':
                    variable = "Invitado";
                    break;
                default:
                    break;
            }
        } else {
            variable = "S/V";
        }

        return variable;
    }
     public String getnomestado() {
        String variable = "";
        if (this.iacEstadoInforme != null) {
            switch (this.iacEstadoInforme) {
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
    

    public InfanualCalendario() {
    }

    public InfanualCalendario(InfanualCalendarioPK infanualCalendarioPK) {
        this.infanualCalendarioPK = infanualCalendarioPK;
    }

    public InfanualCalendario(long iacAnio, Character iacTipoDocente, int iacTipoContrato) {
        this.infanualCalendarioPK = new InfanualCalendarioPK(iacAnio, iacTipoDocente, iacTipoContrato);
    }

    public InfanualCalendarioPK getInfanualCalendarioPK() {
        return infanualCalendarioPK;
    }

    public void setInfanualCalendarioPK(InfanualCalendarioPK infanualCalendarioPK) {
        this.infanualCalendarioPK = infanualCalendarioPK;
    }

    public Date getIacFecinicio() {
        return iacFecinicio;
    }

    public void setIacFecinicio(Date iacFecinicio) {
        this.iacFecinicio = iacFecinicio;
    }

    public Date getIacFecfin() {
        return iacFecfin;
    }

    public void setIacFecfin(Date iacFecfin) {
        this.iacFecfin = iacFecfin;
    }

    public Character getIacEstadoInforme() {
        return iacEstadoInforme;
    }

    public void setIacEstadoInforme(Character iacEstadoInforme) {
        this.iacEstadoInforme = iacEstadoInforme;
    }

    public Date getIacFreapertura() {
        return iacFreapertura;
    }

    public void setIacFreapertura(Date iacFreapertura) {
        this.iacFreapertura = iacFreapertura;
    }

    public Integer getIacNumRecordatorio() {
        return iacNumRecordatorio;
    }

    public void setIacNumRecordatorio(Integer iacNumRecordatorio) {
        this.iacNumRecordatorio = iacNumRecordatorio;
    }

    public Integer getIacNumRecordatorioReapertura() {
        return iacNumRecordatorioReapertura;
    }

    public void setIacNumRecordatorioReapertura(Integer iacNumRecordatorioReapertura) {
        this.iacNumRecordatorioReapertura = iacNumRecordatorioReapertura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (infanualCalendarioPK != null ? infanualCalendarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfanualCalendario)) {
            return false;
        }
        InfanualCalendario other = (InfanualCalendario) object;
        if ((this.infanualCalendarioPK == null && other.infanualCalendarioPK != null) || (this.infanualCalendarioPK != null && !this.infanualCalendarioPK.equals(other.infanualCalendarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.infoanual.entities.InfanualCalendario[ infanualCalendarioPK=" + infanualCalendarioPK + " ]";
    }
    
}
