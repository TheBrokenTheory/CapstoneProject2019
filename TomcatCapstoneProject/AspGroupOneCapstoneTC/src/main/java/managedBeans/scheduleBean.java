package managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import org.aspgroup1.crud.DoctorCrud;
import org.aspgroup1.entity.Doctor;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean (name ="scheduleBean")
@RequestScoped
public class scheduleBean {
    
    private String firstName ="";
    private String lastName ="";
    private String reasonForVisit="";
    private String doctorSeen="";
    private String dateTimeOfAppointment="";
    List doctorsL;
    
    private EntityManager em;
 

    /**
     * Creates a new instance of scheduleBean
     */
    public scheduleBean()
    {
    }
    
    public scheduleBean(String fName, String lName, 
                        String reason, String doctorSeen, String dateTime)
    {
        this.firstName = fName;
        this.lastName = lName;
        this.reasonForVisit = reason;
        this.doctorSeen = reason;
        this.dateTimeOfAppointment = dateTime;
    }
    
    public void createAppointment()
    {
       // System.out.println(firstName + "TEST--" + lastName + " " + dateTimeOfAppointment);
        System.out.println(firstName + " " + lastName + " " + reasonForVisit
                          + " " + doctorSeen + " " + dateTimeOfAppointment );
    }
    
    
     public List<Doctor> getDoctors()
    {
        
        //return (List<Doctor>) em.createNamedQuery("findBYID").getResultList();
         if(doctorsL == null){
            doctorsL = new ArrayList(DoctorCrud.getDoctors());
        }
        
        return doctorsL;
    }
    
     public Doctor getDoctor(String doctorID)
     {
        Doctor requestedTax = em.find(Doctor.class, doctorID);
        return requestedTax;
    }
    
    //Getters and Setters
    public String getFirstName(){return firstName;}
    public void setFirstName(String fName){this.firstName = fName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lName){this.lastName = lName;}
    public String getReasonForVisit() {return reasonForVisit;}
    public void setReasonForVisit(String reason) {this.reasonForVisit = reason;}
    public String getDoctorSeen(){return doctorSeen;}
    public void setDoctorSeen(String doctorSeen){this.doctorSeen = doctorSeen;}
    public String getDateTime(){return dateTimeOfAppointment;}
    public void setDateTime(String dateTime) {this.dateTimeOfAppointment = dateTime;}
}
