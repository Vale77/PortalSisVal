/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "RESPUESTA", schema = "EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuesta.findAll", query = "SELECT r FROM Respuesta r"),
    @NamedQuery(name = "Respuesta.findByAnio", query = "SELECT r FROM Respuesta r WHERE r.respuestaPK.anio = :anio"),
    @NamedQuery(name = "Respuesta.findByCiclo", query = "SELECT r FROM Respuesta r WHERE r.respuestaPK.ciclo = :ciclo"),
    @NamedQuery(name = "Respuesta.findByCodigoMateria", query = "SELECT r FROM Respuesta r WHERE r.respuestaPK.codigoMateria = :codigoMateria"),
    @NamedQuery(name = "Respuesta.findByNumeroEncuesta", query = "SELECT r FROM Respuesta r WHERE r.respuestaPK.numeroEncuesta = :numeroEncuesta"),
    @NamedQuery(name = "Respuesta.findByCodigoPregunta", query = "SELECT r FROM Respuesta r WHERE r.respuestaPK.codigoPregunta = :codigoPregunta"),
    @NamedQuery(name = "Respuesta.findByCodigoEncuesta", query = "SELECT r FROM Respuesta r WHERE r.respuestaPK.codigoEncuesta = :codigoEncuesta"),
    @NamedQuery(name = "Respuesta.findByCodigoProfesor", query = "SELECT r FROM Respuesta r WHERE r.respuestaPK.codigoProfesor = :codigoProfesor"),
    @NamedQuery(name = "Respuesta.findByValor", query = "SELECT r FROM Respuesta r WHERE r.valor = :valor"),
    @NamedQuery(name = "Respuesta.findByTexto", query = "SELECT r FROM Respuesta r WHERE r.texto = :texto"),
    @NamedQuery(name = "Respuesta.findByFecha", query = "SELECT r FROM Respuesta r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Respuesta.findByCodigoEsp", query = "SELECT r FROM Respuesta r WHERE r.codigoEsp = :codigoEsp"),
    @NamedQuery(name = "Respuesta.findByCodigoNivel", query = "SELECT r FROM Respuesta r WHERE r.codigoNivel = :codigoNivel"),
    @NamedQuery(name = "Respuesta.countByCodigoEncuesta", query = "SELECT count(distinct r.respuestaPK.codigoEncuesta) FROM Respuesta r WHERE r.respuestaPK.codigoEncuesta = :codigoEncuesta"),
    @NamedQuery(name = "Respuesta.countByCodEncuPreg", query = "SELECT count(distinct r.respuestaPK.codigoPregunta) FROM Respuesta r WHERE r.respuestaPK.codigoEncuesta = :codigoEncuesta and  r.respuestaPK.codigoPregunta = :codigoPregunta")})
public class Respuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RespuestaPK respuestaPK;
    @Column(name = "VALOR")
    private Long valor;
    @Size(max = 8000)
    @Column(name = "TEXTO")
    private String texto;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "CODIGO_ESP")
    private Long codigoEsp;
    @Column(name = "CODIGO_NIVEL")
    private Long codigoNivel;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PREGUNTA", referencedColumnName = "CODIGO_PREGUNTA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_ENCUESTA", referencedColumnName = "CODIGO_ENCUESTA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Pregunta pregunta;

    public Respuesta() {
    }

    public Respuesta(RespuestaPK respuestaPK) {
        this.respuestaPK = respuestaPK;
    }

    public Respuesta(long anio, long ciclo, long codigoMateria, long numeroEncuesta, long codigoPregunta, long codigoEncuesta, long codigoProfesor) {
        this.respuestaPK = new RespuestaPK(anio, ciclo, codigoMateria, numeroEncuesta, codigoPregunta, codigoEncuesta, codigoProfesor);
    }

    public RespuestaPK getRespuestaPK() {
        return respuestaPK;
    }

    public void setRespuestaPK(RespuestaPK respuestaPK) {
        this.respuestaPK = respuestaPK;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getCodigoEsp() {
        return codigoEsp;
    }

    public void setCodigoEsp(Long codigoEsp) {
        this.codigoEsp = codigoEsp;
    }

    public Long getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(Long codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (respuestaPK != null ? respuestaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuesta)) {
            return false;
        }
        Respuesta other = (Respuesta) object;
        if ((this.respuestaPK == null && other.respuestaPK != null) || (this.respuestaPK != null && !this.respuestaPK.equals(other.respuestaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.sisevaluacion.entities.Respuesta[ respuestaPK=" + respuestaPK + " ]";
    }
    
}
