package org.aspgroup1.crud;

//Java Utility Imports
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.aspgroup1.entity.Doctor;

//Hibernate Imports
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author tfran
 */
public class DoctorCrud {
    private Session sessionObj;
    private SessionFactory sessionFactoryObj;

    
     // This Method Is Used To Create The Hibernate's SessionFactory Object
    protected SessionFactory setUp(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactoryObj = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
		}
                
                return sessionFactoryObj;
    }
    
    
    
    public void createDoctor(String doctorFN, String doctorLN, String doctorS, Date doctorDOB, String doctorPN){
        Doctor docObj;
        
        try {
            //Create Session
            sessionObj = setUp().openSession();
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
    
    public List getDoctors(){
        List<Doctor> doctorList = new ArrayList();
        
        try {
            //Create Session
            sessionObj = setUp().openSession();
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
    
    public void updateDoctor(long id, String doctorFN, String doctorLN, String doctorS, Date doctorDOB, String doctorPN){
        try {
            //Create Session
            sessionObj = setUp().openSession();
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
    
    public void deleteDoctor(long id){
        try {
            //Create Session
            sessionObj = setUp().openSession();
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
    
    public Doctor findByID(long id){
        Doctor docObj = null;
        try {
            //Create Session
            sessionObj = setUp().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            docObj = (Doctor) sessionObj.load(Doctor.class, id);
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
