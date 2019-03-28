/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aspgroup1.persistenceBeans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import org.aspgroup1.crud.DoctorCrud;


/**
 *
 * @author tfran
 */
@ManagedBean(name = "docBean")
@RequestScoped
public class doctorPersistBean {

    List doctorsL;
    private long doctorID;
    private String doctorFN;
    private String doctorLN;
    private String doctorS;
    private Date doctorDOB;
    private String doctorPN;
    
    
    public doctorPersistBean() {
        
    }
    
    public List getDoctorsL(){
        if(doctorsL == null){
            doctorsL = new ArrayList(DoctorCrud.getDoctors());
        }
        
        return doctorsL;
    }
    
    public String createDoc(){
        
        DoctorCrud.createDoctor(doctorID, doctorFN, doctorLN, doctorS, doctorDOB, doctorPN);
        
        return "databaseTestPage";
    }

    public long getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(long doctorID) {
        this.doctorID = doctorID;
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
