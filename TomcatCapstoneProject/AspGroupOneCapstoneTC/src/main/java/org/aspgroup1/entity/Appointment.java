package org.aspgroup1.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Appointment")
public class Appointment {
    @Id
    @Column(name="appID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long appID;
    
    @Column(name="firstName")
    private String firstName;
    
    @Column(name="lastName")
    private String lastName;
    
    @Column(name="appDate")
    private String appDate;
    
    @Column(name="appTime")
    private String appTime;
    
    @Column(name="reasonForVisit")
    private String reasonForVisit;
    
    @Column(name="doctorSeen")
    private String doctorSeen;
    
    
    public Appointment(){
        
    }

    public long getAppID() {
        return appID;
    }
    public void setAppID(long appID) {
        this.appID = appID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public String getDoctorSeen() {
        return doctorSeen;
    }
    public void setDoctorSeen(String doctorSeen) {
        this.doctorSeen = doctorSeen;
    }
    
}
