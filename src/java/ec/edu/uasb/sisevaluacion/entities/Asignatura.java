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
public class Asignatura implements Serializable{
    private static final long serialVersionUID = 1L;
     @Id
    @Column (name="CODIGO_ASIGNATURA")
    private int codigoAsignatura;
    @Column (name="NOMBRE_ASIGNATURA")
    private String nombreAsignatura;

    public int getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(int codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }
    
}
