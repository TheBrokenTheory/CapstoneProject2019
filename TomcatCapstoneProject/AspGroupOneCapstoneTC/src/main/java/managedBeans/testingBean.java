package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean
@Named(value = "testingBean")
@RequestScoped
public class testingBean {

    /**
     * Creates a new instance of testingBean
     */
    public testingBean()
    {
    }
    
}
