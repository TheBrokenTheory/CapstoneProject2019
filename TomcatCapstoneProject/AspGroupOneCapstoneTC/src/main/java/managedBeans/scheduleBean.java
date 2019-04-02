package managedBeans;


import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import net.bootsfaces.component.fullCalendar.FullCalendarEventList;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean
@Named(value = "scheduleBean")
@RequestScoped
public class scheduleBean {
    
    String inputDate = "03-03-2019";
    Calendar cal = new GregorianCalendar(2019, Calendar.MARCH, 03, 5,45);
    Date d = cal.getTime();
    FullCalendarEventList df = new FullCalendarEventList();
    //Year, Month, Day
    StringBuilder test = new StringBuilder("[{title:'test', start:'2019-04-04'}, {title:'test', start:'2019-04-05'}]");

   
    /**
     * Creates a new instance of scheduleBean
     */
    public scheduleBean()
    {

    }
    
    public void createAppts(){
        Event test = new Event("Test", d);
        //df.toJson();
        System.out.println(d.toString());
    }

    public StringBuilder getTest(){return test;}
}
