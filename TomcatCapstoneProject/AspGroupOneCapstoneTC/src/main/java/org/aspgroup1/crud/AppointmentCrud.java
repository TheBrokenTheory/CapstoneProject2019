package org.aspgroup1.crud;

//Java Utility Imports
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.aspgroup1.HibernateUtilities.HibernateUtil;
import org.aspgroup1.entity.Appointment;

//Hibernate Imports
import org.hibernate.Session;


public class AppointmentCrud {
    
    public AppointmentCrud(){}
    
    public void createAppointment(String firstName, String lastName, String appDate, String appTime, String reasonForVisit, String doctorSeen){
        Appointment appObj;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            //Create Session
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            
            //Creating Doctor Object
            appObj = new Appointment();
            appObj.setFirstName(firstName);
            appObj.setLastName(lastName);
            appObj.setAppDate(appDate);
            appObj.setAppTime(appTime);
            appObj.setReasonForVisit(reasonForVisit);
            appObj.setDoctorSeen(doctorSeen);
            
            //Saving object information
            sessionObj.save(appObj);
            
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
    
    public List getAppointments(){
        List<Appointment> appointmentList = new ArrayList();
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            //Create Session

            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            appointmentList = sessionObj.createQuery("FROM Appointment").list();
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
        return appointmentList;
    }
    
    public void updateAppointment(long id, String firstName, String lastName, String appDate, String appTime, String reasonForVisit, String doctorSeen){
         Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            //Create Session
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            // Creating Transaction Entity
            Appointment appObj = (Appointment) sessionObj.get(Appointment.class, id);
            
            if(appObj.getFirstName() != firstName){ appObj.setFirstName(firstName); }
            if(appObj.getLastName() != lastName){ appObj.setLastName(lastName); }
            if(appObj.getAppDate() != appDate){ appObj.setAppDate(appDate); }
            if(appObj.getAppTime() != appTime) { appObj.setAppTime(appTime); }
            if(appObj.getReasonForVisit() != reasonForVisit) { appObj.setReasonForVisit(reasonForVisit); }
            if(appObj.getDoctorSeen() != doctorSeen) { appObj.setDoctorSeen(doctorSeen); }
            
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.print("\nAppointment With Id?= " + appObj.getAppID()+ " Is Successfully Updated In The Database!\n");
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
    
    public void deleteAppointment(long id){
         Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            //Create Session
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            Appointment appObj = findByID(id);
            sessionObj.delete(appObj);
 
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.print("\nAppointment With Id?= " + appObj.getAppID() + " Is Successfully Deleted From The Database!\n");
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
    
    public Appointment findByID(long id){
        Appointment appObj = null;
         Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        try {
            //Create Session
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            appObj = (Appointment) sessionObj.load(Appointment.class, id);
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
        return appObj;
    }

}
