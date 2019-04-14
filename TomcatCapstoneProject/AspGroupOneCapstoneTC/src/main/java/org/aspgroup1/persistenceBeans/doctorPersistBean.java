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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.aspgroup1.crud.DoctorCrud;
import org.aspgroup1.entity.Doctor;


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
        doctorDOB = convertDate(doctorDOB);
        dc.createDoctor(this.doctorFN, this.doctorLN, this.doctorS, this.doctorDOB, this.doctorPN);
        System.out.println(doctorDOB);
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
    
     //Converts date to proper format for fullCalendar
    private String convertDate(String fullString)
    {  
        //Substring locations
        //Mon 4-7 Day 8-10 Year 24 - 28
        
        //Gets substrings from the date/time input
        String strMonth = fullString.substring(4, 7);
        
        //Converts month from 3 lttr abbriviation to number
        String month = convertMonth(strMonth);
        String day = fullString.substring(8, 10);
        String year = fullString.substring(24, 28);
        
        //Date compatable with fullCal component
        String date = year + "-" + month + "-" + day;
        return date;
    }
    
    //Convert month from abbriviation to number
    private String convertMonth(String abbrMonth)
    {
        String month = "";
        
        switch (abbrMonth)
        {
            case "Jan":
                month = "01";
                break;
            case "Feb":
                month = "02";
                break;
            case "Mar":
                month = "03";
                break;
            case "Apr":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "Jun":
                month = "06";
                break;
            case "Jul":
                month = "07";
                break;
            case "Aug":
                month = "08";
                break;
            case "Sep":
                month = "09";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
            case "Dec":
                month = "12";
                break;
        }
        
        return month;
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
