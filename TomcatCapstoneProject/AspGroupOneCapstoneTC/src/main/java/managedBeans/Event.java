
package managedBeans;

import java.util.Date;
import net.bootsfaces.component.fullCalendar.FullCalendarEventBean;

/**
 *
 * @author Jonathan Anders
 */
public class Event extends FullCalendarEventBean {

    public Event(String title, Date start)
    {
        super(title, start);
    }

    @Override
    public void addExtendedFields(StringBuilder sb)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
