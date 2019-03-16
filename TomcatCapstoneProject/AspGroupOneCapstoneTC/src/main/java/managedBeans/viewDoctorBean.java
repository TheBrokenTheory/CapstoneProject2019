package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean
@Named(value = "viewDoctorBean")
@RequestScoped
public class viewDoctorBean {

    /**
     * Creates a new instance of viewDoctorBean
     */
    public viewDoctorBean()
    {
    }
    
}
