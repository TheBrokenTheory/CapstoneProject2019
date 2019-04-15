package org.aspgroup1.crud;

//Java Utility Imports
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.aspgroup1.HibernateUtilities.HibernateUtil;
import org.aspgroup1.entity.User;

//Hibernate Imports
import org.hibernate.Session;

/**
 *
 * @author tfran
 */
public class UserCrud {

    public UserCrud(){}

    public void createUser(String uName, String pWord, int accType, String uFName, String uLName){
        User userObj;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            
            //Creating User Object
            userObj = new User();
            
            //Setting User Values
            userObj.setUsername(uName);
            userObj.setPassword(pWord);
            userObj.setAccountType(accType);
            userObj.setFname(uFName);
            userObj.setLname(uLName);
            
            //Saving object information
            sessionObj.save(userObj);
            
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
    
    public List getUsers(){
        List<User> userList = new ArrayList();
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            userList = sessionObj.createQuery("FROM User").list();
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
        return userList;
    }
    
    public void updateUser(String uName, String pWord, int accType, String uFName, String uLName){
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            // Creating Transaction Entity
            User userObj = (User) sessionObj.get(User.class, uName);
            
            if(userObj.getUsername()!= uName){ userObj.setUsername(uName); }
            if(userObj.getPassword()!= pWord){ userObj.setPassword(pWord); }
            if(userObj.getAccountType()!= accType){ userObj.setAccountType(accType); }
            if(userObj.getFname()!= uFName){ userObj.setFname(uFName); }
            if(userObj.getLname()!= uLName){ userObj.setLname(uLName); }
            
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.print("\nUser With Username?= " + userObj.getUsername() + " Is Successfully Updated In The Database!\n");
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
    
    public void deleteUser(String uName){
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            User userObj = findByUserName(uName);
            sessionObj.delete(userObj);
 
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.print("\nUser With Username?= " + userObj.getUsername()+ " Is Successfully Deleted From The Database!\n");
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
    
    public User findByUserName(String uName){
        User userObj = null;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            userObj = (User) sessionObj.load(User.class, uName);
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
        return userObj;
    }
    
}
