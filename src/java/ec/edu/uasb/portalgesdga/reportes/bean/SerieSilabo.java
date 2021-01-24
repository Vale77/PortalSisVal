/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdga.reportes.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author victor.barba
 */
@Entity
public class SerieSilabo implements Serializable {

    @EmbeddedId
    private SerieSilaboPK serieSilaboPK;
    @Column(name = "PROGRAMA")
    private String programa;
    @Column(name = "TRIMESTRE")
    private String trimestre;
    @Column(name = "NO_REGISTRADAS")
    private Long noRegis;
    @Column(name = "REGISTRADAS")
    private Long registradas;
    @Column(name = "POR_APROBAR")
    private Long porAbropar;
    @Column(name = "APROBADAS")
    private Long aprobadas;

    public SerieSilabo() {
    }

    public SerieSilabo(SerieSilaboPK serieSilaboPK, String programa, String trimestre, Long noRegis, Long porAbropar,
            Long aprobadas, Long registradas) {
        this.serieSilaboPK = serieSilaboPK;
        this.programa = programa;
        this.trimestre = trimestre;
        this.noRegis = noRegis;
        this.porAbropar = porAbropar;
        this.aprobadas = aprobadas;
        this.registradas = registradas;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public Long getNoRegis() {
        return noRegis;
    }

    public void setNoRegis(Long noRegis) {
        this.noRegis = noRegis;
    }

    public Long getPorAbropar() {
        return porAbropar;
    }

    public void setPorAbropar(Long porAbropar) {
        this.porAbropar = porAbropar;
    }

    public Long getAprobadas() {
        return aprobadas;
    }

    public void setAprobadas(Long aprobadas) {
        this.aprobadas = aprobadas;
    }

    public Long getRegistradas() {
        return registradas;
    }

    public void setRegistradas(Long registradas) {
        this.registradas = registradas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.serieSilaboPK != null ? this.serieSilaboPK.hashCode() : 0);
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
        final SerieSilabo other = (SerieSilabo) obj;
        if (this.serieSilaboPK != other.serieSilaboPK && (this.serieSilaboPK == null || !this.serieSilaboPK.equals(other.serieSilaboPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SerieSilabo{" + "serieSilaboPK=" + serieSilaboPK + ", programa=" + programa + ", trimestre=" + trimestre + ", noRegis=" + noRegis + ", registradas=" + registradas + ", porAbropar=" + porAbropar + ", aprobadas=" + aprobadas + '}';
    }


}
