/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aspgroup1.persistenceBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.aspgroup1.crud.DoctorCrud;
import org.aspgroup1.crud.ScheduleCrud;
import org.aspgroup1.entity.Doctor;
import org.aspgroup1.entity.Schedule;
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
    
    private boolean mondayBool;
    private boolean tuesdayBool;
    private boolean wednesdayBool;
    private boolean thursdayBool;
    private boolean fridayBool;
    private boolean saturdayBool;
    private boolean sundayBool;
    
    
    public doctorPersistBean(){
        dc = new DoctorCrud();
        doctorsL = getDoctorsL();
    }
    
    
    public void createDoc(){
        int availableDays[] = new int[7];
        //Convert Booleans
        availableDays[0] = UtilityMethods.convertBool(mondayBool);
        availableDays[1] = UtilityMethods.convertBool(tuesdayBool);
        availableDays[2] = UtilityMethods.convertBool(wednesdayBool);
        availableDays[3] = UtilityMethods.convertBool(thursdayBool);
        availableDays[4] = UtilityMethods.convertBool(fridayBool);
        availableDays[5] = UtilityMethods.convertBool(saturdayBool);
        availableDays[6] = UtilityMethods.convertBool(sundayBool);
        
        
        doctorDOB = UtilityMethods.convertDate(doctorDOB);
        dc.createDoctor(this.doctorFN, this.doctorLN, this.doctorS, this.doctorDOB, this.doctorPN, availableDays);
        doctorsL = getDoctorsL();
        clearValues();
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
    
    public String getDoctorWorkDays(long docID)
    {
        String workDays = "";
        ScheduleCrud sc = new ScheduleCrud();
        Schedule schObj = sc.findByID(docID);
        int daysAvailable[] = new int[7];
        
        daysAvailable[0] = schObj.getMon();
        daysAvailable[1] = schObj.getTue();
        daysAvailable[2] = schObj.getWed();
        daysAvailable[3] = schObj.getThur();
        daysAvailable[4] = schObj.getFri();
        daysAvailable[5] = schObj.getSat();
        daysAvailable[6] = schObj.getSun();
        
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        
        for(int i = 0; i < 6; i++)
        {
            if(daysAvailable[i] == 1)
            {
                workDays += days[i] + " ";
            }
        }
        
        return workDays;
    }
    
    public void clearValues()
    {
        this.doctorFN = "";
        this.doctorLN = "";
        this.doctorS = "";
        this.doctorDOB = "";
        this.doctorPN = "";
        this.mondayBool = false;
        this.tuesdayBool = false;
        this.wednesdayBool = false;
        this.thursdayBool = false;
        this.fridayBool = false;
        this.saturdayBool = false;
        this.sundayBool = false;
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

    public boolean isMondayBool()
    {
        return mondayBool;
    }

    public void setMondayBool(boolean mondayBool)
    {
        this.mondayBool = mondayBool;
    }

    public boolean isTuesdayBool()
    {
        return tuesdayBool;
    }

    public void setTuesdayBool(boolean tuesdayBool)
    {
        this.tuesdayBool = tuesdayBool;
    }

    public boolean isWednesdayBool()
    {
        return wednesdayBool;
    }

    public void setWednesdayBool(boolean wednesdayBool)
    {
        this.wednesdayBool = wednesdayBool;
    }

    public boolean isThursdayBool()
    {
        return thursdayBool;
    }

    public void setThursdayBool(boolean thursdayBool)
    {
        this.thursdayBool = thursdayBool;
    }

    public boolean isFridayBool()
    {
        return fridayBool;
    }

    public void setFridayBool(boolean fridayBool)
    {
        this.fridayBool = fridayBool;
    }

    public boolean isSaturdayBool()
    {
        return saturdayBool;
    }

    public void setSaturdayBool(boolean saturdayBool)
    {
        this.saturdayBool = saturdayBool;
    }

    public boolean isSundayBool()
    {
        return sundayBool;
    }

    public void setSundayBool(boolean sundayBool)
    {
        this.sundayBool = sundayBool;
    }
    
    
}
