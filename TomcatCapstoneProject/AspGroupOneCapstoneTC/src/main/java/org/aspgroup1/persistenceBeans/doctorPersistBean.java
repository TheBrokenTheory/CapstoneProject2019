/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aspgroup1.persistenceBeans;

import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.aspgroup1.crud.DoctorCrud;


@ManagedBean(name = "docBean")
@Dependent
public class doctorPersistBean {

    ListDataModel doctors;
    
    public doctorPersistBean() {
        
    }
    
    public DataModel getDoctors(){
        
        
        if(doctors == null){
            System.out.println("BEAN ACTIVITY!");
            System.out.println("BEAN ACTIVITY!");
            doctors = new ListDataModel(DoctorCrud.getDoctors());
            System.out.println("BEAN ACTIVITY!");
            System.out.println("BEAN ACTIVITY!");
            System.out.println("BEAN ACTIVITY!");
        }
        return doctors;
    }
    
}
