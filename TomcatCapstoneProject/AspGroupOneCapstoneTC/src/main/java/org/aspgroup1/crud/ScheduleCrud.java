package org.aspgroup1.crud;

//Java Utility Imports
import java.util.ArrayList;
import java.util.List;
import org.aspgroup1.HibernateUtilities.HibernateUtil;
import org.aspgroup1.entity.Schedule;

//Hibernate Imports
import org.hibernate.Session;

public class ScheduleCrud {
    
    public ScheduleCrud(){}
    
    public void createSchedule(long doctorID, int mon, int tue, int wed, int thur, int fri, int sat, int sun){
        Schedule schObj;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            
            //Creating Doctor Object
            schObj = new Schedule();
            schObj.setDoctorID(doctorID);
            schObj.setMon(mon);
            schObj.setTue(tue);
            schObj.setWed(wed);
            schObj.setThur(thur);
            schObj.setFri(fri);
            schObj.setSat(sat);
            schObj.setSun(sun);
            
            //Saving object information
            sessionObj.save(schObj);
            
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
    
    public List getSchedules(){
        List<Schedule> scheduleList = new ArrayList();
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            scheduleList = sessionObj.createQuery("FROM Schedule").list();
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
        return scheduleList;
    }
    
    public void updateSchedule(long id, int mon, int tue, int wed, int thur, int fri, int sat, int sun){
         Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            // Creating Transaction Entity
            Schedule schObj = (Schedule) sessionObj.get(Schedule.class, id);
            
            if(schObj.getMon() != mon){ schObj.setMon(mon); }
            if(schObj.getTue() != tue){ schObj.setTue(tue); }
            if(schObj.getWed() != wed){ schObj.setWed(wed); }
            if(schObj.getThur() != thur) { schObj.setThur(thur); }
            if(schObj.getFri() != fri) { schObj.setFri(fri); }
            if(schObj.getSat()!= sat) { schObj.setSat(sat); }
            if(schObj.getSun() != sun) { schObj.setSun(sun); }
            
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.print("\nSchedule With Id?= " + schObj.getDoctorID()+ " Is Successfully Updated In The Database!\n");
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
    
    public void deleteSchedule(long id){
         Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            Schedule schObj = findByID(id);
            sessionObj.delete(schObj);
 
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.print("\nSchedule With Id?= " + schObj.getDoctorID()+ " Is Successfully Deleted From The Database!\n");
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
    
    public Schedule findByID(long id){
        Schedule schObj = null;
         Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            schObj = (Schedule) sessionObj.load(Schedule.class, id);
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
        return schObj;
    }
}
