package managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean(name = "viewDoctorBean")
@ViewScoped
public class viewDoctorBean {

    private List<Field> fields;
    

    public viewDoctorBean() {
        fields = new ArrayList<>();
    }

    public List<Field> getFields() {
        return fields;
    }
    

}
