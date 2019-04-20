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
@ManagedBean (name ="notUsing")
@RequestScoped
public class scheduleBean {

    List doctorsL;
    
    private EntityManager em;
    //Year, Month, Day
    private StringBuilder test = new StringBuilder();
    private String firstName;
    private String lastName;
    private String reasonForVisit;
    private String doctorSeen;
    private String dateTimeOfAppointment;
    List<String> eventList = new ArrayList();
   
    /**
     * Creates a new instance of scheduleBean
     */
    public scheduleBean()
    {
        //For some reason the calendar won't display
        //Unless there is an event already
        //This event is scheduled for 2016 so it
        //won't affect anything were doing
        eventList.add("{title:'test', start:'2016-04-04T02:30'}");
        test = createCalString();
    }
    
    //Creates Event Class & Adds to the eventList
    public void createAppointment()
    {   
        //Creates new Event obj and stores it in ArrayList
        Event newEvent = new Event(firstName, lastName, dateTimeOfAppointment, reasonForVisit, doctorSeen);
        eventList.add(newEvent.jsonString());
        test = createCalString();
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
    
    /*
    public List<Doctor> getDoctors()
    {
               
         if(doctorsL == null){
            doctorsL = new ArrayList(DoctorCrud.getDoctors());
        }
        
        return doctorsL;
    }
    */
    
     public Doctor getDoctor(String doctorID)
     {
        Doctor requestedTax = em.find(Doctor.class, doctorID);
        return requestedTax;
    }
    
    //Creates the string required for the fullCalendar component
    public StringBuilder createCalString()
    {
        StringBuilder calString = new StringBuilder();
        
        calString.append("[");
        for(int i = 0; i < eventList.size(); i++)
        {
            calString.append(eventList.get(i));
            if(i < eventList.size())
            {
                calString.append(",");
            }
        }
        calString.append("]");
        
        return calString;
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
    public String getDateTimeOfAppointment(){return dateTimeOfAppointment;}
    public void setDateTimeOfAppointment(String dateTime) {this.dateTimeOfAppointment = dateTime;}

    public StringBuilder getTest(){return test;}
}
