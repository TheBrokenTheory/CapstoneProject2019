package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

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
 

    /**
     * Creates a new instance of scheduleBean
     */
    public scheduleBean()
    {
    }
    
    public void createAppointment()
    {
        System.out.println(firstName);
    }
    
    //First Name Getter and Setter
    public String getFirstName(){return firstName;}
    public void setFirstName(String fName){this.firstName = fName;}
}
