/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.publicacionDGA.session;

import ec.edu.uasb.publicacionDGA.entities.GeacPublicacionDga;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface GeacPublicacionDgaFacadeLocal {

    void create(GeacPublicacionDga geacPublicacionDga);

    void edit(GeacPublicacionDga geacPublicacionDga);

    void remove(GeacPublicacionDga geacPublicacionDga);

    GeacPublicacionDga find(Object id);

    List<GeacPublicacionDga> findAll();

    List<GeacPublicacionDga> findRange(int[] range);

    int count();

    public List<GeacPublicacionDga> findAllbyPersona(String tipPersona, String cedPersona);

    public List<GeacPublicacionDga> findAllOrde();
    
}
