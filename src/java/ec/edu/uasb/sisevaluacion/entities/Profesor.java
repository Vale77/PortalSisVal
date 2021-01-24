/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.entities;



import ec.edu.uasb.infoanual.entities.InfAnualRealizadoPK;
import ec.edu.uasb.infoanual.entities.InfanualRealizado;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 *
 * @author johana.orozco
 */
@Entity
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODIGO_PROFESOR")
    private int codigoProfesor;
    @Column(name = "NOMBRE_PROFESOR")
    private String nombreProfesor;
    

    public Profesor() {
    }

    public int getCodigoProfesor() {
        return codigoProfesor;
    }

    public void setCodigoProfesor(int codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

   

  
}
