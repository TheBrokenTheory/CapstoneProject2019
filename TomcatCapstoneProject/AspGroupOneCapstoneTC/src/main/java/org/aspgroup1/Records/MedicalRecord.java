package org.aspgroup1.Records;

import org.aspgroup1.crud.AppointmentCrud;
import org.aspgroup1.crud.DoctorCrud;
import org.aspgroup1.crud.PatientCrud;
import org.aspgroup1.entity.Appointment;
import org.aspgroup1.entity.Doctor;
import org.aspgroup1.entity.Patient;

/**
 *
 * @author Jonathan Anders
 */
public class MedicalRecord {
    
    AppointmentCrud ac;
    PatientCrud pc;
    DoctorCrud dc;
    
    
    private long patientID;
    private String patientFirstName;
    private String patientLastName;
    private String doctorLastName;
    private long doctorID;
    private String dateOfApt;
    private String timeOfApt;
    private String reasonForVisit;
    private String diagnosis;
    private String treatment;

    public MedicalRecord(long apptID)
    {
        ac = new AppointmentCrud();
        pc = new PatientCrud();
        dc = new DoctorCrud();
        
        generateMedicalRecord(apptID);
    }
    
    private void generateMedicalRecord(long apptID)
    {
        Appointment appt = ac.findByID(apptID);
        
        patientID = appt.getPatientID();
        Patient pat = pc.findByID(patientID);
        
        doctorID = appt.getDoctorID();
        if(dc.findByID(doctorID) != null)
        {
            Doctor doc = dc.findByID(doctorID);
            doctorLastName = doc.getDoctorLastName();
        }
        else
        {
            doctorID = 000000;
            doctorLastName = null;
        }
        
        
        patientFirstName = pat.getPatientFirstName();
        patientLastName = pat.getPatientLastName();
        dateOfApt = appt.getAppDate();
        timeOfApt = appt.getAppTime();
        reasonForVisit = appt.getReasonForVisit();
        diagnosis = appt.getDiagnosis();
        treatment = appt.getTreatment();
    }
 
    //Getters and setters
    public long getPatientID() {return patientID;}
    public void setPatientID(long patientID) {this.patientID = patientID;}
    public String getPatientFirstName() {return patientFirstName;}
    public void setPatientFirstName(String patientFirstName) {this.patientFirstName = patientFirstName;}
    public String getPatientLastName() {return patientLastName;}
    public void setPatientLastName(String patientLastName) {this.patientLastName = patientLastName;}
    public String getDoctorLastName() {return doctorLastName;}
    public void setDoctorLastName(String doctorLastName) {this.doctorLastName = doctorLastName;}
    public long getDoctorID() {return doctorID;}
    public void setDoctorID(long doctorID) {this.doctorID = doctorID;}
    public String getDateOfApt() {return dateOfApt;}
    public void setDateOfApt(String dateOfApt) {this.dateOfApt = dateOfApt;}
    public String getTimeOfApt() {return timeOfApt;}
    public void setTimeOfApt(String timeOfApt) {this.timeOfApt = timeOfApt;}
    public String getReasonForVisit() {return reasonForVisit;}
    public void setReasonForVisit(String reasonForVisit) {this.reasonForVisit = reasonForVisit;}
    public String getDiagnosis() {return diagnosis;}
    public void setDiagnosis(String diagnosis) {this.diagnosis = diagnosis;}
    public String getTreatment() {return treatment;}
    public void setTreatment(String treatment) {this.treatment = treatment;}

}
