/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "PREGUNTA", schema = "EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByCodigoPregunta", query = "SELECT p FROM Pregunta p WHERE p.preguntaPK.codigoPregunta = :codigoPregunta"),
    @NamedQuery(name = "Pregunta.findByCodigoEncuesta", query = "SELECT p FROM Pregunta p WHERE p.preguntaPK.codigoEncuesta = :codigoEncuesta"),
    @NamedQuery(name = "Pregunta.findByDescripcion", query = "SELECT p FROM Pregunta p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Pregunta.findByReferencia", query = "SELECT p FROM Pregunta p WHERE p.referencia = :referencia"),
    @NamedQuery(name = "Pregunta.findByTipo", query = "SELECT p FROM Pregunta p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Pregunta.findByPeso", query = "SELECT p FROM Pregunta p WHERE p.peso = :peso"),
    @NamedQuery(name = "Pregunta.findByFormato", query = "SELECT p FROM Pregunta p WHERE p.formato = :formato"),
    @NamedQuery(name = "Pregunta.findByNivel", query = "SELECT p FROM Pregunta p WHERE p.nivel = :nivel"),
    @NamedQuery(name = "Pregunta.findBySecpregunta", query = "SELECT p FROM Pregunta p WHERE p.secpregunta = :secpregunta"),
    @NamedQuery(name = "Pregunta.countByCodigoEncuesta", query = "SELECT count(distinct p.preguntaPK.codigoEncuesta) FROM Pregunta p WHERE p.preguntaPK.codigoEncuesta = :codigoEncuesta"),
@NamedQuery(name = "Pregunta.countByCodigoPregunta", query = "SELECT count(distinct p.preguntaPK.codigoPregunta) FROM Pregunta p WHERE p.preguntaPK.codigoEncuesta = :codigoEncuesta and p.preguntaPadre = :codigoPregunta")})
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreguntaPK preguntaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 20)
    @Column(name = "REFERENCIA")
    private String referencia;
    @Column(name = "TIPO")
    private Character tipo;
    @Column(name = "PESO")
    private Long peso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "FORMATO")
    private String formato;
    @Column(name = "NIVEL")
    private Long nivel;
    @Column(name = "SECPREGUNTA")
    private Long secpregunta;
    @Column(name = "PREGUNTA_PADRE")
    private Long preguntaPadre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta")
    private List<Respuesta> respuestaList;
    @JoinColumn(name = "CODIGO_ENCUESTA", referencedColumnName = "CODIGO_ENCUESTA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Encuesta encuesta;

    public Pregunta() {
    }

    public String getformato() {
        String variable = "";
        int longitud = 0;
        int n = 0;
        int i = 0;
        if (this.formato != null) {
            longitud = formato.trim().length();
            if (longitud == 1) {
                variable = formato.substring(0, 1);
            }
            if (longitud == 2) {
                variable = formato.substring(0, 2);
            }

            if (longitud > 2) {
                // variable = formato.substring(0, 2);
                while (n < longitud) {
                    if ((n % 2) == 0) {
                        variable = variable + formato.substring(i, n) + ".";
                        //variable = String.valueOf(n);
                        i = n;
                    }

                    n++;
                }
            }

        } else {
            variable = "S/V";
        }
        variable = formato;


        return variable;
    }

    public Pregunta(PreguntaPK preguntaPK) {
        this.preguntaPK = preguntaPK;
    }

    public Pregunta(PreguntaPK preguntaPK, String descripcion, String formato) {
        this.preguntaPK = preguntaPK;
        this.descripcion = descripcion;
        this.formato = formato;
    }

    public Pregunta(long codigoPregunta, long codigoEncuesta) {
        this.preguntaPK = new PreguntaPK(codigoPregunta, codigoEncuesta);
    }

    public PreguntaPK getPreguntaPK() {
        return preguntaPK;
    }

    public void setPreguntaPK(PreguntaPK preguntaPK) {
        this.preguntaPK = preguntaPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public Long getSecpregunta() {
        return secpregunta;
    }

    public void setSecpregunta(Long secpregunta) {
        this.secpregunta = secpregunta;
    }

    @XmlTransient
    public List<Respuesta> getRespuestaList() {
        return respuestaList;
    }

    public void setRespuestaList(List<Respuesta> respuestaList) {
        this.respuestaList = respuestaList;
    }

    public Long getPreguntaPadre() {
        return preguntaPadre;
    }

    public void setPreguntaPadre(Long preguntaPadre) {
        this.preguntaPadre = preguntaPadre;
    }

//    @XmlTransient
//    public List<Pregunta> getPreguntaList() {
//        return preguntaList;
//    }
//
//    public void setPreguntaList(List<Pregunta> preguntaList) {
//        this.preguntaList = preguntaList;
//    }
//
//    public Pregunta getPreguntaPadre() {
//        return preguntaPadre;
//    }
//
//    public void setPreguntaPadre(Pregunta preguntaPadre) {
//        this.preguntaPadre = preguntaPadre;
//    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preguntaPK != null ? preguntaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.preguntaPK == null && other.preguntaPK != null) || (this.preguntaPK != null && !this.preguntaPK.equals(other.preguntaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.sisevaluacion.entities.Pregunta[ preguntaPK=" + preguntaPK + " ]";
    }
}
