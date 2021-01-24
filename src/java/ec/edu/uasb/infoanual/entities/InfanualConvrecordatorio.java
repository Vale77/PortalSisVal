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
@Table(name = "INFANUAL_CONVRECORDATORIO", schema = "GESTIONACADEMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfanualConvrecordatorio.findAll", query = "SELECT i FROM InfanualConvrecordatorio i"),
    @NamedQuery(name = "InfanualConvrecordatorio.findByIarCodigo", query = "SELECT i FROM InfanualConvrecordatorio i WHERE i.iarCodigo = :iarCodigo"),
    @NamedQuery(name = "InfanualConvrecordatorio.findByIarNumRecordatorio", query = "SELECT i FROM InfanualConvrecordatorio i WHERE i.iarNumRecordatorio = :iarNumRecordatorio"),
    @NamedQuery(name = "InfanualConvrecordatorio.findByIarDiasRecordatorio", query = "SELECT i FROM InfanualConvrecordatorio i WHERE i.iarDiasRecordatorio = :iarDiasRecordatorio"),
    @NamedQuery(name = "InfanualConvrecordatorio.findByIarMensaje", query = "SELECT i FROM InfanualConvrecordatorio i WHERE i.iarMensaje = :iarMensaje"),
    @NamedQuery(name = "InfanualConvrecordatorio.findByIarDiasReapertura", query = "SELECT i FROM InfanualConvrecordatorio i WHERE i.iarDiasReapertura = :iarDiasReapertura"),
    @NamedQuery(name = "InfanualConvrecordatorio.findByIarMensajeReapertura", query = "SELECT i FROM InfanualConvrecordatorio i WHERE i.iarMensajeReapertura = :iarMensajeReapertura"),
    @NamedQuery(name = "InfanualConvrecordatorio.findByIarEstadoRecordatorio", query = "SELECT i FROM InfanualConvrecordatorio i WHERE i.iarEstadoRecordatorio = :iarEstadoRecordatorio")})
public class InfanualConvrecordatorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IAR_CODIGO")
    private Long iarCodigo;
    @Column(name = "IAR_NUM_RECORDATORIO")
    private Integer iarNumRecordatorio;
    @Column(name = "IAR_DIAS_RECORDATORIO")
    private Integer iarDiasRecordatorio;
    @Size(max = 8000)
    @Column(name = "IAR_MENSAJE")
    private String iarMensaje;
    @Column(name = "IAR_DIAS_REAPERTURA")
    private Integer iarDiasReapertura;
    @Size(max = 8000)
    @Column(name = "IAR_MENSAJE_REAPERTURA")
    private String iarMensajeReapertura;
    @Column(name = "IAR_ESTADO_RECORDATORIO")
    private Character iarEstadoRecordatorio;
    @Column(name = "IAR_TIPO_CONTRATO")
    private Integer iarTipoContrato;

    public String getnomestado() {
        String variable = "";
        if (this.iarEstadoRecordatorio != null) {
            switch (this.iarEstadoRecordatorio) {
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

    public InfanualConvrecordatorio() {
    }

    public InfanualConvrecordatorio(Long iarCodigo) {
        this.iarCodigo = iarCodigo;
    }

    public Integer getIarTipoContrato() {
        return iarTipoContrato;
    }

    public void setIarTipoContrato(Integer iarTipoContrato) {
        this.iarTipoContrato = iarTipoContrato;
    }

    public Long getIarCodigo() {
        return iarCodigo;
    }

    public void setIarCodigo(Long iarCodigo) {
        this.iarCodigo = iarCodigo;
    }

    public Integer getIarNumRecordatorio() {
        return iarNumRecordatorio;
    }

    public void setIarNumRecordatorio(Integer iarNumRecordatorio) {
        this.iarNumRecordatorio = iarNumRecordatorio;
    }

    public Integer getIarDiasRecordatorio() {
        return iarDiasRecordatorio;
    }

    public void setIarDiasRecordatorio(Integer iarDiasRecordatorio) {
        this.iarDiasRecordatorio = iarDiasRecordatorio;
    }

    public String getIarMensaje() {
        return iarMensaje;
    }

    public void setIarMensaje(String iarMensaje) {
        this.iarMensaje = iarMensaje;
    }

    public Integer getIarDiasReapertura() {
        return iarDiasReapertura;
    }

    public void setIarDiasReapertura(Integer iarDiasReapertura) {
        this.iarDiasReapertura = iarDiasReapertura;
    }

    public String getIarMensajeReapertura() {
        return iarMensajeReapertura;
    }

    public void setIarMensajeReapertura(String iarMensajeReapertura) {
        this.iarMensajeReapertura = iarMensajeReapertura;
    }

    public Character getIarEstadoRecordatorio() {
        return iarEstadoRecordatorio;
    }

    public void setIarEstadoRecordatorio(Character iarEstadoRecordatorio) {
        this.iarEstadoRecordatorio = iarEstadoRecordatorio;
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
        if (!(object instanceof InfanualConvrecordatorio)) {
            return false;
        }
        InfanualConvrecordatorio other = (InfanualConvrecordatorio) object;
        if ((this.iarCodigo == null && other.iarCodigo != null) || (this.iarCodigo != null && !this.iarCodigo.equals(other.iarCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.infoanual.entities.InfanualConvrecordatorio[ iarCodigo=" + iarCodigo + " ]";
    }

}
