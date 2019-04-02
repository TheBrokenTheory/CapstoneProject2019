
package managedBeans;

/**
 *
 * @author Jonathan Anders
 */
public class Event {
    private String firstName;
    private String lastName;
    private String eventTitle;
    private String eventDate;
    private String eventTime;
    private String reasonForVisit;
    private String doctorSeen;
    
    //Initialize all attributes
    public Event(String fName, String lName, String dateAndTime, String reason, String doctor)
    {
        this.firstName = fName;
        this.lastName = lName;
        this.eventDate = convertDate(dateAndTime);
        this.eventTime = convertTime(dateAndTime);
        this.reasonForVisit = reason;
        this.doctorSeen = doctor;
        this.eventTitle = generateTitle();
    }

    //Generates Title of Event
    private String generateTitle()
    {
        String title = firstName + " " + lastName;
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
    public String jsonString()
    {
        String jsonStr = "{title:'" + eventTitle + "', start:'" + eventDate + "T" + eventTime + "'}";
        return jsonStr;
    }
    

}
