package org.aspgroup1.persistenceBeans;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import managedBeans.Event;
import org.aspgroup1.crud.AppointmentCrud;
import org.aspgroup1.entity.Appointment;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean (name="eventBean")
@RequestScoped
public class eventPersistBean {
    
    //Year, Month, Day
    private StringBuilder eventString = new StringBuilder();
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
        eventDate = convertDate(dateTimeOfAppointment);
        eventTime = convertTime(dateTimeOfAppointment);
        
        //Creates new Event obj and stores it in ArrayList
        ac.createAppointment(this.firstName, this.lastName, this.eventDate, this.eventTime, this.reasonForVisit, this.doctorSeen);
        
        eventTitle = generateTitle(this.firstName, this.lastName);
        eventList.add(jsonString(eventTitle, eventDate, eventTime));
        eventString = createCalString();
    }
    
    public void cancelAppointment()
    {
        ac.deleteAppointment(this.appID);
        
        eventL.clear();
        eventList.clear();
        updateEventList();
        eventString = createCalString();
    }
    
    public List getEventsL()
    {
        eventL = new ArrayList(ac.getAppointments());
        return eventL;
    }
    
    public void updateEventList()
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
    
    //Generates Title of Event
    private String generateTitle(String fName, String lName)
    {
        String title = fName + " " + lName;
        return title;
    }
    
    //Converts time to proper format for fullCalendar
    private String convertTime(String fullString)
    {
        String timeSub = fullString.substring(11, 16);
        return timeSub;
    }
    
    //Converts date to proper format for fullCalendar
    private String convertDate(String fullString)
    {  
        //Substring locations
        //Mon 4-7 Day 8-10 Year 24 - 28
        
        //Gets substrings from the date/time input
        String strMonth = fullString.substring(4, 7);
        
        //Converts month from 3 lttr abbriviation to number
        String month = convertMonth(strMonth);
        String day = fullString.substring(8, 10);
        String year = fullString.substring(24, 28);
        
        //Date compatable with fullCal component
        String date = year + "-" + month + "-" + day;
        return date;
    }
    
    //Convert month from abbriviation to number
    private String convertMonth(String abbrMonth)
    {
        String month = "";
        
        switch (abbrMonth)
        {
            case "Jan":
                month = "01";
                break;
            case "Feb":
                month = "02";
                break;
            case "Mar":
                month = "03";
                break;
            case "Apr":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "Jun":
                month = "06";
                break;
            case "Jul":
                month = "07";
                break;
            case "Aug":
                month = "08";
                break;
            case "Sep":
                month = "09";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
            case "Dec":
                month = "12";
                break;
        }
        
        return month;
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
}
