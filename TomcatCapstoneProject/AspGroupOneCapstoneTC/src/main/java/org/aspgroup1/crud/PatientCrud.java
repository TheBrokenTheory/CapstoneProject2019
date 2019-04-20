package org.aspgroup1.crud;

//Java Utility Imports
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.aspgroup1.HibernateUtilities.HibernateUtil;
import org.aspgroup1.entity.Patient;

//Hibernate Imports
import org.hibernate.Session;
import org.hibernate.annotations.Proxy;

public class PatientCrud {

    public PatientCrud(){
        
    }
    
    public void createPatient(String patientFN, String patientLN, String patientDOB, String patientAddress, String patientPhoneNum, String patientInsurance){
        Patient patObj;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            
            //Creating Doctor Object
            patObj = new Patient();
            patObj.setPatientFirstName(patientFN);
            patObj.setPatientLastName(patientLN);
            patObj.setPatientDOB(patientDOB);
            patObj.setPatientAddress(patientAddress);
            patObj.setPatientPhoneNum(patientPhoneNum);
            patObj.setPatientInsurance(patientInsurance);
            
            //Saving object information
            sessionObj.save(patObj);
            
            //Commit to DB
            sessionObj.getTransaction().commit();
            
            
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.print("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }
    
    public List getPatients(){
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        List<Patient> patientList = new ArrayList();
        
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            patientList = sessionObj.createQuery("FROM Patient").list();
            
            
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.print("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        
        return patientList;
    }
    
    public void updatePatient(long id, String patientFN, String patientLN, String patientDOB, String patientAddress, String patientPhoneNum, String patientInsurance){
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();

        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            // Creating Transaction Entity
            Patient patObj = (Patient) sessionObj.get(Patient.class, id);
            
            if(patObj.getPatientFirstName() != patientFN){ patObj.setPatientFirstName(patientFN); }
            if(patObj.getPatientLastName() != patientLN){ patObj.setPatientLastName(patientLN); }
            if(patObj.getPatientDOB() != patientDOB){ patObj.setPatientDOB(patientDOB); }
            if(patObj.getPatientAddress() != patientAddress) { patObj.setPatientAddress(patientAddress); }
            if(patObj.getPatientPhoneNum() != patientPhoneNum) { patObj.setPatientPhoneNum(patientPhoneNum); }            
            if(patObj.getPatientInsurance() != patientInsurance) { patObj.setPatientInsurance(patientInsurance); }
            
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            
            System.out.print("\nPatient With First Name?= " + patObj.getPatientFirstName()+ " Is Successfully Updated In The Database!\n");
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.print("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }
    
    public void deletePatient(long id){
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            Patient patObj = findByID(id);
            sessionObj.delete(patObj);
 
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            

            System.out.print("\nPatient With First Name?= " + patObj.getPatientFirstName() + " Is Successfully Deleted From The Database!\n");
        }catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.print("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }
    
    @Transactional
    public Patient findByID(long id){
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        Patient patObj = null;
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            patObj = (Patient) sessionObj.load(Patient.class, id);
            
            sessionObj.close();
            
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.print("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return patObj;
    } 
}
