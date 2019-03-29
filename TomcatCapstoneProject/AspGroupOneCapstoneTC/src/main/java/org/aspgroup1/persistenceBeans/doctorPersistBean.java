/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aspgroup1.persistenceBeans;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

import org.aspgroup1.crud.DoctorCrud;


@ManagedBean(name = "docBean")
@Dependent
public class doctorPersistBean implements Serializable {

    List doctorsL;
    private long doctorIDB;
    private String doctorFN;
    private String doctorLN;
    private String doctorS;
    private Date doctorDOB;
    private String doctorPN;
    
    public List getDoctorsL(){
        if(doctorsL == null){
            doctorsL = new ArrayList(DoctorCrud.getDoctors());
        }
        
        return doctorsL;
    }
    
    public String createDoc(){
        
        DoctorCrud.createDoctor(this.doctorFN, this.doctorLN, this.doctorS, this.doctorDOB, this.doctorPN);
        
        return "databaseTestPage";
    }

    public long getDoctorIDB() {
        return doctorIDB;
    }
    public void setDoctorIDB(long doctorIDB) {
        this.doctorIDB = doctorIDB;
    }

    public String getDoctorFN() {
        return doctorFN;
    }
    public void setDoctorFN(String doctorFN) {
        this.doctorFN = doctorFN;
    }
    public String getDoctorLN() {
        return doctorLN;
    }
    public void setDoctorLN(String doctorLN) {
        this.doctorLN = doctorLN;
    }
    public String getDoctorS() {
        return doctorS;
    }
    public void setDoctorS(String doctorS) {
        this.doctorS = doctorS;
    }
    public Date getDoctorDOB() {
        return doctorDOB;
    }
    public void setDoctorDOB(Date doctorDOB) {
        this.doctorDOB = doctorDOB;
    }
    public String getDoctorPN() {
        return doctorPN;
    }
    public void setDoctorPN(String doctorPN) {
        this.doctorPN = doctorPN;
    }
    
}
