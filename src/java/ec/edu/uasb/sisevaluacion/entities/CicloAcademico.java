/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "CICLO_ACADEMICO")
@NamedQueries({
    @NamedQuery(name = "CicloAcademico.findAll", query = "SELECT c FROM CicloAcademico c"),
    @NamedQuery(name = "CicloAcademico.findByEstadoCiclo", query = "SELECT c FROM CicloAcademico c WHERE c.estadoCiclo = :estadoCiclo")})

public class CicloAcademico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ANIO")
    @Id
    private Long anio;
    @Size(max = 384)
    @Column(name = "CICLO")
    private String ciclo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_CICLO")
    private String estadoCiclo;
    @Size(max = 27)
    @Column(name = "F_FINAL")
    private String fFinal;
    @Size(max = 27)
    @Column(name = "F_INICIO")
    private String fInicio;
    @Size(max = 7)
    @Column(name = "NOMBRE_CICLO")
    private String nombreCiclo;
    @Size(max = 384)
    @Column(name = "NR_FORMULARIO")
    private String nrFormulario;
    @Size(max = 384)
    @Column(name = "NR_INSCRIPCION")
    private String nrInscripcion;
    @Size(max = 384)
    @Column(name = "NR_MATRICULA")
    private String nrMatricula;
    @Column(name = "COD_EJERCICIO")
    private Short codEjercicio;

    public CicloAcademico() {
    }

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

   
    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getEstadoCiclo() {
        return estadoCiclo;
    }

    public void setEstadoCiclo(String estadoCiclo) {
        this.estadoCiclo = estadoCiclo;
    }

    public String getFFinal() {
        return fFinal;
    }

    public void setFFinal(String fFinal) {
        this.fFinal = fFinal;
    }

    public String getFInicio() {
        return fInicio;
    }

    public void setFInicio(String fInicio) {
        this.fInicio = fInicio;
    }

    public String getNombreCiclo() {
        return nombreCiclo;
    }

    public void setNombreCiclo(String nombreCiclo) {
        this.nombreCiclo = nombreCiclo;
    }

    public String getNrFormulario() {
        return nrFormulario;
    }

    public void setNrFormulario(String nrFormulario) {
        this.nrFormulario = nrFormulario;
    }

    public String getNrInscripcion() {
        return nrInscripcion;
    }

    public void setNrInscripcion(String nrInscripcion) {
        this.nrInscripcion = nrInscripcion;
    }

    public String getNrMatricula() {
        return nrMatricula;
    }

    public void setNrMatricula(String nrMatricula) {
        this.nrMatricula = nrMatricula;
    }

    public Short getCodEjercicio() {
        return codEjercicio;
    }

    public void setCodEjercicio(Short codEjercicio) {
        this.codEjercicio = codEjercicio;
    }
    
}
