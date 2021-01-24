/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.entities;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 *
 * @author johana.orozco
 */

@Entity
public class VPrograma implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "CODIGO_PROGRAMA")
    private int codigoPrograma;
    @Column(name = "NOMBRE_PROGRAMA")
    private String nombrePrograma;
    @Column(name = "CODIGO_AREA")
    private String codigoArea;
    @Column(name = "ANIO")
    private int anio;

    public VPrograma() {
    }

    public int getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(int codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}

