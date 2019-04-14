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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.aspgroup1.crud.DoctorCrud;
import org.aspgroup1.entity.Doctor;
import org.aspgroup1.utilities.UtilityMethods;


@ManagedBean(name = "docBean")
@RequestScoped
public class doctorPersistBean implements Serializable {

    List doctorsL;
    Doctor docObj;
    DoctorCrud dc;
    private long doctorIDB;
    private String doctorFN;
    private String doctorLN;
    private String doctorS;
    private String doctorDOB;
    private String doctorPN;
    private int numDoc;
    
    public doctorPersistBean(){
        dc = new DoctorCrud();
        doctorsL = getDoctorsL();
    }
    
    
    public void createDoc(){
        doctorDOB = UtilityMethods.convertDate(doctorDOB);
        dc.createDoctor(this.doctorFN, this.doctorLN, this.doctorS, this.doctorDOB, this.doctorPN);
        doctorsL = getDoctorsL();
    }
    
    public List getDoctorsL(){
        doctorsL = new ArrayList(dc.getDoctors());
        updateNumDocs();
        return doctorsL;
    }
    
    public Doctor getDoctor(){
        docObj = dc.findByID(doctorIDB);
        
        return docObj;
    }
    
    public void deleteDoc(){
        dc.deleteDoctor(this.doctorIDB);
        doctorsL = getDoctorsL();
    }
    
    public String updateDoc(){
        
        dc.updateDoctor(this.doctorIDB, this.doctorFN, this.doctorLN, this.doctorS, this.doctorDOB, this.doctorPN);
        
        return "databaseTestPage";
    }
    
    public void updateNumDocs()
    {
        this.numDoc = doctorsL.size();
    }
    
    public void clearValues()
    {
        this.doctorFN = "";
        this.doctorLN = "";
        this.doctorS = "";
        this.doctorDOB = "";
        this.doctorPN = "";
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
    public String getDoctorDOB() {
        return doctorDOB;
    }
    public void setDoctorDOB(String doctorDOB) {
        this.doctorDOB = doctorDOB;
    }
    public String getDoctorPN() {
        return doctorPN;
    }
    public void setDoctorPN(String doctorPN) {
        this.doctorPN = doctorPN;
    }
    public int getNumDoc(){
        return numDoc;
    }
}
