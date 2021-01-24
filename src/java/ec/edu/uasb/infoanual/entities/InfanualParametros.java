/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "INFANUAL_PARAMETROS", schema = "GESTIONACADEMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfanualParametros.findAll", query = "SELECT i FROM InfanualParametros i"),
    @NamedQuery(name = "InfanualParametros.findByIapCodigo", query = "SELECT i FROM InfanualParametros i WHERE i.iapCodigo = :iapCodigo"),
    @NamedQuery(name = "InfanualParametros.findByIapDiasHabilitada", query = "SELECT i FROM InfanualParametros i WHERE i.iapDiasHabilitada = :iapDiasHabilitada"),
    @NamedQuery(name = "InfanualParametros.findByIapDiasReapertura", query = "SELECT i FROM InfanualParametros i WHERE i.iapDiasReapertura = :iapDiasReapertura"),
    @NamedQuery(name = "InfanualParametros.findByIapCopiaA", query = "SELECT i FROM InfanualParametros i WHERE i.iapCopiaA = :iapCopiaA"),
    @NamedQuery(name = "InfanualParametros.findByIapAsunto", query = "SELECT i FROM InfanualParametros i WHERE i.iapAsunto = :iapAsunto"),
    @NamedQuery(name = "InfanualParametros.findByIapAsuntoReapertura", query = "SELECT i FROM InfanualParametros i WHERE i.iapAsuntoReapertura = :iapAsuntoReapertura"),
    @NamedQuery(name = "InfanualParametros.findByIapAsuntoCierre", query = "SELECT i FROM InfanualParametros i WHERE i.iapAsuntoCierre = :iapAsuntoCierre"),
    @NamedQuery(name = "InfanualParametros.findByIapMensajeCierre", query = "SELECT i FROM InfanualParametros i WHERE i.iapMensajeCierre = :iapMensajeCierre")})
public class InfanualParametros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IAP_CODIGO")
    private Long iapCodigo;
    @Column(name = "IAP_DIAS_HABILITADA")
    private Integer iapDiasHabilitada;
    @Column(name = "IAP_DIAS_REAPERTURA")
    private Integer iapDiasReapertura;
    @Size(max = 100)
    @Column(name = "IAP_COPIA_A")
    private String iapCopiaA;
    @Size(max = 100)
    @Column(name = "IAP_ASUNTO")
    private String iapAsunto;
    @Size(max = 100)
    @Column(name = "IAP_ASUNTO_REAPERTURA")
    private String iapAsuntoReapertura;
    @Size(max = 100)
    @Column(name = "IAP_ASUNTO_CIERRE")
    private String iapAsuntoCierre;
    @Size(max = 8000)
    @Column(name = "IAP_MENSAJE_CIERRE")
    private String iapMensajeCierre;
    @Size(max = 100)
    @Column(name = "IAP_ASUNTO_FINALIZACION")
    private String iapAsuntoFinalizacion;
    @Size(max = 8000)
    @Column(name = "IAP_MENSAJE_FINALIZACION")
    private String iapMensajeFinalizacion;

    public InfanualParametros() {
    }

    public InfanualParametros(Long iapCodigo) {
        this.iapCodigo = iapCodigo;
    }

    public Long getIapCodigo() {
        return iapCodigo;
    }

    public void setIapCodigo(Long iapCodigo) {
        this.iapCodigo = iapCodigo;
    }

    public Integer getIapDiasHabilitada() {
        return iapDiasHabilitada;
    }

    public void setIapDiasHabilitada(Integer iapDiasHabilitada) {
        this.iapDiasHabilitada = iapDiasHabilitada;
    }

    public Integer getIapDiasReapertura() {
        return iapDiasReapertura;
    }

    public void setIapDiasReapertura(Integer iapDiasReapertura) {
        this.iapDiasReapertura = iapDiasReapertura;
    }

    public String getIapCopiaA() {
        return iapCopiaA;
    }

    public void setIapCopiaA(String iapCopiaA) {
        this.iapCopiaA = iapCopiaA;
    }

    public String getIapAsunto() {
        return iapAsunto;
    }

    public void setIapAsunto(String iapAsunto) {
        this.iapAsunto = iapAsunto;
    }

    public String getIapAsuntoReapertura() {
        return iapAsuntoReapertura;
    }

    public void setIapAsuntoReapertura(String iapAsuntoReapertura) {
        this.iapAsuntoReapertura = iapAsuntoReapertura;
    }

    public String getIapAsuntoCierre() {
        return iapAsuntoCierre;
    }

    public void setIapAsuntoCierre(String iapAsuntoCierre) {
        this.iapAsuntoCierre = iapAsuntoCierre;
    }

    public String getIapMensajeCierre() {
        return iapMensajeCierre;
    }

    public void setIapMensajeCierre(String iapMensajeCierre) {
        this.iapMensajeCierre = iapMensajeCierre;
    }

    public String getIapAsuntoFinalizacion() {
        return iapAsuntoFinalizacion;
    }

    public void setIapAsuntoFinalizacion(String iapAsuntoFinalizacion) {
        this.iapAsuntoFinalizacion = iapAsuntoFinalizacion;
    }

    public String getIapMensajeFinalizacion() {
        return iapMensajeFinalizacion;
    }

    public void setIapMensajeFinalizacion(String iapMensajeFinalizacion) {
        this.iapMensajeFinalizacion = iapMensajeFinalizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iapCodigo != null ? iapCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfanualParametros)) {
            return false;
        }
        InfanualParametros other = (InfanualParametros) object;
        if ((this.iapCodigo == null && other.iapCodigo != null) || (this.iapCodigo != null && !this.iapCodigo.equals(other.iapCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.infoanual.entities.InfanualParametros[ iapCodigo=" + iapCodigo + " ]";
    }
    
}
