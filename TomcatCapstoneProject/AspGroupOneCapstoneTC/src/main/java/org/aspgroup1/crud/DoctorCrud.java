package org.aspgroup1.crud;

//Java Utility Imports
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
    
    public static void doctorStuff(){
        System.out.println("Display method!");
        System.out.println("Display method!");
        System.out.println("Display method!");
    }
    
    public static List getDoctors(){
        List<Doctor> doctorList = new ArrayList();
        
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            
            System.out.println("transations!!!");
            System.out.println("transations!!!");
            System.out.println("transations!!!");
            doctorList = sessionObj.createQuery("FROM Doctor").list();
        } catch(Exception sqlException) {
            /*if(sessionObj.getTransaction() != null) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }*/
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return doctorList;
    }
    
}