package org.aspgroup1.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name="Appointment")
@Proxy(lazy = false)
public class Appointment implements Serializable{
    @Id
    @Column(name="appID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long appID;
    
    @Column(name="patientID")
    private long patientID;
    
    @Column(name="appDate")
    private String appDate;
    
    @Column(name="appTime")
    private String appTime;
    
    @Column(name="reasonForVisit")
    private String reasonForVisit;
    
    @Column(name="doctorID")
    private long doctorID;
    
    @Column(name="diagnosis")
    private String diagnosis;
    
    @Column(name="treatment")
    private String treatment;
    
    
    public Appointment(){
        
    }

    public long getAppID() {
        return appID;
    }
    public void setAppID(long appID) {
        this.appID = appID;
    }
    public long getPatientID() {
        return patientID;
    }
    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }
    public String getAppDate() {
        return appDate;
    }
    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }
    public String getAppTime() {
        return appTime;
    }
    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }
    public String getReasonForVisit() {
        return reasonForVisit;
    }
    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }
    public long getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(long doctorID) {
        this.doctorID = doctorID;
    }
    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public String getTreatment() {
        return treatment;
    }
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
    
}
