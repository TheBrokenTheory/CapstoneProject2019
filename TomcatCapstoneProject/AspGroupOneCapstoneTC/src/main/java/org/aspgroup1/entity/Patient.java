package org.aspgroup1.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Patient")
public class Patient implements Serializable {
    
    @Id
    @Column(name="patientID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long patientID;
       
    @Column(name="patientFirstName")
    private String patientFirstName;
       
    @Column(name="patientLastName")
    private String patientLastName;
       
    @Column(name="patientDOB")
    private String patientDOB;
       
    @Column(name="patientAddress")
    private String patientAddress;
       
    @Column(name="patientPhoneNum")
    private String patientPhoneNum;
       
    @Column(name="patientInsurance")
    private String patientInsurance;

    public Patient(){
        
    }
       
    public long getPatientID() {
        return patientID;
    }
    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }
    public String getPatientFirstName() {
        return patientFirstName;
    }
    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }
    public String getPatientLastName() {
        return patientLastName;
    }
    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }
    public String getPatientDOB() {
        return patientDOB;
    }
    public void setPatientDOB(String patientDOB) {
        this.patientDOB = patientDOB;
    }
    public String getPatientAddress() {
        return patientAddress;
    }
    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }
    public String getPatientPhoneNum() {
        return patientPhoneNum;
    }
    public void setPatientPhoneNum(String patientPhoneNum) {
        this.patientPhoneNum = patientPhoneNum;
    }
    public String getPatientInsurance() {
        return patientInsurance;
    }
    public void setPatientInsurance(String patintInsurance) {
        this.patientInsurance = patintInsurance;
    }
       
    
}
