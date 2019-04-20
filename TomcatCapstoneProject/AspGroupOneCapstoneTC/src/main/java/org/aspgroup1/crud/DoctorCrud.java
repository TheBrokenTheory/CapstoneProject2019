package org.aspgroup1.crud;

//Java Utility Imports
import java.util.ArrayList;
import java.util.List;
import org.aspgroup1.HibernateUtilities.HibernateUtil;
import org.aspgroup1.entity.Doctor;
import org.aspgroup1.entity.Schedule;
import org.aspgroup1.crud.ScheduleCrud;

//Hibernate Imports
import org.hibernate.Session;

/**
 *
 * @author tfran
 */
public class DoctorCrud {
    
    public DoctorCrud(){}

    
    public void createDoctor(String doctorFN, String doctorLN, String doctorS, String doctorDOB, String doctorPN, int days[]){
        Doctor docObj;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            
            //Creating Doctor Object
            docObj = new Doctor();
            docObj.setDoctorFirstName(doctorFN);
            docObj.setDoctorLastName(doctorLN);
            docObj.setDoctorSpecialty(doctorS);
            docObj.setDoctorDOB(doctorDOB);
            docObj.setDoctorPhoneNum(doctorPN);
            
            //Saving object information
            sessionObj.save(docObj);
            
            //Commit to DB
            sessionObj.getTransaction().commit();
            
            //Generate Doc Schedule
            createSchedule(days, docObj.getDoctorID());
            
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
    
    //Create Doc Schedule
    public void createSchedule(int days[], long docID)
    {
        int mon = days[0];
        int tue = days[1];
        int wed = days[2];
        int thu = days[3];
        int fir = days[4];
        int sat = days[5];
        int sun = days[6];
        ScheduleCrud sc = new ScheduleCrud();
        
        sc.createSchedule(docID, mon, tue, wed, thu, fir, sat, sun);
    }
    
    
    public List getDoctors(){
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        List<Doctor> doctorList = new ArrayList();
        
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            doctorList = sessionObj.createQuery("FROM Doctor").list();
            
            
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
        
        return doctorList;
    }
    
    
    public void updateDoctor(long id, String doctorFN, String doctorLN, String doctorS, String doctorDOB, String doctorPN){
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();

        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            // Creating Transaction Entity
            Doctor docObj = (Doctor) sessionObj.get(Doctor.class, id);
            
            if(docObj.getDoctorFirstName() != doctorFN){ docObj.setDoctorFirstName(doctorFN); }
            if(docObj.getDoctorLastName() != doctorLN){ docObj.setDoctorLastName(doctorLN); }
            if(docObj.getDoctorSpecialty() != doctorS){ docObj.setDoctorSpecialty(doctorS); }
            if(docObj.getDoctorDOB() != doctorDOB) { docObj.setDoctorDOB(doctorDOB); }
            if(docObj.getDoctorPhoneNum() != doctorPN) { docObj.setDoctorPhoneNum(doctorPN); }
            
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            
            System.out.print("\nDoctor With Id?= " + docObj.getDoctorID() + " Is Successfully Updated In The Database!\n");
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
    
    public void deleteDoctor(long id){
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            Doctor docObj = findByID(id);
            sessionObj.delete(docObj);
 
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            

            System.out.print("\nDoctor With Id?= " + docObj.getDoctorID() + " Is Successfully Deleted From The Database!\n");
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
    
    public Doctor findByID(long id){
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        Doctor docObj = null;
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            docObj = (Doctor) sessionObj.load(Doctor.class, id);
            
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
        return docObj;
    }
    
    
}
