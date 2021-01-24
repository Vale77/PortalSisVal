/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.infoanual.managedBean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "fileDownloadView")
@ViewScoped
public class descargaArchivoJSFManagedBean implements Serializable{

     private StreamedContent file;
     
    public descargaArchivoJSFManagedBean() {       
       
        //InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/images/chupon.jpeg");
        //InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/images/chupon.jpeg");     
       // file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_boromir.jpg");
     
//         InputStream stream = null;
//        try {
//            stream = new FileInputStream("D:\\opt\\Documentos\\InformeAnual\\Publicaciones\\911_LANDIN PAREDESRENATO SEBASTIAN\\13375_Lib_InformeBiometrico086Posterior.pdf");
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }        
//        file = new DefaultStreamedContent(stream, "application/pdf",
//                "downloaded_optimus.pdf");
//        System.out.println("Fie" + file);                
    }
 
    public StreamedContent getFile() {
        return file;
    }
    

public void processAction(ActionEvent actionEvent) throws AbortProcessingException {
   
    InputStream stream = null;
        try {
            stream = new FileInputStream("D:\\opt\\Documentos\\InformeAnual\\Publicaciones\\911_LANDIN PAREDESRENATO SEBASTIAN\\13375_Lib_InformeBiometrico086Posterior.pdf");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
        file = new DefaultStreamedContent(stream, "application/pdf",
                "downloaded_optimus1.pdf");
           
}
    
}
