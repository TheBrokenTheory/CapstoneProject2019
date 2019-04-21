package org.aspgroup1.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;


@Entity
@Table(name="Schedule")
@Proxy(lazy = false)
public class Schedule implements Serializable{
    
    @Id
    @Column(name="doctorID")
    private long doctorID;
    
    @Column(name="mon")
    private int mon;
    
    @Column(name="tue")
    private int tue;
    
    @Column(name="wed")
    private int wed;
    
    @Column(name="thur")
    private int thur;
    
    @Column(name="fri")
    private int fri;
    
    @Column(name="sat")
    private int sat;
    
    @Column(name="sun")
    private int sun;

    
    public Schedule(){
        
    }
    
    
    public long getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(long doctorID) {
        this.doctorID = doctorID;
    }
    public int getMon() {
        return mon;
    }
    public void setMon(int mon) {
        this.mon = mon;
    }
    public int getTue() {
        return tue;
    }
    public void setTue(int tue) {
        this.tue = tue;
    }
    public int getWed() {
        return wed;
    }
    public void setWed(int wed) {
        this.wed = wed;
    }
    public int getThur() {
        return thur;
    }
    public void setThur(int thur) {
        this.thur = thur;
    }
    public int getFri() {
        return fri;
    }
    public void setFri(int fri) {
        this.fri = fri;
    }
    public int getSat() {
        return sat;
    }
    public void setSat(int sat) {
        this.sat = sat;
    }
    public int getSun() {
        return sun;
    }
    public void setSun(int sun) {
        this.sun = sun;
    }
    
    
    
}
