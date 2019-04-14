package org.aspgroup1.persistenceBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.aspgroup1.crud.PatientCrud;
import org.aspgroup1.entity.Patient;
import org.aspgroup1.utilities.UtilityMethods;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean(name = "patientBean")
@RequestScoped
public class patientPersistBean {
    List patientL;
    Patient patObj;
    PatientCrud pc;
    
    private long patientID;
    private String patientFN;
    private String patientLN;
    private String patientDOB;
    private String patientAddress;
    private String patientPN;
    private String patientInsur;
    private int numPatients;
    
    public patientPersistBean()
    {
        pc = new PatientCrud();
        getPatientsL();
    }
    
    public void createPatient()
    {
        patientDOB = UtilityMethods.convertDate(patientDOB);
        pc.createPatient(this.patientFN, this.patientLN,
                this.patientDOB, this.patientAddress, this.patientPN, this.patientInsur);
        patientL = getPatientsL();  
    }
    
    public List getPatientsL(){
        patientL = new ArrayList(pc.getPatients());
        updateNumPatients();
        return patientL;
    }
    
    public void updateNumPatients()
    {
        this.numPatients = patientL.size();
    }
    
    public Patient getPatient()
    {
        patObj = pc.findByID(patientID);
        return patObj;
    }
    
    public void deletePatient()
    {
        pc.deletePatient(this.patientID);
        patientL = getPatientsL();  
    }
  
    public void clearValues()
    {
        this.patientFN = "";
        this.patientLN = "";
        this.patientDOB = "";
        this.patientAddress = "";
        this.patientPN = "";
        this.patientInsur = "";
    }
    
    //Getters and Setters
    public long getPatientID()
    {
        return patientID;
    }

    public void setPatientID(long patientID)
    {
        this.patientID = patientID;
    }

    public String getPatientFN()
    {
        return patientFN;
    }

    public void setPatientFN(String patientFN)
    {
        this.patientFN = patientFN;
    }

    public String getPatientLN()
    {
        return patientLN;
    }

    public void setPatientLN(String patientLN)
    {
        this.patientLN = patientLN;
    }

    public String getPatientDOB()
    {
        return patientDOB;
    }

    public void setPatientDOB(String patientDOB)
    {
        this.patientDOB = patientDOB;
    }

    public String getPatientAddress()
    {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress)
    {
        this.patientAddress = patientAddress;
    }

    public String getPatientPN()
    {
        return patientPN;
    }

    public void setPatientPN(String patientPN)
    {
        this.patientPN = patientPN;
    }

    public String getPatientInsur()
    {
        return patientInsur;
    }

    public void setPatientInsur(String patientInsur)
    {
        this.patientInsur = patientInsur;
    }

    public int getNumPatients()
    {
        return numPatients;
    }

    public void setNumPatients(int numPatients)
    {
        this.numPatients = numPatients;
    }

    public List getPatientL()
    {
        return patientL;
    }

    public void setPatientL(List patientL)
    {
        this.patientL = patientL;
    }
    
}
