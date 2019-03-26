package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean
@Named(value = "scheduleBean")
@RequestScoped
public class scheduleBean {

    /**
     * Creates a new instance of scheduleBean
     */
    public scheduleBean()
    {
    }
    
}
