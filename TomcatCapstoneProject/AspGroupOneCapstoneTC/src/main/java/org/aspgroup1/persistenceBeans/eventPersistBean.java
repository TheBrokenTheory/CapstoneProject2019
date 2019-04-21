package org.aspgroup1.persistenceBeans;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import org.aspgroup1.crud.AppointmentCrud;
import org.aspgroup1.entity.Appointment;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import org.aspgroup1.crud.PatientCrud;
import org.aspgroup1.crud.ScheduleCrud;
import org.aspgroup1.entity.Patient;
import org.aspgroup1.entity.Schedule;
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
    PatientCrud pc;
    
    private long patientID;
    private long appID;
    private String firstName;
    private String lastName;
    private String eventTitle;
    private String eventDate;
    private String eventTime;
    private String reasonForVisit;
    private long doctorSeen;
    private String dateTimeOfAppointment;
    List<String> eventList = new ArrayList();
    boolean showAlertMsg;
    String alertMsg;
    
    /**
     * Creates a new instance of eventPersistBean
     */
    public eventPersistBean()
    {
        ac = new AppointmentCrud();
        pc = new PatientCrud();
        fetchExistingApts();
        eventString = createCalString(eventList);
    }
    
    //Creates Event Class & Adds to the eventList
    public void createAppt()
    {   
        alertMsg = "";
        showAlertMsg = false;
        System.out.println(dateTimeOfAppointment);
        
        int eventDay = UtilityMethods.convertDay(dateTimeOfAppointment);
        eventDate = UtilityMethods.convertDate(dateTimeOfAppointment);
        eventTime = UtilityMethods.convertTime(dateTimeOfAppointment);
        

        //Check that the doctor works on selected Day
        if(doctorWorksOnSelectedDay(eventDay, this.doctorSeen))
        {
            //Check that the appointment times don't overlap with the same doctor
            if (appointmentVerifies(eventDate, eventTime, this.doctorSeen))
            {
                //Creates new Event obj and stores it in ArrayList
                ac.createAppointment(this.patientID, this.eventDate, this.eventTime, this.reasonForVisit, this.doctorSeen);
        
                Patient patObj = pc.findByID(patientID);
                String fName = patObj.getPatientFirstName();
                String lName = patObj.getPatientLastName();

                eventTitle = generateTitle(fName, lName);
                eventList.add(jsonString(eventTitle, eventDate, eventTime));
                eventString = createCalString(eventList);
            }
            else
            {
                showAlertMsg = true;
                alertMsg = "Sorry, This doctor already has an appointment for selected time & date.";
            }
        }
        else
        {
            showAlertMsg = true;
            alertMsg = "Sorry, This doctor is not scheduled to work on selected day.";
        }
        
        
    }
    
    //Checks to make sure it appt doesn't overlap with an existing
    public boolean appointmentVerifies(String date, String time, long docSeen)
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
                    if(docSeen == appointmentList.get(i).getDoctorID())
                    {
                        appointmentVerifies = false;
                    }
                }
            }
        }
        
        return appointmentVerifies;
    }
    
    //Checks that the selected doctor works on that day
    public boolean doctorWorksOnSelectedDay(int eventDay, long docID)
    {
        boolean doctorWorks = true;
        ScheduleCrud sc = new ScheduleCrud();
        Schedule schObj = sc.findByID(docID);
        int daysAvailable[] = new int[7];
        
        daysAvailable[0] = schObj.getMon();
        daysAvailable[1] = schObj.getTue();
        daysAvailable[2] = schObj.getWed();
        daysAvailable[3] = schObj.getThur();
        daysAvailable[4] = schObj.getFri();
        daysAvailable[5] = schObj.getSat();
        daysAvailable[6] = schObj.getSun();

        for(int i = 0; i < 6; i++)
        {
            if(daysAvailable[eventDay] != 1)
            {
                doctorWorks = false;
            }
        }
        
        return doctorWorks;
    }
    
    public void cancelAppointment()
    {
        ac.deleteAppointment(this.appID);
        
        eventL.clear();
        eventList.clear();
        fetchExistingApts();
        eventString = createCalString(eventList);
    }
    
    public List getEventsL()
    {
        eventL = new ArrayList(ac.getAppointments());
        return eventL;
    }
    
    public void fetchExistingApts()
    {
        List<Appointment> appointmentList = appointmentList= ac.getAppointments();

        //Adds the appointments to JSON string
        for(int i=0; i < appointmentList.size(); i++)
        {
            String firstName;
            String lastName;
            String eventTitle;
            String eventDate;
            String eventTime;
            
            long aptID = appointmentList.get(i).getAppID();
            
            long patID = appointmentList.get(i).getPatientID();
            Patient patObj = pc.findByID(patID);
            
            firstName = patObj.getPatientFirstName();
            lastName = patObj.getPatientLastName();
            eventTitle = generateTitle(firstName, lastName);
            eventDate = appointmentList.get(i).getAppDate();
            eventTime = appointmentList.get(i).getAppTime();
            eventList.add(jsonString(eventTitle, eventDate, eventTime));
        }
    }
    
    //Creates the string required for the fullCalendar component
    public StringBuilder createCalString(List events)
    {
        StringBuilder calString = new StringBuilder();
        
        calString.append("[");
        for(int i = 0; i < events.size(); i++)
        {
            calString.append(events.get(i));
            if(i < events.size())
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
    
    //Populates a calendar for each Doc upon request
    public String getDoctorsSchedule(long docID)
    {
        doctorPersonalSchedule.setLength(0);
        List<Appointment> appointmentList = appointmentList= ac.getAppointments();
        List<String> docEvents = new ArrayList();
        
        for(int i = 0; i < appointmentList.size(); i++)
        {
            if(docID == appointmentList.get(i).getDoctorID())
            {
                long patID = appointmentList.get(i).getPatientID();
                Patient patObj = pc.findByID(patID);
                firstName = patObj.getPatientFirstName();
                lastName = patObj.getPatientLastName();
                eventTitle = generateTitle(firstName, lastName);
                eventDate = appointmentList.get(i).getAppDate();
                eventTime = appointmentList.get(i).getAppTime();
                
                docEvents.add(jsonString(eventTitle, eventDate, eventTime));
            }
        }
        doctorPersonalSchedule = createCalString(docEvents);
        
        return "doctorSchedule";
    }
    
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
    public long getPatientID(){return this.patientID;}
    public void setPatientID(long id){this.patientID = id;}
    public long getAppID(){return this.appID;}
    public void setAppID(long id){this.appID = id;}
    public String getFirstName(){return firstName;}
    public void setFirstName(String fName){this.firstName = fName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lName){this.lastName = lName;}
    public String getReasonForVisit() {return reasonForVisit;}
    public void setReasonForVisit(String reason) {this.reasonForVisit = reason;}
    public long getDoctorSeen(){return doctorSeen;}
    public void setDoctorSeen(long doctorSeen){this.doctorSeen = doctorSeen;}
    public String getDateTimeOfAppointment(){return dateTimeOfAppointment;}
    public void setDateTimeOfAppointment(String dateTime) {this.dateTimeOfAppointment = dateTime;}
    public StringBuilder getEventString(){return eventString;}
    public StringBuilder getDoctorPersonalSchedule(){return doctorPersonalSchedule;}
    public boolean getShowAlertMsg() { return showAlertMsg;}
    public void setShowAlertMsg(boolean testBool) { this.showAlertMsg = testBool;}
    public String getAlertMsg(){return alertMsg;}
    public void setAlertMsg(String msg) {this.alertMsg = msg;}
    
}
