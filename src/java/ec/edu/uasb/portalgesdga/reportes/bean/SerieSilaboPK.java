/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdga.reportes.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class SerieSilaboPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESP")
    private Long codPrograma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_NIVEL")    
    private Long codTrimestre;

    public SerieSilaboPK() {
    }

    public SerieSilaboPK(Long codPrograma, Long codTrimestre) {
        this.codPrograma = codPrograma;
        this.codTrimestre = codTrimestre;
    }

    public Long getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(Long codPrograma) {
        this.codPrograma = codPrograma;
    }

    public Long getCodTrimestre() {
        return codTrimestre;
    }

    public void setCodTrimestre(Long codTrimestre) {
        this.codTrimestre = codTrimestre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.codPrograma != null ? this.codPrograma.hashCode() : 0);
        hash = 97 * hash + (this.codTrimestre != null ? this.codTrimestre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SerieSilaboPK other = (SerieSilaboPK) obj;
        if (this.codPrograma != other.codPrograma && (this.codPrograma == null || !this.codPrograma.equals(other.codPrograma))) {
            return false;
        }
        if (this.codTrimestre != other.codTrimestre && (this.codTrimestre == null || !this.codTrimestre.equals(other.codTrimestre))) {
            return false;
        }
        return true;
    }
    
    
}
