package org.aspgroup1.crud;

//Java Utility Imports
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.aspgroup1.entity.Doctor;

//Hibernate Imports
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author tfran
 */
public class DoctorCrud {
    Session session = null;

    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

     // This Method Is Used To Create The Hibernate's SessionFactory Object
    private static SessionFactory buildSessionFactory() {
        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = new Configuration().configure().buildSessionFactory();
        return sessionFactoryObj;
    }
    
    public static void createDoctor(String doctorFN, String doctorLN, String doctorS, Date doctorDOB, String doctorPN){
        Doctor docObj;
        
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
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
            
        } catch(Exception sqlException) {
            if(sessionObj.getTransaction() != null) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }
    
    public static List getDoctors(){
        List<Doctor> doctorList = new ArrayList();
        
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            
            
            doctorList = sessionObj.createQuery("FROM Doctor").list();
        } catch(Exception sqlException) {
            if(sessionObj.getTransaction() != null) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
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
    
    public static void updateDoctor(long id, String doctorFN, String doctorLN, String doctorS, Date doctorDOB, String doctorPN){
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            // Creating Transaction Entity
            Doctor docObj = (Doctor) sessionObj.get(Doctor.class, id);
            
            docObj.setDoctorFirstName(doctorFN);
            docObj.setDoctorLastName(doctorLN);
            docObj.setDoctorSpecialty(doctorS);
            docObj.setDoctorDOB(doctorDOB);
            docObj.setDoctorPhoneNum(doctorPN);
            
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.print("\nStudent With Id?= " + docObj.getDoctorID() + " Is Successfully Updated In The Database!\n");
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
    
    public static void deleteDoctor(long id){
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            Doctor docObj = findByID(id);
            sessionObj.delete(docObj);
 
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.print("\nStudent With Id?= " + docObj.getDoctorID() + " Is Successfully Deleted From The Database!\n");
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
    
    public static Doctor findByID(long id){
        Doctor docObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            docObj = (Doctor) sessionObj.load(Doctor.class, id);
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.print("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } 
        return docObj;
    }
    
}
