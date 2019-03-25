/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aspgroup1.persistenceBeans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.aspgroup1.crud.DoctorCrud;


@Named(value = "docBean")
@Dependent
public class doctorPersistBean {

    DoctorCrud dc;
    ListDataModel doctors;
    
    public doctorPersistBean() {
        dc = new DoctorCrud();
    }
    
    public DataModel getDoctors(){
        if(doctors == null){
            doctors = new ListDataModel(dc.getDoctors());
            System.out.println("BEAN ACTIVITY!");
            System.out.println("BEAN ACTIVITY!");
            System.out.println("BEAN ACTIVITY!");
        }
        return doctors;
    }
    
}
