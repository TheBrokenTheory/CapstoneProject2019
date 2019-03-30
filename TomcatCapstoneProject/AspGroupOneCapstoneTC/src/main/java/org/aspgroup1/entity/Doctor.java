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
@Table(name="Doctor")
public class Doctor implements Serializable {
    
    @Id
    @Column(name="doctorID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long doctorID;
    
    @Column(name="doctorFirstName")
    private String doctorFirstName;
    
    @Column(name="doctorLastName")
    private String doctorLastName;
    
    @Column(name="doctorSpecialty")
    private String doctorSpecialty;
    
    @Column(name="doctorDOB")
    private Date doctorDOB;
    
    @Column(name="doctorPhoneNum")
    private String doctorPhoneNum;

    public Doctor() {
    }
    
    public long getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(long doctorID) {
        this.doctorID = doctorID;
    }
    public String getDoctorFirstName() {
        return doctorFirstName;
    }
    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }
    public String getDoctorLastName() {
        return doctorLastName;
    }
    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }
    public String getDoctorSpecialty() {
        return doctorSpecialty;
    }
    public void setDoctorSpecialty(String doctorSpecialty) {
        this.doctorSpecialty = doctorSpecialty;
    }
    public Date getDoctorDOB() {
        return doctorDOB;
    }
    public void setDoctorDOB(Date doctorDOB) {
        this.doctorDOB = doctorDOB;
    }
    public String getDoctorPhoneNum() {
        return doctorPhoneNum;
    }
    public void setDoctorPhoneNum(String doctorPhoneNum) {
        this.doctorPhoneNum = doctorPhoneNum;
    }
}
