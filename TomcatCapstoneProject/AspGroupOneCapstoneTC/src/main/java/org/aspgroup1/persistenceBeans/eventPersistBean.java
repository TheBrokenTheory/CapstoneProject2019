package org.aspgroup1.persistenceBeans;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import org.aspgroup1.crud.AppointmentCrud;
import org.aspgroup1.entity.Appointment;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import org.aspgroup1.utilities.UtilityMethods;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean (name="eventBean")
@RequestScoped
public class eventPersistBean {
    
    //Year, Month, Day
    private StringBuilder eventString = new StringBuilder();
    private StringBuilder doctorPersonalSchedule = new StringBuilder();
    
    List eventL;
    Appointment apptObj;
    AppointmentCrud ac;
    
    private long appID;
    private String firstName;
    private String lastName;
    private String eventTitle;
    private String eventDate;
    private String eventTime;
    private String reasonForVisit;
    private String doctorSeen;
    private String dateTimeOfAppointment;
    List<String> eventList = new ArrayList();
    boolean showAlertMsg;
    
    /**
     * Creates a new instance of eventPersistBean
     */
    public eventPersistBean()
    {
        ac = new AppointmentCrud();
        fetchExistingApts();
        eventString = createCalString();
    }
    
    //Creates Event Class & Adds to the eventList
    public void createAppt()
    {   
        showAlertMsg = false;
        
        eventDate = UtilityMethods.convertDate(dateTimeOfAppointment);
        eventTime = UtilityMethods.convertTime(dateTimeOfAppointment);
        
        if (appointmentVerifies(eventDate, eventTime, this.doctorSeen))
        {
            //Creates new Event obj and stores it in ArrayList
            ac.createAppointment(this.firstName, this.lastName, this.eventDate, this.eventTime, this.reasonForVisit, this.doctorSeen);
        
            eventTitle = generateTitle(this.firstName, this.lastName);
            eventList.add(jsonString(eventTitle, eventDate, eventTime));
            eventString = createCalString();
        }
        else
        {
            showAlertMsg = true;
        }
    }
    
    //Checks to make sure it appt doesn't overlap with an existing
    public boolean appointmentVerifies(String date, String time, String docSeen)
    {
        boolean appointmentVerifies = true;
        List<Appointment> appointmentList = new ArrayList();
        appointmentList= ac.getAppointments();
        
        for(int i = 0; i < eventList.size(); i++)
        {
            if(date.equals(appointmentList.get(i).getAppDate()))
            {
                if(time.equals(appointmentList.get(i).getAppTime()))
                {
                    if(docSeen.equals(appointmentList.get(i).getDoctorSeen()))
                    {
                        appointmentVerifies = false;
                    }
                }
            }
        }
        
        return appointmentVerifies;
    }
    
    public void cancelAppointment()
    {
        ac.deleteAppointment(this.appID);
        
        eventL.clear();
        eventList.clear();
        fetchExistingApts();
        eventString = createCalString();
    }
    
    public List getEventsL()
    {
        eventL = new ArrayList(ac.getAppointments());
        return eventL;
    }
    
    public void fetchExistingApts()
    {
        List<Appointment> appointmentList = new ArrayList();
        appointmentList= ac.getAppointments();
        
        //Adds the appointments to JSON string
        for(int i=0; i < appointmentList.size(); i++)
        {
            String firstName;
            String lastName;
            String eventTitle;
            String eventDate;
            String eventTime;
            String reasonForVisit;
            String doctorSeen;
            
            firstName = appointmentList.get(i).getFirstName();
            lastName = appointmentList.get(i).getLastName();
            eventTitle = generateTitle(firstName, lastName);
            eventDate = appointmentList.get(i).getAppDate();
            eventTime = appointmentList.get(i).getAppTime();
            eventList.add(jsonString(eventTitle, eventDate, eventTime));
        }
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
    
    //Returns number of appointmets for today's date
    public int getTodaysAppointments()
    {
        int todaysAppointments = 0;
        String DATE_FORMAT_NOW = "yyyy-MM-dd";
        //Get todays date in proper format
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String todaysDate = sdf.format(cal.getTime());
        
        List<Appointment> appointmentList = new ArrayList();
        appointmentList= ac.getAppointments();
        
        //Compares Dates with events in DB
        for(int i=0; i < appointmentList.size(); i++)
        {
            String eventDate = appointmentList.get(i).getAppDate();
            if(todaysDate.equals(eventDate))
            {
                todaysAppointments++;
            }
        }
        
        return todaysAppointments;
    }
    /*
    //Populates a calendar for each Doc upon request
    public String getDoctorsSchedule(String lastName)
    {
        System.out.println("Test");
        
        doctorPersonalSchedule.append("[");
        for(int i = 0; i < eventList.size(); i++)
        {
            List<Appointment> appointmentList = new ArrayList();
            appointmentList= ac.getAppointments();
        
        //Adds the appointments to JSON string
        for(int i=0; i < appointmentList.size(); i++)
        {
            String firstName;
            String lastName;
            String eventTitle;
            String eventDate;
            String eventTime;
            String reasonForVisit;
            String doctorSeen;
            
            firstName = appointmentList.get(i).getFirstName();
            lastName = appointmentList.get(i).getLastName();
            eventTitle = generateTitle(firstName, lastName);
            eventDate = appointmentList.get(i).getAppDate();
            eventTime = appointmentList.get(i).getAppTime();
            eventList.add(jsonString(eventTitle, eventDate, eventTime));
        }
            
   
            
            doctorPersonalSchedule.append(eventList.get(i));
            if(i < eventList.size())
            {
                doctorPersonalSchedule.append(",");
            }
        }
        doctorPersonalSchedule.append("]");

        return "doctorSchedule";
    }
    */
    
    //Generates Title of Event
    private String generateTitle(String fName, String lName)
    {
        String title = fName + " " + lName;
        return title;
    }
    
    //Returns string for this event. 
    //String is in proper format for fullCalendar component
    public String jsonString(String title, String date, String time)
    {
        String jsonStr = "{title:'" + title + "', start:'" + date + "T" + time + "'}";
        return jsonStr;
    }
    
    
    //Getters and Setters
    public long getAppID(){return this.appID;}
    public void setAppID(long id){this.appID = id;}
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

    public StringBuilder getEventString(){return eventString;}
    public StringBuilder getDoctorPersonalSchedule(){return doctorPersonalSchedule;}

    public boolean getShowAlertMsg() { return showAlertMsg;}

    public void setShowAlertMsg(boolean testBool) { this.showAlertMsg = testBool;}
    
}
